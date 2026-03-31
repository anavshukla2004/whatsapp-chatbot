package com.whatsapp.chatbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ChatbotService contains the core reply logic.
 *
 * Predefined mappings (case-insensitive):
 *   hi      → Hello
 *   bye     → Goodbye
 *   help    → Here are the commands you can use: Hi, Bye
 *   thanks  → You're welcome!
 *   (other) → Sorry, I didn't understand that. Type 'Help' for available commands.
 */
@Service
public class ChatbotService {

    // SLF4J logger — Spring Boot auto-configures Logback underneath
    private static final Logger log = LoggerFactory.getLogger(ChatbotService.class);

    /**
     * Logs the incoming message and returns the appropriate reply.
     *
     * @param from    Sender's phone number
     * @param message Raw message text
     * @return Bot reply string
     */
    public String processMessage(String from, String message) {

        // ── 1. LOG every incoming message ────────────────────────────────────
        log.info("📩 Incoming message | From: {} | Message: \"{}\"", from, message);

        // ── 2. Guard: handle null / blank input ───────────────────────────────
        if (message == null || message.isBlank()) {
            log.warn("⚠️  Empty message received from {}", from);
            return "Message cannot be empty. Type 'Help' for available commands.";
        }

        // ── 3. Match predefined replies (trim + lowercase) ───────────────────
        String normalized = message.trim().toLowerCase();

        String reply = switch (normalized) {
            case "hi", "hello", "hey"       -> "Hello! 👋 How can I help you?";
            case "bye", "goodbye", "see you" -> "Goodbye! 👋 Have a great day!";
            case "help"                      -> "📋 Available commands:\n• Hi – Greet the bot\n• Bye – Say goodbye\n• Thanks – Express gratitude";
            case "thanks", "thank you", "ty" -> "You're welcome! 😊";
            default -> "🤖 Sorry, I didn't understand \"" + message.trim() + "\". Type 'Help' for available commands.";
        };

        // ── 4. Log outgoing reply ────────────────────────────────────────────
        log.info("📤 Outgoing reply  | To:   {} | Reply:   \"{}\"", from, reply);

        return reply;
    }
}
