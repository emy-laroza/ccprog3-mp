import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI class for Special Vending Machine features.
 * Extends JFrame and implements ActionListener to handle button events.
 */
public class SpecialVendingGUI extends JFrame implements ActionListener {
    private VendingMachineController vendingMachineController;
    private JTextArea textArea;

    // Constructor
    /**
     * Constructs a SpecialVendingGUI instance.
     *
     * @param vendingMachineController The controller for the vending machine.
     */
    public SpecialVendingGUI(VendingMachineController vendingMachineController){
        this.vendingMachineController = vendingMachineController;
        setTitle("Special Vending Features");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents();

        setVisible(true);
    }

    /**
     * Initializes the components of the GUI.
     */
    private void initComponents(){
        // TextArea for displaying messages
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // buttons for special vending options
        JButton itemOnlyButton = new JButton("Item/s only");
        JButton pizzaBasedOnItemsButton = new JButton("Pizza based on Items");
        JButton readyMadePizzaButton = new JButton("Ready-made Pizza");
        JButton backButton = new JButton("Back to Previous Menu");

        // action listeners for each button
        itemOnlyButton.addActionListener(this);
        pizzaBasedOnItemsButton.addActionListener(this);
        readyMadePizzaButton.addActionListener(this);
        backButton.addActionListener(this);

        // Panel for item buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1,4, 10, 10));
        buttonPanel.add(itemOnlyButton);
        buttonPanel.add(pizzaBasedOnItemsButton);
        buttonPanel.add(readyMadePizzaButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);

        updateItemsDisplay(vendingMachineController.getRegularVendingMachine().displayItems());
    }

    /**
     * Updates the text area with additional text.
     *
     * @param text The text to be added to the text area.
     */
    public void updateTextArea(String text){
        textArea.setText(textArea.getText() + text + "\n");
    }

    /**
     * Clears the text area.
     */
    public void clearTextArea() {
        textArea.setText("");
    }

    /**
     * Updates the text area with a new item list.
     *
     * @param itemList The list of items to be displayed.
     */
    public void updateItemsDisplay(String itemList) {
        textArea.setText(itemList);
    }

    /**
     * Handles button events and performs actions based on the clicked button.
     *
     * @param e The ActionEvent triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if(command.equals("Item/s only")){
            vendingMachineController.showItemsOnlyDialog();
        } else if(command.equals("Pizza based on Items")){
            vendingMachineController.showPizzaBasedItemsDialog();
        } else if(command.equals("Ready-made Pizza")){
            vendingMachineController.showReadyMadePizza();
        } else if (command.equals("Back to Previous Menu")){
            setVisible(false);
            new TestSpecialVendingMachine().setVisible(true);
        }
    }
}
