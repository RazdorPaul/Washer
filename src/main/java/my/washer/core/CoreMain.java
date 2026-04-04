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

    public void setClientName(String text) {
        client.setName(text);
    }

    public String getClientName() {
        return client.getName();
    }
    
    public void setCarBrand(String brand){
        car.setBrand(brand);
    }

    public String getCarBrand() {
        return car.getBrand();
    }

    public void setModel(String text) {
        car.setModel(text);
    }

    public String getCarModel() {
        return car.getModel();
    }

    public void setTimeRecord(String text) {
        order.setTimeRecord(text);
    }

    public String getTimeRecord() {
        return order.getTimeRecord();
    }

    public void setService(String text) {
        order.setService(text);
    }

    public String getService() {
        return order.getService();
    }

    public void setPrice(String text) {
        order.setPrice(text);
    }

    public String getPrice() {
        return order.getPrice();
    }

    public void setTypePayment(String text) {
        order.setTypePayment(text);
    }

    public String getTypePayment() {
        return order.getTypePayment();
    }

    public void setWorker(String text) {
        order.setWorker(text);
    }

    public String getWorker() {
        return order.getWorker();
    }

    public void setWorkTime(String text) {
        order.setTimeWork(text);
    }

    public String getWorkTime() {
        return order.getTimeWork();
    }

    public void setPhone(String text) {
        client.setPhoneNumber(text);
    }

    public String getClientPhone() {
        return client.getPhoneNumber();
    }

    public void setCarNumber(String text) {
        car.setCarNumber(text);
    }

    public String getCarNumber() {
        return car.getCarNumber();
    }

    public void setDateRecord(String text) {
    }

    public void setMaterialsCost(String text) {
    }

    public boolean saveOrder() {
        return true;
    }

    public String getDateRecord() {
        return "";
    }

    public String getMaterialsCost() {
        return "";
    }

    public boolean createOrder() {
        return true;
    }
}