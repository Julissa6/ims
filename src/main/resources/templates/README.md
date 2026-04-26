Inventory Management System (IMS)

 1.Project Name
Inventory Management System (IMS)

2.Description:
This is a simple **Spring Boot + Thymeleaf + MySQL** based Inventory Management System used to manage devices, employees, and assignments.  

It allows users to:
- Add new devices
- View all devices
- Update device information
- Delete devices
- Assign devices to employees
- Track device status (Available / Assigned)


3.Technologies Used: 
- Java 17
- Spring Boot 2.7.18
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- HTML / CSS
- Maven



..Database Configuration (application.properties)

properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

