package my.washer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ServiceAddingController {

    @FXML
    private ComboBox<String> serviceComboBox;

    @FXML
    private TextField discountField;

    @FXML
    private ComboBox<String> removeComboBox;

    @FXML
    private ListView<String> selectedServicesListView;

    @FXML
    private ListView<String> availableServicesListView;

    @FXML
    private TextField totalPriceField;

    @FXML
    private Button addToOrderButton;

    @FXML
    private Button exitButton;

    // Хранение цен услуг
    private Map<String, Integer> servicePrices = new HashMap<>();
    private Map<String, Integer> selectedServicesWithPrices = new HashMap<>();

    @FXML
    private void initialize() {
        initializeServices();
        setupServiceComboBox();
        setupListViewHandlers();
        setupRemoveComboBox();
        setupDiscountListener();
    }

    private void initializeServices() {
        // Добавление услуг
        servicePrices.put("Мойка кузова", 500);
        servicePrices.put("Химчистка салона", 2000);
        servicePrices.put("Полировка кузова", 1500);
        servicePrices.put("Чернение резины", 300);
        servicePrices.put("Комплексная мойка", 800);
        servicePrices.put("Нанесение керамики", 5000);
        servicePrices.put("Озонирование", 1000);

        // Заполнение доступных услуг
        availableServicesListView.getItems().addAll(servicePrices.keySet());
    }

    private void setupServiceComboBox() {
        serviceComboBox.setEditable(true);
        serviceComboBox.getItems().addAll(servicePrices.keySet());

        // Добавление выбранной услуги в список
        serviceComboBox.setOnAction(event -> {
            String selected = serviceComboBox.getValue();
            if (selected != null && !selectedServicesListView.getItems().contains(selected)) {
                if (showConfirmation("Добавление услуги", "Добавить услугу \"" + selected + "\"?", "Подтверждение")) {
                    selectedServicesListView.getItems().add(selected);
                    selectedServicesWithPrices.put(selected, servicePrices.get(selected));
                    updateRemoveComboBox();
                    calculateTotal();

                    // Очистка поля выбора
                    serviceComboBox.getEditor().clear();
                    serviceComboBox.setValue(null);
                }
            }
        });
    }

    private void setupListViewHandlers() {
        // Двойной клик для удаления из списка
        selectedServicesListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                removeSelectedService();
            }
        });
    }

    private void setupRemoveComboBox() {
        removeComboBox.setOnAction(event -> {
            String selected = removeComboBox.getValue();
            if (selected != null) {
                if (showConfirmation("Удаление услуги", "Удалить услугу \"" + selected + "\"?", "Подтверждение")) {
                    selectedServicesListView.getItems().remove(selected);
                    selectedServicesWithPrices.remove(selected);
                    updateRemoveComboBox();
                    calculateTotal();
                }
                removeComboBox.setValue(null);
            }
        });
    }

    private void updateRemoveComboBox() {
        removeComboBox.getItems().clear();
        removeComboBox.getItems().addAll(selectedServicesListView.getItems());
    }

    private void setupDiscountListener() {
        discountField.textProperty().addListener((obs, oldVal, newVal) -> {
            calculateTotal();
        });
    }

    private void removeSelectedService() {
        String selected = selectedServicesListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (showConfirmation("Удаление услуги", "Удалить услугу \"" + selected + "\"?", "Подтверждение")) {
                selectedServicesListView.getItems().remove(selected);
                selectedServicesWithPrices.remove(selected);
                updateRemoveComboBox();
                calculateTotal();
            }
        }
    }

    private void calculateTotal() {
        int total = 0;
        for (int price : selectedServicesWithPrices.values()) {
            total += price;
        }

        // Применение скидки
        try {
            String discountText = discountField.getText().trim();
            if (!discountText.isEmpty()) {
                double discount = Double.parseDouble(discountText);
                if (discount > 0 && discount <= 100) {
                    total = (int) (total * (1 - discount / 100));
                }
            }
        } catch (NumberFormatException e) {
            // Неверный формат скидки - игнорируем
        }

        totalPriceField.setText(String.valueOf(total));
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

    @FXML
    private void addToOrderButton() {
        if (selectedServicesListView.getItems().isEmpty()) {
            showInfo("Не выбрано ни одной услуги");
            return;
        }

        if (showConfirmation("Добавление к записи", "Добавить выбранные услуги к заказу?", "Подтверждение")) {
            // Передача данных в основной контроллер
            String services = String.join(", ", selectedServicesListView.getItems());
            String totalPrice = totalPriceField.getText();
            String discount = discountField.getText().trim();

            // Здесь логика передачи данных в OrderWindowController
            // Можно использовать статическую переменную или передать через FXMLLoader

            showInfo("Услуги добавлены к заказу");
            closeWindow();
        }
    }

    @FXML
    private void exitButton() {
        if (showConfirmation("Выход", "Закрыть окно добавления услуг?", "Подтверждение")) {
            closeWindow();
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    // Геттеры для передачи данных
    public String getSelectedServices() {
        return String.join(", ", selectedServicesListView.getItems());
    }

    public int getTotalPrice() {
        try {
            return Integer.parseInt(totalPriceField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getDiscount() {
        try {
            String discount = discountField.getText().trim();
            return discount.isEmpty() ? 0 : Integer.parseInt(discount);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}