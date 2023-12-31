import javax.swing.*;

/**
 * A GUI class for the regular vending machine menu.
 * Extends javax.swing.JFrame.
 */
public class RegularVendingMachineGUI extends javax.swing.JFrame {
    private boolean regularVendingMachineCreated = false;

    /**
     * Creates a new RegularVendingMachineGUI instance.
     * Initializes the GUI components.
     */
    public RegularVendingMachineGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        BackToMainMenuButton = new javax.swing.JButton();
        createRegularVendingMachineButton = new javax.swing.JButton();
        testRegularVendingMachineButton = new javax.swing.JButton();
        regularVendingMachineLabel = new java.awt.Label();
        vendingMachineLabel = new java.awt.Label();

        jButton1.setText("jButton1");
        jButton1.setPreferredSize(new java.awt.Dimension(166, 23));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 357, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 140, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackToMainMenuButton.setText("Go Back to Main Menu");
        BackToMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackToMainMenuButtonActionPerformed(evt);
            }
        });

        createRegularVendingMachineButton.setText("Create Regular Vending Machine");
        createRegularVendingMachineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRegularVendingMachineButtonActionPerformed(evt);
            }
        });

        testRegularVendingMachineButton.setText("Test Regular Vending Machine");
        testRegularVendingMachineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testRegularVendingMachineButtonActionPerformed(evt);
            }
        });

        regularVendingMachineLabel.setAlignment(java.awt.Label.CENTER);
        regularVendingMachineLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        regularVendingMachineLabel.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        regularVendingMachineLabel.setText("Regular Vending Machine");

        vendingMachineLabel.setAlignment(java.awt.Label.CENTER);
        vendingMachineLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        vendingMachineLabel.setText("PIZZARIA VENDING MACHINE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(vendingMachineLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(createRegularVendingMachineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BackToMainMenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(testRegularVendingMachineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(regularVendingMachineLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(vendingMachineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(regularVendingMachineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(createRegularVendingMachineButton)
                                .addGap(15, 15, 15)
                                .addComponent(testRegularVendingMachineButton)
                                .addGap(15, 15, 15)
                                .addComponent(BackToMainMenuButton)
                                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    /**
     * Action performed when the "Go Back to Main Menu" button is clicked.
     * Closes the current window and opens the main vending machine menu.
     *
     * @param evt The ActionEvent triggered by clicking the "Go Back to Main Menu" button.
     */
    private void BackToMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        new VendingMachineGUI().setVisible(true);
        setVisible(false);
    }

    /**
     * Action performed when the "Create Regular Vending Machine" button is clicked.
     * Sets the regularVendingMachineCreated flag to true and displays a message dialog.
     *
     * @param evt The ActionEvent triggered by clicking the "Create Regular Vending Machine" button.
     */
    private void createRegularVendingMachineButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        regularVendingMachineCreated = true;
        JOptionPane.showMessageDialog(this, "Regular Vending Machine created!");
    }

    /**
     * Action performed when the "Test Regular Vending Machine" button is clicked.
     * Opens the TestRegularVendingMachine window if the regular vending machine is created.
     * Otherwise, displays a message dialog indicating that the vending machine is not created yet.
     *
     * @param evt The ActionEvent triggered by clicking the "Test Regular Vending Machine" button.
     */
    private void testRegularVendingMachineButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (regularVendingMachineCreated) {
            new TestRegularVendingMachine().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Regular Vending Machine not created yet!");
        }
    }

    /**
     * Main method to start the application and display the regular vending machine menu.
     * Sets the Nimbus look and feel and creates an instance of RegularVendingMachineGUI.
     *
     * @param args The command line arguments (not used in this application).
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegularVendingMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegularVendingMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegularVendingMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegularVendingMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegularVendingMachineGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackToMainMenuButton;
    private javax.swing.JButton createRegularVendingMachineButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Label regularVendingMachineLabel;
    private javax.swing.JButton testRegularVendingMachineButton;
    private java.awt.Label vendingMachineLabel;

    // End of variables declaration
}
