package main.java.my.washer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.my.washer.core.CoreMain;
import main.java.my.washer.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderWindowController {

    @FXML
    private Button saveButton;
    @FXML
    private Button editButton;
    @FXML
    private Button closeButton;

    @FXML
    private TextField nameField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField timeRecordField;
    @FXML
    private TextField serviceField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField typePaymentField;
    @FXML
    private TextField workerField;
    @FXML
    private TextField workTimeField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField carNumberField;

    private CoreMain coreMain;
    private Map<String, String> fields;

    @FXML
    private void initialize() {
        coreMain = new CoreMain();
        setupTextFieldHandlers();
        fields = Map.ofEntries(
                 Map.entry("nameField", nameField.getText()),
                 Map.entry("brandField", brandField.getText()),
                 Map.entry("modelField", modelField.getText()),
                 Map.entry("timeRecordField", timeRecordField.getText()),
                 Map.entry("serviceField", serviceField.getText()),
                 Map.entry("priceField", priceField.getText()),
                 Map.entry("typePaymentField", typePaymentField.getText()),
                 Map.entry("workerField", workerField.getText()),
                 Map.entry("workTimeField", workTimeField.getText()),
                 Map.entry("phoneField", phoneField.getText()),
                 Map.entry("carNumberField", carNumberField.getText())
        );
    }

    private boolean showConfirmation(String title, String confirm, String header) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(confirm);

        ButtonType yesButton = new ButtonType("Да");
        ButtonType noButton = new ButtonType("Нет");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yesButton;
    }



    @FXML
    private void saveButton() {
        if (showConfirmation("запись заказа",
                "Проверьте введенные данные!",
                "Все данные указаны верно?")) {

        }
    }

    @FXML
    private void editButton() {
        System.out.println(StringUtils.timeIntegerToString());
        System.out.println(StringUtils.dateToString() + ", " + StringUtils.timeToString());

    }

    @FXML
    private void closeButton() {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    private void setupTextFieldHandlers() {
        // нужно будет в обработчики добавить проверки содержимого поля
        nameField.setOnAction(event -> {
            nameField.setEditable(false);
            nameField.setDisable(true);
            coreMain.setClientName(nameField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getClientName());
        });
        brandField.setOnAction(event -> {
            brandField.setEditable(false);
            brandField.setDisable(true);
            coreMain.setCarBrand(brandField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getCarBrand());
            });
        modelField.setOnAction(event -> {
            modelField.setEditable(false);
            modelField.setDisable(true);
            coreMain.setModel(modelField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getCarModel());
        });
        timeRecordField.setOnAction(event -> {
            timeRecordField.setEditable(false);
            timeRecordField.setDisable(true);
            coreMain.setTimeRecord(timeRecordField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getTimeRecord());
        });
        serviceField.setOnAction(event -> {
            serviceField.setEditable(false);
            serviceField.setDisable(true);
            coreMain.setService(serviceField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getService());
        });
        priceField.setOnAction(event -> {
            priceField.setEditable(false);
            priceField.setDisable(true);
            coreMain.setPrice(priceField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getPrice());
        });
        typePaymentField.setOnAction(event -> {
            typePaymentField.setEditable(false);
            typePaymentField.setDisable(true);
            coreMain.setTypePayment(typePaymentField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getTypePayment());
        });
        workerField.setOnAction(event -> {
            workerField.setEditable(false);
            workerField.setDisable(true);
            coreMain.setWorker(workerField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getWorker());
        });
        workTimeField.setOnAction(event -> {
            workTimeField.setEditable(false);
            workTimeField.setDisable(true);
            coreMain.setWorkTime(workTimeField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getWorkTime());
        });
        phoneField.setOnAction(event -> {
            phoneField.setEditable(false);
            phoneField.setDisable(true);
            coreMain.setPhone(phoneField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getClientPhone());
        });
        carNumberField.setOnAction(event -> {
            carNumberField.setEditable(false);
            carNumberField.setDisable(true);
            coreMain.setCarNumber(carNumberField.getText());
            System.out.println("записанное в буфер значение: " + coreMain.getCarNumber());
        });
    }
    }