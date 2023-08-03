import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The VendingMachineController class serves as a controller that manages the interactions
 * between the model classes (RegularVendingMachine and SpecialVendingMachine) and the view
 * classes (RegularVendingGUI, SpecialVendingGUI, and MaintenanceGUI).
 */
public class VendingMachineController extends Component {
    // Attributes
    private RegularVendingMachine regularVendingMachine;
    private SpecialVendingMachine specialVendingMachine;
    private RegularVendingGUI regularVendingGUI;
    private SpecialVendingGUI specialVendingGUI;
    private MaintenanceGUI maintenanceGUI;

    // Constructor
    /**
     * Constructor for the VendingMachineController class.
     * Initializes instances of RegularVendingMachine, SpecialVendingMachine, MaintenanceGUI,
     * RegularVendingGUI, and SpecialVendingGUI. Also, initializes the items in the vending machines.
     */
    public VendingMachineController() {
        // model classes
        regularVendingMachine = new RegularVendingMachine("Pizzaria Vending Machine");
        specialVendingMachine = new SpecialVendingMachine("Pizzaria Vending Machine");

        // view classes
        maintenanceGUI = new MaintenanceGUI(this);
        regularVendingGUI = new RegularVendingGUI(this);
        specialVendingGUI = new SpecialVendingGUI(this);

        // initialize items
        regularVendingMachine.initializeItemSlots();
        specialVendingMachine.initializeCustomItems();
    }

    // Getters
    /**
     * Gets the RegularVendingMachine instance.
     *
     * @return The RegularVendingMachine instance associated with this VendingMachineController.
     */
    public RegularVendingMachine getRegularVendingMachine(){
        return regularVendingMachine;
    }

    /**
     * Gets the SpecialVendingMachine instance.
     *
     * @return The SpecialVendingMachine instance associated with this VendingMachineController.
     */
    public SpecialVendingMachine getSpecialVendingMachine(){
        return specialVendingMachine;
    }

    // Methods
    /**
     * Display items available in the Regular Vending Machine.
     * The displayed items will be updated in the RegularVendingGUI.
     */
    public void displayItemsRegular() {
        String itemList = regularVendingMachine.displayItems();
        regularVendingGUI.updateItemsDisplay(itemList);
    }

    /**
     * Display items available in the Special Vending Machine.
     * The displayed items will be updated in the SpecialVendingGUI.
     */
    public void displayItemsSpecial(){
        String itemList = specialVendingMachine.displayItems();
        specialVendingGUI.updateItemsDisplay(itemList);
    }

