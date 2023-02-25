package org.sapient.tbs.service.Impl;

import com.google.common.util.concurrent.*;
import org.sapient.tbs.dto.SeatBooking;
import org.sapient.tbs.dto.SeatBookingFutureResponse;
import org.sapient.tbs.repository.ScreeningRepository;
import org.sapient.tbs.repository.model.Screening;
import org.sapient.tbs.service.HandleSeatLockRequestService;
import org.sapient.tbs.service.PaymentService;
import org.sapient.tbs.service.TheaterClientService;
import org.sapient.tbs.util.BookingStatus;
import org.sapient.tbs.util.TicketBookingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class HandleSeatLockRequestServiceImpl implements HandleSeatLockRequestService {
    private static final Logger logger = LoggerFactory.getLogger(HandleSeatLockRequestServiceImpl.class);

    @Value(value = "${seat.lock.kafka.topic}")
    private String seatLockTopic;

    @Value(value = "${seat.booking.thread.pool.size}")
    private Integer seatBookingThreadPoolSize;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
   private TheaterClientService clientService;

    @Autowired
    PaymentService paymentService;

    private Map<String, SeatBooking> seatBookingMap = new HashMap<>();

    ListeningExecutorService execService = null;

    @PostConstruct
    public void init() {
       execService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(seatBookingThreadPoolSize));
    }

    /**
     * Check if seats are available from DB
     * @param seatBooking
     * @return
     */
    public boolean isSeatsAvailable(SeatBooking seatBooking) {
        Screening screening = screeningRepository.findByMovieAndTheater(seatBooking.getMovieId()
                , seatBooking.getTheaterId());
        Set<Integer> bookedSeats = null;
        if (screening.getBookedSeats() == null)
            return true;
        else if (!screening.getIsHouseFull())
            bookedSeats = TicketBookingUtil.getIntegerSetFromString(screening.getBookedSeats());

        Boolean available = true;
        for(Integer seat: seatBooking.getSeats()) {
            if (bookedSeats.contains(seat)) {
                available = false;
                break;
            }
        }
        if(available) {
            available = !isSeatLocked(seatBooking);
        }

        return available;
    }

    /**
     * Process seat booking request async so that it will not block kafka consumer
     * @param seatBooking
     */
    public void createAsyncRequestToReserveSeats(SeatBooking seatBooking) {
        SeatBookingRequestThread request = new SeatBookingRequestThread(seatBooking);
        ListenableFuture<SeatBooking> asyncTask = execService.submit(request);
        Futures.addCallback(
                asyncTask,
                new FutureCallback<SeatBooking>() {
                    // we want this handler to run immediately after we push the big red button!
                    public void onSuccess(SeatBooking response) {
                        seatBookingMap.remove(response.getHashedKey());
                    }
                    public void onFailure(Throwable thrown) {
                        seatBookingMap.remove(seatBooking.getHashedKey());
                    }
                },
                execService);
    }

    /**
     * Uses the cache Map for cashing the locked seat with hashed value of (Theater id, Movie id, StartTime)
     * if seats are present in cache then seats are locked return true otherwise false
     * @param seatBooking
     * @return
     */
    private boolean isSeatLocked(SeatBooking seatBooking) {
        byte[] dateBytes = BigInteger.valueOf(seatBooking.getStartTime().getTime()).toByteArray();
        byte[] theaterIdBytes = BigInteger.valueOf(seatBooking.getTheaterId()).toByteArray();
        byte[] movieIdBytes = BigInteger.valueOf(seatBooking.getMovieId()).toByteArray();
        ByteBuffer byteBuffer = ByteBuffer.allocate(dateBytes.length + theaterIdBytes.length + movieIdBytes.length);
        byteBuffer.put(dateBytes);
        byteBuffer.put(theaterIdBytes);
        byteBuffer.put(movieIdBytes);

        String key = DigestUtils.md5DigestAsHex(byteBuffer.array());
        SeatBooking seatBookingValue = seatBookingMap.get(key);
        if (seatBookingValue == null) {
            seatBooking.setHashedKey(key);
            seatBookingMap.put(key, seatBooking);
            return false;
        } else {
            return true;
        }
    }

    public void updateSeatsNotAvailable() {

    }

    /**
     * Shutdown the executor service
     */
    @PreDestroy
    public void cleanup() {
        logger.info("Executor Service shutting down");
        execService.shutdown();
        try {
            execService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * This is a thread runs in parallel for seat booking
     */
    class SeatBookingRequestThread implements Callable<SeatBooking> {
        private SeatBooking seatBooking;
        public SeatBookingRequestThread(SeatBooking seatBooking) {
            this.seatBooking = seatBooking;
        }

        @Override
        public SeatBooking call() throws Exception {
            SeatBookingFutureResponse seatBookingFutureResponse = new SeatBookingFutureResponse(seatBooking);
            if (clientService.updateClientToLockSeat() && paymentService.pay(seatBooking)) {
                Screening screening = screeningRepository.findByMovieAndTheater(seatBooking.getMovieId(), seatBooking.getTheaterId());
                Set<Integer> bookedSeats = null;
                if(screening.getBookedSeats() == null) {
                    bookedSeats = new HashSet<>();
                } else {
                    bookedSeats = TicketBookingUtil.getIntegerSetFromString(screening.getBookedSeats());
                }
                bookedSeats.addAll(seatBooking.getSeats());
                String bookedSeatsString = TicketBookingUtil.getCommaSeparatedStringFromSet(bookedSeats);
                screening.setBookedSeats(bookedSeatsString);
                screeningRepository.save(screening);
                seatBookingMap.remove(seatBooking.getHashedKey());
                clientService.updateTheaterClientWithBookingInfo(seatBooking);
                seatBookingFutureResponse.setBookingStatus(BookingStatus.SUCCESSFUL);
            } else {
                seatBookingFutureResponse.setBookingStatus(BookingStatus.FAILED);
            }

            return seatBooking;
        }
    }
}
