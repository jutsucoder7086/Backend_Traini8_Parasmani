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
5. [API Documentation](#api-documentation)
6. [Validation Rules](#validation-rules)
7. [Error Handling](#error-handling)
8. [Project Structure](#project-structure)

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
```sh
git clone <repository-url>
cd <repository-directory>
