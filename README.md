
# 🚗 ParkEngine - Parking Lot Automation System 

A Java-based **Parking Lot Automation System** designed to manage vehicle parking, parking-space allocation, ticket generation, billing, and payments. This project demonstrates Object-Oriented Programming (OOP) concepts and software design patterns through a real-world parking management application.

---

## 📖 Project Overview

The Parking Lot Automation System is a console-based Java application that automates parking operations.

It manages multiple parking floors, allocates suitable parking spaces based on vehicle types, generates parking tickets, calculates parking fees, and handles vehicle exits.

The system uses different design patterns to make the application modular, reusable, and maintainable.

**Main Objective:** To simplify parking management through an automated system using Java and Object-Oriented Programming.

---

## ✨ Features

- **Vehicle Management:** Supports Bike, Car, and Truck.
- **Multiple Floors:** Manages parking spaces across multiple floors.
- **Automatic Parking Allocation:** Finds the first available compatible parking spot.
- **Ticket Generation:** Generates a unique parking ticket for every vehicle.
- **Duplicate Vehicle Check:** Prevents a vehicle from having multiple active parking entries.
- **Vehicle Search:** Searches for vehicles using their vehicle numbers.
- **Parking Availability:** Displays available and occupied parking spaces.
- **Automatic Updates:** Updates display boards when parking availability changes.
- **Billing System:** Calculates parking fees according to vehicle type and parking duration.
- **Flexible Pricing:** Supports normal and weekend pricing strategies.
- **Payment Options:** Supports simulated Cash, UPI, and Card payments.
- **Vehicle Exit:** Releases the parking spot after the exit process.

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| OOP | Application design and implementation |
| Java Collections | Managing parking spots and tickets |
| Enums | Defining vehicle types and ticket statuses |
| Design Patterns | Creating modular architecture |
| Console Interface | User interaction |

---

## 🚘 Supported Vehicles

The application supports three types of vehicles:

| Vehicle | Vehicle Class | Parking Spot |
|---|---|---|
| Bike | Bike | BikeSpot |
| Car | Car | CarSpot |
| Truck | Truck | TruckSpot |

Each vehicle can park only in its compatible parking spot.

---

## 🏗️ Project Architecture

The application follows a modular architecture.

### Main Components

1. **User Interface Layer**
   - `ParkEngine`
   - Handles menu-driven user interaction.

2. **Parking Management Layer**
   - `ParkingLot`
   - `ParkingFloor`
   - `EntryGate`
   - `ExitGate`

3. **Vehicle Management Layer**
   - `Vehicle`
   - `Bike`
   - `Car`
   - `Truck`
   - `VehicleFactory`

4. **Parking Spot Management Layer**
   - `ParkingSpot`
   - `BikeSpot`
   - `CarSpot`
   - `TruckSpot`

5. **Strategy Layer**
   - `ParkingStrategy`
   - `PricingStartegy`
   - `PaymentStrategy`

6. **Ticket and Notification Layer**
   - `ParkingTicket`
   - `ParkingObserver`
   - `ParkingDisplayBoard`

### Architecture Diagram

```text
             User
              |
              v
       ParkEngine (Main)
              |
              v
          ParkingLot
              |
       +------+------+
       |             |
       v             v
 ParkingFloor     EntryGate
       |             |
       v             v
 ParkingSpot    VehicleFactory
       |             |
       |         Bike / Car / Truck
       |
       v
 ParkingStrategy
       |
       v
 ParkingTicket
       |
       v
    ExitGate
       |
       v
 PricingStrategy
       |
       v
 PaymentStrategy
       |
       v
 Cash / UPI / Card

 ParkingObserver
       |
       v
 ParkingDisplayBoard
```

---

## 📚 Class Description

