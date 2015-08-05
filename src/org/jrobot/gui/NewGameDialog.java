package org.jrobot.gui;

import org.jrobot.game.GameConfig;
import org.jrobot.game.GameUtils;
import org.jrobot.server.Serv;
import org.jrobot.server.ServImpl;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.io.IOException;
import javax.swing.*;

/**
 * NewGameDialog.java
 * 
 * Displays the NewGameDialog
 *
 * @author $Author: savio $
 * @version $Id: NewGameDialog.java,v 1.10 2005/07/05 01:00:54 savio Exp $
 */

public class NewGameDialog extends JDialog {

    private JPanel jPanel = null;
    private JTextField jTextField2 = null;
    private JLabel jLabel = null;
    private JButton jButton = null;
    private JLabel jLabel1 = null;
    private JTextField jTextField1 = null;
    private JLabel jLabel2 = null;
    private JLabel jLabel3 = null;
    private JTextField jTextField3 = null;
    private JLabel jLabel4 = null;
    private static JTextField jTextField4 = null;
    private JButton jButton1 = null;
    private JButton jButton2 = null;
    private JCheckBox jCheckBox = null;
    private JPanel jPanel1 = null;
    private JLabel jLabel5 = null;
    private JButton jButton3 = null;
    private JButton jButton4 = null;
    public static JTextField jTextField = null;
    public static ConnectDialog connDlg;
    private JCheckBox avaPropers[];

    /* Form Numbers */
    public static String filename = "";
    private static String gamename;
    private static int teams;
    private static int robots;
    private static int gametime; //seconds
    private static boolean manual = false;
    private static String[] properList;

    /**
     * @throws java.awt.HeadlessException
     */
    public NewGameDialog() throws HeadlessException {
        super();
        initialize();
    }

