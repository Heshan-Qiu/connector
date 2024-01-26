# Visa API Connector

## Introduction

This project provides a Spring Boot micro service for connecting to Visa's API. It includes a custom implementation for handling secure HTTP requests to the Visa API endpoints, with a focus on the `helloworld` endpoint.

## Setup

### Prerequisites

-   Java 8 or 11
-   Maven

### Installation

1. Clone the repository:
   git clone https://github.com/Heshan-Qiu/connector.git

2. Navigate to the project directory:
   cd connector

## Usage

To run the application:
gradle bootRun

Access the service at: `http://localhost:8080/api/helloworld-status`

## Configuration

Configure the application properties in `src/main/resources/application.properties`.

# SSL Configuration

ssl.key-store-path=your-keystore-path
ssl.key-store-password=your-keystore-password
ssl.private-key-password=your-private-key-password

# VISA API URL Configuration

visa.api.helloworld.url=visa-api-url

# VISA API Authentication Configuration

visa.api.authentication.user.id=your-user-id
visa.api.authentication.password=your-password

# API Key Configuration

api.key=your-api-key

## API Endpoints

-   `GET /api/helloworld-status`: Connects to Visa's `helloworld` endpoint.

## Contributing

Contributions to this project are welcome. Please fork the repository and submit a pull request.

## Contact

For any inquiries, please contact heshan.chiu@gmail.com.
