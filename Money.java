/**
 * Represents the money used in the vending machine,
 * denominated according to each Philippine peso bill
 */
public class Money{
    private int php500Num;
    private int php200Num;
    private int php100Num;
    private int php50Num;
    private int php20Num;
    private int php10Num;
    private int php5Num;
    private int php1Num;

    // Constructor
    /**
     * Constructs a new Money object with the specified denomination quantities
     *
     * @param php500Num     the quantity of Php 500 denominations
     * @param php200Num     the quantity of Php 200 denominations
     * @param php100Num     the quantity of Php 100 denominations
     * @param php50Num      the quantity of Php 50 denominations
     * @param php20Num      the quantity of Php 20 denominations
     * @param php10Num      the quantity of Php 10 denominations
     * @param php5Num       the quantity of Php 5 denominations
     * @param php1Num       the quantity of Php 1 denominations
     */
    public Money(int php500Num, int php200Num, int php100Num, int php50Num, int php20Num, int php10Num, int php5Num, int php1Num){
        this.php500Num = php500Num;
        this.php200Num = php200Num;
        this.php100Num = php100Num;
        this.php50Num = php50Num;
        this.php20Num = php20Num;
        this.php10Num = php10Num;
        this.php5Num = php5Num;
        this.php1Num = php1Num;
    }

    // Getters
    /**
     * Gets the number of PHP 500 bills in the vending machine's change dispenser.
     *
     * @return The number of PHP 500 bills.
     */
    public int get500Num() {
        return php500Num;
    }

    /**
     * Gets the number of PHP 200 bills in the vending machine's change dispenser.
     *
     * @return The number of PHP 200 bills.
     */
    public int get200Num() {
        return php200Num;
    }

    /**
     * Gets the number of PHP 100 bills in the vending machine's change dispenser.
     *
     * @return The number of PHP 100 bills.
     */
    public int get100Num() {
        return php100Num;
    }

    /**
     * Gets the number of PHP 50 bills in the vending machine's change dispenser.
     *
     * @return The number of PHP 50 bills.
     */
    public int get50Num() {
        return php50Num;
    }

    /**
     * Gets the number of PHP 20 bills in the vending machine's change dispenser.
     *
     * @return The number of PHP 20 bills.
     */
    public int get20Num() {
        return php20Num;
    }

    /**
     * Gets the number of PHP 10 coins in the vending machine's change dispenser.
     *
     * @return The number of PHP 10 coins.
     */
    public int get10Num() {
        return php10Num;
    }

    /**
     * Gets the number of PHP 5 coins in the vending machine's change dispenser.
     *
     * @return The number of PHP 5 coins.
     */
    public int get5Num() {
        return php5Num;
    }

    /**
     * Gets the number of PHP 1 coins in the vending machine's change dispenser.
     *
     * @return The number of PHP 1 coins.
     */
    public int get1Num() {
        return php1Num;
    }

    // Setters
    /**
     * Adds the specified number of PHP 500 bills to the vending machine's change dispenser.
     *
     * @param num The number of PHP 500 bills to add.
     */
    public void set500Num(int num) {
	    this.php500Num += num;
    }

    /**
     * Adds the specified number of PHP 200 bills to the vending machine's change dispenser.
     *
     * @param num The number of PHP 200 bills to add.
     */
    public void set200Num(int num) {
        this.php200Num += num;
    }

    /**
     * Adds the specified number of PHP 100 bills to the vending machine's change dispenser.
     *
     * @param num The number of PHP 100 bills to add.
     */
    public void set100Num(int num) {
        this.php100Num += num;
    }

    /**
     * Adds the specified number of PHP 50 bills to the vending machine's change dispenser.
     *
     * @param num The number of PHP 50 bills to add.
     */
    public void set50Num(int num) {
        this.php50Num += num;
    }

    /**
     * Adds the specified number of PHP 20 bills to the vending machine's change dispenser.
     *
     * @param num The number of PHP 20 bills to add.
     */
    public void set20Num(int num) {
        this.php20Num += num;
    }

    /**
     * Adds the specified number of PHP 10 coins to the vending machine's change dispenser.
     *
     * @param num The number of PHP 10 coins to add.
     */
    public void set10Num(int num) {
        this.php10Num += num;
    }

    /**
     * Adds the specified number of PHP 5 coins to the vending machine's change dispenser.
     *
     * @param num The number of PHP 5 coins to add.
     */
    public void set5Num(int num) {
        this.php5Num += num;
    }

    /**
     * Adds the specified number of PHP 1 coins to the vending machine's change dispenser.
     *
     * @param num The number of PHP 1 coins to add.
     */
    public void set1Num(int num) {
        this.php1Num += num;
    }

    /**
     * Sets the denominations based on the provided array
     *
     * @param denominations the array of denominations
     */
    public void setDenominations(int[] denominations){
        if (denominations.length == 8) {
            this.php500Num += denominations[0];
            this.php200Num += denominations[1];
            this.php100Num += denominations[2];
            this.php50Num += denominations[3];
            this.php20Num += denominations[4];
            this.php10Num += denominations[5];
            this.php5Num += denominations[6];
            this.php1Num += denominations[7];
        }
    }

    // Methods
    /**
     * Calculates and returns the total amount of money in the denominations
     *
     * @return  the total amount of money
     */
    public int calculateTotalAmount(){
        int amount500Php = php500Num * 500;
        int amount200Php = php200Num * 200;
        int amount100Php = php100Num * 100;
        int amount50Php = php50Num * 50;
        int amount20Php = php20Num * 20;
        int amount10Php = php10Num * 10;
        int amount5Php = php5Num * 5;

        return amount500Php + amount200Php + amount100Php + amount50Php + amount20Php + amount10Php + amount5Php + php1Num;
    }

    /**
     * Updates the petty cash denominations in the vending machine's change dispenser based on the given array of denominations
     * and the specified operation.
     *
     * @param denominations An array of integers representing the quantities of different denominations of petty cash to update.
     *                      The array must contain exactly 8 elements in the following order:
     *                      [PHP 500, PHP 200, PHP 100, PHP 50, PHP 20, PHP 10, PHP 5, PHP 1].
     * @param operation     The operation to perform. Use "add" to add the quantities specified in the denominations array,
     *                      and use any other string value to subtract the quantities.
     */
    public void updatePettyCashDenominations(int[] denominations, String operation){
        if (denominations.length == 8 && operation.equals("add")) {
            this.php500Num += denominations[0];
            this.php200Num += denominations[1];
            this.php100Num += denominations[2];
            this.php50Num += denominations[3];
            this.php20Num += denominations[4];
            this.php10Num += denominations[5];
            this.php5Num += denominations[6];
            this.php1Num += denominations[7];
        }
        else {
            this.php500Num -= denominations[0];
            this.php200Num -= denominations[1];
            this.php100Num -= denominations[2];
            this.php50Num -= denominations[3];
            this.php20Num -= denominations[4];
            this.php10Num -= denominations[5];
            this.php5Num -= denominations[6];
            this.php1Num -= denominations[7];
        }
    }
}
