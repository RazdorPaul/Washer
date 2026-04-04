package main.java.my.washer.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    private String nameClient;
    private String phoneNumberClient;
    private List<Car> carsOfClient;
    private List<Integer> ordersClientList;
    private Integer countRecordsClient;
}