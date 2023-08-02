import java.util.*;

/**
 * Represents a log of transactions
 */
public class TransactionLog{
    private ArrayList<Transaction> transactionList;
    private ArrayList<Transaction> pizzaTransactionList;

    /**
     * Constructs a new TransactionLog object
     * Initializes an empty transaction list
     */
    public TransactionLog(){
        transactionList = new ArrayList<>();
        pizzaTransactionList = new ArrayList<>();
    }

    /**
     * Returns the list of transactions in the log
     *
     * @return  the list of transactions
     */
    public ArrayList<Transaction> getTransactionList(){
        return transactionList;
    }

    public ArrayList<Transaction> getPizzaTransactionList(){
        return pizzaTransactionList;
    }

    /**
     * Adds a new transaction to the transaction log
     *
     * @param transaction   the new transaction to be added
     */
    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }

    public void addPizzaTransaction(Transaction transaction){
        pizzaTransactionList.add(transaction);
    }

}
