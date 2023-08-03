/**
 * Represents an item of a vending machine with a name, price, and calories
 */
public class Item{
    private String itemName;
    private int itemPrice;
    private int itemCalories;

    /**
     * Constructs a new Item object with the specified name, price, and calories
     *
     * @param itemName          name of the item
     * @param itemPrice         price of the item
     * @param itemCalories      calories of the item
     */
    public Item(String itemName, int itemPrice, int itemCalories){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCalories = itemCalories;
    }

    /**
     * Returns the name of the item
     *
     * @return  name of the item
     */
    public String getItemName(){
        return itemName;
    }

    /**
     * Returns the price of the item
     *
     * @return  the price of the item
     */
    public int getItemPrice(){
        return itemPrice;
    }

    /**
     * Returns the calories of the item
     *
     * @return  the calories of the item
     */
    public int getItemCalories(){
        return itemCalories;
    }

    /**
     * Sets the price of the item
     *
     * @param itemPrice the new price of the item
     */
    public void setItemPrice(int itemPrice){
        this.itemPrice = itemPrice;
    }
}




