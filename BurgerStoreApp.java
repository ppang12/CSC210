package com.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javafx.scene.image.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BurgerStoreApp extends Application {
    private BurgerStore store;
    private Stage primaryStage;
    private Map<String, Integer> orderItems = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        store = new BurgerStore();
        login();
    }

// ================== Login =================

    private void login() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));

        // =================== Title ==================

        Label titleLabel = new Label("Burger Store Login");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: black;");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: #fff; -fx-border-radius: 10; -fx-background-radius: 10;");

        // =================== login screen user & pass ==================

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Label messageLabel = new Label("");
        messageLabel.setStyle("-fx-text-fill: #e74c3c;");

        String textFieldStyle = "-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #bdc3c7; -fx-border-width: 1; -fx-padding: 10;";
        usernameField.setStyle(textFieldStyle);
        passwordField.setStyle(textFieldStyle);

        loginButton.setStyle("""
            -fx-background-color: #3498db;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-padding: 12 24;
            -fx-background-radius: 5;
            """);

        loginButton.setOnMouseEntered(e ->
                loginButton.setStyle("""
                    -fx-background-color: #2980b9;
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-padding: 12 24;
                    -fx-background-radius: 5;
                    """));

        loginButton.setOnMouseExited(e ->
                loginButton.setStyle("""
                    -fx-background-color: #3498db;
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-padding: 12 24;
                    -fx-background-radius: 5;
                    """));

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(messageLabel, 1, 3);

        // ================== Hit login button when pressing enter ================

        passwordField.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                loginButton.fire();
            }
        });

        loginButton.setOnAction(e ->{
            if (usernameField.getText().equals(store.getUsername()) && passwordField.getText().equals(store.getPassword()))
                menu();
            else{
                messageLabel.setText("Incorrect user and pass");
                usernameField.setStyle(textFieldStyle + "-fx-border-color: red;");
                passwordField.setStyle(textFieldStyle + "-fx-border-color: red;");
            }
        });

        root.getChildren().addAll(titleLabel, grid);
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Burger Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // =============== Main Body =================

    private void menu() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f2f5;");

        // ================= Categories ===============

        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #f0f0f0; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5); -fx-border-radius: 12;");
        sidebar.setPrefWidth(300);

        Label sidebarTitle = new Label("Categories");
        sidebarTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 0 0 20 0;");
        sidebar.getChildren().add(sidebarTitle);

        Button burgerButton = new Button("Burgers\t\t", new ImageView(new Image("burger.jpg", 40, 40, true, true)));
        Button sidesButton = new Button("Sides\t\t", new ImageView(new Image("sides.jpg", 40, 40, true, true)));
        Button drinksButton = new Button("Drinks\t\t", new ImageView(new Image("drinks.jpg", 40, 40, true, true)));

        String catBtnStyle = """
            -fx-background-color: none;
            -fx-text-fill: #333333;
            -fx-font-weight: bold;
            -fx-cursor: hand;
            -fx-padding: 15 20;
            -fx-font-size: 18px;
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 5);
            """;

        String activeCatButton = """
            -fx-background-color: #f0f2f5;
            -fx-text-fill: #333333;
            -fx-font-weight: bold;
            -fx-padding: 15 20;
            -fx-font-size: 18px;
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5);
            """;

        burgerButton.setStyle(catBtnStyle);
        sidesButton.setStyle(catBtnStyle);
        drinksButton.setStyle(catBtnStyle);

        burgerButton.setMaxWidth(Double.MAX_VALUE);
        sidesButton.setMaxWidth(Double.MAX_VALUE);
        drinksButton.setMaxWidth(Double.MAX_VALUE);

        // ====================== Logout Button ===================

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("""
            -fx-background-color: #ff4c4c;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-padding: 12 24;
            -fx-background-radius: 8;
            """
        );
        logoutButton.setOnMouseEntered(e ->
                logoutButton.setStyle("""
                    -fx-background-color: #ff6666;
                    -fx-cursor: hand;
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-padding: 12 24;
                    -fx-background-radius: 8;
                    """));
        logoutButton.setOnMouseExited(e ->
                logoutButton.setStyle("""
                    -fx-background-color: #ff4c4c;
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-padding: 12 24;
                    -fx-background-radius: 8;
                    """));
        logoutButton.setOnAction(e -> {
            orderItems.clear();
            login();
        });

        sidebar.getChildren().addAll(burgerButton, sidesButton, drinksButton, logoutButton);

        // ===================== Products ======================

        VBox mainContent = new VBox(10);
        mainContent.setPadding(new Insets(10));
        mainContent.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 12; -fx-padding: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 10, 0, 0, 5);");

        Label defaultTitle = new Label("Burgers");
        defaultTitle.setAlignment(Pos.TOP_CENTER);
        defaultTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-padding: 0 0 20 0; -fx-text-fill: #34495e;");
        mainContent.getChildren().add(defaultTitle);

        GridPane menuGrid = new GridPane();
        menuGrid.setAlignment(Pos.TOP_CENTER);
        menuGrid.setHgap(20);
        menuGrid.setVgap(20);

        mainContent.getChildren().add(menuGrid);

        // just fun

        burgerButton.setOnAction(e -> {
            defaultTitle.setText("Burgers");
            displayItemsByCategory("Burger", menuGrid);
            burgerButton.setStyle(activeCatButton);
            sidesButton.setStyle(catBtnStyle);
            drinksButton.setStyle(catBtnStyle);
        });
        sidesButton.setOnAction(e -> {
            defaultTitle.setText("Sides");
            displayItemsByCategory("Sides", menuGrid);
            sidesButton.setStyle(activeCatButton);
            burgerButton.setStyle(catBtnStyle);
            drinksButton.setStyle(catBtnStyle);
        });
        drinksButton.setOnAction(e -> {
            defaultTitle.setText("Drinks");
            displayItemsByCategory("Drinks", menuGrid);
            drinksButton.setStyle(activeCatButton);
            burgerButton.setStyle(catBtnStyle);
            sidesButton.setStyle(catBtnStyle);
        });

        // ================= Default set to Burger Cat =====================

        defaultTitle.setText("Burgers");
        burgerButton.setStyle(activeCatButton);
        displayItemsByCategory("Burger", menuGrid);

        // =================== Calculate total and reset ====================

        HBox bottomBar = new HBox(30);
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 12; -fx-padding: 20;");

        Button calculateButton = new Button("Calculate Total");
        calculateButton.setStyle("""
            -fx-background-color: #00aadd;
            -fx-cursor: hand;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-padding: 15 30;
            -fx-background-radius: 12;
            -fx-font-size: 18px;
            """
        );

        calculateButton.setOnMouseEntered(e ->
                calculateButton.setStyle("""
                    -fx-background-color: #0099cc;
                    -fx-cursor: hand;
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-padding: 15 30;
                    -fx-background-radius: 12;
                    -fx-font-size: 18px;
                    """));

        calculateButton.setOnMouseExited(e ->
                calculateButton.setStyle("""
                    -fx-background-color: #00aadd;
                    -fx-cursor: hand;
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-padding: 15 30;
                    -fx-background-radius: 12;
                    -fx-font-size: 18px;
                    """));

        calculateButton.setOnAction(e -> {
            double subtotal = 0;
            StringBuilder receiptBuilder = new StringBuilder();

            // Add store details
            receiptBuilder.append("BMCC Burger Shop \n");
            receiptBuilder.append("Manhattan, New York\n");
            receiptBuilder.append("Phone: +1 (xxx) xxx-xxxx\n\n");

            // Add date and time
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a");
            LocalDateTime now = LocalDateTime.now();
            receiptBuilder.append("Date: ").append(dtf.format(now)).append("\n\n");

            // Add items
            receiptBuilder.append("Items:\n");
            for (Map.Entry<String, Integer> entry : orderItems.entrySet()) {
                double itemTotal = store.calculateCost(entry.getKey(), entry.getValue());
                subtotal += itemTotal;
                receiptBuilder.append(entry.getValue()).append(" x ").append(entry.getKey()).append(" - $").append(String.format("%.2f", itemTotal)).append("\n");
            }

            // Calculate totals
            double tax = store.calculateTax(subtotal);
            double total = store.calculateTotalCost(subtotal);

            // Add subtotal, tax, and total
            receiptBuilder.append("\nSubtotal: $").append(String.format("%.2f", subtotal)).append("\n");
            receiptBuilder.append("Tax (8%): $").append(String.format("%.2f", tax)).append("\n");
            receiptBuilder.append("---------------------------\n");
            receiptBuilder.append("Total: $").append(String.format("%.2f", total)).append("\n\n");

            // Add thank you note
            receiptBuilder.append("Thank you for visiting!\n");

            Alert selectedItemsAlert = new Alert(Alert.AlertType.INFORMATION);
            selectedItemsAlert.setTitle("Order Receipt");
            selectedItemsAlert.setHeaderText(null);
            selectedItemsAlert.setContentText(receiptBuilder.toString());
            selectedItemsAlert.showAndWait();
        });

        Button clearButton = new Button("Clear Order");
        clearButton.setStyle("""
            -fx-background-color: #a300cc;
            -fx-cursor: hand;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-padding: 15 30;
            -fx-background-radius: 12;
            -fx-font-size: 18px;
            """
        );

        clearButton.setOnMouseEntered(e ->
                clearButton.setStyle("""
                    -fx-background-color: #9900aa;
                    -fx-cursor: hand;
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-padding: 15 30;
                    -fx-background-radius: 12;
                    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5);
                    -fx-font-size: 18px;
                    """));

        clearButton.setOnMouseExited(e ->
                clearButton.setStyle("""
                    -fx-background-color: #a300cc;
                    -fx-cursor: hand;
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-padding: 15 30;
                    -fx-background-radius: 12;
                    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5);
                    -fx-font-size: 18px;
                    """));

        clearButton.setOnAction(e -> {
            orderItems.clear();
            defaultTitle.setText("Burgers");
            burgerButton.setStyle(activeCatButton);
            sidesButton.setStyle(catBtnStyle);
            drinksButton.setStyle(catBtnStyle);
            displayItemsByCategory("Burger", menuGrid);
        });

        bottomBar.getChildren().addAll(calculateButton, clearButton);

        // ============== Main Body Screen Setup ==============

        root.setLeft(sidebar);
        root.setCenter(mainContent);
        root.setBottom(bottomBar);

        Scene scene = new Scene(root, 1600, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burger Store Menu");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void displayItemsByCategory(String category, GridPane menuGrid) {
        menuGrid.getChildren().clear();

        String[] items = store.getItemNames();
        String[] categories = store.getItemCategories();
        Spinner<Integer>[] spinners = new Spinner[items.length];

        int col = 0;
        int row = 0;
        for (int i = 0; i < items.length; i++) {
            if (categories[i].equals(category)) {
                VBox itemBox = new VBox(20);
                itemBox.setAlignment(Pos.CENTER);
                itemBox.setStyle("-fx-border-radius: 25; -fx-background-radius: 25; -fx-padding: 20;");

                String[] imageUrls = {
                        "bigMac.jpg",
                        "quarterWithCheese.jpg",
                        "doubleQuarter.jpg",
                        "mcDouble.jpg",
                        "cheeseBurger.jpg",
                        "doubleCheese.jpg",
                        "hamBurger.jpg",
                        "nuggets.jpg",
                        "fries.jpg",
                        "apple.jpg",
                        "honey.jpg",
                        "mustard.jpg",
                        "ketchup.jpg",
                        "coke.jpg",
                        "sprite.jpg",
                        "drPepper.jpg",
                        "fanta.jpg",
                        "dietCoke.jpg",
                        "lemonade.jpg",
                };

                ImageView productImg = new ImageView(new Image(imageUrls[i], 150, 150, true, true));
                Label itemLabel = new Label(items[i]);
                itemLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-padding: 10 0 5 0;");

                Label priceLabel = new Label(String.format("$%.2f", store.getItemPrices(items[i])));
                priceLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #495057; -fx-padding: 0 0 10 0;");

                spinners[i] = new Spinner<>(0, 100, 0);
                spinners[i].setEditable(true);
                spinners[i].setPrefWidth(150);
                spinners[i].getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
                spinners[i].setStyle("-fx-font-size: 16px; -fx-background-radius: 10; -fx-padding: 10;");

                final int index = i;
                spinners[i].valueProperty().addListener((obs, oldVal, newVal) -> {
                    if (newVal == 0) {
                        orderItems.remove(items[index]);
                    } else {
                        orderItems.put(items[index], newVal);
                    }
                    priceLabel.setText(String.format("$%.2f", store.calculateCost(items[index], newVal)));
                });

                itemBox.getChildren().addAll(productImg, itemLabel, priceLabel, spinners[i]);
                menuGrid.add(itemBox, col, row);

                col++;
                if (col >= 5) {
                    col = 0;
                    row++;
                }
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
