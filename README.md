# 📱 WhatsApp Chatbot – Spring Boot Backend Simulation

A lightweight REST API that simulates a WhatsApp chatbot backend.
Send a JSON message to `POST /webhook` and the bot replies instantly.

---

## 🏗️ Project Structure

```
whatsapp-bot/
├── pom.xml
├── render.yaml                          ← Render deployment config
└── src/main/
    ├── java/com/whatsapp/chatbot/
    │   ├── ChatbotApplication.java      ← Spring Boot entry point
    │   ├── controller/
    │   │   └── WebhookController.java   ← POST /webhook endpoint
    │   ├── service/
    │   │   └── ChatbotService.java      ← Reply logic + logging
    │   └── model/
    │       ├── WhatsAppMessage.java     ← Incoming message POJO
    │       └── BotResponse.java        ← Outgoing response POJO
    └── resources/
        └── application.properties
```

---

## ▶️ Run Locally

### Prerequisites
- Java 17+
- Maven 3.8+

```bash
cd whatsapp-bot
mvn spring-boot:run
```

Server starts at: `http://localhost:8080`

---

## 🧪 Test the API

### 1. Health check (GET)
```bash
curl http://localhost:8080/webhook
# Response: ✅ WhatsApp Chatbot is running!
```

### 2. Send a message (POST)
```bash
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from": "+919876543210", "message": "Hi"}'
```
Response:
```json
{"to": "+919876543210", "reply": "Hello! 👋 How can I help you?"}
```

### 3. All predefined replies
| Input          | Reply                                      |
|----------------|--------------------------------------------|
| Hi / Hello / Hey | Hello! 👋 How can I help you?           |
| Bye / Goodbye  | Goodbye! 👋 Have a great day!              |
| Help           | 📋 Available commands list                 |
| Thanks / Thank you | You're welcome! 😊                    |
| Anything else  | 🤖 Sorry, I didn't understand...          |

---

## ☁️ Deploy on Render (Bonus)

1. Push this project to a GitHub repository
2. Go to [render.com](https://render.com) → New → Web Service
3. Connect your GitHub repo
4. Render auto-detects `render.yaml` and configures everything
5. Your live URL: `https://whatsapp-chatbot.onrender.com/webhook`

---

## 📋 Sample Console Logs

```
2024-01-15 10:30:01 [INFO ] ChatbotService - 📩 Incoming message | From: +919876543210 | Message: "Hi"
2024-01-15 10:30:01 [INFO ] ChatbotService - 📤 Outgoing reply   | To:   +919876543210 | Reply:   "Hello! 👋 How can I help you?"
```
