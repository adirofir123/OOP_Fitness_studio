# OOP Fitness Studio

🏋️‍♀️ A fully object-oriented gym management system implemented in Java, based on a university OOP assignment.

## Overview

This program simulates the operations of a fitness studio using principles of Object-Oriented Programming (OOP). It supports managing customers, trainers, workout classes, and the studio secretary — who serves as the sole interface for interacting with the system.

## Key Features

- 👩‍💼 **Secretary-controlled system**: All actions (registrations, class creation, messages, etc.) are done through the secretary.
- 👥 **Customer Management**: Add, remove, and track customers based on age, gender, and budget.
- 🧑‍🏫 **Trainer Management**: Recruit trainers with custom pay-per-hour rates and skillsets.
- 📆 **Class Management**: Schedule fitness classes by type, time, audience, and trainer authorization.
- 📝 **Class Enrollment**: Validate customer eligibility and balance before enrolling.
- 💬 **Messaging System**: Send targeted messages to individuals or class attendees.
- 🕘 **Action History**: All actions are logged and stored as output entries.
- 🧠 **Design Patterns Used**:
  - Singleton
  - Factory
  - Observer

## Technologies

- Java
- OOP principles (encapsulation, inheritance, interfaces, polymorphism)
- SOLID design
- Design patterns (3 required)

## How to Run

1. Ensure you have Java installed.
2. Place `main.java`, `output.txt`, and all project files in the same folder.
3. Compile and run `main.java`.

```bash
javac main.java
java main
