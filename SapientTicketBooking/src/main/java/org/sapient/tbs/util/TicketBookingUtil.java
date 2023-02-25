package org.sapient.tbs.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketBookingUtil {
    public static Set<Integer> getIntegerSetFromString(String commaSeparated) {
        return Arrays.stream(commaSeparated.split(",")).map(t -> Integer.parseInt(t.trim())).sorted().collect(Collectors.toSet());
    }

    public static String getCommaSeparatedStringFromSet(Set<Integer> seats) {
       return String.join(", ", seats.stream().sorted().map(t -> t.toString()).collect(Collectors.toSet()));
    }

    public static boolean contains(Set<Integer> originalSet, Set<Integer> setToCompare) {
        boolean available = true;
        for(Integer item: setToCompare) {
            if (originalSet.contains(item)) {
                available = false;
                break;
            }
        }

        return available;
    }
}
