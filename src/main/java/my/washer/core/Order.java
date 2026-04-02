package main.java.my.washer.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private Client client;
    private Car car;
    private String timeRecord;
    private String service;
    private String price;
    private String typePayment;
    private String worker;
    private String timeWork;
}