package com.whatsapp.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the WhatsApp Chatbot Backend Simulation.
 *
 * Run this class to start the embedded Tomcat server on port 8080.
 * Then test the webhook at: POST http://localhost:8080/webhook
 */
@SpringBootApplication
public class ChatbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatbotApplication.class, args);
    }
}
