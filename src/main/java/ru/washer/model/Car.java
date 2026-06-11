package ru.washer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Car {
    @ToString.Exclude
    private Long id;

    private String brand;
    private String model;

    public Car (String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
}
