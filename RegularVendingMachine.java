import java.util.*;
/**
 * Represents a regular vending machine that manages item slots, transactions, money bank, and payments.
 */
public class RegularVendingMachine {
    protected ArrayList<ItemSlot> itemSlots;
    protected TransactionLog transactionLog;
    protected int slotCapacity;
    protected MoneyBank moneyBank;
    protected Money receivedPayment;
    protected String name;

    // Constructor
    /**
     * Constructs a new RegularVendingMachine object with the specified name.
     *
     * @param name the name of the vending machine
     */
    public RegularVendingMachine(String name) {
        this.name = name;
        this.slotCapacity = 10; // Set the default slot capacity
        itemSlots = new ArrayList<ItemSlot>(slotCapacity); 
        transactionLog = new TransactionLog();
        receivedPayment = new Money(0, 0, 0, 0, 0, 0, 0, 0);
        moneyBank = new MoneyBank(10, 10, 10, 10, 10, 10, 10, 10);
        initializeItemSlots(); // Initialize the item slots with default items
    }

    // Getters
    /**
     * Retrieves the name of the vending machine.
     *
     * @return the name of the vending machine
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the money bank associated with the vending machine.
     *
     * @return the money bank
     */
    public MoneyBank getMoneyBank() {
        return moneyBank;
    }

    /**
     * Retrieves the money received as payment from the user.
     *
     * @return the received payment
     */
    public Money getReceivedPayment() {
        return receivedPayment;
    }

    /**
     * Retrieves the transaction log that stores the details of all the transactions made in the vending machine.
     *
     * @return the transaction log containing transaction details
     */
    public TransactionLog getTransactionLog(){ return transactionLog;}

    // Setter

    /**
     * Sets the money received as payment from the user.
     *
     * @param money the money received as payment
     */
    public void setReceivePayment(Money money) {
	receivedPayment = money;
    }

    // Methods

    /**
     * Initializes the item slots with default items.
     */
    public void initializeItemSlots() {
        // Slot 1
        ItemSlot itemSlot1 = new ItemSlot(new Item("Mozzarella", 172, 280), 10);
        itemSlots.add(itemSlot1);

        // Slot 2
        ItemSlot itemSlot2 = new ItemSlot(new Item("Pepperoni", 189, 494), 10);
        itemSlots.add(itemSlot2);

        // Slot 3
        ItemSlot itemSlot3 = new ItemSlot(new Item("Tomatoes", 27, 22), 10);
        itemSlots.add(itemSlot3);

        // Slot 4
        ItemSlot itemSlot4 = new ItemSlot(new Item("Bacon", 115, 541), 10);
        itemSlots.add(itemSlot4);

        // Slot 5
        ItemSlot itemSlot5 = new ItemSlot(new Item("Basil", 140, 22), 10);
        itemSlots.add(itemSlot5);

        // Slot 6
        ItemSlot itemSlot6 = new ItemSlot(new Item("Pineapple", 60, 50), 10);
        itemSlots.add(itemSlot6);

        // Slot 7
        ItemSlot itemSlot7 = new ItemSlot(new Item("Dough", 200, 289), 10);
        itemSlots.add(itemSlot7);

        // Slot 8
        ItemSlot itemSlot8 = new ItemSlot(new Item("Ground Pork", 105, 241), 10);
        itemSlots.add(itemSlot8);
    }

    /**
     * Generates a formatted list of toppings and their details for display.
     *
     * @return a string containing the list of toppings with their prices, calories, and quantities
     */
    public String displayItems() {
        StringBuilder sb = new StringBuilder("List of Toppings:\n");
        for (int i = 0; i < 8; i++) {
            ItemSlot itemSlot = itemSlots.get(i);
            Item item = itemSlot.getItem();
            int quantity = itemSlot.getQuantity();

            sb.append("Item ").append(i + 1).append(": ").append(item.getItemName())
                    .append(" - Price: ").append(item.getItemPrice())
                    .append(" - Calories: ").append(item.getItemCalories())
                    .append(" - Quantity: ").append(quantity).append("\n");
        }
        return sb.toString();
    }

    /**
     * Receives payment from the user.
     *
     * @param paymentMoney the money received as payment
     */
    public void receivePayment(Money paymentMoney) {
        receivedPayment.set500Num(paymentMoney.get500Num());
        receivedPayment.set200Num(paymentMoney.get200Num());
        receivedPayment.set100Num(paymentMoney.get100Num());
        receivedPayment.set50Num(paymentMoney.get50Num());
        receivedPayment.set20Num(paymentMoney.get20Num());
        receivedPayment.set10Num(paymentMoney.get10Num());
        receivedPayment.set5Num(paymentMoney.get5Num());
        receivedPayment.set1Num(paymentMoney.get1Num());
    }

