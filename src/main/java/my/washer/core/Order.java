package main.java.my.washer.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Order {
    private Client clientOrder;
    private List<Car> carsOrder;
    private List<String> servicesOrder;
    private String timeRecordOrder;
    private Integer priceOrder;
    private String typePaymentOrder;
    private String workerOrder;
    private String workStartedTimeOrder;
    private String workFinishedTimeOrder;
}