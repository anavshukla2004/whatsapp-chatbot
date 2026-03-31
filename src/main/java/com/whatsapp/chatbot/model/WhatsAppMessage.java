package com.whatsapp.chatbot.model;

/**
 * Represents an incoming WhatsApp message payload (JSON body of POST /webhook).
 *
 * Example JSON:
 * {
 *   "from": "+919876543210",
 *   "message": "Hi"
 * }
 */
public class WhatsAppMessage {

    private String from;      // Sender's phone number
    private String message;   // The text sent by the user

    // ── Constructors ──────────────────────────────────────────────────────────

    public WhatsAppMessage() {}

    public WhatsAppMessage(String from, String message) {
        this.from = from;
        this.message = message;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WhatsAppMessage{from='" + from + "', message='" + message + "'}";
    }
}