    /**
     * Generates a string containing the denominations and counts of the change to be returned to the user.
     * Resets the received payment in the vending machine after returning the change.
     *
     * @param regularVendingMachine the RegularVendingMachine object to reset the received payment
     * @param userMoney the Money object representing the money to be returned
     * @return a formatted string containing the denominations and counts of the change to be returned
     */
    public String returnUserMoney(RegularVendingMachine regularVendingMachine, Money userMoney) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nDispensing change in denominations...\n");

        if (userMoney.get500Num() > 0) {
            sb.append("₱500 Count: ").append(userMoney.get500Num()).append("\n");
        }
        if (userMoney.get200Num() > 0) {
            sb.append("₱200 Count: ").append(userMoney.get200Num()).append("\n");
        }
        if (userMoney.get100Num() > 0) {
            sb.append("₱100 Count: ").append(userMoney.get100Num()).append("\n");
        }
        if (userMoney.get50Num() > 0) {
            sb.append("₱50 Count: ").append(userMoney.get50Num()).append("\n");
        }
        if (userMoney.get20Num() > 0) {
            sb.append("₱20 Count: ").append(userMoney.get20Num()).append("\n");
        }
        if (userMoney.get10Num() > 0) {
            sb.append("₱10 Count: ").append(userMoney.get10Num()).append("\n");
        }
        if (userMoney.get5Num() > 0) {
            sb.append("₱5 Count: ").append(userMoney.get5Num()).append("\n");
        }
        if (userMoney.get1Num() > 0) {
            sb.append("₱1 Count: ").append(userMoney.get1Num()).append("\n");
        }
        regularVendingMachine.setReceivePayment(new Money(0, 0, 0, 0, 0, 0, 0, 0));
        int total = userMoney.calculateTotalAmount();
        sb.append("Total Change: ₱").append(total);