    /**
     * Handles the regular vending process when a user selects an item.
     * It calculates the total amount and calories, updates the GUI, and handles the purchase process.
     *
     * @param itemName The name of the item selected by the user.
     */
    public void handleRegularVending(String itemName) {
        int totalAmount = 0, totalCalories = 0;
        int accTotalAmount = 0;
        int accTotalCalories = 0;
        String display;

        int userMoneyTotal = regularVendingMachine.getReceivedPayment().calculateTotalAmount();
        Money pettyMoney = regularVendingMachine.getMoneyBank().getPettyMoney();

        if (userMoneyTotal < 1) {
            regularVendingGUI.updateTextArea("Current user money: ₱" + userMoneyTotal + "\n");
            regularVendingGUI.updateTextArea("Insert user money? Y/N");

            // dialog box to ask for user money
            int result = JOptionPane.showConfirmDialog(regularVendingGUI, "Insert user money to use the vending machine:", "User Money", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                insertUserMoney(regularVendingMachine, "regular");
            } else if (result == JOptionPane.NO_OPTION) {
                regularVendingGUI.setVisible(false);
                JOptionPane.showMessageDialog(regularVendingGUI, "Going back to Test Regular Menu");
                new TestRegularVendingMachine().setVisible(true);
            }
        } else {
            regularVendingGUI.updateTextArea("=================================");
            display = regularVendingMachine.displayItems();
            regularVendingGUI.updateTextArea(display);
            displayItemsRegular();
            // User chose to cancel
            if (itemName.equals("Produce Change")) {
                regularVendingGUI.updateTextArea("Do you want to produce change without making a purchase? (Y/N)");
                int result = JOptionPane.showConfirmDialog(regularVendingGUI, "Do you want to produce change without making a purchase?", "Produce Change", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    display = regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                    regularVendingGUI.updateTextArea(display);
                    regularVendingGUI.updateTextArea("\nChange produced...\n");

                    int option = JOptionPane.showConfirmDialog(regularVendingGUI, "Quit the vending machine?", "Quit Vending Machine", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(this, "Thank you for using the vending machine. Please come again!");
                        regularVendingGUI.setVisible(false);
                    } else if (option == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(regularVendingGUI, "Going back to Test Regular Menu");
                        regularVendingGUI.setVisible(false);
                        new TestRegularVendingMachine().setVisible(true);
                    }
                } else {
                    regularVendingGUI.updateTextArea("\nOkay...\n");
                    regularVendingGUI.setVisible(false);
                    JOptionPane.showMessageDialog(regularVendingGUI, "Going Back to Test Regular Menu");
                    new TestRegularVendingMachine().setVisible(true);
                }
            } else {
                int itemChoice = 0;
                switch (itemName) {
                    case "Mozzarella" -> itemChoice = 1;
                    case "Pepperoni" -> itemChoice = 2;
                    case "Tomatoes" -> itemChoice = 3;
                    case "Bacon" -> itemChoice = 4;
                    case "Basil" -> itemChoice = 5;
                    case "Pineapple" -> itemChoice = 6;
                    case "Dough" -> itemChoice = 7;
                    case "Ground Pork" -> itemChoice = 8;
                }

                int itemQuantityChoice = Integer.parseInt(JOptionPane.showInputDialog(regularVendingGUI, "Choose the desired quantity (1-10):"));

                ItemSlot itemSlot = regularVendingMachine.itemSlots.get(itemChoice - 1);

                if (itemSlot.isEmpty() || itemQuantityChoice > itemSlot.getQuantity() || itemQuantityChoice < 1) {
                    regularVendingGUI.updateTextArea("Item not available in the specified quantity.");
                    itemQuantityChoice = Integer.parseInt(JOptionPane.showInputDialog(regularVendingGUI, "Choose the desired quantity (1-10):"));
                }

                // Calculate the item price and update the total amount
                int itemPrice = regularVendingMachine.getItemPrice(itemChoice - 1, itemQuantityChoice);
                totalAmount += itemPrice;
                int itemCalories = regularVendingMachine.getItemCalories(itemChoice - 1, itemQuantityChoice);
                totalCalories += itemCalories;

                if (userMoneyTotal >= totalAmount) {
                    if (RegularVendingMachine.hasEnoughChange(regularVendingMachine, pettyMoney, totalAmount, userMoneyTotal)) {
                        accTotalAmount += totalAmount;
                        accTotalCalories += totalCalories;
                        display = regularVendingMachine.selectItem(itemChoice, itemQuantityChoice, "regular");
                        regularVendingGUI.updateTextArea(display);
                    } else {
                        regularVendingGUI.updateTextArea("\nError: Not enough change available.\n");
                    }

                    // Ask if the user wants to make another purchase or exit the vending machine
                    int result = JOptionPane.showConfirmDialog(regularVendingGUI, "Do you want to buy another item?", "Buy Another Item", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.NO_OPTION) {
                        regularVendingGUI.updateTextArea("\nHere are the items that you bought:");
                        for (Transaction transaction : regularVendingMachine.getTransactionLog().getTransactionList()) {
                            regularVendingGUI.updateTextArea(transaction.getItem().getItemName() + " - " + transaction.getQuantity() + " - ₱" + transaction.getAmount());
                        }
                        regularVendingGUI.updateTextArea("Total Amount: ₱" + accTotalAmount);
                        regularVendingGUI.updateTextArea("Total Calories: " + accTotalCalories);
                        regularVendingGUI.updateTextArea("\nThank you for using the vending machine. Please take your change.");
                        display = regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                        regularVendingGUI.updateTextArea(display);
                        regularVendingGUI.updateTextArea("Change produced...\n");
                    }
                } else {
                    regularVendingGUI.updateTextArea("\nError: You do not have enough money to purchase this item/s.\n");
                    regularVendingGUI.updateTextArea("Total Amount: ₱" + totalAmount);
                    regularVendingGUI.updateTextArea("User Money: ₱" + userMoneyTotal + "\n");
                }
            }
        }
    }

