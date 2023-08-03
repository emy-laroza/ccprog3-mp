/**
 * Represents a bank that manages the money earned by the vending machine and petty cash for making change
 */
public class MoneyBank{
    private Money vendingMoney; // money earned by the vending machine
    private Money pettyMoney; // petty cash for making change

    /**
     * Constructs a new MoneyBank object with the specified initial denominations for vending money and petty money.
     *
     * @param php500Num the quantity of Php 500 denominations for vending money
     * @param php200Num the quantity of Php 200 denominations for vending money
     * @param php100Num the quantity of Php 100 denominations for vending money
     * @param php50Num  the quantity of Php 50 denominations for vending money
     * @param php20Num  the quantity of Php 20 denominations for vending money
     * @param php10Num  the quantity of Php 10 denominations for vending money
     * @param php5Num   the quantity of Php 5 denominations for vending money
     * @param php1Num   the quantity of Php 1 denominations for vending money
     */
    public MoneyBank(int php500Num, int php200Num, int php100Num, int php50Num, int php20Num,
                     int php10Num, int php5Num, int php1Num){
        vendingMoney = new Money(php500Num, php200Num, php100Num, php50Num, php20Num, php10Num, php5Num, php1Num);
        pettyMoney = new Money(10, 10, 10, 10, 10, 10, 10, 50);
    }

    // Getters

    /**
     * Retrieves the petty money available in the money bank
     *
     * @return  the petty money
     */
    public Money getPettyMoney(){
        return pettyMoney;
    }
}