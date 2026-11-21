
package com.arthi.retail;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {

        primaryStage = stage;

        try {

            System.out.println("Initializing database...");

            Database.initialize();

            System.out.println("Checking user account...");

            boolean exists = Database.userExists();

            System.out.println("User exists: " + exists);

            if (exists) {

                System.out.println("Loading login screen...");
                showLogin();

            } else {

                System.out.println("Loading setup screen...");
                showSetup();
            }

            System.out.println("JavaFX window launched!");

        } catch (Exception e) {

            e.printStackTrace();

            new Alert(
                    Alert.AlertType.ERROR,
                    "Application initialization failed:\n" + e.getMessage()).showAndWait();
        }
    }

    public static void showSetup() throws Exception {
        loadScreen("/fxml/setup.fxml", "ArthiRetail - Setup");
    }

    public static void showLogin() throws Exception {
        loadScreen("/fxml/login.fxml", "ArthiRetail - Login");
    }

    public static void showDashboard() throws Exception {
        loadScreen("/fxml/dashboard.fxml", "ArthiRetail - Dashboard");
    }

    private static void loadScreen(
            String file, String title) throws Exception {

        Parent root = FXMLLoader.load(
                MainApp.class.getResource(file));

        Scene scene = new Scene(root);

        var css = MainApp.class.getResource("/css/style.css");

        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        if ("/fxml/dashboard.fxml".equals(file)) {
            primaryStage.setWidth(1100);
            primaryStage.setHeight(750);
        } else {
            primaryStage.setWidth(700);
            primaryStage.setHeight(750);
        }
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(600);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.out.println("ArthiRetail closed.");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showRecovery() throws Exception {
    loadScreen("/fxml/recovery.fxml", "ArthiRetail - Recover Credentials");
}
}
