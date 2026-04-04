package my.washer.controllers;

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
    private void initialize() {}

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
    }

    @FXML
    private void carButton() {
    }

    @FXML
    private void servicesButton() {
        System.out.println("Нажата кнопка: Заказ");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/my/washer/fxml/serviceAddingWindow.fxml"));
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
    }

    @FXML
    private void paymentButton() {
    }

    @FXML
    private void createRecordButton() {
    }
    @FXML
    private void editButton() {
    }

    @FXML
    private void cancelButton() {
    }

    private void closeWindow() {
    }
}