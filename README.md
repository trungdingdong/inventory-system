# Inventory System

A Spring Boot application designed to manage personal inventory, specifically focusing on Users, Albums, and Photos. This application leverages MySQL for structured data storage and AWS S3 for scalable photo storage.

## Technology Stack

-   **Java**: 17
-   **Framework**: Spring Boot 3.4.0
-   **Dependencies**:
    -   Spring Web
    -   Spring Data JPA
    -   Spring Thymeleaf
    -   AWS SDK for Java (S3)
    -   Lombok
    -   MySQL Driver
    -   Spring Boot DevTools

## Prerequisites

Before running the application, ensure you have the following installed:

-   Java Development Kit (JDK) 17 or higher
-   Maven
-   MySQL Server
-   An AWS Account with an S3 Bucket

## Configuration

The application requires configuration for both the database and AWS S3. These settings are located in `src/main/resources/application.properties`.

### Database Configuration

Ensure your MySQL server is running and create a database named `inventory_db`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/inventory_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=123456
```

*Note: Update `localhost:3307`, `username`, and `password` to match your local MySQL setup.*

### AWS S3 Configuration

You need to provide your AWS credentials and S3 bucket details.

```properties
aws.accessKeyId=YOUR_ACCESS_KEY
aws.secretAccessKey=YOUR_SECRET_KEY
aws.region=us-east-1
aws.s3.bucketName=inventory-nghich-s3
```

*Replace the placeholders with your actual AWS credentials.*

## Installation & Running

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    cd inventory-system
    ```

2.  **Build the project:**
    ```bash
    mvn clean install
    ```

3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

The application will start effectively on the configured port (default is usually 8080).

## Features

-   **User Management**: Manage system users.
-   **Album Management**: Create and organize albums.
-   **Photo Storage**: Upload and retrieve photos using AWS S3.

## Project Structure

-   `src/main/java/com/example/inventory_system`: Contains the main source code.
    -   `controller`: API endpoints (e.g., `PhotoController`).
    -   `entity`: Database models (`User`, `Album`, `Photo`).
    -   `repository`: Data access layers.
    -   `service`: Business logic.
    -   `config`: Configuration classes.
