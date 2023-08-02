import java.util.ArrayList;

/**
 * The SpecialVendingMachine class represents a vending machine that extends the functionality of a RegularVendingMachine
 * to include custom items and toppings for pizzas.
 */
public class SpecialVendingMachine extends RegularVendingMachine {
    private ArrayList<Item> customItems;
    private ArrayList<Item> toppings;

    // Constructor
    /**
     * Constructs a new SpecialVendingMachine with the given name.
     *
     * @param name The name of the special vending machine.
     */
    public SpecialVendingMachine(String name) {
        super(name);
        customItems = new ArrayList<>();
        toppings = new ArrayList<>();
    }

    // Getters
    /**
     * Retrieves the list of custom items available in the special vending machine.
     *
     * @return The list of custom items.
     */
    public ArrayList<Item> getCustomItems() {
        return customItems;
    }

    /**
     * Retrieves the list of toppings available in the special vending machine.
     *
     * @return The list of toppings.
     */
    public ArrayList<Item> getToppings() {
        return toppings;
    }

    /**
     * Initializes the custom items available in the special vending machine by adding predefined pizza items.
     */
    public void initializeCustomItems() {
        // Item 1
        Item item1 = new Item("Pepperoni Pizza", 561, 1063);
        customItems.add(item1);

        // Item 2
        Item item2 = new Item("Veggie Pizza", 367, 333);
        customItems.add(item2);

        // Item 3
        Item item3 = new Item("Cheesy Bacon", 487, 1110);
        customItems.add(item3);

        // Item 4
        Item item4 = new Item("Hawaiian Pizza", 449, 833);
        customItems.add(item4);

        // Item 5
        Item item5 = new Item("Meaty Pizza", 494, 1024);
        customItems.add(item5);

        // Item 6
        Item item6 = new Item("Overload Pizza", 1008, 1939);
        customItems.add(item6);
    }

