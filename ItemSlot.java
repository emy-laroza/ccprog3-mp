/**
 * Represents a slot in the vending machine that holds an item with its quantity
 */
public class ItemSlot{
    private Item item;
    private int quantity;
    private int initialQuantity;

    /**
     * Constructs a new ItemSlot object with the specified item and quantity
     *
     * @param item          the item held in the slot
     * @param quantity      the quantity of the item in the slot
     */
    public ItemSlot(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
        this.initialQuantity = quantity;
    }

    /**
     * Returns the item held in the slot
     *
     * @return  the item held in the slot
     */
    public Item getItem(){
        return item;
    }

    /**
     * Returns the current quantity of the item in the slot
     *
     * @return  the current quantity of the item in the slot
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Returns the initial quantity of the item in the slot
     *
     * @return  the initial quantity of the item in the slot
     */
    public int getInitialQuantity(){
        return initialQuantity;
    }

    /**
     * Sets the quantity of the item in the slot
     *
     * @param quantity  the new quantity of the item in the slot
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Checks if the slot is empty (quantity == 0)
     *
     * @return  true if the slot is empty, false if the slot is not empty
     */
    public boolean isEmpty(){
        return quantity == 0;
    }

    /**
     * Decreases the quantity of the item in the slot by the specified number
     *
     * @param quantity the number to decrease the quantity by
     */
    public void decreaseQuantity(int quantity) {
        if (quantity > 0) {
            int currentQuantity = getQuantity();
            int newQuantity = currentQuantity - quantity;
            if (newQuantity >= 0) {
                setQuantity(newQuantity);
            } else {
                System.out.println("Insufficient quantity to decrease.");
            }
        } else {
            System.out.println("Invalid quantity. Please enter a positive value.");
        }
    }
}
