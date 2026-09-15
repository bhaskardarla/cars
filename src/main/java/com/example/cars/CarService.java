package com.example.cars;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class CarService {
    private final List<Car> cars = new ArrayList<>();

    public void add(Car car) {
        if (cars.stream().anyMatch(existing -> existing.id().equalsIgnoreCase(car.id()))) {
            throw new IllegalArgumentException("A car with this ID already exists.");
        }
        cars.add(car);
    }

    public List<Car> all() {
        return cars.stream().sorted(Comparator.comparing(Car::id)).toList();
    }

    public List<Car> search(String query) {
        String normalized = query.toLowerCase(Locale.ROOT);
        return cars.stream()
                .filter(car -> car.make().toLowerCase(Locale.ROOT).contains(normalized)
                        || car.model().toLowerCase(Locale.ROOT).contains(normalized))
                .toList();
    }

    public BigDecimal totalValue() {
        return cars.stream().map(Car::price).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
