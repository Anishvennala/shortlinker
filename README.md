# ShortLinker

**ShortLinker** is a TinyURL-like web application built using Java and Spring Boot. It allows users to shorten long URLs into compact, shareable links and redirect users from the short link to the original URL.

---

## Table of Contents

* [Features](#features)
* [Tech Stack](#tech-stack)
* [Architecture](#architecture)
* [Setup Instructions](#setup-instructions)
* [Usage](#usage)
* [API Endpoints](#api-endpoints)
* [Screenshots](#screenshots)
* [Testing](#testing)
* [Challenges & Learnings](#challenges--learnings)
* [Future Improvements](#future-improvements)

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
