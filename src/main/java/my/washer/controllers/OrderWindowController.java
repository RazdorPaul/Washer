package main.java.my.washer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.my.washer.core.CoreMain;

import java.util.Optional;

public class OrderWindowController {

    @FXML
    private Button clientButton;
    @FXML
    private Button carButton;
    @FXML
    private Button timeOfRecordButton;
    @FXML
    private Button priceButton;
    @FXML
    private Button typeOfPayButton;
    @FXML
    private Button workerButton;
    @FXML
    private Button timeOfWorkButton;
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
    @FXML
    private Button servicesButton;

    private CoreMain coreMain;

    @FXML
    private void initialize() {
        coreMain = new CoreMain();
        setupTextFieldHandlers();
        // инициализация не требуется
    }

    private boolean showConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        String confirmationTitleString = "Подтверждение";
        alert.setTitle(confirmationTitleString);
        alert.setHeaderText(null);
        String confirmationString = "Подтвердить?";
        alert.setContentText(confirmationString);

        ButtonType yesButton = new ButtonType("Да");
        ButtonType noButton = new ButtonType("Нет");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yesButton;
    }

    @FXML
    private void clientButton() {
        if (showConfirmation()) {

            // логика выбора клиента
        }
    }

    @FXML
    private void carButton() {
        if (showConfirmation()) {
            // логика выбора автомобиля
        }
    }

    @FXML
    private void timeOfRecordButton() {
        if (showConfirmation()) {
            // логика выбора времени
        }
    }
    @FXML
    private void servicesButton() {
        if (showConfirmation()) {
        }
    }

    @FXML
    private void priceButton() {
        if (showConfirmation()) {
            // логика расчёта стоимости
        }
    }

    @FXML
    private void typeOfPayButton() {
        if (showConfirmation()) {
            // логика выбора вида оплаты
        }
    }

    @FXML
    private void workerButton() {
        if (showConfirmation()) {
            // логика выбора работника
        }
    }

    @FXML
    private void timeOfWorkButton() {
        if (showConfirmation()) {
            // логика расчёта времени работ
        }
    }

    @FXML
    private void saveButton() {
        if (showConfirmation()) {

        }
    }

    @FXML
    private void editButton() {
        if (showConfirmation()) {
            // логика редактирования
        }
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