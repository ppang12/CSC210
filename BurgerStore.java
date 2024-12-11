package com.example.javafx;

public class BurgerStore {
    private String[][] user_and_pass = {{"admin", "pass"}};
    private String[] itemNames = {
            "Big Mac",
            "Quarter With Cheese",
            "Double Quarter",
            "McDouble",
            "CheeseBurger",
            "Double Cheese Burger",
            "Hamburger",

            "Chicken Nuggets",
            "Fries",
            "Apple Slices",
            "Honey",
            "Mustard Packet",
            "Ketchup",

            "Coca-cola",
            "Sprite",
            "Dr Pepper",
            "Fanta",
            "Diet Coke",
            "Lemonade",
            };
    public String[] itemCategories = {
            "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger",

            "Sides","Sides","Sides","Sides","Sides","Sides",

            "Drinks","Drinks","Drinks","Drinks","Drinks","Drinks","Drinks","Drinks"};
    private double[] itemPrices = {
            6.99, 12.99, 9.99, 7.99, 11.99, 13.99, 11,33,
            1.99, 1.99, 1.99, 1.99, 1.99, 1.99,
            3.99, 1.99, 2.99, 4.99, 5.99, 2.99, 4.99, 3.99,};
    private double taxRate = 0.08;

    public String getUsername() {
        return user_and_pass[0][0];
    }

    public String getPassword() {
        return user_and_pass[0][1];
    }

    public String[] getItemNames() {
        return itemNames;
    }

    public String[] getItemCategories() {
        return itemCategories;
    }

    public double getItemPrices(String itemName) {
        for (int i = 0; i < itemNames.length; i++) {
            if (itemNames[i].equals(itemName)) {
                return itemPrices[i];
            }
        }
        return 0.0;
    }

    public double calculateCost(String itemName, int qty) {
        return getItemPrices(itemName) * qty;
    }

    public double calculateTax(double cost) {
        return cost * taxRate;
    }

    public double calculateTotalCost(double cost) {
        return cost + calculateTax(cost);
    }
}
