import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI class for the maintenance menu of the vending machine.
 * Extends javax.swing.JFrame and implements ActionListener.
 */
public class MaintenanceGUI extends JFrame implements ActionListener {
    private VendingMachineController vendingMachineController;
    private JTextArea textArea;

    /**
     * Creates a new MaintenanceGUI instance.
     * Initializes the VendingMachineController and sets up the GUI components.
     * Initializes the items display and makes the frame visible.
     *
     * @param vendingMachineController The VendingMachineController instance used for handling maintenance actions.
     */
    public MaintenanceGUI(VendingMachineController vendingMachineController) {
        this.vendingMachineController = vendingMachineController;
        vendingMachineController.setMaintenanceGUI(this);

        setTitle("Maintenance Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents(); // Move this line to the beginning of the constructor
        updateItemsDisplay(""); // Initialize the items display
        setVisible(true);

        updateItemsDisplay(vendingMachineController.getRegularVendingMachine().displayItems());
    }

    /**
     * Initializes the GUI components for the maintenance menu.
     * Sets up the text area, buttons, and their action listeners.
     */
    private void initComponents() {
        // TextArea for displaying messages
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // buttons for maintenance options
        JButton restockItemsButton = new JButton("Restock Items");
        JButton setItemPricesButton = new JButton("Set Item Prices");
        JButton replenishChangeButton = new JButton("Replenish Change");
        JButton printTransactionSummaryButton = new JButton("Print Transaction Summary");
        JButton backButton = new JButton("Back to Previous Menu");

        // Add action listeners for each button
        restockItemsButton.addActionListener(this);
        setItemPricesButton.addActionListener(this);
        replenishChangeButton.addActionListener(this);
        printTransactionSummaryButton.addActionListener(this);
        backButton.addActionListener(this);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10)); // 5 columns, 1 row
        buttonPanel.add(restockItemsButton);
        buttonPanel.add(setItemPricesButton);
        buttonPanel.add(replenishChangeButton);
        buttonPanel.add(printTransactionSummaryButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen

        // Display the initial items
        updateItemsDisplay(vendingMachineController.getRegularVendingMachine().displayItems());
    }

    /**
     * Updates the text area with additional text.
     *
     * @param text The text to be added to the text area.
     */
    public void updateTextArea(String text) {
        textArea.setText(textArea.getText() + text + "\n");
    }

    /**
     * Clears the text area.
     */
    public void clearTextArea() {
        textArea.setText("");
    }

    /**
     * Updates the items display in the text area.
     *
     * @param itemList The list of items to be displayed in the text area.
     */
    public void updateItemsDisplay(String itemList) {
        textArea.setText(itemList);
    }

    /**
     * Action performed when a button in the maintenance menu is clicked.
     * Executes the corresponding maintenance action based on the button clicked.
     *
     * @param e The ActionEvent triggered by clicking a button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Restock Items")) {
            vendingMachineController.showRestockItemsDialog("regular");
        } else if (command.equals("Set Item Prices")) {
            vendingMachineController.showSetItemPricesDialog("regular");
        } else if (command.equals("Replenish Change")) {
            vendingMachineController.showReplenishChangeDialog("regular");
        } else if (command.equals("Print Transaction Summary")) {
            vendingMachineController.showTransactionSummary("regular");
        } else if (command.equals("Back to Previous Menu")) {
            setVisible(false);
            new TestRegularVendingMachine().setVisible(true);
        }
    }
}
