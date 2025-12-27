# Hotel Reservation System - Java

## Overview
This project implements a **Hotel Reservation System** in Java, designed to simulate the process of searching, booking, and managing hotel rooms. It leverages **Object-Oriented Programming (OOP) principles** and **File I/O** for persistent storage of bookings. This system can be used as a base for more advanced hotel management applications.

The project demonstrates my skills in Java programming, OOP concepts, and file handling—making it suitable for showcasing in an internship portfolio.

---

## Features

### 1. Room Categorization
The system supports multiple types of rooms with different pricing:
- **Standard**: Affordable basic rooms.
- **Deluxe**: Comfortable rooms with additional amenities.
- **Suite**: Premium rooms with luxury features.

### 2. Search Functionality
Users can search for available rooms based on room type. Only rooms that are currently available for booking are displayed.

### 3. Booking & Cancellation
- Users can **book a room** by providing their name and room number.
- Users can **cancel a booking** if they no longer require the room.
- The system updates room availability dynamically.

### 4. Booking Details
Users can view all existing bookings, including:
- Customer name
- Room number
- Room type
- Price

### 5. Payment Simulation
The booking process simulates a simple payment flow by confirming the booking and displaying the total price.

### 6. Data Persistence
All bookings are stored in a file named `bookings.txt` using **File I/O**:
- When the system is closed and reopened, all previous bookings are loaded automatically.
- Room availability is updated based on saved bookings.

---

## Technologies Used
- **Java**: Core programming language used for implementation.
- **OOP Concepts**:
  - Classes and Objects (`Room`, `Booking`, `Hotel`)
  - Encapsulation
  - Abstraction
  - Modularity and reusability
- **File I/O**: Reading and writing booking data to a text file.
- **Collections Framework**: `ArrayList` and `Iterator` for managing rooms and bookings.

---

## Project Structure
- `HotelReservationSystem.java` – Main Java program containing all classes:
  - `Room` – Represents a hotel room.
  - `Booking` – Stores booking details.
  - `Hotel` – Manages rooms, bookings, search, and file operations.
- `bookings.txt` – Stores booking records for persistence.

---

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/HotelReservationSystem.git
