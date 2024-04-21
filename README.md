Spring Boot MVC and REST API Project
Overview
This project demonstrates the implementation of a Spring Boot application that combines both MVC (Model-View-Controller) and REST API functionalities. The application interacts with a remote MySQL database and includes components such as entities, repositories, services, API controllers, and HTML views.

Features
Spring Boot: Utilizes the Spring Boot framework for easy setup and configuration.
MVC Architecture: Implements the MVC design pattern for handling web requests and responses.
RESTful API: Provides a set of RESTful endpoints for interacting with the application programmatically.
MySQL Database: Connects to a remote MySQL database to store and retrieve data.
Entity Classes: Defines entity classes to represent data objects in the application domain.
Repository Layer: Utilizes Spring Data JPA repositories for database operations.
Service Layer: Implements service classes to encapsulate business logic and interact with repositories.
API Controllers: Defines controllers for handling REST API requests and responses.
HTML Views: Provides HTML views for rendering user interfaces in web browsers.
Installation
Clone the repository to your local machine:
bash
Copy code
git clone https://github.com/your-username/your-repository.git
Open the project in your preferred IDE (Integrated Development Environment), such as IntelliJ IDEA or Eclipse.
Configure the application properties (application.properties or application.yml) to specify the MySQL database connection details.
Run the application:
arduino
Copy code
./mvnw spring-boot:run
or
arduino
Copy code
mvn spring-boot:run
Access the application using the provided URLs for MVC views and REST API endpoints.
Usage
MVC Views: Navigate to the HTML views using a web browser to interact with the application's user interface.
REST API Endpoints: Send HTTP requests to the API endpoints using tools like cURL or Postman to interact with the application programmatically.
Technologies Used
Java
Spring Boot
Spring MVC
Spring Data JPA
MySQL
HTML
RESTful API
Credits
This project was created by Mohammad Sarwar. Feel free to contribute or provide feedback!
