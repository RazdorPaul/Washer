package ru.washer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Car extends Vehicle {

    // Специфичные поля для автомобиля (если появятся)
    private Integer numberOfDoors;
    private String bodyType; // седан, хэтчбек, внедорожник и т.д.

    public Car(Long clientId, Long modelId) {
        super(clientId, modelId, "CAR");
    }

    public Car() {
        super();
    }
}