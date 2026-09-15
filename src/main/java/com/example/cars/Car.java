package com.example.cars;

import java.math.BigDecimal;
import java.util.Objects;

public record Car(String id, String make, String model, int year, BigDecimal price,
                  String fuelType, String transmission, int mileage, String color, String imageUrl) {
    public Car {
        Objects.requireNonNull(id, "id is required");
        Objects.requireNonNull(make, "make is required");
        Objects.requireNonNull(model, "model is required");
        Objects.requireNonNull(price, "price is required");
        if (year < 1886) throw new IllegalArgumentException("year must be 1886 or later");
        if (price.signum() < 0) throw new IllegalArgumentException("price cannot be negative");
        if (mileage < 0) throw new IllegalArgumentException("mileage cannot be negative");
    }
}
