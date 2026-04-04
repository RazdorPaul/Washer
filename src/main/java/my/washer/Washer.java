package my.washer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Washer extends Application{
    @Override
    public void start(Stage primaryStage) {
        try {
            // Загружаем FXML файл
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/my/washer/fxml/mainWindow.fxml"));
            Parent root = loader.load();

            // Создаем сцену
            Scene scene = new Scene(root);

            // Настраиваем окно
            primaryStage.setTitle("Система управления автомойкой");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