| Class / Interface | Responsibility |
|---|---|
| `Vehicle` | Abstract base class for vehicles |
| `Bike` | Represents a bike |
| `Car` | Represents a car |
| `Truck` | Represents a truck |
| `VehicleFactory` | Creates vehicle objects |
| `ParkingSpot` | Abstract base class for parking spots |
| `BikeSpot` | Represents a bike parking spot |
| `CarSpot` | Represents a car parking spot |
| `TruckSpot` | Represents a truck parking spot |
| `ParkingFloor` | Manages parking spots on a floor |
| `ParkingLot` | Central parking management class |
| `ParkingTicket` | Generates tickets and calculates parking charges |
| `EntryGate` | Handles vehicle entry |
| `ExitGate` | Handles vehicle exit |
| `ParkingObserver` | Defines parking availability notifications |
| `ParkingDisplayBoard` | Displays parking availability |
| `ParkingStrategy` | Defines parking allocation strategy |
| `firstAvailableParkingStrategy` | Allocates the first available compatible spot |
| `PricingStartegy` | Defines the parking pricing strategy |
| `NormalPricingStrategy` | Calculates normal parking charges |
| `WeekendPricingStrategy` | Calculates weekend parking charges |
| `PaymentStrategy` | Defines payment processing |
| `CashPayment` | Simulates cash payment |
| `UPIPayment` | Simulates UPI payment |
| `CardPayment` | Simulates card payment |
| `ParkEngine` | Main class containing the console menu |

---

## 💡 OOP Concepts Used

### 1. Encapsulation
Data members are managed through classes and their methods.

### 2. Inheritance
Bike, Car, and Truck inherit from the abstract Vehicle class.

### 3. Polymorphism
Different vehicle, pricing, parking, and payment implementations can be used through common parent types or interfaces.

### 4. Abstraction
Abstract classes and interfaces define common operations while hiding implementation details.

---

## 🎯 Design Patterns Implemented

### 1. Singleton Pattern
Used in the `ParkingLot` class to maintain a single central parking management instance.

### 2. Factory Pattern
Used in `VehicleFactory` to create vehicle objects based on vehicle type.

### 3. Observer Pattern
Used to notify parking display boards whenever parking availability changes.

### 4. Strategy Pattern
Used to provide interchangeable implementations for:

- Parking allocation
- Parking pricing
- Payment processing

---

## 💰 Parking Pricing

The system supports two pricing strategies.

### Normal Pricing

| Vehicle | Price Per Hour |
|---|---:|
| Bike | ₹20 |
| Car | ₹50 |
| Truck | ₹100 |

### Weekend Pricing

| Vehicle | Price Per Hour |
|---|---:|
| Bike | ₹40 |
| Car | ₹100 |
| Truck | ₹200 |

The applicable strategy determines the parking charges.

---

## 🔄 Project Workflow

### Vehicle Entry

1. User selects the vehicle type.
2. User enters the vehicle number.
3. VehicleFactory creates the vehicle object.
4. ParkingLot checks for an existing active ticket.
5. ParkingStrategy searches for a compatible available spot.
6. The vehicle is assigned a parking spot.
7. A parking ticket is generated.
8. Parking availability is updated.

### Vehicle Exit

1. User provides the parking ticket details.
2. The system retrieves the active parking ticket.
3. Parking duration is calculated.
4. Parking charges are generated.
5. User selects a payment method.
6. Payment is simulated.
7. The parking ticket is closed.
8. The parking spot becomes available.
9. Parking display boards are updated.

---

## ⚙️ Sample Parking Configuration

The initial application configuration creates two parking floors.

Each floor contains:

- 2 Bike Parking Spots
- 2 Car Parking Spots
- 2 Truck Parking Spots

**Total Parking Capacity:**

| Parking Type | Total Spots |
|---|---:|
| Bike | 4 |
| Car | 4 |
| Truck | 4 |
| **Total** | **12** |

---

## 🚀 Future Enhancements

- Database integration using MySQL.
- Graphical User Interface (GUI).
- Web-based parking management system.
- Real payment gateway integration.
- Parking history and transaction reports.
- Admin dashboard.
- Improved exception handling.
- Automated unit testing.
- Concurrent parking-space allocation.

---

## 🎓 Learning Outcomes

Through this project, I gained practical experience in:

- Java programming and OOP concepts.
- Abstract classes and interfaces.
- Inheritance and polymorphism.
- Java Collections Framework.
- Factory, Singleton, Observer, and Strategy design patterns.
- Real-world object-oriented system design.
- Managing parking operations and business logic.

---

## 👨‍💻 Author

**Mayur Ahire**

- GitHub: [mayurahire1](https://github.com/mayurahire1)
- LinkedIn: [Mayur Ahire](https://www.linkedin.com/in/mayur-ahire1/)

---

**Project Name:** Parking Lot Automation System  
**Project Type:** Console-Based Java Application  
**Programming Language:** Java  
**Domain:** Parking Management System
