# QR-Based Attendance Management System

A QR-Based Attendance Management System developed using **Java, Spring Boot, Spring Data JPA, and MySQL**. The application aims to simplify attendance tracking through QR codes and maintain attendance records digitally.

## 🚀 Project Overview

The QR-Based Attendance Management System is designed to make attendance tracking easier, faster, and more efficient. It uses QR code technology to support digital attendance management and Spring Boot to handle backend operations.

The application integrates MySQL for storing and managing attendance-related information.

## ✨ Features

- QR Code-Based Attendance Management
- Digital Attendance Tracking
- MySQL Database Integration
- Backend Development using Spring Boot
- REST API Integration
- Database Operations using Spring Data JPA and Hibernate
- Automatic Database Table Management through Hibernate

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming Language |
| Spring Boot | Backend Framework |
| Spring Data JPA | Database Operations |
| Hibernate | ORM Framework |
| MySQL | Relational Database |
| Maven | Dependency Management |
| Spring Tool Suite (STS) | Development Environment |
| Git & GitHub | Version Control |

## 📸 Application Screenshots

### 1. Application Execution

<img width="1600" height="721" alt="REGISTERATION" src="https://github.com/user-attachments/assets/61e8947c-73bd-470a-bb8f-518cf868e16e" />



### 2. QR Code Attendance

<img width="1391" height="714" alt="QR-GENERATION" src="https://github.com/user-attachments/assets/4040c7b1-eb34-459f-93b9-803e1f6c027c" />


### 3. Attendance Records

<img width="1235" height="703" alt="ATTENDENCE MARKING" src="https://github.com/user-attachments/assets/40fe1b26-8f63-4692-ba57-1a07144ead75" />

<img width="1600" height="313" alt="ATTENDENCE" src="https://github.com/user-attachments/assets/41016de3-71eb-42f8-be53-8dd48bfa1a2f" />



> Replace the screenshot filenames above with your actual image filenames.

## 📂 Project Structure

```text
APIATTENDENCEPROJECT/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── Application Packages
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── screenshots/
│   ├── application.png
│   ├── qrcode.png
│   └── attendance.png
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## ⚙️ Installation and Setup

### Prerequisites

Make sure you have installed:

- Java JDK
- Spring Tool Suite (STS) or Eclipse
- MySQL Server
- Maven

### Step 1: Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/APIATTENDENCEPROJECT.git
```

### Step 2: Navigate to the Project

```bash
cd APIATTENDENCEPROJECT
```

### Step 3: Create MySQL Database

Open MySQL and execute:

```sql
CREATE DATABASE acetcse;
```

### Step 4: Configure Database Connection

Update `src/main/resources/application.properties`:

```properties
spring.application.name=APIATTENDENCEPROJECT

spring.datasource.url=jdbc:mysql://localhost:3306/acetcse
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

Replace `YOUR_PASSWORD` with your MySQL password.

### Step 5: Run the Application

Open the project in Spring Tool Suite.

1. Import the project as an existing Maven project.
2. Wait for Maven dependencies to download.
3. Open the main Spring Boot application class.
4. Right-click and select **Run As → Spring Boot App**.

The application will start if the configuration is correct.

## 🎯 Project Objectives

- Simplify the attendance management process.
- Reduce manual attendance tracking.
- Improve attendance management efficiency.
- Maintain attendance data in a centralized database.
- Implement backend development using Spring Boot.

## 👨‍💻 Author

**Kamepalli Pujitha**

