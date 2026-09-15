# Cars Project

A Maven-based Spring Boot web application for managing a small car inventory.

## Run

```powershell
mvn clean package
mvn spring-boot:run
```

Then open `http://localhost:8080` in your browser.

Or run it with Docker:

```powershell
docker build -t cars-project .
docker run --rm -p 8080:8080 cars-project
```

Then open `http://localhost:8080` in your browser.

Features: a responsive showroom web UI, generated car imagery, detailed vehicle cards (fuel, transmission, mileage and colour), add cars, search by make/model, and calculate total inventory value.
