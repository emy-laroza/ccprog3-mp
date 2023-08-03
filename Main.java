import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Main class represents the entry point of the vending machine program.
 * It provides the user interface and prompts for vending and maintenance operations.
 */
public class Main {

    private static void insertUserMoney(RegularVendingMachine regularVendingMachine){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert money to use the vending machine:");
        System.out.print("No. of ₱500: ");
        int num500 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱200: ");
        int num200 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱100: ");
        int num100 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱50: ");
        int num50 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱20: ");
        int num20 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱10: ");
        int num10 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱5: ");
        int num5 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱1: ");
        int num1 = scanner.nextInt();
        scanner.nextLine();

        Money userMoney;
        regularVendingMachine.receivePayment(new Money(num500, num200, num100, num50, num20, num10, num5, num1));
        regularVendingMachine.updatePettyMoney(num500, num200, num100, num50, num20, num10, num5, num1, "add");
        userMoney = regularVendingMachine.getReceivedPayment();

        int userMoneyTotal = userMoney.calculateTotalAmount();
        System.out.println("Amount Received: ₱" + userMoneyTotal);
        System.out.println("User money was updated.\n");
    }

    /**
     * Prompts the user for vending machine operations.
     * Allows the user to select items, make purchases, and receive change.
     *
     * @param regularVendingMachine The regular vending machine to operate on.
     */
    private static void vendingPrompt(RegularVendingMachine regularVendingMachine, SpecialVendingMachine specialVendingMachine, String indicator) {
        Scanner scanner = new Scanner(System.in);
        boolean vendingMenu = true;
        int totalAmount, accTotalAmount = 0; // Variable to keep track of the total amount
        int totalCalories, accTotalCalories = 0;

        if(indicator.equalsIgnoreCase("regular")){
            do {
                // Regular Vending Prompt
                totalAmount = 0;
                totalCalories = 0;
                int userMoneyTotal = regularVendingMachine.getReceivedPayment().calculateTotalAmount();
                Money pettyMoney = regularVendingMachine.getMoneyBank().getPettyMoney();

                if (userMoneyTotal < 1) {
                    System.out.println("Current user money: ₱" + userMoneyTotal + "\n");

                    System.out.print("\nInsert user money? Y/N: ");
                    String yn = scanner.nextLine();

                    if (yn.equalsIgnoreCase("Y")) {
                        insertUserMoney(regularVendingMachine);
                        vendingMenu = false;
                    } else {
                        vendingMenu = false;
                    }
                } else {
                    System.out.println("=================================");
                    regularVendingMachine.displayItems();
                    System.out.print("\nChoose the desired item (Item Number only) or enter 0 to cancel: ");
                    int itemChoice = scanner.nextInt();
                    scanner.nextLine();

                    // User chose to cancel
                    if (itemChoice == 0) {
                        System.out.print("Do you want to produce change without making a purchase? (Y/N): ");
                        String produceChangeMenu = scanner.nextLine();

                        if (produceChangeMenu.equalsIgnoreCase("Y")) {
                            regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                            System.out.println("\nChange produced...\n");
                            vendingMenu = false;
                        } else {
                            System.out.println("\nOkay...\n");
                            vendingMenu = false;
                        }
                        // continue;
                    } else if (itemChoice >= 1 && itemChoice <= 8) {
                        System.out.print("Choose the desired quantity (1-10): ");
                        int itemQuantityChoice = scanner.nextInt();
                        scanner.nextLine();

                        ItemSlot itemSlot = regularVendingMachine.itemSlots.get(itemChoice - 1);

                        if (itemSlot.isEmpty() || itemQuantityChoice > itemSlot.getQuantity() || itemQuantityChoice < 1) {
                            System.out.println("Item not available in the specified quantity.");
                            return;
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
                                regularVendingMachine.selectItem(itemChoice, itemQuantityChoice, "regular");
                            } else {
                                System.out.println("\nError: Not enough change available.\n");
                            }

                            // Ask if the user wants to make another purchase or exit the vending machine
                            System.out.print("\nDo you want to buy another item? (Y/N): ");
                            String buyAnotherItem = scanner.nextLine();
                            if (buyAnotherItem.equalsIgnoreCase("N")) {
                                System.out.println("\nHere are the items that you bought:");
                                for(Transaction transaction : regularVendingMachine.getTransactionLog().getTransactionList()){
                                    System.out.println(transaction.getItem().getItemName() + " - " + transaction.getQuantity() + " - ₱" + transaction.getAmount());
                                }
                                System.out.println("Total Amount: ₱" + accTotalAmount);
                                System.out.println("Total Calories: " + accTotalCalories);
                                System.out.println("\nThank you for using the vending machine. Please take your change.");
                                vendingMenu = false;
                                regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                                System.out.println("Change produced...\n");
                            }
                        } else {
                            System.out.println("\nError: You do not have enough money to purchase this item/s.\n");
                            System.out.println("Total Amount: ₱" + totalAmount);
                            System.out.println("User Money: ₱" + userMoneyTotal + "\n");
                        }
                    } else {
                        System.out.println("\nInvalid item number.\n");
                    }
                }
            } while (vendingMenu);
        } else{
            // Special Vending Prompt
            boolean specialVendingMenu = true;
            do {
                totalAmount = 0;
                totalCalories = 0;
                int userMoneyTotal = regularVendingMachine.getReceivedPayment().calculateTotalAmount();
                Money pettyMoney = regularVendingMachine.getMoneyBank().getPettyMoney();

                if (userMoneyTotal < 1) {
                    System.out.println("Current user money: ₱" + userMoneyTotal + "\n");

                    System.out.print("\nInsert user money? Y/N: ");
                    String yn = scanner.nextLine();
                    if (yn.equalsIgnoreCase("Y")) {
                        insertUserMoney(regularVendingMachine);
                        specialVendingMenu = false;
                    } else {
                        specialVendingMenu = false;
                    }
                } else {
                    System.out.println("=================================");
                    specialVendingMachine.displayItems();
                    System.out.println("=================================");
                    System.out.println("\nChoose 1 if you want to choose item/s only.");
                    System.out.println("Choose 2 if you want to create a pizza based on the items.");
                    System.out.println("Choose 3 if you want a ready-made pizza in the list.");
                    System.out.print("Enter choice: ");
                    String specialChoice = scanner.nextLine();

                    switch (specialChoice) {
                        case "1" -> {
                            // Item/s only
                            boolean itemMenu = true;
                            do {
                                userMoneyTotal = regularVendingMachine.getReceivedPayment().calculateTotalAmount();
                                pettyMoney = regularVendingMachine.getMoneyBank().getPettyMoney();

                                System.out.println("=================================");
                                regularVendingMachine.displayItems();
                                System.out.println("\nNote: Pepperoni, Bacon, and Ground Pork are the only items that can be sold individually.");
                                System.out.print("Choose the desired item (2, 4, or 8) or enter 0 to cancel: ");
                                int itemChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (itemChoice == 0) {
                                    System.out.print("Do you want to produce change without making a purchase? (Y/N): ");
                                    String produceChangeMenu = scanner.nextLine();

                                    if (produceChangeMenu.equalsIgnoreCase("Y")) {
                                        regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                                        System.out.println("\nChange produced...\n");
                                        itemMenu = false;
                                    } else {
                                        System.out.println("\nOkay...\n");
                                        itemMenu = false;
                                    }
                                } else if (itemChoice == 2 || itemChoice == 4 || itemChoice == 8) {
                                    System.out.print("Choose the desired quantity (1-10): ");
                                    int itemQuantityChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    ItemSlot itemSlot = specialVendingMachine.itemSlots.get(itemChoice - 1);

                                    if (itemSlot.isEmpty() || itemQuantityChoice > itemSlot.getQuantity() || itemQuantityChoice < 1) {
                                        System.out.println("Invalid slot number.");
                                        continue; // Restart the loop to choose another item
                                    }

                                    // Calculate the item price and update the total amount
                                    int itemPrice = specialVendingMachine.getItemPrice(itemChoice - 1, itemQuantityChoice);
                                    totalAmount += itemPrice;
                                    int itemCalories = specialVendingMachine.getItemCalories(itemChoice - 1, itemQuantityChoice);
                                    totalCalories += itemCalories;
                                    if (userMoneyTotal >= totalAmount) {
                                        if (RegularVendingMachine.hasEnoughChange(regularVendingMachine, pettyMoney, totalAmount, userMoneyTotal)) {
                                            accTotalAmount += totalAmount;
                                            accTotalCalories += itemCalories; // Update the total calories here instead of after the user exits
                                            regularVendingMachine.selectItem(itemChoice, itemQuantityChoice, "regular");
                                            totalAmount = 0;
                                        } else {
                                            System.out.println("\nError: Not enough change available.\n");
                                        }

                                        // Ask if the user wants to make another purchase or exit the vending machine
                                        System.out.print("\nDo you want to buy another item? (Y/N): ");
                                        String buyAnotherItem = scanner.nextLine();
                                        if (buyAnotherItem.equalsIgnoreCase("N")) {
                                            System.out.println("\nHere are the items that you bought:");
                                            for (Transaction transaction : specialVendingMachine.getTransactionLog().getTransactionList()) {
                                                System.out.println(transaction.getItem().getItemName());
                                            }
                                            System.out.println("Total Amount: ₱" + accTotalAmount);
                                            System.out.println("Total Calories: " + accTotalCalories);
                                            System.out.println("\nHere's the change: ");
                                            regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                                            System.out.println("Change produced...\n");
                                            itemMenu = false;
                                        }
                                    } else {
                                        System.out.println("\nError: You do not have enough money to purchase this item/s.\n");
                                        System.out.println("Total Amount: ₱" + totalAmount);
                                        System.out.println("User Money: ₱" + userMoneyTotal + "\n");
                                    }
                                } else {
                                    System.out.println("\nInvalid item number.\n");
                                }
                            } while (itemMenu);
                            specialVendingMenu = false;
                        }
                        case "2" -> {
                            // Pizza based on the items
                            boolean basedMenu = true;
                            do {
                                userMoneyTotal = regularVendingMachine.getReceivedPayment().calculateTotalAmount();
                                System.out.println("=================================");
                                regularVendingMachine.displayItems();
                                System.out.println("Do not choose 'Dough' as it will be automatically added in your order after choosing the toppings.");
                                System.out.print("Choose the desired topping/s for the pizza or enter 0 to cancel: ");
                                int itemChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (itemChoice == 0) {
                                    System.out.print("Do you want to produce change without making a purchase? (Y/N): ");
                                    String produceChangeMenu = scanner.nextLine();

                                    if (produceChangeMenu.equalsIgnoreCase("Y")) {
                                        regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                                        System.out.println("\nChange produced...");
                                        basedMenu = false;
                                    } else {
                                        System.out.println("\nOkay...\n");
                                        basedMenu = false;
                                    }
                                } else if (itemChoice >= 1 && itemChoice <= 8 && itemChoice != 7) {
                                    System.out.print("Choose the desired quantity (1-10): ");
                                    int itemQuantityChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    ItemSlot itemSlot = regularVendingMachine.itemSlots.get(itemChoice - 1);
                                    if (itemSlot.isEmpty() || itemQuantityChoice > itemSlot.getQuantity() || itemQuantityChoice < 1) {
                                        System.out.println("Invalid slot number.");
                                        continue;
                                    }

                                    // Calculate the item price and update the total amount
                                    int itemPrice = regularVendingMachine.getItemPrice(itemChoice - 1, itemQuantityChoice);
                                    totalAmount += itemPrice;
                                    int itemCalories = regularVendingMachine.getItemCalories(itemChoice - 1, itemQuantityChoice);
                                    totalCalories += itemCalories;

                                    if (userMoneyTotal >= totalAmount) {
                                        if (RegularVendingMachine.hasEnoughChange(regularVendingMachine, pettyMoney, totalAmount, userMoneyTotal)) {
                                            accTotalAmount += totalAmount;
                                            accTotalCalories += itemCalories; // Update the total calories here instead of after the user exits
                                            specialVendingMachine.selectItem(itemChoice, itemQuantityChoice, "based");
                                            totalAmount = 0;
                                        } else {
                                            System.out.println("\nError: Not enough change available.\n");
                                        }

                                        // Ask if the user wants to make another purchase or exit the vending machine
                                        System.out.print("\nDo you want to buy another topping? (Y/N): ");
                                        String buyAnotherItem = scanner.nextLine();
                                        if (buyAnotherItem.equalsIgnoreCase("N")) {

                                            // dough is added with the toppings
                                            Item dough = specialVendingMachine.itemSlots.get(7 - 1).getItem();
                                            specialVendingMachine.getToppings().add(dough);
                                            specialVendingMachine.transactionLog.addTransaction(new Transaction(dough, 1, dough.getItemPrice()));

                                            // prepare the pizza
                                            specialVendingMachine.preparePizza(specialVendingMachine.getToppings());

                                            System.out.println("\nTotal Amount: ₱" + accTotalAmount);
                                            System.out.println("Total Calories: " + accTotalCalories);
                                            System.out.println("\nHere's the change: ");
                                            regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                                            System.out.println("Change produced...\n");
                                            basedMenu = false;
                                        }
                                    } else {
                                        System.out.println("\nError: You do not have enough money to purchase this item/s.\n");
                                        System.out.println("Total Amount: ₱" + totalAmount);
                                        System.out.println("User Money: ₱" + userMoneyTotal + "\n");
                                    }
                                } else {
                                    System.out.println("\nInvalid item number.\n");
                                }
                            } while (basedMenu);
                            specialVendingMenu = false;
                        }
                        case "3" -> {
                            // Ready-made pizza
                            boolean readyMenu = true;
                            do {
                                userMoneyTotal = regularVendingMachine.getReceivedPayment().calculateTotalAmount();
                                System.out.println("=================================");
                                specialVendingMachine.displayItems();
                                System.out.print("Choose the desired custom pizza or enter 0 to cancel: ");
                                int pizzaChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (pizzaChoice == 0) {
                                    System.out.print("Do you want to produce change without making a purchase? (Y/N): ");
                                    String produceChangeMenu = scanner.nextLine();

                                    if (produceChangeMenu.equalsIgnoreCase("Y")) {
                                        regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                                        System.out.println("\nChange produced...");
                                        totalAmount = 0;
                                        readyMenu = false;
                                    } else {
                                        System.out.println("\nOkay...\n");
                                        readyMenu = false;
                                    }
                                } else if (pizzaChoice >= 1 && pizzaChoice <= 6) {
                                    int itemQuantity = 1;

                                    ItemSlot itemSlot = specialVendingMachine.itemSlots.get(pizzaChoice - 1);
                                    if (itemSlot.isEmpty() || itemQuantity > itemSlot.getQuantity()) {
                                        System.out.println("Invalid slot number.");
                                        continue;
                                    }

                                    Item chosenPizza = specialVendingMachine.getCustomItems().get(pizzaChoice - 1);
                                    int chosenPizzaPrice = chosenPizza.getItemPrice();
                                    int chosenPizzaCalories = chosenPizza.getItemCalories();
                                    totalAmount += chosenPizzaPrice;
                                    totalCalories += chosenPizzaCalories;

                                    if (userMoneyTotal >= totalAmount) {
                                        if (RegularVendingMachine.hasEnoughChange(regularVendingMachine, pettyMoney, totalAmount, userMoneyTotal)) {
                                            accTotalAmount += totalAmount;
                                            accTotalCalories += totalCalories; // Update the total calories here instead of after the user exits
                                            specialVendingMachine.selectItem(pizzaChoice, itemQuantity, "ready");
                                            totalAmount = 0;
                                        } else {
                                            System.out.println("\nError: Not enough change available.\n");
                                        }

                                        // Ask if the user wants to make another purchase or exit the vending machine
                                        System.out.print("\nDo you want to buy another pizza? (Y/N): ");
                                        String buyAnotherItem = scanner.nextLine();
                                        if (buyAnotherItem.equalsIgnoreCase("N")) {
                                            System.out.println("\nHere are the pizzas that you bought:");
                                            for (Transaction transaction : specialVendingMachine.getTransactionLog().getPizzaTransactionList()) {
                                                System.out.println(transaction.getItem().getItemName() + ": ₱" + transaction.getAmount() + ", " + transaction.getQuantity());
                                            }

                                            specialVendingMachine.prepareCustomPizza(chosenPizza);

                                            System.out.println("\nTotal Amount: ₱" + accTotalAmount);
                                            System.out.println("Total Calories: " + accTotalCalories);
                                            System.out.println("\nHere's the change: ");
                                            regularVendingMachine.returnUserMoney(regularVendingMachine, regularVendingMachine.getReceivedPayment());
                                            System.out.println("Change produced...\n");
                                            readyMenu = false;
                                        }
                                    } else {
                                        System.out.println("\nError: You do not have enough money to purchase this pizza.\n");
                                        System.out.println("Total Amount: ₱" + totalAmount);
                                        System.out.println("User Money: ₱" + userMoneyTotal + "\n");
                                        totalAmount = 0;
                                    }
                                } else {
                                    System.out.println("\nInvalid item number.\n");
                                }
                            } while (readyMenu);
                            specialVendingMenu = false;
                        }
                        default -> System.out.println("Invalid choice. Choose again.");
                    }
                }
            } while (specialVendingMenu);
        }
    }

    /**
     * Prompts the user for maintenance operations on the vending machine.
     *
     * @param regularVendingMachine The regular vending machine to perform maintenance on.
     */
    private static void maintenancePrompt(RegularVendingMachine regularVendingMachine, SpecialVendingMachine specialVendingMachine, String vendingIndicator) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("[1] - Restock Items");
        System.out.println("[2] - Set Item Prices");
        System.out.println("[3] - Replenish Change");
        System.out.println("[4] - Print Transaction Summary");
        System.out.println("[5] - Go Back to Previous Menu");
        System.out.print("Enter choice: ");
        String maintenanceChoice = scanner.nextLine();
        boolean validMaintenanceChoice = maintenanceChoice.equals("1")
                || maintenanceChoice.equals("2")
                || maintenanceChoice.equals("3")
                || maintenanceChoice.equals("4")
                || maintenanceChoice.equals("5");
        if(validMaintenanceChoice){
            switch (maintenanceChoice) {
                case "1" -> {
                    // restock items
                    System.out.println("=================================");
                    if(vendingIndicator.equalsIgnoreCase("regular")){
                        regularVendingMachine.displayItems();
                        System.out.print("Choose the item number that you want to restock: ");
                        int restockItemChoice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("How many items should be added: ");
                        int restockQuantity = scanner.nextInt();
                        scanner.nextLine();
                        regularVendingMachine.restockItems(restockItemChoice, restockQuantity);
                    } else{
                        regularVendingMachine.displayItems();
                        System.out.print("Choose the item number that you want to restock: ");
                        int restockItemChoice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("How many items should be added: ");
                        int restockQuantity = scanner.nextInt();
                        scanner.nextLine();
                        regularVendingMachine.restockItems(restockItemChoice, restockQuantity);
                    }
                }
                case "2" -> {
                    // set item prices
                    System.out.println("=================================");
                    if(vendingIndicator.equalsIgnoreCase("regular")){
                        regularVendingMachine.displayItems();
                        System.out.print("Choose the item number to set a new price: ");
                        int newItemPriceChoice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter the new price: ");
                        int newItemPrice = scanner.nextInt();
                        scanner.nextLine();
                        regularVendingMachine.setPrice(newItemPriceChoice, newItemPrice);
                    } else {
                        specialVendingMachine.displayItems();
                        System.out.print("Choose the item number to set a new price: ");
                        int newItemPriceChoice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter the new price: ");
                        int newItemPrice = scanner.nextInt();
                        scanner.nextLine();
                        specialVendingMachine.setPrice(newItemPriceChoice, newItemPrice);
                    }
                }
                case "3" -> {
                    // replenish change
                    System.out.println("=================================");
                    if(vendingIndicator.equalsIgnoreCase("regular")){
                        regularVendingMachine.displayPettyMoney(regularVendingMachine.getMoneyBank().getPettyMoney());
                    } else{
                        specialVendingMachine.displayPettyMoney(specialVendingMachine.getMoneyBank().getPettyMoney());
                    }
                    System.out.print("Enter the number of ₱500 bills to replenish: ");
                    int num500 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the number of ₱200 bills to replenish: ");
                    int num200 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the number of ₱100 bills to replenish: ");
                    int num100 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the number of ₱50 bills to replenish: ");
                    int num50 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the number of ₱20 bills to replenish: ");
                    int num20 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the number of ₱10 coins to replenish: ");
                    int num10 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the number of ₱5 coins to replenish: ");
                    int num5 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the number of ₱1 coin to replenish: ");
                    int num1 = scanner.nextInt();
                    scanner.nextLine();

                    if(vendingIndicator.equalsIgnoreCase("regular")){
                        regularVendingMachine.replenishPettyMoney(num500, num200, num100, num50, num20, num10, num5, num1);
                    } else{
                        specialVendingMachine.replenishPettyMoney(num500, num200, num100, num50, num20, num10, num5, num1);
                    }
                    System.out.println("Change replenished successfully.");
                }
                case "4" -> {
                    System.out.println("=================================");
                    // transaction summary
                    if(vendingIndicator.equalsIgnoreCase("regular")){
                        regularVendingMachine.displayTransactionSummary();
                    } else{
                        specialVendingMachine.displayTransactionSummary();
                    }
                }
                case "5" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Choose again.");
            }
        } else{
            System.out.println("Invalid choice. Choose again.");
        }
    }

    /**
     * The main method is the entry point of the vending machine program.
     * It initializes the vending machine and provides the user interface.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<RegularVendingMachine> regularVendingMachines = new ArrayList<RegularVendingMachine>();
        regularVendingMachines.add(new RegularVendingMachine("JAVIER-LAROZA PIZZARIA"));
        ArrayList<SpecialVendingMachine> specialVendingMachines = new ArrayList<SpecialVendingMachine>();
        specialVendingMachines.add(new SpecialVendingMachine("JAVIER-LAROZA PIZZARIA"));

        System.out.println("=================================");
        System.out.println("WELCOME TO " + regularVendingMachines.get(0).getName());
        System.out.println("=================================");

        // display products
        regularVendingMachines.get(0).initializeItemSlots();
        specialVendingMachines.get(0).initializeCustomItems();
        regularVendingMachines.get(0).displayItems();
        System.out.println("=================================");
        specialVendingMachines.get(0).displayItems();
        System.out.println("=================================");

        // ask user to insert money
        System.out.println("\nInsert money to use the vending machine:");
        System.out.print("No. of ₱500: ");
        int num500 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱200: ");
        int num200 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱100: ");
        int num100 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱50: ");
        int num50 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱20: ");
        int num20 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱10: ");
        int num10 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱5: ");
        int num5 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("No. of ₱1: ");
        int num1 = scanner.nextInt();
        scanner.nextLine();

        regularVendingMachines.get(0).receivePayment(new Money(num500, num200, num100, num50, num20, num10, num5, num1));
        regularVendingMachines.get(0).updatePettyMoney(num500, num200, num100, num50, num20, num10, num5, num1, "add");
        Money userMoney = regularVendingMachines.get(0).getReceivedPayment();
        int userMoneyTotal = userMoney.calculateTotalAmount();
        System.out.println("Amount Received: ₱" + userMoneyTotal);

        boolean menu1 = true;
        do{
            System.out.println("\n=================================");
            System.out.println("[1] - Regular Vending Machine");
            System.out.println("[2] - Special Vending Machine");
            System.out.println("[3] - Exit");

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            boolean validChoice = choice.equals("1") || choice.equals("2") || choice.equals("3");
            if(validChoice){
                switch (choice) {
                    case "1" -> {
                        boolean menu2 = true;
                        do{
                            System.out.println("=================================");
                            System.out.println("[1] - Regular Vending Features");
                            System.out.println("[2] - Maintenance Features");
                            System.out.println("[3] - Go Back to Main Menu");
                            System.out.print("Enter choice: ");
                            String choice2 = scanner.nextLine();
                            boolean validChoice2 = choice2.equals("1") || choice2.equals("2") || choice2.equals("3");
                            if(validChoice2){
                                switch (choice2) {
                                    case "1" -> {
                                        // Vending Features
                                        vendingPrompt(regularVendingMachines.get(0), specialVendingMachines.get(0), "regular");
                                        menu2 = false;
                                    }
                                    case "2" -> {
                                        // Maintenance Features
                                        maintenancePrompt(regularVendingMachines.get(0), specialVendingMachines.get(0), "regular");
                                        menu2 = false;
                                    }
                                    case "3" -> {
                                        menu2 = false;
                                        System.out.println("=================================");
                                        System.out.println("WELCOME TO " + regularVendingMachines.get(0).getName());
                                    }
                                }
                            } else{
                                System.out.println("Invalid choice. Choose again.");
                            }
                        } while(menu2);
                    }
                    case "2" -> {
                        // Special Vending Machine
                        boolean menu3 = true;
                        do {
                            System.out.println("=================================");
                            System.out.println("[1] - Special Vending Features");
                            System.out.println("[2] - Maintenance Features");
                            System.out.println("[3] - Go Back to Main Menu");
                            System.out.print("Enter choice: ");
                            String choice3 = scanner.nextLine();
                            boolean validChoice3 = choice3.equals("1") || choice3.equals("2") || choice3.equals("3");
                            if (validChoice3) {
                                switch (choice3) {
                                    case "1" -> {
                                        // Special Vending Features
                                        vendingPrompt(regularVendingMachines.get(0), specialVendingMachines.get(0), "special");
                                        menu3 = false;
                                    }
                                    case "2" -> {
                                        // Special Maintenance Features
                                        maintenancePrompt(regularVendingMachines.get(0), specialVendingMachines.get(0), "special");
                                        menu3 = false;
                                    }
                                    case "3" -> {
                                        menu3 = false;
                                        System.out.println("=================================");
                                        System.out.println("WELCOME TO " + specialVendingMachines.get(0).getName());
                                    }
                                }
                            } else {
                                System.out.println("Invalid choice. Choose again.");
                            }
                        } while (menu3);
                    }
                    case "3" -> menu1 = false;
                }
            } else {
                System.out.println("Invalid choice. Choose again.");
            }
        } while(menu1);
        System.out.println("Thank you for using the vending machine. Please come again!");
    }
}
