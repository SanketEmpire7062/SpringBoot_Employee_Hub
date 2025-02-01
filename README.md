A Spring Boot-based portal allowing company employee to register, view employee details, and manage employee records efficiently.

Features - 

* Add new employees
* View employee details
* Update employee information
* Delete employees
* Admin login and dashboard
* Employee login and profile management
* Rest Password

Technologies Used

* Java (Spring Boot, JPA, Hibernate, Spring MVC)
* MySQL (Database)
* Thymeleaf (Front-end templates)
* Maven (Dependency Management)

User Roles and Authentication -

* Admin Login -
  
  * Admins have access to the dashboard where they can:

    1) Manage employees
    2) View detailed reports
    3) Assign roles
    4) Delete Employee
   
  * Employee Login -

    1) View their profile
    2) Update personal details
    3) Delete account

** Database Configuration -

1) Open application.properties
2) Update database credentials (MySQL username & password)

** API Endpoints 



GET                /login               -         open login page

POST               /login               -         Process login request (authentication)

GET                /employeedashboard    -        display employee dashboard after successful login   

POST               /registration         -        create new employee

GET                /admindashboard          -     display admin dashboard after login using admin cridentials

POST               /updateemployee{email}     -   Employee can update there information

DELETE             /delete-data/{email}      -    Employee delete there own account

DELETE             /delete-employee/{email}   -   Admin can delete employee record

POST               /change-role/{email}      -    Admin can change employee role

GET                /forgot-password           -   opens forgot passowrd dashboard

POST               /forgot-password           -   Employees can update their password if they forget it

