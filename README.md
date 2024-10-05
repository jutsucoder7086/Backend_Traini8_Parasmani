# Training Center Management API

This project is a Spring Boot RESTful application that allows users to create, save, and retrieve information about training centers. The application uses an inbuilt H2 file-based database for persisting data.

## Features
- **Create Training Center**: Create and save information about a new training center.
- **Get Training Centers**: Retrieve a list of all stored training centers.
- **Validations**: Mandatory fields, email, phone, and size validations are handled using annotations.
- **H2 Database**: Inbuilt file-based H2 database to store training center information.

## Table of Contents
1. [Technologies Used](#technologies-used)
2. [Installation](#installation)
3. [Database Configuration](#database-configuration)
4. [Running the Application](#running-the-application)
5. [Using H2 Console](#using-h2-console)
6. [API Documentation](#api-documentation)
7. [Validation Rules](#validation-rules)
8. [Error Handling](#error-handling)
9. [Project Structure](#project-structure)

## Technologies Used
- Java 17
- Spring Boot 3.3.4
- H2 Database (file-based)
- Maven
- Spring Data JPA

## Installation
Follow the steps below to set up and run the project locally:

### Prerequisites
- Java 17 or above installed.
- Maven installed.

### Clone the Repository
```
git clone <repository-url>
cd <repository-directory>
```

## Database Configuration
The project uses H2 file-based database. No additional configuration is needed for H2, as it is embedded. The database will be created in the file system, and you can access it from the H2 console.

### H2 Database Configuration (in application.properties)
- spring.datasource.url=jdbc:h2:file:./data/testdb
- spring.datasource.driverClassName=org.h2.Driver
- spring.datasource.username=sa
- spring.datasource.password=password
- spring.jpa.hibernate.ddl-auto = update
- spring.h2.console.enabled=true
- spring.h2.console.path=/h2-console

## Running the Application
To run the application:

1. **Build the application using Maven**:
    ```sh
    mvn clean install
    ```

2. **Run the application**:
    ```sh
    mvn spring-boot:run
    ```

By default, the application will start on port `8080`. You can access the APIs using a tool like Postman or `curl`.

## Using H2 Console
To use the H2 console, follow these steps:

1. After running the application, open a web browser.
2. Navigate to the H2 console URL: `http://localhost:8080/h2-console`.
3. Enter the following details:
   - **JDBC URL**: `jdbc:h2:file:./data/testdb`
   - **User Name**: `sa`
   - **Password**: `password`
4. Click on the **Connect** button to access the database. 

You can now run SQL queries to view or manipulate your data in the H2 database.

## API Documentation

### Create Training Center (POST)
- **URL**: `/api/training-centers`
- **Method**: `POST`
- **Content-Type**: `application/json`
- **Description**: This API endpoint creates a new training center and saves it in the database.

**Request Body**:
```json
{
  "centerName": "ABC Training Center",
  "centerCode": "TC1234567890",
  "address": {
    "detailedAddress": "123, Main Road",
    "city": "Mumbai",
    "state": "Maharashtra",
    "pincode": "400001"
  },
  "studentCapacity": 150,
  "coursesOffered": ["Java", "Spring Boot", "React"],
  "contactEmail": "info@abctraining.com",
  "contactPhone": "9876543210"
}
```

### Get Training Centers (GET)
- **URL**: `/api/training-centers`
- **Method**: `GET`
- **Content-Type**: `application/json`
- **Description**: This API endpoint retrieves all stored training centers.
- **Parameters**:
- **`cities`** (optional, `Collection<String>`): Filter training centers by city names. Example: `?cities=Mumbai,Delhi`
- **`createdOnStart`** (optional, `String`): Filter training centers that were created on or after this date. Format: `yyyy-MM-dd`. Example: `?createdOnStart=2024-01-01`
- **`createdOnEnd`** (optional, `String`): Filter training centers that were created on or before this date. Format: `yyyy-MM-dd`. Example: `?createdOnEnd=2024-12-31`

**Request Example**:
```
http://localhost:8080/api/training-centers?cities=Bengaluru,Mumbai&createdOnStart=2024-10-05&createdOnEnd=2024-10-05
```

**Response**:
- **Success**: Returns a list of all stored training centers in JSON format. If no training centers are available, it returns an empty list.
- **Errors**: Returns an appropriate error message if the end date is earlier than the start date.

**Note**:
- If `cities` is null, the endpoint will return results for all cities present in the table.
- If `createdOnStart` is null, the endpoint will fetch the earliest date available in the table.
- If `createdOnEnd` is null, the endpoint will fetch the latest date available in the table.

**Example Response**:
```json
[
  {
    "centerName": "ABC Training Center",
    "centerCode": "TC1234567890",
    "address": {
      "detailedAddress": "123, Main Road",
      "city": "Mumbai",
      "state": "Maharashtra",
      "pincode": "400001"
    },
    "studentCapacity": 150,
    "coursesOffered": ["Java", "Spring Boot", "React"],
    "contactEmail": "info@abctraining.com",
    "contactPhone": "9876543210",
    "createdOn": 1696372945000
  },
  {
    "centerName": "XYZ Training Center",
    "centerCode": "TC0987654321",
    "address": {
      "detailedAddress": "456, Side Road",
      "city": "Pune",
      "state": "Maharashtra",
      "pincode": "411001"
    },
    "studentCapacity": 100,
    "coursesOffered": ["Python", "Django"],
    "contactEmail": "info@xyztraining.com",
    "contactPhone": "9876543211",
    "createdOn": 1696372945000
  }
]
```

## Validation Rules
- **CenterName**: Must be provided and be less than 40 characters.
- **CenterCode**: Must be exactly 12 characters (alphanumeric).
- **Address**: Must have a valid detailed address, city, state, and pincode.
- **ContactPhone**: Must be a valid 10-digit phone number.
- **ContactEmail** (if present): Must be a valid email address.
- **CreatedOn**: Automatically generated by the server.

## Error Handling
The application includes an `ExceptionHandler` to manage validation and other exceptions gracefully. On validation failure, the user will receive a descriptive error message.

## Project Structure
```
training-center-api/
 ├── src/
 │    ├── main/
 │    │    ├── java/
 │    │    │    └── com.example.demo/
 │    │    │         ├── controllers/        # API Controllers
 │    │    │         ├── services/           # Service Layer
 │    │    │         ├── repositories/       # Repository for Database Interaction
 │    │    │         ├── entities/           # Entity Classes
 │    │    │         └── customExceptions/   # Custom Exception 
Handling		    	
 │    │    └── resources/
 │    │         ├── application.properties   # Application Configuration
 └── pom.xml                                 # Maven Dependencies
```

