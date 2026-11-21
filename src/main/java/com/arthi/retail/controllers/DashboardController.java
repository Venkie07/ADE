package com.arthi.retail.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardController {

    @FXML
    private VBox contentArea;

    @FXML
    private void initialize() {
        showDashboard();
    }

    @FXML
    private void showDashboard() {
        showPage("Dashboard",
                "Welcome to ArthiRetail",
                "Store overview will be available here.");
    }

    @FXML
    private void showUpdateStocks() {
        showPage("Update Stocks", "Stock Management", "Manage product stock updates.");
    }

    @FXML
    private void showBilling() {
        showPage("Billing", "Create Bills", "Generate customer bills.");
    }

    @FXML
    private void showViewStocks() {
        showPage("View Stocks", "Inventory", "Browse and search available products.");
    }

    @FXML
    private void showProfile() {
        showPage("Profile", "Store and Account", "View and manage store information.");
    }

    @FXML
    private void showSubscriptionPayments() {
        showPage("Subscription Payments", "Subscription", "View plans and payment history.");
    }

    @FXML
    private void showSalesHistory() {
        showPage("Sales History", "Completed Bills", "Review previous sales transactions.");
    }

    @FXML
    private void showCustomerDetails() {
        showPage("Customer Details", "Customers", "View and manage customer records.");
    }

    private void showPage(String title, String subtitle, String description) {
        contentArea.getChildren().setAll(
                createLabel(title, "page-title"),
                createLabel(subtitle, "page-subtitle"),
                createLabel(description, "page-subtitle")
        );
    }

    private Label createLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        return label;
    }
}
