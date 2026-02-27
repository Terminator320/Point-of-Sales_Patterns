# Point-of-Sales Patterns (JavaFX + MySQL)
Final Project for Programming Patterns

A Java **Point-of-Sale (POS)** desktop application built with **JavaFX** and backed by a **MySQL/MariaDB** database. This project was created as a final project for a Programming Patterns course and demonstrates practical usage of common patterns and techniques (ex: **Factory Method**, MVC-style structure, logging configuration, and multithreading with synchronization).

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Database Setup](#database-setup)
  - [Configure Database Connection](#configure-database-connection)
  - [Run the App](#run-the-app)
- [Design Patterns & Concepts Used](#design-patterns--concepts-used)
- [Usage](#usage)
- [Troubleshooting](#troubleshooting)
- [Contributors](#contributors)
- [License](#license)

## Project Overview
This POS app includes multiple JavaFX screens (menu/order flow, inventory, sales, payment) and stores/retrieves data from a database named `pos`.

The Maven module is inside the `POSApp/` folder.

## Features
- **JavaFX GUI** with multiple views:
  - Main screen
  - Menu / new order flow
  - Sales order screen
  - Payment screen
  - Inventory screen
  - Sales screen (includes profit calculations)
- **Database-backed** menu items, inventory, sales orders, and ingredient relationships
- **Payment processing selection** using a factory approach (Cash / Debit / Credit)
- **Logging** to `POSApp/src/logfile.log`
- **Multithreaded profit calculation** (splits sales list across two threads + semaphore synchronization)

## Tech Stack
- **Java** (project `pom.xml` targets Java `24`)
- **JavaFX** (controls + fxml `17.0.6`)
- **Maven**
- **MySQL/MariaDB** (SQL dump provided: `pos.sql`)

## Project Structure
Top-level:
- `POSApp/` → the Maven JavaFX application  
- `pos.sql` → database schema + seed data  
- `mysql-connector-j-9.5.0.jar` → MySQL JDBC driver jar (also referenced via IDE configs)

Inside `POSApp/src/main/java`:
- `com.example.posapp` → application entry + logging config
- `com.example.posapp.controller` → JavaFX controllers (UI logic)
- `com.example.posapp.models` → model classes (domain/data objects)
- `com.example.posapp.PaymentFactory` → payment factory + implementations
- `com.example.posapp.multithreadingprofitcalculator` → profit calculator threads + semaphore usage
- `database/ConfigManager.java` → DB connection loader from `config.xml`

Resources:
- `POSApp/src/main/resources/com/example/posapp/*.fxml` → JavaFX views
- `POSApp/src/main/resources/com/example/posapp/config.xml` → DB connection config
- images + css files for UI styling

## Getting Started

### Prerequisites
- **JDK 24** (because the Maven compiler plugin sets `<source>24</source>` and `<target>24</target>`)
- **Maven**
- **MySQL or MariaDB** running locally

> If you don’t have JDK 24 installed, you can still run it by lowering the Java version in `POSApp/pom.xml` (example: 17 or 21), but make sure your code stays compatible.

### Database Setup
1. Create a database named `pos`
2. Import the SQL file:
   - Use phpMyAdmin / MySQL Workbench, or run:
     ```sql
     SOURCE path/to/pos.sql;
     ```

The SQL file includes:
- `inventory`
- `menu_item`
- `menu_item_ingredient`
- plus seed data for the POS menu and inventory

### Configure Database Connection
Edit:
`POSApp/src/main/resources/com/example/posapp/config.xml`

Example:
```xml
<database>
    <url>jdbc:mysql://127.0.0.1/pos</url>
    <user>root</user>
    <password></password>
</database>
