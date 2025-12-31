
# Spring Boot Microservices with Eureka Service Discovery

This project demonstrates a **simple Microservices Architecture** using **Spring Boot** and **Spring Cloud Netflix Eureka**.  
It consists of three decoupled services that communicate via a **Service Registry (Eureka)**.

---

## 🏗 Architecture Overview

The system shows how independent services can discover and communicate with each other **without hardcoding IP addresses**.

### Components

- **Service Registry (Eureka Server)**  
  Acts like a *phonebook*. All services register here.

- **Contact Service (Provider)**  
  Manages contact data and registers itself with Eureka.

- **User Service (Consumer)**  
  Fetches user info and uses `RestTemplate` (client-side load balancing) to discover and call the Contact Service.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Cloud Netflix Eureka (Server & Client)
- Spring Data JPA
- H2 Database (In-memory)
- RestTemplate

---

## 🛠️ Project Components

### 1️⃣ Service Registry (e-server)
- **Port:** 8761  
- **Role:** Discovery Server  
- **Configuration:** Does NOT register with itself  

---

### 2️⃣ Contact Service (contact-service)
- **Port:** 9002  
- **Role:** Resource Server (Provider)  
- **Database:** H2 (In-memory, resets on restart)  
- **Endpoints:**
  - POST `/contact`
  - GET `/contact/user/{userId}`

---

### 3️⃣ User Service (user-service)
- **Port:** 9001  
- **Role:** Client (Consumer)  
- **Logic:**
  - Contains a hardcoded list of users  
  - Fetches contacts dynamically from `contact-service` using Eureka  

---

## ⚙️ Setup & Installation

### ✅ Prerequisites
- JDK 17 or higher  
- Maven  

---

## ▶️ Step-by-Step Run Instructions

### Step 1: Clone the Repository
```bash
git clone https://github.com/your-username/your-repo-name.git
```

---

### Step 2: Run the Services (Strict Order)

> Microservices depend on Eureka, so the order matters.

---

### 🔹 Start Eureka Server
```bash
cd e-server
mvn spring-boot:run
```

✅ Verify:  
Open → http://localhost:8761  

---

### 🔹 Start Contact Service
```bash
cd contact-service
mvn spring-boot:run
```

✅ Verify:  
Refresh Eureka Dashboard → `CONTACT-SERVICE` should appear.

---

### 🔹 Start User Service
```bash
cd user-service
mvn spring-boot:run
```

✅ Verify:  
Refresh Eureka Dashboard → `USER-SERVICE` should appear.

---

## 🧪 How to Test

### 1️⃣ Add Contact Data (Contact Service)

**URL**
```
http://localhost:9002/contact
```

**Method**
```
POST
```

**Body**
```json
{
  "email": "arpit@gmail.com",
  "contactName": "Arpit",
  "userId": 1234
}
```

---

### 2️⃣ Fetch Aggregated User Data (User Service)

**URL**
```
http://localhost:9001/user/1234
```

**Method**
```
GET
```

**Response**
```json
{
  "userId": 1234,
  "name": "Arpit",
  "phone": "8765432184",
  "contacts": [
    {
      "contactId": 1,
      "email": "arpit@gmail.com",
      "contactName": "Arpit",
      "userId": 1234
    }
  ]
}
```

---

## 🧠 Code Highlight – Service Communication

```java
@GetMapping("/{userId}")
public User getUser(@PathVariable Long userId) {
    User user = userService.getUser(userId);

    List contacts = this.restTemplate.getForObject(
            "http://contact-service/contact/user/" + userId,
            List.class
    );

    user.setContacts(contacts);
    return user;
}
```

---

## 🔍 Eureka Discovery Configuration

Add this to both client services:

```properties
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```

---

## ✅ Summary

- Eureka acts as a **central registry**
- Services register automatically
- Clients discover services by name
- No hardcoded IPs
- Clean and scalable microservice design
- Uses in-memory H2 database

---

✨ Ideal for learning:
- Spring Boot Microservices  
- Eureka Service Discovery  
- Inter-service communication  
- Load-balanced RestTemplate  
