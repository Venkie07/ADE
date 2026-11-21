
package com.arthi.retail.controllers;

import com.arthi.retail.AuthService;
import com.arthi.retail.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class SetupController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private PasswordField adminPasswordField;
    @FXML private Label messageLabel;

    @FXML
    private void handleCreateAccount() {

        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirm = confirmPasswordField.getText();
        String admin = adminPasswordField.getText();

        try {

            if (username.isBlank() || password.isBlank()
                    || confirm.isBlank() || admin.isBlank()) {

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

            AuthService.createAccount(username, password);

            MainApp.showLogin();

        } catch (Exception e) {

            messageLabel.setText(
                "Account creation failed: " + e.getMessage()
            );
        }
    }
}