    /**
     * @param arg0
     * @throws java.awt.HeadlessException
     */
    public NewGameDialog(Frame arg0) throws HeadlessException {
        super(arg0);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public NewGameDialog(Frame arg0, boolean arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public NewGameDialog(Frame arg0, String arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws java.awt.HeadlessException
     */
    public NewGameDialog(Frame arg0, String arg1, boolean arg2)
            throws HeadlessException {
        super(arg0, arg1, arg2);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    public NewGameDialog(Frame arg0, String arg1, boolean arg2,
                         GraphicsConfiguration arg3) {
        super(arg0, arg1, arg2, arg3);
        initialize();
    }

    /**
     * @param arg0
     * @throws java.awt.HeadlessException
     */
    public NewGameDialog(Dialog arg0) throws HeadlessException {
        super(arg0);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public NewGameDialog(Dialog arg0, boolean arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public NewGameDialog(Dialog arg0, String arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws java.awt.HeadlessException
     */
    public NewGameDialog(Dialog arg0, String arg1, boolean arg2)
            throws HeadlessException {
        super(arg0, arg1, arg2);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @throws java.awt.HeadlessException
     */
    public NewGameDialog(Dialog arg0, String arg1, boolean arg2,
                         GraphicsConfiguration arg3) throws HeadlessException {
        super(arg0, arg1, arg2, arg3);
        initialize();
    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.setModal(true);
        this.setContentPane(getJPanel());
        this.setResizable(false);
        this.setTitle("New Game");
        this.setSize(314, 435);
    }

    /**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel() {
        if (jPanel == null) {

            jLabel4 = new JLabel();
            jLabel3 = new JLabel();
            jLabel2 = new JLabel();
            jLabel1 = new JLabel();
            jLabel = new JLabel();
            jPanel = new JPanel();
            jPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jLabel.setText("MapFile:");
            jLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jLabel1.setText("Name:");
            jLabel1.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jLabel1.setPreferredSize(new java.awt.Dimension(54, 19));
            jLabel2.setText("Teams:");
            jLabel2.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jLabel2.setPreferredSize(new java.awt.Dimension(53, 19));
            jLabel3.setText("Robots: ");
            jLabel3.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jLabel4.setText("Game GetTime:");
            jLabel4.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jPanel.add(jLabel1, null);
            jPanel.add(getJTextField1(), null);
            jPanel.add(jLabel, null);
            jPanel.add(getJTextField(), null);
            getJButton();
            jPanel.add(jLabel2, null);
            jPanel.add(getJTextField2(), null);
            jPanel.add(jLabel3, null);
            jPanel.add(getJTextField3(), null);
            jPanel.add(jLabel4, null);
            jPanel.add(getJTextField4(), null);
            jPanel.add(getJButton1(), null);
            jPanel.add(getJButton2(), null);
            jPanel.add(getJCheckBox(), null);
            jPanel.add(getJPanel1(), null);
            jPanel.add(getJButton3(), null);
            jPanel.add(getJButton4(), null);

        }
        return jPanel;
    }

    /**
     * This method initializes jTextField
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setColumns(15);
            jTextField.setEditable(false);
        }
        return jTextField;
    }

    /**
     * This method initializes jButton
     */
    private void getJButton() {
        jButton = new JButton();
        FileDialog of = new FileDialog();
        Action action = of.OpenFile();
        jButton.addActionListener(action);
        jButton.setText("...");

        jPanel.add(jButton);
    }

    /**
     * This method initializes jTextField1
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField1() {
        if (jTextField1 == null) {
            jTextField1 = new JTextField(GameUtils.getGameName());
            jTextField1.setColumns(19);
            jTextField1.setEditable(false);
        }
        return jTextField1;
    }

    /**
     * This method initializes jTextField2
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField2() {
        if (jTextField2 == null) {
            jTextField2 = new JFormattedTextField(new DecimalFormat("#"));
            jTextField2.setColumns(7);
        }
        return jTextField2;
    }

    /**
     * This method initializes jTextField3
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField3() {
        if (jTextField3 == null) {
            jTextField3 = new JFormattedTextField(new DecimalFormat("#"));
            jTextField3.setColumns(6);
        }
        return jTextField3;
    }

    /**
     * This method initializes jTextField4
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField4() {
        if (jTextField4 == null) {
            jTextField4 = new JFormattedTextField(new DecimalFormat("#"));
            jTextField4.setColumns(9);
            jTextField4.setText("0");
        }
        return jTextField4;
    }

    /**
     * This method initializes jButton1
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton1() {
        if (jButton1 == null) {
            jButton1 = new JButton();
            jButton1.setText("-");

            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (jTextField4.getText().equals(""))
                        jTextField4.setText("0");

                    else {
                        int value = (int) Long.parseLong(jTextField4.getText());
                        value--;

                        if (value < 0)
                            value = 0;

                        jTextField4.setText("" + value);
                    }
                }
            });

        }
        return jButton1;
    }

    /**
     * This method initializes jButton2
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton2() {
        if (jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setText("+");

            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (jTextField4.getText().equals(""))
                        jTextField4.setText("0");

                    else {
                        int value = (int) Long.parseLong(jTextField4.getText());
                        value++;

                        jTextField4.setText("" + value);
                    }
                }
            });


        }
        return jButton2;
    }

    /**
     * This method initializes jCheckBox
     *
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getJCheckBox() {
        if (jCheckBox == null) {
            jCheckBox = new JCheckBox();
            jCheckBox.setText("Allow manual");
            jCheckBox.setMnemonic(java.awt.event.KeyEvent.VK_A);
            jCheckBox.setPreferredSize(new java.awt.Dimension(300, 50));
            jCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jCheckBox.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));

            jCheckBox.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (jCheckBox.isSelected()) {
                        manual = true;
                    } else {
                        manual = false;
                    }
                }
            });

        }
        return jCheckBox;
    }

    /**
     * This method initializes jPanel1
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel1() {
        if (jPanel1 == null) {
            jLabel5 = new JLabel();
            jPanel1 = new JPanel();
            jLabel5.setText("Dynamic Properties");
            jLabel5.setPreferredSize(new java.awt.Dimension(300, 22));
            jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            jPanel1.setPreferredSize(new java.awt.Dimension(300, 200));
            jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            jPanel1.add(jLabel5, null);

            /* Getting Lenght of dynamic proper list */
            int qt = GameUtils.getProperList().length;
            String[] proper = new String[qt];
            properList = GameUtils.getProperList();

            avaPropers = new JCheckBox[qt];


            for (int i = 0; i < qt; i++)
            {
            //Add Robot Name
                avaPropers[i] = new JCheckBox();
                avaPropers[i].setText(properList[i]);
                avaPropers[i].setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
                jPanel1.add(avaPropers[i], null);
            }

        }
        return jPanel1;
    }

    /**
     * This method initializes jButton3
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton3() {
        if (jButton3 == null) {
            jButton3 = new JButton();
            jButton3.setText("OK");
            jButton3.setMnemonic(java.awt.event.KeyEvent.VK_O);
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e)
                {
                    /* Getting Fields */

                    //GameName
                    gamename = jTextField1.getText();

                    //Teams
                    if (jTextField2.getText().equals(""))
                        teams = 0;
                    else
                        teams = (int) Long.parseLong(jTextField2.getText());

                    //Robots
                    if (jTextField3.getText().equals(""))
                        robots = 0;
                    else
                        robots = (int) Long.parseLong(jTextField3.getText());

                    //Game GetTime
                    if (jTextField4.getText().equals(""))
                        gametime = 0;
                    else
                        gametime = (int) Long.parseLong(jTextField4.getText());


                    //ProperList

                    /* Getting size of dynamic proper list */
                    int qt=0;
                    for (int i = 0; i <avaPropers.length ; i++)
                    {
                        if(avaPropers[i].isSelected())
                        {
                          qt++;
                        }
                    }

                    /* Saving Selected propers */
                    properList = new String[qt];
                    for (int i = 0; i <properList.length ; i++)
                    {
                        if(avaPropers[i].isSelected())
                        {
                          properList[i] = avaPropers[i].getText();
                        }
                    }



                    //Checking...
                    if ((teams == 0) || (robots == 0) || (gametime == 0) ||
                            (gamename.equals("")) || (filename.equals(""))) {
                        JOptionPane.showMessageDialog(jPanel, "Please fill all fields!",
                                "Form Incomplete", JOptionPane.WARNING_MESSAGE);
                    } else  //OK
                    {
                        try {
                            connDlg = new ConnectDialog();    //fields
                            connect();

                        } catch (IOException exp)
                        {
                            JOptionPane.showMessageDialog(jPanel, "Couldn't open connection!!",
                                "Error!", JOptionPane.ERROR_MESSAGE);

                            return;
                        }
                    }
                }
            });
        }
        return jButton3;
    }

    /**
     * This method connects to the server
     */
    private void connect() throws IOException
    {
        connDlg.connectServer(connDlg, filename, properList, gamename, teams, robots, gametime, manual);
    }

    /**
     * This method initializes jButton4
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton4() {
        if (jButton4 == null) {
            jButton4 = new JButton();
            jButton4.setText("Cancel");
            jButton4.setMnemonic(java.awt.event.KeyEvent.VK_C);
            jButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            });
        }
        return jButton4;
    }
}
