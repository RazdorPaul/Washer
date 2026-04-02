package main.java.my.washer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.my.washer.core.CoreMain;
import main.java.my.washer.utils.StringUtils;

import java.util.Optional;

public class OrderWindowController {

    // Кнопки
    @FXML
    private Button saveButton;
    @FXML
    private Button editButton;
    @FXML
    private Button closeButton;

    // Текстовые поля
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField carNumberField;
    @FXML
    private TextField timeRecordField;
    @FXML
    private TextField dateRecordField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField discountField;
    @FXML
    private TextField totalPriceField;
    @FXML
    private TextField paymentAmountField;
    @FXML
    private TextField workTimeField;
    @FXML
    private TextField workStartedField;
    @FXML
    private TextField workFinishedField;
    @FXML
    private TextField materialsCostField;

    // Выпадающие списки
    @FXML
    private ComboBox<String> serviceChoiceBox;
    @FXML
    private ComboBox<String> paymentTypeChoiceBox;
    @FXML
    private ComboBox<String> paymentStatusChoiceBox;
    @FXML
    private ComboBox<String> workerChoiceBox;
    @FXML
    private ComboBox<String> recordStatusChoiceBox;

    // Метки
    @FXML
    private Label materialsLabel;

    private CoreMain coreMain;

    @FXML
    private void initialize() {
        coreMain = new CoreMain();
        initializeChoiceBoxes();
        setupTextFieldHandlers();
        setupChoiceBoxHandlers();
        setupPriceCalculation();
    }

    private void initializeChoiceBoxes() {
        // Услуги
        serviceChoiceBox.getItems().addAll(
                "Мойка кузова - 500₽",
                "Химчистка - 2000₽",
                "Полировка - 1500₽",
                "Чернение резины - 300₽",
                "Комплекс - 3500₽"
        );

        // Вид оплаты
        paymentTypeChoiceBox.getItems().addAll(
                "Наличные",
                "Карта",
                "Перевод"
        );

        // Статус оплаты
        paymentStatusChoiceBox.getItems().addAll(
                "Не оплачено",
                "Частично оплачено",
                "Оплачено"
        );

        // Работники
        workerChoiceBox.getItems().addAll(
                "Иванов И.И.",
                "Петров П.П.",
                "Сидоров С.С."
        );

        // Статус записи
        recordStatusChoiceBox.getItems().addAll(
                "Новая",
                "Подтверждена",
                "Выполнена",
                "Отменена"
        );
    }

