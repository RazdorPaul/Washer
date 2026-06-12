package ru.washer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Motorcycle extends Vehicle {

    // Специфичные поля для мотоцикла
    private Integer engineCapacity; // объем двигателя в куб.см
    private String category; // A, A1, A2 и т.д.

    public Motorcycle(Long clientId, Long modelId) {
        super(clientId, modelId, "MOTORCYCLE");
    }

    public Motorcycle() {
        super();
    }
}