package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Items {

    public static List<EachItem> cartItems = new ArrayList<>();

    // __________________________________inner class for each item__________________________________
    static class EachItem {
        public String currentName = "name";
        public int currentCost = 0;
        public int currentQty = 0;
        public EachItem(String getName, int getCost, int getQty) {
            currentName = getName;
            currentCost = getCost;
            currentQty = getQty;
        }
    }

    // ___________________________________________methods___________________________________________
    // adds the new item to the cart. If it's already there, it increases the quantity by 1
    public static void addNewItem(String itemName, int itemCost, int itemQuantity) {
        // checking if the item is already in the cart
        for (int i = 0; i < cartItems.size(); i++) {
            if (itemName.equals(cartItems.get(i).currentName)) {
                cartItems.get(i).currentQty++;
                return;
            }
        }
        // if no, adding the new item
        cartItems.add(new EachItem(itemName, itemCost, itemQuantity));
    }

    // removes the item from the cart
    public static void removeItem(String itemName) {
        EachItem current = null;
        // obtaining the EachItem object that has the name itemName
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).currentName.equals(itemName)) {
                current = cartItems.get(i);
                break;
            }
        }
        // if itemName isn't in the cart, return
        if (current == null) {
            return;
        }
        // if the item has a quantity more than 1, reduce the quantity by 1
        if (current.currentQty > 1) {
            current.currentQty--;
            return;
        } else { // if no, delete the item from the cart
            cartItems.remove(current);
        }
    }

    // returns an array of item names
    public static String[] getItemNames() {
        String[] toReturn = new String[cartItems.size()];
        for (int i = 0; i < cartItems.size(); i++) {
            toReturn[i] = cartItems.get(i).currentName;
        }
        return toReturn;
    }

    // returns an array of item costs
    public static int[] getItemCosts() {
        int[] toReturn = new int[cartItems.size()];
        for (int i = 0; i < cartItems.size(); i++) {
            toReturn[i] = cartItems.get(i).currentCost;
        }
        return toReturn;
    }

    // returns an array of item quantities
    public static int[] getItemQty() {
        int[] toReturn = new int[cartItems.size()];
        for (int i = 0; i < cartItems.size(); i++) {
            toReturn[i] = cartItems.get(i).currentQty;
        }
        return toReturn;
    }

    // returns to the total cost
    public static int getTotal() {
        int toReturn = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            toReturn += (cartItems.get(i).currentCost * cartItems.get(i).currentQty);
        }
        return toReturn;
    }

    // returns the cart in the form of a string
    public static String getCartString() {
        String toReturn = "St. John Bosco Fish Fry Order" + "\n" + "\n";
        for (int i = 0; i < cartItems.size(); i++) {
            toReturn += (cartItems.get(i).currentName) +
                    " x" + cartItems.get(i).currentQty
                    + ".......$" + (cartItems.get(i).currentCost) + "\n";
        }
        toReturn += "\n" + "Total: " + getTotal();
        return toReturn;
    }
}
