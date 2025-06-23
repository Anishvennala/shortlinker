# ShortLinker

**ShortLinker** is a TinyURL-like web application built using Java and Spring Boot. It allows users to shorten long URLs into compact, shareable links and redirect users from the short link to the original URL.

---

## Table of Contents

* [Features](#features)
* [Tech Stack](#tech-stack)
* [Architecture](#architecture)
* [End-to-End Workflow](#end-to-end-workflow)
* [Setup Instructions](#setup-instructions)
* [API Endpoints](#api-endpoints)
* [Testing](#testing)
* [Key Learning Outcomes](#-key-learning-outcomes)
* [Future Improvements](#-future-improvements)

---

## Features

* Shorten long URLs into short, easy-to-share links.
* Redirect from short link to original URL.
* Caching: Efficient in-memory cache to speed up URL lookups for recently accessed short codes.
* Collision Handling: Handles hash collisions by modifying original URL and re-hashing until unique code generated.
---

## Tech Stack

* Java 21
* Spring Boot 3.5
* Spring Data JPA (for database interaction)
* MySQL (for persistent URL mappings)
* Thymeleaf (front-end rendering)
* JUnit 5 (for unit testing)
* Mockito (for mocking dependencies in tests)
* Maven (Build & dependency management)

---

## Architecture

```
[User]
   ↓
[Controller] → [Service Layer] → [Repository] → [Model (@Entity)] → [MySQL Database]
```

* **Controller**: Handles HTTP requests and responses
* **Service**: Contains business logic (e.g., generate short codes)
* **Repository**: Interfaces with MySQL using JPA
* **Model**: Defines structure of table in DB 

---

## End-to-End Workflow

1. **User Interface (Frontend)**
   * User opens the homepage (http://localhost:8080) rendered using Thymeleaf.
   * A form is shown for the user to paste a long URL and submit by clicking a "Shorten" button.
2. **Controller Layer (Spring Boot)**
   * The form sends a **POST** request to endpoint /shorten.
   * The URLShortenerController receives the post request and forwards the long URL to the **service layer**.
3. **Service Layer (Application Logic)** 
   * Validation: Checks if the input URL is valid.
   * Duplicate Check: Searches the database to see if the URL already exists and has a short code. 
   * Short Code Generation: If it’s a new URL, a unique short code (e.g., abc123) is generated using a hash or random alphanumeric logic.
   * Persistence: The mapping of the original URL and the short code is saved in the MySQL database via the repository.
4. **Repository Layer (Data Access)**
   * This layer uses Spring Data JPA to interact with MySQL DB.
   * Stores original URL, short code and creation date.
5. **Response to User**
   * Service returns the short URL (e.g., http://localhost:8080/abc123) to the controller.
   * The controller sends this to the frontend and renders to UI for the user to copy, use it or visit link.
6. **Redirection Workflow**
   * When short URL is clicked, the app performs GET request to /{shortCode}.
   * Controller receives short code and asks service to find matching long URL. 
   * If it's found, controller sends HTTP redirect respond (302 Found) to long URL.
   * User's browser is redirected to the actual site of short URL.
7. **Testing**
    * Logic is tested using JUnit 5 and Mockito.
    * Tests verify:
      * Correct short code generation
      * Proper redirection behaviour 
      * Handling of invalid or duplicate URLs

---

## Setup Instructions

1. **Clone the repository**

   ```bash
   git clone https://github.com/Anishvennala/shortlinker.git
   cd shortlinker
   ```
2. **Create database named "shortlinker" in MySQL**
```sql
   CREATE DATABASE shortlinker;
```
* Edit /resources/application.properties:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/shortlinker
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build and run the project**
* Use Maven to build and run application:
   ```bash                               
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. **Open in your browser**

   ```
   http://localhost:8080
   ```

---

## API Endpoints

### `POST /shorten`

* **Description**: Shortens a long URL
* **Request Body**:

  ```json
  {
    "originalUrl": "https://example.com"
  }
  ```
* **Response**:

  ```json
  {
    "shortUrl": "http://localhost:8080/abc123"
  }
  ```

### `GET /{shortCode}`

* **Description**: Redirects to the original URL
* **Example**: `/abc123` → redirects to the stored URL

---

## Testing

* Unit tests written using **JUnit 5** and **Mockito**
* Run all tests using:

  ```bash
  ./mvnw test
  ```
---

## 🎓 Key Learning Outcomes


### Use of `Optional<T>` in Java
* Learned to use `Optional<T>` to safely handle potentially null return values from database queries.
* Helped avoid `NullPointerException` and encouraged cleaner conditional logic (e.g., `ifPresent`, `orElseGet`).

### Understanding Model–Repository–Service Architecture
* Designed an `@Entity` model class (`UrlMapping`) that maps to a MySQL table storing short codes and original
  URLs.
* Used `JpaRepository` in `UrlMappingRepository` to leverage built-in CRUD operations and create custom query
  methods (`findByOriginalUrl`, `findByShortCode`) with minimal code.
* Integrated the repository into a service layer (`URLShortener`) for business logic, making the codebase modular
  and scalable.

### Spring Boot and MySQL Integration
* Connected the Spring Boot app to a local MySQL instance.
* Configured `spring.datasource.*` and Hibernate settings to auto-create tables and show SQL logs for debugging.

### Testing with Mockito and JUnit 5
* Wrote unit tests using JUnit 5 and Mockito to mock the repository and validate the `shortenURL` logic.
* Ensured database interaction logic worked correctly without hitting a real database.

### Environment Variable Management in Spring Boot
* Used the `export` command to set `DB_USERNAME` and `DB_PASSWORD` as environment variables.
* Accessed them in `application.properties` using `${DB_USERNAME}` and `${DB_PASSWORD}` for secure and flexible
  database configuration.

### Running and Debugging a Spring Boot App Locally
* Learned to start the application locally on `localhost:8080`.
* Inspected errors like DB connection failure and verified DB status via the MySQL CLI.

---

## 🌱 Future Improvements

* Add user accounts and history of shortened links
* Track analytics (e.g., click count, short URL visits)
* Expiration system for links
* Deploy project to cloud platform

---

## Contact

Built by [Anish Vennala](https://github.com/Anishvennala)  
📫 [LinkedIn](https://www.linkedin.com/in/anish-vennala) – Let’s connect!

---

## License

This project is open-source and available under the [MIT License](LICENSE).
