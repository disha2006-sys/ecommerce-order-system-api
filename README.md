E-Commerce Order Management System

A backend REST API for managing products, users, and customer orders, built with Spring Boot and MySQL. The project follows a layered architecture and demonstrates real-world backend development practices such as DTOs, validation, exception handling, JPA/Hibernate, and RESTful API design.

🚀 Project Overview

The E-Commerce Order Management System is a Spring Boot-based backend application designed to manage products, users, and orders.

The system provides REST APIs for:

Managing products
Managing users
Creating and managing orders
Managing order items
Validating incoming requests
Handling application exceptions
Managing product stock
Returning structured API responses
🛠️ Tech Stack
Technology	Purpose
Java 21	Backend programming
Spring Boot	Application framework
Spring Web	REST APIs
Spring Data JPA	Database interaction
Hibernate	ORM
MySQL	Relational database
Maven	Build & dependency management
Lombok	Boilerplate code reduction
Swagger / OpenAPI	API documentation
Git & GitHub	Version control
✨ Key Features
RESTful API architecture
Product management
User management
Order creation and management
Order item management
DTO-based request/response handling
Input validation
Global exception handling
Duplicate resource handling
Insufficient stock validation
MySQL database integration
Swagger API documentation
Layered architecture using Controller, Service, Repository and Entity layers
## 🏗️ System Architecture

The application follows a layered architecture to maintain separation of concerns, scalability, and maintainability.

```text
┌──────────────────────────────┐
│          Client              │
│   Postman / Swagger / Web    │
└──────────────┬───────────────┘
               │ HTTP Request
               ▼
┌──────────────────────────────┐
│      Controller Layer        │
│  OrderController             │
│  ProductController           │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│        Service Layer         │
│  OrderService                │
│  ProductService              │
│  UserService                 │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│       Repository Layer       │
│  OrderRepository             │
│  OrderItemRepository         │
│  ProductRepository           │
│  UserRepository              │
└──────────────┬───────────────┘
               │ JPA / Hibernate
               ▼
┌──────────────────────────────┐
│        MySQL Database        │
│  User | Product | Order      │
│          | OrderItem         │
└──────────────────────────────┘
Architecture Layers
Layer	Responsibility
Controller	Handles HTTP requests and responses
Service	Contains business logic
Repository	Performs database operations
Entity	Represents database tables
DTO	Transfers data between client and application
Mapper	Converts entities to DTOs and vice versa
Exception Handler	Handles application errors globally
## 🗄️ Database Design / ER Diagram

The application uses MySQL as the relational database. The database consists of four main entities: User, Product, Order, and OrderItem.

```text
┌─────────────────────┐
│        USER         │
├─────────────────────┤
│ PK  id              │
│     name            │
│     email           │
│     role            │
└──────────┬──────────┘
           │
           │ 1 : N
           ▼
┌─────────────────────┐
│        ORDER        │
├─────────────────────┤
│ PK  id              │
│ FK  user_id         │
│     order_date      │
│     total_amount    │
│     status          │
└──────────┬──────────┘
           │
           │ 1 : N
           ▼
┌─────────────────────┐
│      ORDER_ITEM     │
├─────────────────────┤
│ PK  id              │
│ FK  order_id        │
│ FK  product_id      │
│     quantity        │
│     price           │
└──────────┬──────────┘
           │
           │ N : 1
           ▼
┌─────────────────────┐
│       PRODUCT       │
├─────────────────────┤
│ PK  id              │
│     name            │
│     price           │
│     stock_quantity  │
└─────────────────────┘
Entity Relationships
Relationship	- Description
User → Order	-One user can have multiple orders
Order → OrderItem -	One order can contain multiple order items
Product → OrderItem -	One product can appear in multiple order items
OrderItem → Product	 - Each order item belongs to one product

## 📋 Prerequisites

Before running the project, make sure the following are installed:

- Java 21
- Maven
- MySQL 8+
- Git
- IntelliJ IDEA or any Java IDE

## ⚙️ How to Run Locally

### 1. Clone the Repository
``bash
git clone git@github-disha:disha2006-sys/ecommerce-order-system-api.git
cd ecommerce-order-system-api

2. Create the MySQL Database

Open MySQL Workbench or MySQL Command Line and create the database:
CREATE DATABASE ecommerce;

3. Configure Database Credentials

Create your local:
src/main/resources/application.properties

and configure your local MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

Note: application.properties contains local database credentials and is intentionally excluded from Git version control.

4. Build the Project

Using Maven:

mvn clean install
5. Run the Application
mvn spring-boot:run

The application will start on:

http://localhost:8080
🔒 Configuration & Security

Sensitive configuration such as database passwords is not committed to the repository.

The repository contains:
application-example.properties

as a configuration template, while the actual:
application.properties
remains local.


## 📡 API Documentation
The application provides RESTful APIs for managing products and creating customer orders.

### Base URL
```text
http://localhost:8080

#Product APIs
Method	 Endpoint	      Description	         Success   Response
GET	   /api/v1/products	  Get all products	       200        OK
POST   /api/v1/products	   Create a new product	   201       CREATED
#Order APIs
Method	Endpoint	Description	            Success    Response
POST	/api/v1/orders	Create a new order	  201       CREATED


### Product — Create
```json
{
  "name": "Gaming Mouse",
  "price": 1500,
  "stockQuantity": 20
}

### product -Response
{
  "id": 1,
  "name": "Gaming Mouse",
  "price": 1500,
  "stockQuantity": 20
}

###get All Product- response
[
  {
    "id": 1,
    "name": "Gaming Mouse",
    "price": 1500,
    "stockQuantity": 20
  }
]

### Order — Create

**Endpoint:**

```text
POST /api/v1/orders
#Request-body
{
  "user": {
    "id": 1
  },
  "orderItems": [
    {
      "product": {
        "id": 1
      },
      "quantity": 2,
      "price": 1500
    }
  ],
  "totalAmount": 3000
}
Expected Response:

The API creates the order with automatically generated values for:

id
orderDate
status

The default order status is:

PENDING
Order Response Example
{
  "id": 1,
  "user": {
    "id": 1
  },
  "orderDate": "2026-08-16T15:30:00",
  "totalAmount": 3000,
  "status": "PENDING",
  "orderItems": [
    {
      "id": 1,
      "product": {
        "id": 1
      },
      "quantity": 2,
      "price": 1500
    }
  ]
}

Note: The exact serialized response depends on the JPA relationship and JSON serialization configuration.

## 📚 Swagger API Documentation

The project uses Swagger / OpenAPI for interactive API documentation and testing.

After starting the application, open:

```text
http://localhost:8080/swagger-ui.html
wagger UI allows you to:

View available REST API endpoints
View request and response models
Test APIs directly from the browser
Inspect HTTP methods and response codes
Understand API request parameters and payloads
OpenAPI Specification

The generated OpenAPI specification is available at:

http://localhost:8080/v3/api-docs
