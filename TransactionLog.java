import java.util.*;

/**
 * Represents a log of transactions
 */
public class TransactionLog{
    private ArrayList<Transaction> transactionList;
    private ArrayList<Transaction> pizzaTransactionList;

    // Constructor
    /**
     * Constructs a new TransactionLog object
     * Initializes an empty transaction list
     */
    public TransactionLog(){
        transactionList = new ArrayList<>();
        pizzaTransactionList = new ArrayList<>();
    }

    // Getters
    /**
     * Returns the list of transactions in the log.
     *
     * @return the list of transactions
     */
    public ArrayList<Transaction> getTransactionList(){
        return transactionList;
    }

    /**
     * Returns the list of pizza transactions in the log.
     *
     * @return the list of pizza transactions
     */
    public ArrayList<Transaction> getPizzaTransactionList(){
        return pizzaTransactionList;
    }

    // Methods
    /**
     * Adds a new transaction to the transaction log.
     *
     * @param transaction the new transaction to be added
     */
    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }

    /**
     * Adds a new pizza transaction to the pizza transaction log.
     *
     * @param transaction the new pizza transaction to be added
     */
    public void addPizzaTransaction(Transaction transaction){
        pizzaTransactionList.add(transaction);
    }

}