    /**
     * {@inheritDoc}
     *
     * Overrides the displayItems method in the RegularVendingMachine class to display the list of custom pizzas
     * available in the special vending machine.
     *
     * @return A string containing the list of custom pizzas with their names, prices, and calories.
     */
    @Override
    public String displayItems() {
        StringBuilder sb = new StringBuilder("List of Custom Pizzas:\n");
        for (int i = 0; i < customItems.size(); i++) {
            Item customPizza = customItems.get(i);
            sb.append("Pizza ").append(i + 1).append(": ").append(customPizza.getItemName())
                    .append(" - Price: ").append(customPizza.getItemPrice())
                    .append(" - Calories: ").append(customPizza.getItemCalories()).append("\n");
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     *
     * Overrides the selectItem method in the RegularVendingMachine class to handle the selection of items, toppings,
     * and ready-made pizzas in the special vending machine.
     *
     * @param itemNumber The number of the selected item.
     * @param quantity   The quantity of the selected item or topping.
     * @param indicator  A string indicator specifying the type of selection: "regular" for regular items, "based" for
     *                   pizza toppings, and "ready" for ready-made pizzas.
     * @return A string containing information about the selected item, topping, or pizza.
     */
    @Override
    public String selectItem(int itemNumber, int quantity, String indicator) {
        StringBuilder sb = new StringBuilder("Selecting item...");
        switch (indicator) {
            case "regular" -> {
                // Item/s only
                super.selectItem(itemNumber, quantity, indicator);
            }
            case "based" -> {
                // Pizza based on items
                // Check if the item number is valid
                if (itemNumber < 1 || itemNumber - 1 > itemSlots.size()) {
                    sb.append("Invalid slot number.\n");
                    return sb.toString();
                }

                ItemSlot itemSlot = itemSlots.get(itemNumber - 1);
                Item item = itemSlot.getItem();

                // Check if the item is available in the specified quantity
                if (itemSlot.isEmpty() || quantity > itemSlot.getQuantity() || quantity < 1) {
                    sb.append("Item not available in the specified quantity.\n");
                    return sb.toString();
                }

                int totalAmount = item.getItemPrice() * quantity;
                int totalCalories = item.getItemCalories() * quantity;
                sb.append("\nYou added the topping, ").append(item.getItemName()).append(", for the pizza.\n");
                sb.append("Amount: ₱").append(totalAmount).append("\n");
                sb.append("Calories: ").append(totalCalories).append("\n");

                // Update item slot quantity
                itemSlot.decreaseQuantity(quantity);

                toppings.add(item);
                sb.append("Topping added successfully.\n");

                // Log the transaction
                transactionLog.addTransaction(new Transaction(item, quantity, totalAmount));
            }
            case "ready" -> {
                // ready-made pizza

                Item chosenPizza = customItems.get(itemNumber - 1);

                // check the quantity of items
                if (!checkItemAvailability(chosenPizza)) {
                    sb.append(chosenPizza.getItemName()).append(" is not available. Choose again\n");
                    return sb.toString();
                }

                int totalAmount = chosenPizza.getItemPrice() * quantity;
                int totalCalories = chosenPizza.getItemCalories() * quantity;
                sb.append("Amount: ₱").append(totalAmount).append("\n");
                sb.append("Calories: ").append(totalCalories).append("\n");

                // Decrease the quantity of toppings
                sb.append(decreasePizzaQuantity(chosenPizza, 1));

                // Log the pizza transaction
                transactionLog.addPizzaTransaction(new Transaction(chosenPizza, quantity, totalAmount));
            }
            default -> sb.append("Error\n");
        }
        return sb.toString();
    }

    /**
     * Checks the availability of the required toppings for a specific pizza.
     *
     * @param pizza The Item representing the pizza for which to check topping availability.
     * @return True if all required toppings for the specified pizza are available, otherwise false.
     */
    public boolean checkItemAvailability(Item pizza) {
        switch (pizza.getItemName().toLowerCase()) {
            case "pepperoni pizza" -> {
                if (checkToppingAvailability("Pepperoni", 1) ||
                        checkToppingAvailability("Mozzarella", 1) ||
                        checkToppingAvailability("Dough", 1)) {
                    return true;
                }
            }
            case "veggie pizza" -> {
                if (checkToppingAvailability("Tomatoes", 1) ||
                        checkToppingAvailability("Basil", 1) ||
                        checkToppingAvailability("Dough", 1)) {
                    return true;
                }
            }
            case "cheesy bacon" -> {
                if (checkToppingAvailability("Bacon", 1) ||
                        checkToppingAvailability("Mozzarella", 1) ||
                        checkToppingAvailability("Dough", 1)) {
                    return true;
                }
            }
            case "hawaiian pizza" -> {
                if (checkToppingAvailability("Pepperoni", 1) ||
                        checkToppingAvailability("Pineapple", 1) ||
                        checkToppingAvailability("Dough", 1)) {
                    return true;
                }
            }
            case "meaty pizza" -> {
                if (checkToppingAvailability("Pepperoni", 1) ||
                        checkToppingAvailability("Ground Pork", 1) ||
                        checkToppingAvailability("Dough", 1)) {
                    return true;
                }
            }
            case "overload pizza" -> {
                if (checkToppingAvailability("Mozzarella", 1) ||
                        checkToppingAvailability("Pepperoni", 1) ||
                        checkToppingAvailability("Tomatoes", 1) ||
                        checkToppingAvailability("Bacon", 1) ||
                        checkToppingAvailability("Basil", 1) ||
                        checkToppingAvailability("Pineapple", 1) ||
                        checkToppingAvailability("Ground Pork", 1) ||
                        checkToppingAvailability("Dough", 1)) {
                    return true;
                }
            }
            default -> {
                System.out.println("Invalid pizza name.");
                return false;
            }
        }
        return false;
    }

    /**
     * Checks the availability of a specific topping in the vending machine.
     *
     * @param toppingName      The name of the topping to check.
     * @param requiredQuantity The quantity of the topping required.
     * @return True if the topping is available in the required quantity, otherwise false.
     */
    public boolean checkToppingAvailability(String toppingName, int requiredQuantity) {
        // Find the item slot for the specified topping
        for (ItemSlot itemSlot : itemSlots) {
            if (itemSlot.getItem().getItemName().equals(toppingName)) {
                // Check if the topping is available in the required quantity
                if (itemSlot.getQuantity() >= requiredQuantity) {
                    return true; // Topping is available in the required quantity
                } else {
                    return false; // Topping is not available in the required quantity
                }
            }
        }
        // Topping is not found in the vending machine
        return false;
    }

    /**
     * Decreases the quantity of toppings used to prepare the specified pizza.
     *
     * @param pizza The pizza item for which the toppings need to be decreased.
     * @param quantity The quantity of pizzas to be prepared and, thus, the quantity of toppings to be decreased.
     * @return A string representation of the result of decreasing the toppings' quantity.
     */
    public String decreasePizzaQuantity(Item pizza, int quantity) {
        StringBuilder sb = new StringBuilder();

        switch (pizza.getItemName().toLowerCase()) {
            case "pepperoni pizza" -> {
                sb.append(decreaseToppingQuantity("Pepperoni", quantity));
                sb.append(decreaseToppingQuantity("Mozzarella", quantity));
                sb.append(decreaseToppingQuantity("Dough", quantity));
                break;
            }
            case "veggie pizza" -> {
                sb.append(decreaseToppingQuantity("Tomatoes", quantity));
                sb.append(decreaseToppingQuantity("Basil", quantity));
                sb.append(decreaseToppingQuantity("Dough", quantity));
                break;
            }
            case "cheesy bacon" -> {
                sb.append(decreaseToppingQuantity("Bacon", quantity));
                sb.append(decreaseToppingQuantity("Mozzarella", quantity));
                sb.append(decreaseToppingQuantity("Dough", quantity));
                break;
            }
            case "hawaiian pizza" -> {
                sb.append(decreaseToppingQuantity("Pepperoni", quantity));
                sb.append(decreaseToppingQuantity("Pineapple", quantity));
                sb.append(decreaseToppingQuantity("Dough", quantity));
                break;
            }
            case "meaty pizza" -> {
                sb.append(decreaseToppingQuantity("Pepperoni", quantity));
                sb.append(decreaseToppingQuantity("Ground Pork", quantity));
                sb.append(decreaseToppingQuantity("Dough", quantity));
                break;
            }
            case "overload pizza" -> {
                sb.append(decreaseToppingQuantity("Mozzarella", quantity));
                sb.append(decreaseToppingQuantity("Pepperoni", quantity));
                sb.append(decreaseToppingQuantity("Tomatoes", quantity));
                sb.append(decreaseToppingQuantity("Bacon", quantity));
                sb.append(decreaseToppingQuantity("Basil", quantity));
                sb.append(decreaseToppingQuantity("Pineapple", quantity));
                sb.append(decreaseToppingQuantity("Ground Pork", quantity));
                sb.append(decreaseToppingQuantity("Dough", quantity));
                break;
            }
            default -> sb.append("Invalid pizza\n");
        }

        return sb.toString();
    }

    /**
     * Decreases the quantity of a specific topping in the vending machine.
     *
     * @param toppingName The name of the topping to decrease the quantity of.
     * @param quantity    The quantity of the topping to be decreased.
     */
    public String decreaseToppingQuantity(String toppingName, int quantity) {
        StringBuilder sb = new StringBuilder();

        // Find the item slot for the specified topping
        for (ItemSlot itemSlot : itemSlots) {
            if (itemSlot.getItem().getItemName().equalsIgnoreCase(toppingName)) {
                // Check if there's enough quantity to decrease
                if (itemSlot.getQuantity() >= quantity && quantity > 0) {
                    // Decrease the quantity of the topping
                    itemSlot.decreaseQuantity(quantity);
                    sb.append("Decreased ").append(toppingName).append(" quantity by ").append(quantity).append("\n");
                } else {
                    sb.append("Invalid quantity to decrease for ").append(toppingName).append("\n");
                }
                return sb.toString();
            }
        }

        // Topping is not found in the vending machine
        sb.append("Topping ").append(toppingName).append(" not available in the vending machine.\n");
        return sb.toString();
    }

    /**
     * Prepares a custom pizza with the given list of toppings.
     *
     * @param toppings The list of toppings to be added to the pizza.
     * @return A string representing the preparation steps and ingredients of the pizza.
     */
    public String preparePizza(ArrayList<Item> toppings) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nPreparing the pizza...");
        sb.append("\nGathering the ingredients...");
        sb.append("\nAssembling the pizza with ");

        // Topping with each topping
        for (int i = 0; i < toppings.size(); i++) {
            Item topping = toppings.get(i);

            if (i == toppings.size() - 1) {
                sb.append(topping.getItemName());
            } else {
                sb.append(topping.getItemName()).append(" and ");
            }
        }
        sb.append("...");

        sb.append("\nHeating it in the oven...");
        sb.append("\nPizza done! Enjoy the food!");

        return sb.toString();
    }

    /**
     * Prepares a custom pizza based on the chosen pizza item.
     *
     * @param chosenPizza The custom pizza item selected.
     * @return A string representation of the steps taken to prepare the custom pizza.
     */
    public String prepareCustomPizza(Item chosenPizza) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nPreparing the pizza...");
        sb.append("\nGathering the ingredients...");
        sb.append("\nAssembling the pizza with ");

        String pizzaName = chosenPizza.getItemName();
        if (pizzaName.equals("Pepperoni Pizza")) {
            sb.append("Pepperoni and Mozzarella and Dough...");
        } else if (pizzaName.equals("Veggie Pizza")) {
            sb.append("Tomatoes and Basil and Dough...");
        } else if (pizzaName.equals("Cheesy Bacon")) {
            sb.append("Bacon and Mozzarella and Dough...");
        } else if (pizzaName.equals("Hawaiian Pizza")) {
            sb.append("Pepperoni and Pineapple and Dough...");
        } else if (pizzaName.equals("Meaty Pizza")) {
            sb.append("Pepperoni and Ground Pork and Dough...");
        } else if (pizzaName.equals("Overload Pizza")) {
            sb.append("Mozzarella and Pepperoni and Tomatoes and Bacon and Basil and Pineapple and Ground Pork and Dough...");
        } else {
            sb.append("Invalid pizza");
        }

        sb.append("\nHeating it in the oven...");
        sb.append("\nPizza done! Enjoy the food!");

        return sb.toString();
    }

