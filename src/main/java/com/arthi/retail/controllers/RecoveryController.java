
package com.arthi.retail.controllers;

import com.arthi.retail.AuthService;
import com.arthi.retail.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class RecoveryController {

    @FXML private PasswordField adminPasswordField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label messageLabel;

    @FXML
    private void handleUpdate() {

        String admin = adminPasswordField.getText();
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirm = confirmPasswordField.getText();

        try {
            if (admin.isBlank() || username.isBlank()
                    || password.isBlank() || confirm.isBlank()) {
                messageLabel.setText("Please fill all fields.");
                return;
            }

            if (!password.equals(confirm)) {
                messageLabel.setText("Passwords do not match.");
                return;
            }

            if (!AuthService.verifyAdmin(admin)) {
                messageLabel.setText("Incorrect admin password.");
                return;
            }

            if (AuthService.updateCredentials(username, password)) {
                messageLabel.setText("Credentials updated successfully!");

                adminPasswordField.clear();
                usernameField.clear();
                passwordField.clear();
                confirmPasswordField.clear();

            } else {
                messageLabel.setText("Account not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Update failed: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        try {
            MainApp.showLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
