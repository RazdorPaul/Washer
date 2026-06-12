package ru.washer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public abstract class Vehicle {
    @ToString.Exclude
    private Long id;

    @ToString.Exclude
    private Long clientId; // ссылка на владельца

    private Long modelId; // ссылка на модель из справочника
    private String type; // тип транспорта
    private String licensePlate;
    private String vin;
    private Integer year;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Конструктор для создания нового транспортного средства
    public Vehicle(Long clientId, Long modelId, String type) {
        this.clientId = clientId;
        this.modelId = modelId;
        this.type = type;
    }

    // Пустой конструктор для маппинга из БД
    public Vehicle() {
    }
}