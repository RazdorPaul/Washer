package main.java.my.washer.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    private String brand;
    private String model;
    private String carNumber;
    private String typeCar;
}