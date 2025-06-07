# 🔗 Shortlinker – A Simple URL Shortener

Shortlinker is a minimal URL shortener service built with **Spring Boot** and **MySQL**. 
It allows users to shorten long URLs into compact, shareable short codes, and retrieve the original URLs using those short codes. 
The project uses a basic in-memory cache for faster lookups and ensures unique short codes using hashing.

---

## 🚀 Features

- ✅ Shorten any long URL into a unique short code
- ✅ URL shortening using MD5 hashing
- ✅ Retrieve original URLs using short codes
- ✅ Persistent storage using MySQL
- ✅ In-memory caching (LRU-like) for faster access
- ✅ Collision handling using incremental hashing

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **Hashing with MD5**

---

## 📦 Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/Anishvennala/shortlinker.git
cd shortlinker

