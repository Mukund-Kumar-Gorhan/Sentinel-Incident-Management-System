# Sentinel – Incident Management System

A Java-based Incident Management System designed to manage, track, search, update, and monitor security incidents using MySQL.

## 🚀 Features

- User Registration & Login
- Role-Based Authentication
- Admin Dashboard
- User Dashboard
- Incident Reporting
- View All Incidents
- Search Incident by ID
- Update Incident Status
- Delete Incidents
- Incident History Tracking
- Incident Statistics
- Priority Queue
- MySQL Database Integration
- JDBC Connectivity
- Input Validation

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- JDBC
- MySQL
- IntelliJ IDEA
- Git & GitHub

## 📂 Project Structure

```text
src/
└── sentinel/
    ├── Main.java
    ├── AdminDashboard.java
    ├── UserDashboard.java
    │
    ├── model/
    │   ├── User.java
    │   ├── Incident.java
    │   ├── Category.java
    │   ├── Severity.java
    │   └── Status.java
    │
    ├── repository/
    │   ├── DatabaseConnection.java
    │   ├── UserRepository.java
    │   ├── IncidentRepository.java
    │   └── IncidentHistoryRepository.java
    │
    ├── service/
    │   ├── UserService.java
    │   ├── IncidentService.java
    │   ├── LoginService.java
    │   ├── AdminService.java
    │   └── IncidentStatistics.java
    │
    └── util/
        └── IncidentValidator.java