    private void setupChoiceBoxHandlers() {
        serviceChoiceBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                coreMain.setService(newVal);
                autoCalculatePrice();
            }
        });

        paymentTypeChoiceBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                coreMain.setTypePayment(newVal);
            }
        });

        paymentStatusChoiceBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                // обработка статуса оплаты
            }
        });

        workerChoiceBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                coreMain.setWorker(newVal);
            }
        });

        recordStatusChoiceBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                // обработка статуса записи
            }
        });
    }

    private void setupPriceCalculation() {
        discountField.textProperty().addListener((obs, oldVal, newVal) -> {
            calculateTotalPrice();
        });

        priceField.textProperty().addListener((obs, oldVal, newVal) -> {
            calculateTotalPrice();
        });
    }

    private void autoCalculatePrice() {
        String selectedService = serviceChoiceBox.getValue();
        if (selectedService != null) {
            // Извлечение цены из строки
            String[] parts = selectedService.split(" - ");
            if (parts.length == 2) {
                String priceStr = parts[1].replace("₽", "");
                priceField.setText(priceStr);
            }
        }
    }

    private void calculateTotalPrice() {
        try {
            double price = 0.0;
            double discount = 0.0;

            if (!priceField.getText().isEmpty()) {
                price = Double.parseDouble(priceField.getText());
            }
            if (!discountField.getText().isEmpty()) {
                discount = Double.parseDouble(discountField.getText());
            }

            double total = price * (1 - discount / 100);
            totalPriceField.setText(String.format("%.2f", total));
        } catch (NumberFormatException e) {
            totalPriceField.setText("0");
        }
    }

    private void setupTextFieldHandlers() {
        nameField.setOnAction(event -> {
            coreMain.setClientName(nameField.getText());
            System.out.println("Клиент: " + coreMain.getClientName());
        });

        phoneField.setOnAction(event -> {
            coreMain.setPhone(phoneField.getText());
            System.out.println("Телефон: " + coreMain.getClientPhone());
        });

        brandField.setOnAction(event -> {
            coreMain.setCarBrand(brandField.getText());
            System.out.println("Марка: " + coreMain.getCarBrand());
        });

        modelField.setOnAction(event -> {
            coreMain.setModel(modelField.getText());
            System.out.println("Модель: " + coreMain.getCarModel());
        });

        carNumberField.setOnAction(event -> {
            coreMain.setCarNumber(carNumberField.getText());
            System.out.println("Госномер: " + coreMain.getCarNumber());
        });

        timeRecordField.setOnAction(event -> {
            coreMain.setTimeRecord(timeRecordField.getText());
            System.out.println("Время: " + coreMain.getTimeRecord());
        });

        dateRecordField.setOnAction(event -> {
            coreMain.setDateRecord(dateRecordField.getText());
            System.out.println("Дата: " + coreMain.getDateRecord());
        });

        priceField.setOnAction(event -> {
            coreMain.setPrice(priceField.getText());
            System.out.println("Цена: " + coreMain.getPrice());
        });

        workTimeField.setOnAction(event -> {
            coreMain.setWorkTime(workTimeField.getText());
            System.out.println("Время работ: " + coreMain.getWorkTime());
        });

        materialsCostField.setOnAction(event -> {
            coreMain.setMaterialsCost(materialsCostField.getText());
            System.out.println("Расходники: " + coreMain.getMaterialsCost());
        });
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
    private void saveButton() {
        if (showConfirmation("Запись заказа", "Проверьте введенные данные!", "Все данные указаны верно?")) {
            // Сбор всех данных
            coreMain.setClientName(nameField.getText());
            coreMain.setPhone(phoneField.getText());
            coreMain.setCarBrand(brandField.getText());
            coreMain.setModel(modelField.getText());
            coreMain.setCarNumber(carNumberField.getText());
            coreMain.setTimeRecord(timeRecordField.getText());
            coreMain.setDateRecord(dateRecordField.getText());
            coreMain.setService(serviceChoiceBox.getValue());
            coreMain.setPrice(totalPriceField.getText());
            coreMain.setTypePayment(paymentTypeChoiceBox.getValue());
            coreMain.setWorker(workerChoiceBox.getValue());
            coreMain.setWorkTime(workTimeField.getText());
            coreMain.setMaterialsCost(materialsCostField.getText());

            // Сохранение заказа
            boolean saved = coreMain.saveOrder();
            if (saved) {
                showInfo("Заказ успешно сохранен!");
            } else {
                showError("Ошибка при сохранении заказа");
            }
        }
    }

    @FXML
    private void editButton() {
        if (showConfirmation("Редактирование", "Внести изменения?", "Редактировать заказ?")) {
            nameField.setEditable(true);
            nameField.setDisable(false);
            phoneField.setEditable(true);
            phoneField.setDisable(false);
            brandField.setEditable(true);
            brandField.setDisable(false);
            modelField.setEditable(true);
            modelField.setDisable(false);
            carNumberField.setEditable(true);
            carNumberField.setDisable(false);
            timeRecordField.setEditable(true);
            timeRecordField.setDisable(false);
            dateRecordField.setEditable(true);
            dateRecordField.setDisable(false);
            priceField.setEditable(true);
            priceField.setDisable(false);
            workTimeField.setEditable(true);
            workTimeField.setDisable(false);
            materialsCostField.setEditable(true);
            materialsCostField.setDisable(false);

            showInfo("Теперь вы можете отредактировать поля");
        }
    }

    @FXML
    private void closeButton() {
        if (showConfirmation("Закрытие", "Закрыть окно?", "Подтверждение")) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }
}