
package com.arthi.retail.controllers;

import com.arthi.retail.AuthService;
import com.arthi.retail.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField visiblePasswordField;
    @FXML
    private Button togglePasswordButton;
    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.isVisible()
                ? passwordField.getText()
                : visiblePasswordField.getText();

        try {
            if (username.isBlank() || password.isBlank()) {
                messageLabel.setText("Enter username and password.");
                return;
            }

            if (AuthService.login(username, password)) {
                MainApp.showDashboard();
            } else {
                messageLabel.setText("Invalid username or password.");
            }

        } catch (Exception e) {
            messageLabel.setText("Login failed: " + e.getMessage());
        }
    }

    @FXML
    private void togglePassword() {

        if (passwordField.isVisible()) {

            visiblePasswordField.setText(passwordField.getText());

            passwordField.setVisible(false);
            passwordField.setManaged(false);

            visiblePasswordField.setVisible(true);
            visiblePasswordField.setManaged(true);

            togglePasswordButton.setText("🙈");

        } else {

            passwordField.setText(visiblePasswordField.getText());

            visiblePasswordField.setVisible(false);
            visiblePasswordField.setManaged(false);

            passwordField.setVisible(true);
            passwordField.setManaged(true);

            togglePasswordButton.setText("👁");
        }
    }

    @FXML
    private void handleForgotCredentials() {
        try {
            MainApp.showRecovery();
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Unable to open recovery page.");
        }
    }
}
