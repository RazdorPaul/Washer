package my.washer.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class MainWindowController {

    @FXML
    private Button accountButton;

    @FXML
    private Button todayOrdersButton;

    @FXML
    private Button preRecordButton;

    @FXML
    private Button recordsListButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button permanentClientsButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button exitButton;

    @FXML
    public void initialize() {
        System.out.println("Главное окно загружено");
    }

    @FXML
    private void accountButton() {
        System.out.println("Нажата кнопка: Учетная запись");
        showInfo("Открывается окно учетной записи");
    }

    @FXML
    private void todayOrdersButton() {
        System.out.println("Нажата кнопка: Заказ");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/my/washer/fxml/orderWindow.fxml"));
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
    private void preRecordButton() {
        System.out.println("Нажата кнопка: Предварительная запись");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/my/washer/fxml/newOrderWindow.fxml"));
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
    private void recordsListButton() {
        System.out.println("Нажата кнопка: Список броней");
        showInfo("Открывается список броней");
    }

    @FXML
    private void historyButton() {
        System.out.println("Нажата кнопка: История");
        showInfo("Открывается история заказов");
    }

    @FXML
    private void permanentClientsButton() {
        System.out.println("Нажата кнопка: Постоянные клиенты");
        showInfo("Открывается окно постоянных клиентов");
    }

    @FXML
    private void reportsButton() {
        System.out.println("Нажата кнопка: Отчеты");
        showInfo("Открывается окно отчетов");
    }

    @FXML
    private void exitButton() {
        if (showConfirmation("Выход", "Вы действительно хотите выйти?")) {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }
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

    private boolean showConfirmation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        ButtonType yesButton = new ButtonType("Да");
        ButtonType noButton = new ButtonType("Нет");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yesButton;
    }
}