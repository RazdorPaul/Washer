package main.java.my.washer.core;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class CoreMain {
    private Car car = new Car();
    private Client client = new Client();
    private Order order = new Order();


}