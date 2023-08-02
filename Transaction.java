/**
 * Represents a transaction involving the purchase of an item
 */
public class Transaction{
    private Item item;
    private int quantity;
    private int amount;

    /**
     * Constructs a new Transaction object with the specified item, quantity, and amount
     *
     * @param item          the item purchased by the user
     * @param quantity      the quantity of the item purchased by the user
     * @param amount        the total amount paid for the transaction
     */
    public Transaction(Item item, int quantity, int amount){
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Item getItem(){
        return item;
    }
    /**
     * Returns the quantity of the item in the transaction
     *
     * @return  the quantity of the item
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Returns the total amount paid for the transaction
     *
     * @return  the total amount paid
     */
    public int getAmount(){
        return amount;
    }
}