    /**
     * {@inheritDoc}
     *
     * Sets the price of a custom item in the vending machine.
     *
     * @param itemSlotNumber The slot number of the custom item.
     * @param newPrice The new price to be set for the custom item.
     */
    @Override
    public void setPrice(int itemSlotNumber, int newPrice) {
        Item customItem = customItems.get(itemSlotNumber - 1);
        customItem.setItemPrice(newPrice);
    }

    /**
     * {@inheritDoc}
     *
     * Displays a summary of the transactions made in the vending machine,
     * including the total quantity sold and the total amount collected for custom pizzas.
     *
     * @return A string representation of the transaction summary.
     */
    @Override
    public String displayTransactionSummary() {
        StringBuilder sb = new StringBuilder();
        super.displayTransactionSummary();

        sb.append("\nCustom Pizzas Sold:\n");
        int totalPizzasSold = 0;
        int totalPizzaAmountCollected = 0;
        for (Transaction transaction : transactionLog.getPizzaTransactionList()) {
            int quantity = transaction.getQuantity();
            int amount = transaction.getAmount();
            totalPizzasSold += quantity;
            totalPizzaAmountCollected += amount;
            sb.append(transaction.getItem().getItemName()).append(": ₱").append(transaction.getAmount()).append(" - ").append(transaction.getQuantity()).append("\n");
        }
        sb.append("\nTotal Quantity Sold: ").append(totalPizzasSold).append("\n");
        sb.append("Total Amount Collected: ₱").append(totalPizzaAmountCollected).append("\n");

        return sb.toString();
    }
}
