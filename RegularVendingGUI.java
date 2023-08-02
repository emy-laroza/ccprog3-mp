import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI class for Regular Vending Machine features.
 * Extends JFrame and implements ActionListener to handle button events.
 */
public class RegularVendingGUI extends JFrame implements ActionListener {
    private VendingMachineController vendingMachineController;
    private JTextArea textArea;
    private JButton[] itemButtons;

    // Constructor
    /**
     * Constructs a RegularVendingGUI instance.
     *
     * @param vendingMachineController The controller for the vending machine.
     */
    public RegularVendingGUI(VendingMachineController vendingMachineController) {
        this.vendingMachineController = vendingMachineController;
        setTitle("Regular Vending Features");
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
    private void initComponents() {
        // TextArea for displaying messages
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for item buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        itemButtons = new JButton[9];
        itemButtons[0] = new JButton("Mozzarella");
        itemButtons[1] = new JButton("Pepperoni");
        itemButtons[2] = new JButton("Tomatoes");
        itemButtons[3] = new JButton("Bacon");
        itemButtons[4] = new JButton("Basil");
        itemButtons[5] = new JButton("Pineapple");
        itemButtons[6] = new JButton("Dough");
        itemButtons[7] = new JButton("Ground Pork");
        itemButtons[8] = new JButton("Produce Change");

        itemButtons[0].addActionListener(this);
        itemButtons[1].addActionListener(this);
        itemButtons[2].addActionListener(this);
        itemButtons[3].addActionListener(this);
        itemButtons[4].addActionListener(this);
        itemButtons[5].addActionListener(this);
        itemButtons[6].addActionListener(this);
        itemButtons[7].addActionListener(this);
        itemButtons[8].addActionListener(this);

        buttonPanel.add(itemButtons[0]);
        buttonPanel.add(itemButtons[1]);
        buttonPanel.add(itemButtons[2]);
        buttonPanel.add(itemButtons[3]);
        buttonPanel.add(itemButtons[4]);
        buttonPanel.add(itemButtons[5]);
        buttonPanel.add(itemButtons[6]);
        buttonPanel.add(itemButtons[7]);
        buttonPanel.add(itemButtons[8]);

        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);

        updateItemsDisplay(vendingMachineController.getRegularVendingMachine().displayItems());
    }


    // Methods
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

        switch (command) {
            case "Mozzarella":
                vendingMachineController.handleRegularVending("Mozzarella");
                break;
            case "Pepperoni":
                vendingMachineController.handleRegularVending("Pepperoni");
                break;
            case "Tomatoes":
                vendingMachineController.handleRegularVending("Tomatoes");
                break;
            case "Bacon":
                vendingMachineController.handleRegularVending("Bacon");
                break;
            case "Basil":
                vendingMachineController.handleRegularVending("Basil");
                break;
            case "Pineapple":
                vendingMachineController.handleRegularVending("Pineapple");
                break;
            case "Dough":
                vendingMachineController.handleRegularVending("Dough");
                break;
            case "Ground Pork":
                vendingMachineController.handleRegularVending("Ground Pork");
                break;
            case "Produce Change":
                vendingMachineController.handleRegularVending("Produce Change");
            default:
                break;
        }
    }

}
