package main.java.my.washer.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.my.washer.core.CoreMain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class newOrderWindowController {

    @FXML
    private Label orderTitleLabel;

    @FXML
    private Button clientButton;

    @FXML
    private Button carButton;

    @FXML
    private Button servicesButton;

    @FXML
    private Button dateTimeButton;

    @FXML
    private Button paymentButton;

    @FXML
    private Button createRecordButton;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    private CoreMain coreMain;
    private String orderDate;

    @FXML
    private void initialize() {
        coreMain = new CoreMain();

        // Установка текущей даты в заголовок
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        orderDate = now.format(formatter);
        orderTitleLabel.setText("Заказ от " + orderDate);
    }

    private boolean showConfirmation(String title, String content, String header) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType yesButton = new ButtonType("Да");
        ButtonType noButton = new ButtonType("Нет");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yesButton;
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void clientButton() {
        if (showConfirmation("Выбор клиента", "Выбрать клиента?", "Подтверждение")) {
            // Открыть окно выбора/добавления клиента
            showInfo("Открывается окно выбора клиента");
        }
    }

    @FXML
    private void carButton() {
        if (showConfirmation("Выбор автомобиля", "Выбрать автомобиль?", "Подтверждение")) {
            // Открыть окно выбора автомобиля
            showInfo("Открывается окно выбора автомобиля");
        }
    }

    @FXML
    private void servicesButton() {
        System.out.println("Нажата кнопка: Добавление услуг");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/java/my/washer/fxml/serviceAddingWindow.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Предварительная запись");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Ошибка открытия окна записи");
        }
    }

    @FXML
    private void dateTimeButton() {
        if (showConfirmation("Выбор даты и времени", "Выбрать дату и время записи?", "Подтверждение")) {
            // Открыть окно выбора даты и времени
            showInfo("Открывается окно выбора даты и времени");
        }
    }

    @FXML
    private void paymentButton() {
        if (showConfirmation("Выбор оплаты", "Выбрать способ оплаты?", "Подтверждение")) {
            // Открыть окно выбора оплаты
            showInfo("Открывается окно выбора способа оплаты");
        }
    }

    @FXML
    private void createRecordButton() {
        if (showConfirmation("Создание записи", "Все данные проверены?", "Создать новую запись?")) {
            // Логика создания записи
            boolean success = coreMain.createOrder();
            if (success) {
                showInfo("Запись успешно создана!");
                closeWindow();
            } else {
                showError("Ошибка при создании записи");
            }
        }
    @FXML
    private void editButton() {
        if (showConfirmation("Редактирование", "Внести изменения в запись?", "Редактирование")) {
            // Логика редактирования
            showInfo("Режим редактирования");
        }
    }

    @FXML
    private void cancelButton() {
        if (showConfirmation("Отмена", "Отменить создание записи?", "Подтверждение")) {
            closeWindow();
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    // Метод для передачи данных из другого окна
    public void setOrderData(String clientName, String carModel, String services) {
        // Обновление заголовка или других элементов при необходимости
        System.out.println("Получены данные: " + clientName + ", " + carModel + ", " + services);
    }
}