        return sb.toString();
    }

    /**
     * Checks if the vending machine has enough change to give to the user after a purchase.
     * If enough change is available, it updates the petty money and the money received as payment.
     *
     * @param regularVendingMachine The RegularVendingMachine instance used to access the petty money and update the received payment.
     * @param money The Money object representing the current money in the vending machine.
     * @param itemPrice The price of the item being purchased.
     * @param payment The total amount of money received from the user.
     * @return true if enough change is available and the petty money is updated, false otherwise.
     */
    public static boolean hasEnoughChange(RegularVendingMachine regularVendingMachine, Money money, int itemPrice, int payment) {
        int change = payment - itemPrice;
        int fiveHundreds = money.get500Num();
        int twoHundreds = money.get200Num();
        int hundreds = money.get100Num();
        int fifties = money.get50Num();
        int twenties = money.get20Num();
        int tens = money.get10Num();
        int fives = money.get5Num();
        int ones = money.get1Num();

        int fiveHundredsCount = 0;
        int twoHundredsCount = 0;
        int hundredsCount = 0;
        int fiftiesCount = 0;
        int twentiesCount = 0;
        int tensCount = 0;
        int fivesCount = 0;
        int onesCount = 0;

        int userChange = change;

        while (change >= 500 && fiveHundreds > 0) {
            fiveHundredsCount++;
            fiveHundreds--;
            change -= 500;
        }

        while (change >= 200 && twoHundreds > 0) {
            twoHundredsCount++;
            twoHundreds--;
            change -= 200;
        }

        while (change >= 100 && hundreds > 0) {
            hundredsCount++;
            hundreds--;
            change -= 100;
        }

        while (change >= 50 && fifties > 0) {
            fiftiesCount++;
            fifties--;
            change -= 50;
        }

        while (change >= 20 && twenties > 0) {
            twentiesCount++;
            twenties--;
            change -= 20;
        }

        while (change >= 10 && tens > 0) {
            tensCount++;
            tens--;
            change -= 10;
        }

        while (change >= 5 && fives > 0) {
            fivesCount++;
            fives--;
            change -= 5;
        }

        while (change >= 1 && ones > 0) {
            onesCount++;
            ones--;
            change -= 1;
        }

        int availableChange = (fiveHundredsCount * 500) + (twoHundredsCount * 200) + (hundredsCount * 100) + (fiftiesCount * 50)
                               + (twentiesCount * 20) + (tensCount * 10) + (fivesCount * 5) + (onesCount);

        if (availableChange == userChange) {
            regularVendingMachine.updatePettyMoney(fiveHundredsCount, twoHundredsCount, hundredsCount, fiftiesCount, twentiesCount, tensCount, fivesCount, onesCount, "subtract");
            regularVendingMachine.setReceivePayment(new Money(fiveHundredsCount, twoHundredsCount, hundredsCount, fiftiesCount, twentiesCount, tensCount, fivesCount, onesCount));

            money.set500Num(money.get500Num() - fiveHundredsCount);
            money.set200Num(money.get200Num() - twoHundredsCount);
            money.set100Num(money.get100Num() - hundredsCount);
            money.set50Num(money.get50Num() - fiftiesCount);
            money.set20Num(money.get20Num() - twentiesCount);
            money.set10Num(money.get10Num() - tensCount);
            money.set5Num(money.get5Num() - fivesCount);
            money.set1Num(money.get1Num() - onesCount);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Selects an item from the vending machine and processes the purchase.
     *
     * @param itemNumber The number representing the item to be selected.
     * @param quantity The quantity of the selected item to be purchased.
     * @param indicator The indicator specifying the type of vending machine ("regular" or "special").
     * @return A message indicating the status of the purchase and the item details if successful.
     */
    public String selectItem(int itemNumber, int quantity, String indicator) {
        StringBuilder sb = new StringBuilder();
        sb.append("Selecting item...").append("\n");
        if (indicator.equals("regular")) {
            // Check if the item number is valid
            if (itemNumber < 1 || itemNumber > itemSlots.size()) {
                sb.append("Invalid slot number.\n");
            }

            ItemSlot itemSlot = itemSlots.get(itemNumber - 1);
            Item item = itemSlot.getItem();

            // Check if the item is available in the specified quantity
            if (itemSlot.isEmpty() || quantity > itemSlot.getQuantity() || quantity < 1) {
                sb.append("Item not available in the specified quantity.\n");
            }

            int totalAmount = item.getItemPrice() * quantity;
            int totalCalories = item.getItemCalories() * quantity;
            sb.append("\nYou bought ").append(item.getItemName()).append("\n");
            sb.append("Amount: ₱").append(totalAmount).append("\n");
            sb.append("Calories: ").append(totalCalories).append("\n");

            // Update item slot quantity
            itemSlot.decreaseQuantity(quantity);

            // Log the transaction
            transactionLog.addTransaction(new Transaction(item, quantity, totalAmount));

            sb.append("Item dispensed successfully.\n");
        }
        return sb.toString();
    }


    /**
     * Retrieves the total price of a specified item based on its item number and quantity.
     *
     * @param itemNumber The number representing the item for which the price is to be retrieved.
     * @param quantity The quantity of the item to be priced.
     * @return The total price of the specified item and quantity.
     */
    public int getItemPrice(int itemNumber, int quantity) {
        Item selectedItem = itemSlots.get(itemNumber).getItem();
        if (selectedItem == null) {
            System.out.println("Invalid item number. Please choose a valid item.");
            return 0;
        }
        return selectedItem.getItemPrice() * quantity;
    }

    /**
     * Retrieves the total calories of a specified item based on its item number and quantity.
     *
     * @param itemNumber The number representing the item for which the calories are to be retrieved.
     * @param quantity The quantity of the item to be considered for calorie calculation.
     * @return The total calories of the specified item and quantity.
     */
    public int getItemCalories(int itemNumber, int quantity){
        Item selectedItem = itemSlots.get(itemNumber).getItem();
        if(selectedItem == null){
            System.out.println("Invalid item number. Please choose a valid item.");
            return 0;
        }
        return selectedItem.getItemCalories() * quantity;
    }

    /**
     * Restocks the specified item slot with the given quantity of items.
     *
     * @param itemSlotNumber The number representing the item slot to be restocked.
     * @param quantity The quantity of items to be added to the item slot.
     * @return A string message indicating the successful restocking of the item slot with the new quantity.
     */
    public String restockItems(int itemSlotNumber, int quantity) {
        StringBuilder sb = new StringBuilder();

        ItemSlot itemSlot = itemSlots.get(itemSlotNumber - 1);
        Item item = itemSlot.getItem();

        int currentQuantity = itemSlot.getQuantity();
        int newQuantity = currentQuantity + quantity;

        itemSlot.setQuantity(newQuantity);

        sb.append("Restocked ").append(item.getItemName()).append(" - Quantity: ").append(quantity).append("\n");

        return sb.toString();
    }

    /**
     * Sets the new price for the item in the specified item slot.
     *
     * @param itemSlotNumber The number representing the item slot whose price needs to be updated.
     * @param newPrice The new price to be set for the item in the specified item slot.
     */
    public void setPrice(int itemSlotNumber, int newPrice) {
        ItemSlot itemSlot = itemSlots.get(itemSlotNumber - 1);
        Item item = itemSlot.getItem();
        item.setItemPrice(newPrice);
    }

    /**
     * Replenishes the petty money in the money bank with the specified denominations.
     *
     * @param php500Num The number of ₱500 bills to be replenished.
     * @param php200Num The number of ₱200 bills to be replenished.
     * @param php100Num The number of ₱100 bills to be replenished.
     * @param php50Num The number of ₱50 bills to be replenished.
     * @param php20Num The number of ₱20 bills to be replenished.
     * @param php10Num The number of ₱10 coins to be replenished.
     * @param php5Num The number of ₱5 coins to be replenished.
     * @param php1Num The number of ₱1 coins to be replenished.
     */
    public void replenishPettyMoney(int php500Num, int php200Num, int php100Num, int php50Num, int php20Num,
                                    int php10Num, int php5Num, int php1Num) {
        int[] denominations = {php500Num, php200Num, php100Num, php50Num, php20Num, php10Num, php5Num, php1Num};
        moneyBank.getPettyMoney().setDenominations(denominations);
    }

    /**
     * Updates the petty money denominations in the money bank based on the provided quantities and the specified operation.
     *
     * @param php500Num The number of ₱500 bills to be updated.
     * @param php200Num The number of ₱200 bills to be updated.
     * @param php100Num The number of ₱100 bills to be updated.
     * @param php50Num The number of ₱50 bills to be updated.
     * @param php20Num The number of ₱20 bills to be updated.
     * @param php10Num The number of ₱10 coins to be updated.
     * @param php5Num The number of ₱5 coins to be updated.
     * @param php1Num The number of ₱1 coins to be updated.
     * @param operation The operation to be performed on the petty money denominations (e.g., "add" or "subtract").
     */
    public void updatePettyMoney(int php500Num, int php200Num, int php100Num, int php50Num, int php20Num, int php10Num, int php5Num, int php1Num, String operation) {
        int[] denominations = {php500Num, php200Num, php100Num, php50Num, php20Num, php10Num, php5Num, php1Num};
        moneyBank.getPettyMoney().updatePettyCashDenominations(denominations, operation);
    }

    /**
     * Generates a formatted string displaying the quantities of each denomination in the petty money.
     *
     * @param money The Money object containing the quantities of each denomination.
     * @return A formatted string displaying the quantities of each denomination in the petty money.
     */
    public String displayPettyMoney(Money money) {
        StringBuilder sb = new StringBuilder("\nPetty Money:\n");

        sb.append("₱500: ").append(money.get500Num()).append("\n");
        sb.append("₱200: ").append(money.get200Num()).append("\n");
        sb.append("₱100: ").append(money.get100Num()).append("\n");
        sb.append("₱50: ").append(money.get50Num()).append("\n");
        sb.append("₱20: ").append(money.get20Num()).append("\n");
        sb.append("₱10: ").append(money.get10Num()).append("\n");
        sb.append("₱5: ").append(money.get5Num()).append("\n");
        sb.append("₱1: ").append(money.get1Num()).append("\n");

        return sb.toString();
    }

    /**
     * Generates a formatted string displaying the transaction summary, including starting inventory, ending inventory,
     * items sold, total quantity sold, and total amount collected.
     *
     * @return A formatted string displaying the transaction summary.
     */
    public String displayTransactionSummary() {
        StringBuilder sb = new StringBuilder("Transaction Summary:\n");

        // Calculate the starting inventory based on the initial restocking
        sb.append("\nStarting Inventory:\n");
        for (ItemSlot itemSlot : itemSlots) {
            Item item = itemSlot.getItem();
            int quantity = itemSlot.getInitialQuantity();
            sb.append("Item: ").append(item.getItemName()).append("\n");
            sb.append("Quantity: ").append(quantity).append("\n");
        }

        // Calculate the ending inventory based on the transactions
        sb.append("\nEnding Inventory:\n");
        for (ItemSlot itemSlot : itemSlots) {
            Item item = itemSlot.getItem();
            int quantity = itemSlot.getQuantity();
            sb.append("Item: ").append(item.getItemName()).append("\n");
            sb.append("Quantity: ").append(quantity).append("\n");
        }

        // Calculate the total quantity sold and the total amount collected
        int totalQuantitySold = 0;
        int totalAmountCollected = 0;
        sb.append("\nItems Sold:\n");
        for (Transaction transaction : transactionLog.getTransactionList()) {
            int quantity = transaction.getQuantity();
            double totalAmount = transaction.getAmount();
            totalQuantitySold += quantity;
            totalAmountCollected += totalAmount;

            Item itemSold = transaction.getItem();
            String itemSoldName = itemSold.getItemName();
            int itemSoldAmount = itemSold.getItemPrice();
            int itemSoldQuantity = transaction.getQuantity();
            sb.append(itemSoldName).append(" - ₱").append(itemSoldAmount).append(" - ").append(itemSoldQuantity).append("\n");
        }
        sb.append("\nTotal Quantity Sold: ").append(totalQuantitySold).append("\n");
        sb.append("Total Amount Collected: ₱").append(totalAmountCollected).append("\n");

        return sb.toString();
    }
}
