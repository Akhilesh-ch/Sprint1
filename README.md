# EduTrack CRM - Java Oracle Backend System

This is a Java-based backend project that connects to an Oracle SQL database using JDBC. The system manages student admissions, course registrations, application tracking, and support tickets. It includes full CRUD operations using DAO (Data Access Object) classes.

## Technologies Used
- Java (JDK 8+)
- JDBC (Java Database Connectivity)
- Oracle Database (XE) with user "sprint1"
- SQL (DDL + DML)
- Command-line interface (CLI) with "Scanner"
- Java I/O and Collections

## Database Setup
Ensure Oracle Database is running and you have created the "sprint1" user. The tables required are:

  -   Student
  -   Course
  -   Application
  -   SupportTicket

Use the respective "CREATE TABLE" SQL statements to create the schemas before running the program.

### Core Classes
| Class Name      | Description                            |
|-----------------|----------------------------------------|
|  Mainapp.java   | Main class for Inserting into tables   | 
|  Update.java    | Update class for Updating fields       |
|  Deletion.java  | Deletion class for Deleting fields     |
|  Retrive.java   | Retrive class for Retriving the fields |

### Model Classes
These classes contain private fields, constructors, and **getters/setters**.

-  Student.java
-  Course.java  
-  Application.java  
-  SupportTicket.java 

Each class maps directly to a table in Oracle DB and supports encapsulation.

### DAO Classes
DAO classes handle all DB operations (Insert, Update, Delete, Retrieve):

-  StudentDAO.java  
-  CourseDAO.java  
-  ApplicationDAO.java
-  SupportTicketDAO.java

Each class contains (Example methods):
-  insert<Class>()
-  get<Class>ById()
-  getAll<Class>()
-  update<Class>By<Field>()
-  delete<Class>By<Field>()

### Utility Classes
- "DBConnection.java" – handles Oracle DB connection using 'jdbc:oracle:thin:@localhost:1521:XE'.

### Features
- Add, update, delete, and fetch records from Oracle DB.
- Handles invalid date input using strict parsing via "LocalDate".
- Validations and exception handling included.
- Structured menu-driven interface for CRUD operations.
