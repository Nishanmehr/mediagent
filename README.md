# 🏥 MediAgent — AI-Powered Patient Care Coordinator

> A multi-agent healthcare system where 5 specialized AI agents 
> collaborate through Band to provide comprehensive patient care.

![MediAgent Banner](cover-image.png)

## 🤖 How It Works

5 AI agents communicate through **Band** platform:

| Agent | Role |
|-------|------|
| 🔍 Symptom Analyzer | Analyzes patient symptoms |
| 📋 Medical History | Checks patient history & risks |
| 👨‍⚕️ Doctor Matcher | Finds best specialist needed |
| 📍 Location Agent | Finds nearby top-rated doctors |
| 🏥 Care Planner | Creates personalized care plan |

## 🏗️ Architecture

## 🛠️ Tech Stack

**Backend:**
- Java Spring Boot
- Groq LLM (llama-3.3-70b-versatile)
- Band AI — Agent Communication Layer
- PostgreSQL

**Frontend:**
- React.js
- Modern UI with Teddy Bear Doctor 🐻

**Deployment:**
- Backend: Render
- Frontend: Vercel

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Node.js 18+
- PostgreSQL

### Backend Setup
```bash
git clone https://github.com/Nishanmehr/mediagent.git
cd mediagent
# Add your keys in application.properties
mvn spring-boot:run
```

### Frontend Setup
```bash
git clone https://github.com/Nishanmehr/mediagent-frontend.git
cd mediagent-frontend
npm install
npm start
```

## 🔑 Environment Variables

```properties
groq.api.key=YOUR_GROQ_KEY
band.api.key=YOUR_BAND_KEY
band.room.id=YOUR_ROOM_ID
spring.datasource.url=YOUR_DB_URL
```

## 📱 Features

- ✅ Real-time multi-agent collaboration via Band
- ✅ Simple language health reports
- ✅ Nearby doctor finder with ratings & fees
- ✅ Personalized care plans
- ✅ Beautiful modern UI

## 🏆 Built For

Band of Agents Hackathon — lablab.ai
June 2026

## 👨‍💻 Developer

**Nishant Mehra**
- Backend Developer
- Java Spring Boot + AI Integration
