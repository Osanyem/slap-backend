
# Slap - Learning Management System (LMS)

## Description

This project is a Learning Management System (LMS) built using Java, Spring Boot, SQL, and PostgreSQL. It provides a robust backend for managing courses, users, and roles with a secure and scalable architecture. The system serves more than 15 REST endpoints across 10 distinct resources to support functionalities like course creation, enrollment, and user management.

### Key Features

-   **REST API**: Over 10 resources for seamless interaction with the system.
    
-   **Fast Response Times**: Optimized to achieve response times under 100ms.
    
-   **Role-Based Access Control (RBAC)**: Secure user authentication with three distinct privilege levels:
    
    -   **Admin**: Manage users, roles, and courses.
        
    -   **Instructor**: Create and manage courses.
        
    -   **Student**: Enroll in and access courses.
        
-   **Optimized Database**: Designed with 10 related entities using Spring Data JPA, featuring custom queries for enhanced fetch performance.
    

## Why?


The LMS addresses the need for a reliable and secure backend for educational platforms. It simplifies course and user management while maintaining high performance and scalability. Plus, it was the perfect excuse to dive into Spring Boot while tackling a term project — killing two birds with one stone! Key challenges solved include:

-   Implementing secure authentication and authorization.
    
-   Designing an efficient database schema.
    
-   Providing a fast and user-friendly API for client applications.
    

## Quick Start

### Prerequisites


Ensure the following tools are installed on your system:

-   **Java Development Kit (JDK) 17 or later**
-   **Maven**
-   **Docker**: Command-line Docker is recommended, but Docker Desktop can also be used.
-   **Git**
    



### Setup and Run

Follow these steps to set up and run the project:

1.  **Clone the repository:**
    
    ```bash
    git clone git@github.com:Osanyem/slap-backend.git
    cd slap-backend
    ```

2.  **Start the database using Docker Compose:**
    
    ```bash
    docker-compose up -d
    ```

3.  **Build the project:**
    
    ```bash
    ./mvnw clean install
    ```

4.  **Run the application:**
    
    ```bash
    ./mvnw spring-boot:run
    ```

5.  **Access the application:** Open your browser and navigate to [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html) to view the documentation .


    

## Usage

### Configuration

The project uses the `application.properties` file for configuration. Common settings include:

-   **Database:** Configure your database connection with:
    
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/slap
    spring.datasource.username=your_username # update this to use your postgres username
    spring.datasource.password=your_password # update this to use your database password defined in docker-compose.yml
    ```
    

    

### Endpoints

Sample endpoints include:    
-   `GET /api/courses`: Fetch all available courses.
    
-   `POST /api/courses`: Create a new course (admin/instructor only).
    
-   `PUT /api/courses/{id}`: Update course details (admin/instructor only).
    

### Adding Features

1.  **Add Dependencies:** Update the `pom.xml` file to include new libraries or frameworks.
    
2.  **Extend Functionality:**
    
    -   Define new models in the `domain` package.
        
    -   Implement new endpoints in the `controllers` package.
        
    -   Add custom business logic in the `services` package.
        
    -   Write efficient database queries in the `repositories` package.
        
3.  **Testing:** Write unit and integration tests in the `src/test/java` directory to ensure robustness.
    

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com.gould.slap/
│   │       ├── controllers/       # REST controllers
│   │       ├── services/          # Business logic
│   │       ├── repositories/      # Database interactions
│   │       └── domain/            # Data models
│   └── resources/
│       ├── application.properties # Configuration file
└── test/
    └── java/                      # Unit and integration tests
```

## Contributing

As always, contributions are always welcome! Or feel free to use this project as a starting point for another Spring Boot Project. Here's how you can help:

1.  **Fork the repository:** Click the "Fork" button at the top of this page.
    
2.  **Clone your fork:**
    
    ```bash
    git clone git@github.com:Osanyem/slap-backend.git 
    cd slap-backend
    ```
    
3.  **Create a new branch:**
    
    ```bash
    git checkout -b feature/your-feature-name
    ```
    
4.  **Make your changes:** Implement your feature or bug fix.
    
5.  **Commit your changes:**
    
    ```bash
    git commit -m "Add feature: your-feature-name"
    ```
    
6.  **Push to your branch:**
    
    ```bash
    git push origin feature/your-feature-name
    ```
    
7.  **Open a pull request:**
    
    -   Go to the [original repository](https://github.com/Osanyem/slap-backend) on GitHub.
        
    -   Click "Pull Requests" and then "New Pull Request."
        
    -   Describe your changes and submit the PR.
        

----------

Feel free to reach out via the issue tracker for questions or suggestions!