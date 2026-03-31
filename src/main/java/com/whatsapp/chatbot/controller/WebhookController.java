package com.whatsapp.chatbot.controller;

import com.whatsapp.chatbot.model.BotResponse;
import com.whatsapp.chatbot.model.WhatsAppMessage;
import com.whatsapp.chatbot.service.ChatbotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * WebhookController exposes the POST /webhook endpoint.
 *
 * This simulates the webhook that a real WhatsApp Business API
 * would call whenever a user sends a message.
 */
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final ChatbotService chatbotService;

    // Constructor injection (best practice over @Autowired on field)
    public WebhookController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    /**
     * POST /webhook
     *
     * Accepts a JSON body like:
     * {
     *   "from": "+919876543210",
     *   "message": "Hi"
     * }
     *
     * Returns:
     * {
     *   "to": "+919876543210",
     *   "reply": "Hello! 👋 How can I help you?"
     * }
     */
    @PostMapping
    public ResponseEntity<BotResponse> receiveMessage(@RequestBody WhatsAppMessage incomingMessage) {

        // Validate that "from" field is present
        if (incomingMessage.getFrom() == null || incomingMessage.getFrom().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(new BotResponse("unknown", "Missing 'from' field in request body."));
        }

        // Delegate to service for reply logic
        String reply = chatbotService.processMessage(
                incomingMessage.getFrom(),
                incomingMessage.getMessage()
        );

        // Build and return the response
        BotResponse response = new BotResponse(incomingMessage.getFrom(), reply);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /webhook  — Health check / verification endpoint.
     * Useful for platforms like Render to verify the service is alive,
     * and for simulating WhatsApp's webhook verification challenge.
     */
    @GetMapping
    public ResponseEntity<String> verifyWebhook(
            @RequestParam(value = "hub.challenge", required = false) String challenge) {

        if (challenge != null) {
            // WhatsApp Business API sends hub.challenge during webhook registration
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.ok("✅ WhatsApp Chatbot is running!");
    }
}
