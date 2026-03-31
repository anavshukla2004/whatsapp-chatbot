package com.whatsapp.chatbot.model;

/**
 * Represents the JSON response sent back by the /webhook endpoint.
 *
 * Example JSON:
 * {
 *   "to": "+919876543210",
 *   "reply": "Hello"
 * }
 */
public class BotResponse {

    private String to;     // Echo back the sender's number
    private String reply;  // The bot's reply text

    // ── Constructors ──────────────────────────────────────────────────────────

    public BotResponse() {}

    public BotResponse(String to, String reply) {
        this.to = to;
        this.reply = reply;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
