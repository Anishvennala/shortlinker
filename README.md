# ShortLinker

**ShortLinker** is a TinyURL-like web application built using Java and Spring Boot. It allows users to shorten long URLs into compact, shareable links and redirect users from the short link to the original URL.

---

## Table of Contents

* [Features](#features)
* [Tech Stack](#tech-stack)
* [Architecture](#architecture)
* [End-to-End Workflow](#end-to-end-workflow)
* [Setup Instructions](#setup-instructions)
* [Usage](#usage)
* [API Endpoints](#api-endpoints)
* [Screenshots](#screenshots)
* [Testing](#testing)
* [Challenges & Learnings](#-challenges--learnings)
* [Future Improvements](#-future-improvements)

---

## Features

* Shorten long URLs into short, easy-to-share links
* Redirect from short link to original URL
* Optional support for:

    * Custom aliases
    * URL expiration
    * Analytics

---

## Tech Stack

* Java 21
* Spring Boot 3.5
* Spring Data JPA + MySQL
* Thymeleaf (HTML templating)
* JUnit 5 + Mockito (Testing)
* Maven (Build & dependency management)

---

## Architecture

```
[User]
   ↓
[Controller] → [Service Layer] → [Repository] → [MySQL Database]
```

* **Controller**: Handles HTTP requests and responses
* **Service**: Contains business logic (e.g., generate short codes)
* **Repository**: Interfaces with MySQL using JPA

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
## Setup Instructions

1. **Clone the repository**

   ```bash
   git clone https://github.com/Anishvennala/shortlinker.git
   cd shortlinker
   ```

2. **Configure MySQL Database** Edit `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/shortlinker
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build and run the project**

   ```bash
   mvn spring-boot:run
   ```

4. **Open in your browser**

   ```
   http://localhost:8080
   ```

---

## Usage

* Enter a long URL in the form on the homepage
* Click the "Shorten" button
* Receive a shortened URL like `http://localhost:8080/abc123`
* Accessing the short link redirects you to the original long URL

---

## API Endpoints

### `POST /shorten`

* **Description**: Shortens a long URL
* **Request Body**:

  ```json
  {
    "originalUrl": "https://example.com/some/long/path"
  }
  ```
* **Response**:

  ```json
  {
    "shortUrl": "http://localhost:8080/abc123"
  }
  ```

---

### `GET /{shortCode}`

* **Description**: Redirects to the original URL
* **Example**: `/abc123` → redirects to the stored URL

---

## Screenshots

> Add screenshots to this folder: `/screenshots`

```markdown
![Homepage](screenshots/homepage.png)
![Short URL result](screenshots/result.png)
```

---

## Testing

* Unit tests written using **JUnit 5** and **Mockito**
* To run all tests:

  ```bash
  mvn test
  ```

---

## 🧐 Challenges & Learnings

* Implemented unique short code generation
* Learned URL validation and redirection logic
* Practiced building layered architecture with Spring Boot
* Wrote unit tests for services and controllers
* Integrated MySQL using Spring Data JPA

---

## 🌱 Future Improvements

*

---

## Contact

Built by [Anish Vennala](https://github.com/Anishvennala) – feel free to reach out for collaboration or feedback!

---

## License

This project is open-source and available under the [MIT License](LICENSE).
