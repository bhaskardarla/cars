package com.example.cars;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarController {
    private final CarService carService = new CarService();

    public CarController() {
        carService.add(new Car("C001", "Toyota", "Camry", 2024, new BigDecimal("35000"), "Petrol", "Automatic", 1200, "Midnight Blue", "/images/showroom-sedan.png"));
        carService.add(new Car("C002", "Honda", "City", 2023, new BigDecimal("18000"), "Petrol", "Manual", 8500, "Pearl White", "/images/showroom-sedan.png"));
    }

    @GetMapping
    public List<Car> all() { return carService.all(); }

    @GetMapping("/search")
    public List<Car> search(String query) { return carService.search(query == null ? "" : query); }

    @GetMapping("/total-value")
    public BigDecimal totalValue() { return carService.totalValue(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car add(@RequestBody Car car) {
        carService.add(car);
        return car;
    }
}
