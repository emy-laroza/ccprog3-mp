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
     * Returns an array of denominations
     *
     * @return  the array of denominations
     */
    public int[] getDenominations(){
        return new int[]{php500Num, php200Num, php100Num, php50Num, php20Num, php10Num, php5Num, php1Num};
    }

    public int get500Num() {
        return php500Num;
    }
    public int get200Num() {
        return php200Num;
    }
    public int get100Num() {
        return php100Num;
    }
    public int get50Num() {
        return php50Num;
    }
    public int get20Num() {
        return php20Num;
    }
    public int get10Num() {
        return php10Num;
    }
    public int get5Num() {
        return php5Num;
    }
    public int get1Num() {
        return php1Num;
    }

    // Setters
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

    public void set500Num(int num) {
	this.php500Num += num;
    }
    public void set200Num(int num) {
        this.php200Num += num;
    }
    public void set100Num(int num) {
        this.php100Num += num;
    }
    public void set50Num(int num) {
        this.php50Num += num;
    }
    public void set20Num(int num) {
        this.php20Num += num;
    }
    public void set10Num(int num) {
        this.php10Num += num;
    }
    public void set5Num(int num) {
        this.php5Num += num;
    }
    public void set1Num(int num) {
        this.php1Num += num;
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
