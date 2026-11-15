# Employee Management: Java 3-Tier App

| Tier | Tech | Code |
|------|------|------|
| Presentation | Thymeleaf templates + CSS | `templates/`, `static/`, `controller/` |
| Application  | Spring Boot services, validation | `service/` |
| Data         | MySQL 8 via Spring Data JPA | `repository/`, `model/`, `db/schema.sql` |

```
Browser -> Spring Boot (controller -> service -> repository) -> MySQL :3306
```

## Option A: everything in Docker (easiest)
```bash
docker compose up --build
```
Open http://localhost:8080

## Option B: MySQL installed on your machine
1. Install MySQL 8 and start it.
2. Create the database and user:
   ```bash
   mysql -u root -p < db/schema.sql
   ```
3. Run the app (needs Java 17 + Maven):
   ```bash
   mvn spring-boot:run
   ```
   Open http://localhost:8080

## Option C: MySQL in Docker, app on your machine
```bash
docker compose up -d mysql
mvn spring-boot:run
```

## Check the data tier
```bash
docker compose exec mysql mysql -uempuser -pemppass empdb -e "SELECT * FROM employees;"
```

## Config
DB settings come from env vars `DB_URL`, `DB_USER`, `DB_PASSWORD`
(defaults in `application.properties`).

## Features
Add, edit, delete, search employees; validation; duplicate email check; sample data seeded on first start; `/actuator/health`.
