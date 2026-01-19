# 🛠️ Resume Tailor

**Resume Tailor** is a full‑stack AI‑assisted web application that helps users optimize their resumes against a specific job description. It analyzes resume content, extracts relevant technical keywords from the job description, identifies gaps, and generates **context‑aware, professional resume suggestions** using an LLM.

This project focuses on **practical ATS optimization**, clean backend design, and real‑world frontend–backend integration.

---

## ✨ Why Resume Tailor?

Recruiters and ATS systems often filter resumes based on **keyword relevance** rather than intent. Resume Tailor bridges this gap by:

* Identifying **missing technical skills** from a job reinforce job description
* Enhancing resume bullets **without changing the candidate’s core experience**
* Producing **action‑oriented, concise, and ATS‑friendly** resume lines

---

## 🚀 Features

* Upload or paste **Resume** and **Job Description**
* Automatic extraction of **technical keywords** from JD
* Line‑by‑line resume analysis
* AI‑generated suggestions with:

  * Original line
  * Improved version
  * Reason for change
* Clean REST API architecture
* CORS‑enabled for frontend integration

---

## 🧠 Core Logic & Methods Used

### 1️⃣ Keyword Extraction (Rule‑Based NLP)

* Maintains a curated list of **known technical skills**
* Normalizes job description text
* Matches relevant skills using string containment

**Why this approach?**

* Lightweight
* Fast
* Transparent (easy to debug and extend)

---

### 2️⃣ Resume Line Analysis

Each resume bullet is:

* Compared against extracted JD skills
* Checked for **missing keywords**
* Either:

  * Returned unchanged (if fully aligned)
  * Or rewritten with missing skills added naturally

---

### 3️⃣ AI‑Powered Rewrite (LLM Integration)

* Uses **OpenAI Chat Completions API**
* Sends a structured prompt including:

  * Original resume line
  * Missing technical skills
* Ensures output is:

  * Concise
  * Professional
  * Action‑oriented

**Why AI here?**

* Rule‑based rewriting fails at natural language
* LLM ensures human‑like phrasing while preserving meaning

---

## 🧱 Tech Stack

### 🔹 Frontend

* **React** – component‑based UI
* **Axios** – API communication
* **CSS Modules** – scoped styling

### 🔹 Backend

* **Java 17+**
* **Spring Boot** – REST API framework
* **Spring Web** – controller & request handling
* **Jackson** – JSON serialization/deserialization
* **Java HTTP Client** – OpenAI API integration

### 🔹 AI / External Services

* **OpenAI API** (Chat Completions)
* Secure API key handling via **environment variables**

---

## 🗂️ Project Structure (Backend)

```text
src/main/java/com/example/resume_tailor
│
├── ResumeTailorApplication.java   # Spring Boot entry point
├── ResumeController.java          # REST API controller
├── ResumeRequest.java             # Request DTO (resume + JD)
├── Suggestion.java                # Response model
├── SuggestionService.java         # Core business logic + AI integration
│
src/main/resources
│
└── application.properties
```

---

## 🔌 API Design

### POST `/getSuggestions`

**Request Body**

```json
{
  "resume": [
    "Built REST APIs using Spring Boot",
    "Worked with databases"
  ],
  "jd": "Looking for a Java developer with Spring Boot, REST APIs, and MySQL"
}
```

**Response**

```json
[
  {
    "original": "Built REST APIs using Spring Boot",
    "suggested": "Developed RESTful APIs using Spring Boot and MySQL to support scalable backend services",
    "reason": "Improved to include: mysql"
  }
]
```

---

## ▶️ How to Run Locally

### 🔹 Backend Setup

1. Clone the repository

```bash
git clone https://github.com/your-username/resume-tailor.git
cd resume-tailor/backend
```

2. Set OpenAI API Key

**Linux / macOS**

```bash
export OPENAI_API_KEY=your_api_key_here
```

**Windows (PowerShell)**

```powershell
setx OPENAI_API_KEY "your_api_key_here"
```

3. Run Spring Boot

```bash
./mvnw spring-boot:run
```

Backend will start at:

```
http://localhost:8080
```

---

### 🔹 Frontend Setup

```bash
cd frontend
npm install
npm start
```

Frontend runs at:

```
http://localhost:3000
```

---

## 🔐 Security Considerations

* OpenAI API key stored using **environment variables**
* No API keys exposed to frontend
* Backend handles all AI requests securely

---

## 🌱 Future Enhancements

* ATS score calculation
* Resume PDF parsing
* Skill weighting instead of binary matching
* User authentication
* Cloud deployment (AWS / GCP)

---

  ## 📸 Screenshots

- **Resume upload & job description input** (React UI)
 ![Screenshot 2025-06-21 120118](https://github.com/user-attachments/assets/573f515d-81e3-41ad-80dc-3459ca6d86c1)

- **Tailored suggestion results** displayed after analysis
  ![Screenshot 2025-06-21 120515](https://github.com/user-attachments/assets/af0bcd46-3f48-4cc4-a882-2f7f945b60d3)

- **Suggested keywords and skills** highlighted and returned by backend
  ![Screenshot 2025-06-21 115833](https://github.com/user-attachments/assets/a8324d46-0ddd-45d0-8f7c-cb91743f457d)

- **Backend APIs**
  ![Screenshot 2025-06-21 115747](https://github.com/user-attachments/assets/67ce0e55-1533-4c80-8f68-b59940c01046)
  ![Screenshot 2025-06-21 115805](https://github.com/user-attachments/assets/7409215c-cff5-449b-b534-55ccccc5e138)


## 👩‍💻 Author

**Bhumika Deshmukh**
B.Tech Student | Full‑Stack & Backend Enthusiast



