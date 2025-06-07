Task Manager App

A simple Task Manager desktop application built with Java 11+, JavaFX, and a layered (n‑tier) architecture. Tasks are persisted in a plain text file (tasks.txt), making this ideal for university projects or small demos without a real database.

Features

Add, Remove, and Toggle Completion on tasks

Persistent storage to tasks.txt (one task per line: id|completed|description)

JavaFX-based GUI with a responsive list view and controls

Clean separation of concerns using:

Model: Task domain class

Repository: TaskRepository interface + TextFileTaskRepository implementation

Service: TaskService interface + TaskServiceImpl

UI: JavaFX MainApp in com.example.taskmanager.ui package

Prerequisites

JDK 11 (or newer) installed and on your PATH

JavaFX SDK (e.g. 20.0.2) downloaded

PowerShell (for Windows script) or any terminal for manual commands
