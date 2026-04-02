package main.java.my.washer.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MainWindowController {

    @FXML
    private Button permanentClientsButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button preRecordButton;

    @FXML
    private Button recordsButton;

    @FXML
    private Button finishedRecordsButton;

    @FXML
    private Button onTodayOrdersButton;

    public void initialize() {
        System.out.println("Главное окно загружено");
        // Здесь можно добавить начальную настройку кнопок

    }

    @FXML
    public void permanentClientsButton() {
        System.out.println("Нажата кнопка: постоянные клиенты");
        showInfo("Открывается окно постоянных клиентов");
        // Здесь будет код открытия окна постоянных клиентов
    }

    @FXML
    public void reportsButton() {
        System.out.println("Нажата кнопка: отчеты");
        showInfo("Открывается окно отчетов");
        // Здесь будет код открытия окна отчетов
    }

    @FXML
    public void preRecordButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/java/my/washer/fxml/orderWindow.fxml"));
            Parent root = loader.load();

            // Получение контроллера
            OrderWindowController controller = loader.getController();

            // Передача данных в контроллер
            //controller.setData();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void recordsButton() {
        System.out.println("Нажата кнопка: предварительная запись");
        showInfo("Открывается окно предварительной записи");
        // Здесь будет код открытия окна записи
    }

    @FXML
    public void finishedRecordsButton() {
        System.out.println("Нажата кнопка: список выполненных заказов");
        showInfo("Открывается список выполненных заказов");
        // Здесь будет код открытия списка заказов
    }

    @FXML
    public void onTodayOrdersButton() {
        System.out.println("Нажата кнопка: заказы на сегодня");
        showInfo("Открываются заказы на сегодня");
        // Здесь будет код открытия заказов на сегодня
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}