    /**
     * Inserts user money into the vending machine and updates the user's money information.
     *
     * @param regularVendingMachine The RegularVendingMachine instance for regular vending or SpecialVendingMachine instance for special vending.
     * @param indicator             A string indicating the type of vending ("regular" for regular vending or "special" for special vending).
     */
    public void insertUserMoney(RegularVendingMachine regularVendingMachine, String indicator) {
        int num500, num200, num100, num50, num20, num10, num5, num1;
        if(indicator.equals("regular")){
            num500 = Integer.parseInt(JOptionPane.showInputDialog(regularVendingGUI, "No. of ₱500: "));
            num200 = Integer.parseInt(JOptionPane.showInputDialog(regularVendingGUI, "No. of ₱200: "));
            num100 = Integer.parseInt(JOptionPane.showInputDialog(regularVendingGUI, "No. of ₱100: "));
            num50 = Integer.parseInt(JOptionPane.showInputDialog(regularVendingGUI, "No. of ₱50: "));
            num20 = Integer.parseInt(JOptionPane.showInputDialog(regularVendingGUI, "No. of ₱20: "));
            num10 = Integer.parseInt(JOptionPane.showInputDialog(regularVendingGUI, "No. of ₱10: "));
            num5 = Integer.parseInt(JOptionPane.showInputDialog(regularVendingGUI, "No. of ₱5: "));
            num1 = Integer.parseInt(JOptionPane.showInputDialog(regularVendingGUI, "No. of ₱1: "));
        } else{
            num500 = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "No. of ₱500: "));
            num200 = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "No. of ₱200: "));
            num100 = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "No. of ₱100: "));
            num50 = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "No. of ₱50: "));
            num20 = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "No. of ₱20: "));
            num10 = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "No. of ₱10: "));
            num5 = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "No. of ₱5: "));
            num1 = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "No. of ₱1: "));
        }

        Money userMoney;
        regularVendingMachine.receivePayment(new Money(num500, num200, num100, num50, num20, num10, num5, num1));
        regularVendingMachine.updatePettyMoney(num500, num200, num100, num50, num20, num10, num5, num1, "add");
        userMoney = regularVendingMachine.getReceivedPayment();
        int userMoneyTotal = userMoney.calculateTotalAmount();

        if(indicator.equals("regular")){
            regularVendingGUI.updateTextArea("Amount Received: ₱" + userMoneyTotal);
            regularVendingGUI.updateTextArea("User money was updated.\n");
        } else{
            specialVendingGUI.updateTextArea("Amount Received: ₱" + userMoneyTotal);
            specialVendingGUI.updateTextArea("User money was updated.\n");
        }
    }

    /**
     * Displays a dialog for restocking items in the vending machine.
     *
     * @param vendingIndicator A string indicating the type of vending machine ("regular" for regular vending or "special" for special vending).
     */
    public void showRestockItemsDialog(String vendingIndicator) {
        maintenanceGUI.updateTextArea("=================================");
        String display;
        display = regularVendingMachine.displayItems();
        maintenanceGUI.updateTextArea(display);
        displayItemsRegular();

        int restockItemChoice = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Choose the item number that you want to restock:"));

        if(restockItemChoice < 1 || restockItemChoice > 8){
            JOptionPane.showMessageDialog(maintenanceGUI, "Invalid item slot. Enter again.");
            restockItemChoice = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Choose the item number that you want to restock:"));
        }

        int restockQuantity = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "How many items should be added:"));

        ItemSlot itemSlot = regularVendingMachine.itemSlots.get(restockItemChoice - 1);
        Item item = itemSlot.getItem();

        if(restockQuantity < 1 || restockQuantity > 10){
            JOptionPane.showMessageDialog(maintenanceGUI, "Maximum quantity of " + item.getItemName() + " slots is 10. Cannot restock. Enter again.");
            restockQuantity = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "How many items should be added:"));
        }

        int currentQuantity = itemSlot.getQuantity();
        int newQuantity = currentQuantity + restockQuantity;

        if(newQuantity > 10){
            JOptionPane.showMessageDialog(maintenanceGUI, "Maximum quantity of " + item.getItemName() + " slots is 10. Cannot restock. Enter again.");
            restockQuantity = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "How many items should be added:"));
        }

        if (vendingIndicator.equalsIgnoreCase("regular")) {
            display = regularVendingMachine.restockItems(restockItemChoice, restockQuantity);
        } else {
            display = regularVendingMachine.restockItems(restockItemChoice, restockQuantity);
        }
        maintenanceGUI.updateTextArea(display);
    }

    /**
     * Displays a dialog for setting new prices for items in the vending machine.
     *
     * @param vendingIndicator A string indicating the type of vending machine ("regular" for regular vending or "special" for special vending).
     */
    public void showSetItemPricesDialog(String vendingIndicator) {
        maintenanceGUI.updateTextArea("=================================");
        String display;

        if (vendingIndicator.equalsIgnoreCase("regular")) {
            display = regularVendingMachine.displayItems();
            maintenanceGUI.updateTextArea(display);
            displayItemsRegular();

            int newItemPriceChoice = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Choose the item number to set a new price:"));
            if(newItemPriceChoice < 1 || newItemPriceChoice > 8){
                JOptionPane.showMessageDialog(maintenanceGUI, "Invalid item choice. Enter again.");
                newItemPriceChoice = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Choose the item number to set a new price:"));
            }
            int newItemPrice = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Enter the new price:"));

            regularVendingMachine.setPrice(newItemPriceChoice, newItemPrice);
            display = regularVendingMachine.displayItems();
            maintenanceGUI.updateTextArea("New Prices:\n" + display);
        } else {
            display = specialVendingMachine.displayItems();
            maintenanceGUI.updateTextArea(display);
            displayItemsSpecial();

            int newItemPriceChoice = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Choose the item number to set a new price:"));
            if(newItemPriceChoice < 1 || newItemPriceChoice > 6){
                JOptionPane.showMessageDialog(maintenanceGUI, "Invalid item choice. Enter again.");
                newItemPriceChoice = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Choose the item number to set a new price:"));
            }
            int newItemPrice = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Enter the new price:"));

            specialVendingMachine.setPrice(newItemPriceChoice, newItemPrice);
            display = specialVendingMachine.displayItems();
            maintenanceGUI.updateTextArea("New Prices:\n" + display);
        }
    }

    /**
     * Displays a dialog for replenishing the petty cash change in the vending machine.
     *
     * @param vendingIndicator A string indicating the type of vending machine ("regular" for regular vending or "special" for special vending).
     */
    public void showReplenishChangeDialog(String vendingIndicator) {
        maintenanceGUI.updateTextArea("=================================");

        String display;
        if (vendingIndicator.equalsIgnoreCase("regular")) {
            display = regularVendingMachine.displayPettyMoney(regularVendingMachine.getMoneyBank().getPettyMoney());
            maintenanceGUI.updateTextArea(display);
        } else {
            display = specialVendingMachine.displayPettyMoney(specialVendingMachine.getMoneyBank().getPettyMoney());
            maintenanceGUI.updateTextArea(display);
        }

        int num500 = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Enter the number of ₱500 bills to replenish:"));
        int num200 = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Enter the number of ₱200 bills to replenish:"));
        int num100 = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Enter the number of ₱100 bills to replenish:"));
        int num50 = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Enter the number of ₱50 bills to replenish:"));
        int num20 = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Enter the number of ₱20 bills to replenish:"));
        int num10 = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Enter the number of ₱10 bills to replenish:"));
        int num5 = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Enter the number of ₱5 bills to replenish:"));
        int num1 = Integer.parseInt(JOptionPane.showInputDialog(maintenanceGUI, "Enter the number of ₱1 bills to replenish:"));

        if (vendingIndicator.equalsIgnoreCase("regular")) {
            regularVendingMachine.replenishPettyMoney(num500, num200, num100, num50, num20, num10, num5, num1);
            display = regularVendingMachine.displayPettyMoney(regularVendingMachine.getMoneyBank().getPettyMoney());
            maintenanceGUI.updateTextArea(display);
        } else {
            specialVendingMachine.replenishPettyMoney(num500, num200, num100, num50, num20, num10, num5, num1);
            display = specialVendingMachine.displayPettyMoney(specialVendingMachine.getMoneyBank().getPettyMoney());
            maintenanceGUI.updateTextArea(display);
        }

        maintenanceGUI.updateTextArea("Change replenished successfully.");
    }

    /**
     * Displays a summary of the transactions made in the vending machine.
     *
     * @param vendingIndicator A string indicating the type of vending machine ("regular" for regular vending or "special" for special vending).
     */
    public void showTransactionSummary(String vendingIndicator) {
        maintenanceGUI.updateTextArea("=================================");

        String display;
        if (vendingIndicator.equalsIgnoreCase("regular")) {
            display = regularVendingMachine.displayTransactionSummary();
            maintenanceGUI.updateTextArea(display);
        } else {
            display = specialVendingMachine.displayTransactionSummary();
            maintenanceGUI.updateTextArea(display);
        }
    }

    /**
     * Displays a dialog for the user to interact with the special vending machine by selecting items.
     * If the user has inserted money, they can select items that can be sold individually (Pepperoni, Bacon, and Ground Pork) or cancel the selection.
     * If the user has not inserted money, they will be prompted to insert money to use the vending machine.
     * After selecting an item and quantity, the method displays the updated user money, the selected item, its quantity, and the total amount.
     * The user can choose to buy another item or exit the vending machine, in which case, a transaction summary is shown.
     */
    public void showItemsOnlyDialog(){
        int totalAmount = 0, totalCalories = 0;
        int accTotalAmount = 0;
        int accTotalCalories = 0;
        String display;

        int userMoneyTotal = regularVendingMachine.getReceivedPayment().calculateTotalAmount();
        Money pettyMoney = regularVendingMachine.getMoneyBank().getPettyMoney();

        if(userMoneyTotal < 1){
            specialVendingGUI.updateTextArea("Current user money: ₱" + userMoneyTotal + "\n");
            specialVendingGUI.updateTextArea("Insert user money? Y/N");

            // dialog box to ask for user money
            int result = JOptionPane.showConfirmDialog(specialVendingGUI, "Insert user money to use the vending machine:", "User Money", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                insertUserMoney(regularVendingMachine, "special");
            } else if(result == JOptionPane.NO_OPTION){
                regularVendingGUI.setVisible(false);
                JOptionPane.showMessageDialog(specialVendingGUI, "Going back to Test Special Menu");
                new TestSpecialVendingMachine().setVisible(true);
            }
        } else{
            specialVendingGUI.updateTextArea("=================================");
            display = regularVendingMachine.displayItems();
            specialVendingGUI.updateTextArea(display);
            displayItemsRegular();
            specialVendingGUI.updateTextArea("\nNote: Pepperoni (2), Bacon (4), and Ground Pork (8) are the only items that can be sold individually in Special Vending Machine.");
            JOptionPane.showMessageDialog(specialVendingGUI, "Note: Pepperoni (2), Bacon (4), and Ground Pork (8) are the only items that can be sold individually in Special Vending Machine.");

            int itemChoice;
            specialVendingGUI.updateTextArea("Choose the desired item (2, 4, or 8) or enter 0 to cancel:\n");
            itemChoice = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "Choose the desired item (2, 4, or 8) or enter 0 to cancel:"));

            if(itemChoice != 0 && itemChoice != 2 && itemChoice != 4 && itemChoice != 8){
                itemChoice = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "Choose the desired item (2, 4, or 8) or enter 0 to cancel:"));
            }

            if(itemChoice == 0){
                specialVendingGUI.updateTextArea("Do you want to produce change without making a purchase? (Y/N)");
                int result = JOptionPane.showConfirmDialog(specialVendingGUI, "Do you want to produce change without making a purchase?", "Produce Change", JOptionPane.YES_NO_OPTION);

                if(result == JOptionPane.YES_OPTION) {
                    display = regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                    specialVendingGUI.updateTextArea(display);
                    specialVendingGUI.updateTextArea("\nChange produced...\n");

                    int option = JOptionPane.showConfirmDialog(specialVendingGUI, "Quit the vending machine?", "Quit Vending Machine", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(this, "Thank you for using the vending machine. Please come again!");
                        specialVendingGUI.setVisible(false);
                    } else if (option == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(specialVendingGUI, "Going back to Test Special Menu");
                        specialVendingGUI.setVisible(false);
                        new TestSpecialVendingMachine().setVisible(true);
                    }
                } else {
                    specialVendingGUI.updateTextArea("\nOkay...\n");
                    JOptionPane.showMessageDialog(specialVendingGUI, "Going Back to Test Special Menu");
                    new TestSpecialVendingMachine().setVisible(true);
                }
            } else if(itemChoice == 2 || itemChoice == 4 || itemChoice == 8){
                int itemQuantityChoice;
                specialVendingGUI.updateTextArea("\nChoose the desired quantity (1-10)");
                itemQuantityChoice = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "Choose the desired quantity (1-10):"));

                ItemSlot itemSlot = specialVendingMachine.itemSlots.get(itemChoice - 1);

                if(itemSlot.isEmpty() || itemQuantityChoice > itemSlot.getQuantity() || itemQuantityChoice < 1){
                    specialVendingGUI.updateTextArea("Item not available in the specified quantity.");
                    itemQuantityChoice = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "Choose the desired quantity (1-10):"));
                }

                // Calculate the item price and update the total amount
                int itemPrice = specialVendingMachine.getItemPrice(itemChoice - 1, itemQuantityChoice);
                totalAmount += itemPrice;
                int itemCalories = specialVendingMachine.getItemCalories(itemChoice - 1, itemQuantityChoice);
                totalCalories += itemCalories;

                if(userMoneyTotal >= totalAmount){
                    if(RegularVendingMachine.hasEnoughChange(regularVendingMachine, pettyMoney, totalAmount, userMoneyTotal)){
                        accTotalAmount += totalAmount;
                        accTotalCalories += totalCalories;
                        display = specialVendingMachine.selectItem(itemChoice, itemQuantityChoice, "regular");
                        specialVendingGUI.updateTextArea(display);
                        totalAmount = 0;
                    } else{
                        specialVendingGUI.updateTextArea("\nError: Not enough change available.\n");
                    }

                    int result = JOptionPane.showConfirmDialog(specialVendingGUI, "Do you want to buy another item?", "Buy Another Item", JOptionPane.YES_NO_OPTION);
                    if(result == JOptionPane.NO_OPTION){
                        specialVendingGUI.updateTextArea("\nHere are the items that you bought:");
                        for (Transaction transaction : specialVendingMachine.getTransactionLog().getTransactionList()) {
                            specialVendingGUI.updateTextArea(transaction.getItem().getItemName() + " - " + transaction.getQuantity() + " - ₱" + transaction.getAmount());
                        }
                        specialVendingGUI.updateTextArea("Total Amount: ₱" + accTotalAmount);
                        specialVendingGUI.updateTextArea("Total Calories: " + accTotalCalories);
                        specialVendingGUI.updateTextArea("\nThank you for using the vending machine. Please take your change.");
                        display = regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                        specialVendingGUI.updateTextArea(display);
                        specialVendingGUI.updateTextArea("Change produced...\n");
                    }
                } else{
                    specialVendingGUI.updateTextArea("\nError: You do not have enough money to purchase this item/s.\n");
                    specialVendingGUI.updateTextArea("Total Amount: ₱" + totalAmount);
                    specialVendingGUI.updateTextArea("User Money: ₱" + userMoneyTotal + "\n");
                }
            } else{
                specialVendingGUI.updateTextArea("\nInvalid item number.\n");
            }
        }
    }

    /**
     * Displays a dialog for the user to interact with the special vending machine by selecting pizza toppings.
     * If the user has inserted money, they can choose from various pizza toppings (Mozzarella, Pepperoni, Tomatoes, Bacon, Basil, Pineapple, and Ground Pork).
     * The user can also cancel the selection or choose to produce change without making a purchase.
     * After selecting toppings and quantity, the method displays the updated user money, the selected toppings, their quantities, and the total amount.
     * If the user chooses to exit the vending machine, the toppings are added to the pizza (including Dough) and a pizza preparation summary is shown.
     * The pizza's total amount and calories are also displayed, and the user is prompted to take their change.
     * The user can then choose to quit the vending machine or return to the Test Special Menu.
     */
    public void showPizzaBasedItemsDialog(){
        int totalAmount = 0, totalCalories = 0;
        int accTotalAmount = 0;
        int accTotalCalories = 0;
        String display;

        int userMoneyTotal = regularVendingMachine.getReceivedPayment().calculateTotalAmount();
        Money pettyMoney = regularVendingMachine.getMoneyBank().getPettyMoney();

        if(userMoneyTotal < 1){
            specialVendingGUI.updateTextArea("Current user money: ₱" + userMoneyTotal + "\n");
            specialVendingGUI.updateTextArea("Insert user money? Y/N");

            // dialog box to ask for user money
            int result = JOptionPane.showConfirmDialog(specialVendingGUI, "Insert user money to use the vending machine:", "User Money", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                insertUserMoney(regularVendingMachine, "special");
            } else if(result == JOptionPane.NO_OPTION){
                regularVendingGUI.setVisible(false);
                JOptionPane.showMessageDialog(specialVendingGUI, "Going back to Test Special Menu");
                new TestSpecialVendingMachine().setVisible(true);
            }
        } else {
            specialVendingGUI.updateTextArea("=================================");
            display = regularVendingMachine.displayItems();
            specialVendingGUI.updateTextArea(display);
            displayItemsRegular();
            specialVendingGUI.updateTextArea("\nDo not choose 'Dough' as it will be automatically added in your order after choosing the toppings.");
            JOptionPane.showMessageDialog(specialVendingGUI, "Do not choose 'Dough' as it will be automatically added in your order after choosing the toppings.");

            // dialog box to ask for the desired item number
            Object[] options = {"0 - Cancel",
                    "1 - Mozzarella",
                    "2 - Pepperoni",
                    "3 - Tomatoes",
                    "4 - Bacon",
                    "5 - Basil",
                    "6 - Pineapple",
                    "7 - Dough",
                    "8 - Ground Pork"};
            int itemChoice = JOptionPane.showOptionDialog(
                    specialVendingGUI,
                    "Choose the desired topping/s for the pizza or enter 0 to cancel:",
                    "Item Choice",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (itemChoice == 0) {
                specialVendingGUI.updateTextArea("\nDo you want to produce change without making a purchase? (Y/N)");
                int result = JOptionPane.showConfirmDialog(specialVendingGUI, "Do you want to produce change without making a purchase?", "Produce Change", JOptionPane.YES_NO_OPTION);

                if(result == JOptionPane.YES_OPTION) {
                    display = regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                    specialVendingGUI.updateTextArea(display);
                    specialVendingGUI.updateTextArea("\nChange produced...\n");

                    int option = JOptionPane.showConfirmDialog(specialVendingGUI, "Quit the vending machine?", "Quit Vending Machine", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(this, "Thank you for using the vending machine. Please come again!");
                        specialVendingGUI.setVisible(false);
                        new VendingMachineGUI().setVisible(true);
                    } else if (option == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(specialVendingGUI, "Going back to Test Special Menu");
                        specialVendingGUI.setVisible(false);
                        new TestSpecialVendingMachine().setVisible(true);
                    }
                } else {
                    specialVendingGUI.updateTextArea("\nOkay...\n");
                    JOptionPane.showMessageDialog(specialVendingGUI, "Going Back to Test Special Menu");
                    specialVendingGUI.setVisible(false);
                    new TestSpecialVendingMachine().setVisible(true);
                }
            } else if(itemChoice >= 1 && itemChoice <= 8 && itemChoice != 7){
                specialVendingGUI.updateTextArea("\nChoose the desired quantity (1-10)");
                int itemQuantityChoice;
                itemQuantityChoice = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "Choose the desired quantity (1-10):"));

                ItemSlot itemSlot = regularVendingMachine.itemSlots.get(itemChoice - 1);

                if(itemSlot.isEmpty() || itemQuantityChoice > itemSlot.getQuantity() || itemQuantityChoice < 1){
                    specialVendingGUI.updateTextArea("\nItem not available in the specified quantity.");
                    itemQuantityChoice = Integer.parseInt(JOptionPane.showInputDialog(specialVendingGUI, "Choose the desired quantity (1-10):"));
                }

                // Calculate the item price and update the total amount
                int itemPrice = regularVendingMachine.getItemPrice(itemChoice - 1, itemQuantityChoice);
                totalAmount += itemPrice;
                int itemCalories = regularVendingMachine.getItemCalories(itemChoice - 1, itemQuantityChoice);
                totalCalories += itemCalories;

                if(userMoneyTotal >= totalAmount){
                    if(RegularVendingMachine.hasEnoughChange(regularVendingMachine, pettyMoney, totalAmount, userMoneyTotal)){
                        accTotalAmount += totalAmount;
                        accTotalCalories += totalCalories;
                        display = specialVendingMachine.selectItem(itemChoice, itemQuantityChoice, "based");
                        specialVendingGUI.updateTextArea(display);
                        totalAmount = 0;
                    } else{
                        specialVendingGUI.updateTextArea("\nError: Not enough change available.\n");
                    }

                    int result = JOptionPane.showConfirmDialog(specialVendingGUI, "Do you want to buy another topping?", "Buy Another Topping", JOptionPane.YES_NO_OPTION);
                    if(result == JOptionPane.NO_OPTION){
                        // dough is added with the toppings
                        Item dough = specialVendingMachine.itemSlots.get(7 - 1).getItem();
                        specialVendingMachine.getToppings().add(dough);
                        specialVendingMachine.transactionLog.addTransaction(new Transaction(dough, 1, dough.getItemPrice()));

                        // prepare the pizza
                        ArrayList<Item> toppings = specialVendingMachine.getToppings();
                        if(toppings.isEmpty()){
                            specialVendingGUI.updateTextArea("\nError: No toppings selected. Pizza cannot be prepared.");
                            new TestSpecialVendingMachine().setVisible(true);
                        }
                        display = specialVendingMachine.preparePizza(toppings);
                        specialVendingGUI.updateTextArea(display);

                        specialVendingGUI.updateTextArea("Total Amount: ₱" + accTotalAmount);
                        specialVendingGUI.updateTextArea("\nTotal Calories: " + accTotalCalories);
                        specialVendingGUI.updateTextArea("\nThank you for using the vending machine. Please take your change.");
                        display = regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                        specialVendingGUI.updateTextArea(display);
                        specialVendingGUI.updateTextArea("\nChange produced...\n");
                    }
                } else{
                    specialVendingGUI.updateTextArea("\nError: You do not have enough money to purchase this item/s.\n");
                    specialVendingGUI.updateTextArea("Total Amount: ₱" + totalAmount);
                    specialVendingGUI.updateTextArea("User Money: ₱" + userMoneyTotal + "\n");
                }
            } else{
                specialVendingGUI.updateTextArea("\nInvalid item number.\n");
            }
        }
    }

    /**
     * Displays a dialog for the user to interact with the special vending machine by selecting ready-made pizzas.
     * If the user has inserted money, they can choose from various ready-made pizzas, such as Pepperoni Pizza, Veggie Pizza, Cheesy Bacon, Hawaiian, Meaty Pizza, and Overload Pizza.
     * The user can also cancel the selection or choose to produce change without making a purchase.
     * After selecting a pizza, the method displays the updated user money, the selected pizza, its price, and its calories.
     * If the user chooses to exit the vending machine, the pizza is added to the pizza preparation summary, and the total amount and calories are displayed.
     * The user is then prompted to take their change, and they can choose to quit the vending machine or return to the Test Special Menu.
     */
    public void showReadyMadePizza(){
        int totalAmount = 0, totalCalories = 0;
        int accTotalAmount = 0;
        int accTotalCalories = 0;
        String display;

        specialVendingGUI.clearTextArea();
        display = specialVendingMachine.displayItems();
        specialVendingGUI.updateTextArea(display);

        int userMoneyTotal = regularVendingMachine.getReceivedPayment().calculateTotalAmount();
        Money pettyMoney = regularVendingMachine.getMoneyBank().getPettyMoney();

        if(userMoneyTotal < 1){
            specialVendingGUI.updateTextArea("Current user money: ₱" + userMoneyTotal + "\n");
            specialVendingGUI.updateTextArea("Insert user money? Y/N");

            // dialog box to ask for user money
            int result = JOptionPane.showConfirmDialog(specialVendingGUI, "Insert user money to use the vending machine:", "User Money", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                insertUserMoney(regularVendingMachine, "special");
            } else if(result == JOptionPane.NO_OPTION){
                regularVendingGUI.setVisible(false);
                JOptionPane.showMessageDialog(specialVendingGUI, "Going back to Test Special Menu");
                new TestSpecialVendingMachine().setVisible(true);
            }
        } else{
            specialVendingGUI.updateTextArea("=================================");
            display = specialVendingMachine.displayItems();
            specialVendingGUI.updateTextArea(display);
            displayItemsSpecial();
            specialVendingGUI.updateTextArea("\nChoose the desired custom pizza or enter 0 to cancel:");

            // dialog box to ask for the desired item number
            Object[] options = {"0 - Cancel",
                    "1 - Pepperoni Pizza",
                    "2 - Veggie Pizza",
                    "3 - Cheesy Bacon",
                    "4 - Hawaiian",
                    "5 - Meaty Pizza",
                    "6 - Overload Pizza"};
            int itemChoice = JOptionPane.showOptionDialog(
                    specialVendingGUI,
                    "Choose the desired custom pizza or enter 0 to cancel:",
                    "Pizza Choice",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if(itemChoice == 0){
                specialVendingGUI.updateTextArea("\nDo you want to produce change without making a purchase? (Y/N)");
                int result = JOptionPane.showConfirmDialog(specialVendingGUI, "Do you want to produce change without making a purchase?", "Produce Change", JOptionPane.YES_NO_OPTION);

                if(result == JOptionPane.YES_OPTION) {
                    display = regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                    specialVendingGUI.updateTextArea(display);
                    specialVendingGUI.updateTextArea("\nChange produced...\n");

                    int option = JOptionPane.showConfirmDialog(specialVendingGUI, "Quit the vending machine?", "Quit Vending Machine", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(this, "Thank you for using the vending machine. Please come again!");
                        specialVendingGUI.setVisible(false);
                        new VendingMachineGUI().setVisible(true);
                    } else if (option == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(specialVendingGUI, "Going back to Test Special Menu");
                        specialVendingGUI.setVisible(false);
                        new TestSpecialVendingMachine().setVisible(true);
                    }
                } else {
                    specialVendingGUI.updateTextArea("\nOkay...\n");
                    JOptionPane.showMessageDialog(specialVendingGUI, "Going Back to Test Special Menu");
                    specialVendingGUI.setVisible(false);
                    new TestSpecialVendingMachine().setVisible(true);
                }
            } else if(itemChoice >= 1 && itemChoice <= 6){
                int itemQuantity = 1;

                ItemSlot itemSlot = specialVendingMachine.itemSlots.get(itemChoice - 1);
                if (itemSlot.isEmpty() || itemChoice > specialVendingMachine.getCustomItems().size()) {
                    specialVendingGUI.updateTextArea("Invalid slot number.");
                    itemChoice = JOptionPane.showOptionDialog(
                            specialVendingGUI,
                            "Choose the desired custom pizza or enter 0 to cancel:",
                            "Pizza Choice",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );
                }

                Item chosenPizza = specialVendingMachine.getCustomItems().get(itemChoice - 1);
                int chosenPizzaPrice = chosenPizza.getItemPrice();
                int chosenPizzaCalories = chosenPizza.getItemCalories();
                totalAmount += chosenPizzaPrice;
                totalCalories += chosenPizzaCalories;

                if(userMoneyTotal >= totalAmount){
                    if(RegularVendingMachine.hasEnoughChange(regularVendingMachine, pettyMoney, totalAmount, userMoneyTotal)){
                        accTotalAmount += totalAmount;
                        accTotalCalories += totalCalories;
                        display = specialVendingMachine.selectItem(itemChoice, itemQuantity, "ready");
                        specialVendingGUI.updateTextArea(display);
                        totalAmount = 0;
                    } else{
                        specialVendingGUI.updateTextArea("\nError: Not enough change available.\n");
                    }

                    // Ask if the user wants to make another purchase or exit the vending machine
                    int result = JOptionPane.showConfirmDialog(specialVendingGUI, "Do you want to buy another pizza? (Y/N)", "Buy Another Pizza", JOptionPane.YES_NO_OPTION);
                    if(result == JOptionPane.NO_OPTION){
                        specialVendingGUI.updateTextArea("\nHere are the pizzas that you bought:");
                        for (Transaction transaction : specialVendingMachine.getTransactionLog().getPizzaTransactionList()) {
                            specialVendingGUI.updateTextArea(transaction.getItem().getItemName() + " - " + transaction.getQuantity() + " - ₱" + transaction.getAmount());
                        }
                        
                        display = specialVendingMachine.prepareCustomPizza(chosenPizza);
                        specialVendingGUI.updateTextArea(display);
                        
                        specialVendingGUI.updateTextArea("Total Amount: ₱" + accTotalAmount);
                        specialVendingGUI.updateTextArea("Total Calories: " + accTotalCalories);
                        specialVendingGUI.updateTextArea("\nThank you for using the vending machine. Please take your change.");
                        display = regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                        specialVendingGUI.updateTextArea(display);
                        specialVendingGUI.updateTextArea("Change produced...\n");
                    }
                } else{
                    specialVendingGUI.updateTextArea("\nError: You do not have enough money to purchase this item/s.\n");
                    specialVendingGUI.updateTextArea("Total Amount: ₱" + totalAmount);
                    specialVendingGUI.updateTextArea("User Money: ₱" + userMoneyTotal + "\n");
                    totalAmount = 0;
                }
            } else{
                specialVendingGUI.updateTextArea("\nInvalid item number.\n");
            }
        }
    }
}
