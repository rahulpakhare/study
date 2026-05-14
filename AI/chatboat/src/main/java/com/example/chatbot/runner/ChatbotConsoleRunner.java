package com.example.chatbot.runner;

import com.example.chatbot.service.ChatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Console runner that reads user questions from stdin and prints AI-generated answers.
 */
@Component
public class ChatbotConsoleRunner implements CommandLineRunner {

    private final ChatService chatService;

    public ChatbotConsoleRunner(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================================");
        System.out.println("   Welcome to Spring AI Chatbot!");
        System.out.println("   Type your question and press Enter.");
        System.out.println("   Type 'exit' to quit.");
        System.out.println("===========================================");
        System.out.println();

        while (true) {
            System.out.print("You: ");
            String question = scanner.nextLine().trim();

            if (question.equalsIgnoreCase("exit") || question.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (question.isEmpty()) {
                continue;
            }

            try {
                System.out.println("\nFetching data and generating answer...\n");
                String answer = chatService.ask(question);
                System.out.println("Bot: " + answer);
                System.out.println();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println();
            }
        }

        scanner.close();
    }
}
