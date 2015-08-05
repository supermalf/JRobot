package org.jrobot.gui;

/**
 *  Join Dialog
 *
 * @author Malf
 * @version $Id: JoinDialog.java,v 1.8 2005/07/04 22:20:23 savio Exp $
 */

import org.jrobot.client.ClientConfig;
import org.jrobot.game.*;
import org.jrobot.ogl.oglRenderer;
import org.jrobot.GUILoad;
import org.jrobot.JRobot;

import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.rmi.Naming;
import java.rmi.RemoteException;


public class JoinDialog extends JDialog {

    private JPanel jPanel = null;
    private JTextField jTextField = null;
    private JTextField jTextField1 = null;
    private JLabel jLabel = null;
    private JLabel jLabel1 = null;
    private JLabel jLabel2 = null;
    private JTextField jTextField2 = null;
    private JLabel jLabel3 = null;
    private JPanel jPanel1 = null;
    private JButton jButton = null;
    private JButton jButton1 = null;
    private JPanel jPanel2 = null;
    private JButton jButton2 = null;

    /* Form Numbers */
    public static String hostname = "";
    private static String playername = "";
    private static String teamname = "";
    private static Color teamcolor = Color.blue;


    /**
     * @throws java.awt.HeadlessException
     */
    public JoinDialog() throws HeadlessException {
        super();
        initialize();
    }

    /**
     * @param arg0
     * @throws java.awt.HeadlessException
     */
    public JoinDialog(Frame arg0) throws HeadlessException {
        super(arg0);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public JoinDialog(Frame arg0, boolean arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public JoinDialog(Frame arg0, String arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws java.awt.HeadlessException
     */
    public JoinDialog(Frame arg0, String arg1, boolean arg2) throws HeadlessException {
        super(arg0, arg1, arg2);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    public JoinDialog(Frame arg0, String arg1, boolean arg2, GraphicsConfiguration arg3) {
        super(arg0, arg1, arg2, arg3);
        initialize();
    }

    /**
     * @param arg0
     * @throws java.awt.HeadlessException
     */
    public JoinDialog(Dialog arg0) throws HeadlessException {
        super(arg0);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public JoinDialog(Dialog arg0, boolean arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public JoinDialog(Dialog arg0, String arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws java.awt.HeadlessException
     */
    public JoinDialog(Dialog arg0, String arg1, boolean arg2) throws HeadlessException {
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
    public JoinDialog(Dialog arg0, String arg1, boolean arg2, GraphicsConfiguration arg3) throws HeadlessException {
        super(arg0, arg1, arg2, arg3);
        initialize();

    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.setContentPane(getJPanel());
        this.setModal(true);
        this.setResizable(false);
        this.setTitle("Join Game");
        this.setSize(280, 211);
    }

    /**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel() {
        if (jPanel == null) {
            jLabel3 = new JLabel();
            jLabel2 = new JLabel();
            jLabel1 = new JLabel();
            jLabel = new JLabel();
            FlowLayout flowLayout3 = new FlowLayout();
            jPanel = new JPanel();
            jPanel.setLayout(flowLayout3);
            jPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            flowLayout3.setAlignment(java.awt.FlowLayout.LEFT);
            flowLayout3.setVgap(7);
            jLabel.setText(" Host:   ");
            jLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jLabel1.setText(" Player Name: ");
            jLabel1.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jLabel2.setText(" Team Name: ");
            jLabel2.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jLabel3.setText(" Color: ");
            jLabel3.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jPanel.add(jLabel, null);
            jPanel.add(getJTextField(), null);
            jPanel.add(jLabel1, null);
            jPanel.add(getJTextField1(), null);
            jPanel.add(jLabel2, null);
            jPanel.add(getJTextField2(), null);
            jPanel.add(jLabel3, null);
            jPanel.add(getJPanel1(), null);
            jPanel.add(getJButton(), null);
            jPanel.add(getJPanel2(), null);
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
            jTextField = new JTextField("localhost");
            jTextField.setName("jTextField");
            jTextField.setPreferredSize(new java.awt.Dimension(200, 20));
        }
        return jTextField;
    }

    /**
     * This method initializes jTextField1
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField1() {
        if (jTextField1 == null) {
            jTextField1 = new JTextField();
            jTextField1.setPreferredSize(new java.awt.Dimension(155, 20));
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
            jTextField2 = new JTextField();
            jTextField2.setPreferredSize(new java.awt.Dimension(160, 20));
        }
        return jTextField2;
    }

    /**
     * This method initializes jPanel1
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel1() {
        if (jPanel1 == null) {
            jPanel1 = new JPanel();
            jPanel1.setPreferredSize(new java.awt.Dimension(160, 20));
            jPanel1.setBackground(teamcolor);
            jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.SoftBevelBorder.LOWERED));
        }
        return jPanel1;
    }

    /**
     * This method initializes jButton
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setPreferredSize(new java.awt.Dimension(35, 20));
            jButton.setText(" ... ");
            jButton.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
            jButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    teamcolor = JColorChooser.showDialog(jButton, "Choose the team color", teamcolor);

                    if (teamcolor == null)
                        teamcolor = Color.blue;

                    jPanel1.setBackground(teamcolor);
                }
            });
        }
        return jButton;
    }

    /**
     * This method initializes jButton1
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton1() {
        if (jButton1 == null) {
            jButton1 = new JButton();
            jButton1.setText(" Cancel ");
            jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jButton1.setToolTipText("");
            jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jButton1.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
            jButton1.setMnemonic(java.awt.event.KeyEvent.VK_A);
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            });
        }
        return jButton1;
    }

    /**
     * This method initializes jPanel2
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel2() {
        if (jPanel2 == null) {
            FlowLayout flowLayout4 = new FlowLayout();
            jPanel2 = new JPanel();
            jPanel2.setLayout(flowLayout4);
            jPanel2.setPreferredSize(new java.awt.Dimension(260, 60));
            flowLayout4.setHgap(5);
            flowLayout4.setVgap(20);
            jPanel2.add(getJButton2(), null);
            jPanel2.add(getJButton1(), null);
        }
        return jPanel2;
    }

    /**
     * This method initializes jButton2
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton2(){
        if (jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setText("Connect");
            jButton2.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
            jButton2.setActionCommand(" Connect ");
            jButton2.setMnemonic(java.awt.event.KeyEvent.VK_C);
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e)
                {
                  buttonOKAction();
                }
            });
        }
        return jButton2;
    }


    private void buttonOKAction()
    {

        /* Getting Fields */

        hostname   = jTextField.getText();
        playername = jTextField1.getText();
        teamname   = jTextField2.getText();


        /* Checking... */
        if ((hostname.equals("")) || (teamname.equals("")) || (playername.equals(""))) {
            JOptionPane.showMessageDialog(jPanel, "Please fill all fields!",
                    "Form Incomplete", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            float[] color = {(float)teamcolor.getRed() ,(float)teamcolor.getGreen(),
                             (float)teamcolor.getBlue(),(float)teamcolor.getAlpha()};

            try
            {
                JRobot.remoteGame = (RemoteGame)Naming.lookup("rmi://"+hostname+"/"+GameUtils.getGameName());

                /* Creating Client */
                JRobot.client = new ClientConfig(hostname, playername, teamname, color);

                /* Call Last Window */
                MenuBar.ctrlDlg = new ControlerDialog(JRobot.remoteGame.getNRobots());
                MenuBar.ctrlDlg.show();
            }
            catch(Exception re)
            {
                JOptionPane.showMessageDialog(jPanel, "Host not Found!", "Error!",
                        JOptionPane.WARNING_MESSAGE);

            }



        }
    }
}