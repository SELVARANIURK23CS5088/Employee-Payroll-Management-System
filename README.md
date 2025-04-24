🔍 Overview of Employee Payroll Management System
The Employee Payroll Management System is a desktop-based application developed in Java using Swing for the graphical user interface and MySQL for backend data storage. This system is designed to simplify and automate the payroll process within an organization. It allows authorized users to log in, register new employees, manage employee records, and calculate monthly salaries based on various parameters such as basic pay, bonuses, and deductions. The application ensures accurate and efficient payroll processing, reducing manual effort and the risk of errors. With a modular structure and centralized database connectivity, the system is easy to maintain and scalable for future enhancements. It provides a user-friendly interface and helps organizations manage payroll operations smoothly and securely.


✅ Technologies Used:
Java SE 22
Swing GUI (Java AWT/Swing for user interface)
MySQL Database
JDBC (Java Database Connectivity)
mysql-connector-j-9.3.0.jar (for MySQL connectivity)


🔑 Key Features:
User Authentication:
Secure login system for authorized access.
Validates credentials using data stored in the MySQL database.

Employee Management:
Add new employee details including name, ID, department, designation, and salary.
store and retrieve employee data from the database.

Payroll Calculation:
Calculate employee salaries based on basic pay, deductions, and other allowances.
Supports features like overtime, bonuses, and tax deductions.

Graphical User Interface (GUI):
Intuitive and user-friendly interface built using Java Swing.
Separate windows (frames) for login, main menu, adding employees, and calculating payroll.

Database Integration:
Connects to MySQL database using JDBC.
All employee and payroll data are stored and managed in the database.

Modular Structure:
Organized into multiple packages (e.g., UI and database) for better maintainability.
Follows Java modular system using module-info.java.

Dashboard Navigation:
Central dashboard (MainFrame) for easy access to all core functionalities.
Provides clear navigation between modules like employee entry and payroll computation.

Reusable DB Connection:
Centralized DBConnection class for efficient and secure database connectivity.
Avoids redundant code by reusing the same connection logic across the application.

Scalability:
Easily extendable to include more features like payslip generation, leave management, or attendance tracking.

