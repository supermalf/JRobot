package org.jrobot.gui;

/**
 *  Controler Dialog
 *
 * @author Malf
 * @version $Id: ControlerDialog.java,v 1.5 2005/07/04 22:20:23 savio Exp $
 */


import org.jrobot.JRobot;
import org.jrobot.game.PlayerException;
import org.jrobot.game.TeamException;

import java.awt.HeadlessException;
import java.io.IOException;
import java.rmi.RemoteException;
import javax.swing.*;

public class ControlerDialog extends JDialog {

    private JPanel jPanel = null;
    private JLabel jLabel = null;
    private JLabel jLabel1 = null;
    private JLabel jLabel2 = null;
    private JLabel jLabel3 = null;
    private JTextField jTextField1 = null;
    private JTextField jTextField = null;
    private static int qt;
    private JTextField robots[];
    private JComboBox controler[];
    private String[] itens = {"Remote", "Programmed", "Manual"};
    public static ConnectDialog connDlg;

    /* Passed values */
    private String[] robotNames;
    private String[] robotTypes;

    /**
     * @throws java.awt.HeadlessException
     */
    public ControlerDialog(int qt) throws HeadlessException {
        super();
        ControlerDialog.qt = qt;
        initialize();
    }


    /**
     * This method initializes this
     * 
     */
    private void initialize()
    {
        MenuBar.joinDlg.dispose();

        this.setModal(true);
        this.setContentPane(getJPanel());
        this.setResizable(false);
        this.setTitle("Connect...");
        this.setSize(353, 340);

    }

    /**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel()
    {
        if (jPanel == null) {
            jLabel3 = new JLabel();
            jLabel2 = new JLabel();
            jLabel1 = new JLabel();
            jLabel = new JLabel();
            jPanel = new JPanel();
            jLabel.setText("Connected to: ");
            jLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jLabel1.setText("Robots: ");
            jLabel1.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
            jLabel1.setPreferredSize(new java.awt.Dimension(150, 50));
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
            jLabel2.setText("Controler Type:");
            jLabel2.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
            jLabel2.setPreferredSize(new java.awt.Dimension(110, 50));
            jLabel3.setText("Team Name:");
            jLabel3.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jPanel.add(jLabel, null);
            jPanel.add(getJTextField(), null);
            jPanel.add(jLabel3, null);
            jPanel.add(getJTextField1(), null);
            jPanel.add(jLabel1, null);
            jPanel.add(jLabel2, null);


            robots    = new JTextField[qt];
            controler = new JComboBox[qt];
            jLabel3.setPreferredSize(new java.awt.Dimension(91, 19));
            jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);


            for (int i = 0; i < qt; i++)
            {
                /* Add Robot Name */
                robots[i] = new JTextField();
                robots[i].setColumns(13);
                //robots[i].setText("Robot" + i);
                jPanel.add(robots[i], null);

                /* Add Robot controler */
                controler[i] = new JComboBox(itens);
                controler[i].setPreferredSize(new java.awt.Dimension(120, 25));
                jPanel.add(controler[i], null);
            }

            /* Add Buttons */
            JButton ButtonOK = new JButton();
            ButtonOK.setText("OK");
            ButtonOK.setMnemonic(java.awt.event.KeyEvent.VK_O);
            ButtonOK.setPreferredSize(new java.awt.Dimension(100, 26));
            ButtonOK.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e)
                {
                     callOkAction();
                }
            });

            JButton ButtonCancel = new JButton();
            ButtonCancel.setText("Cancel");
            ButtonCancel.setMnemonic(java.awt.event.KeyEvent.VK_C);
            ButtonCancel.setPreferredSize(new java.awt.Dimension(100, 26));
            ButtonCancel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e)
                {
                    dispose();
                }
            });

            jPanel.add(ButtonOK, null);
            jPanel.add(ButtonCancel, null);

        }
        return jPanel;
    }

    /**
     * This method calls OK action
     *
     */
    private void callOkAction ()
    {
        /* Creating things...*/
        try
        {
            robotNames = new String[JRobot.remoteGame.getNRobots()];
            robotTypes = new String[JRobot.remoteGame.getNRobots()];

        }
        catch(RemoteException e)
        {
            System.err.println(e);
        }

        Object type;
        int qt_manual=0;
        String name="";

        for (int i = 0; i < qt; i++)
        {
            /* Getting the selected controler */
            type = controler[i].getSelectedItem();

            /* Counting the total of manuals controlers */
            if(type.toString() == "Manual")
                qt_manual++;

            /* Checking if all names are filled */
            name = robots[i].getText();

            if (name.equals(""))
            {
                JOptionPane.showMessageDialog(jPanel, "You have to fill all Robots name!",
                                "Error!", JOptionPane.ERROR_MESSAGE);

                return;
            }

            /* Saving...*/
            robotNames[i] = name;
            robotTypes[i] = type.toString();
        }

        /* Checking if more than one Manual controler is selecteded*/
        if(qt_manual > 1)
        {
            JOptionPane.showMessageDialog(jPanel, "You can't have more than one MANUAL controler!",
                                "Error!", JOptionPane.ERROR_MESSAGE);

            return;
        }

        /* Sending Values */
        try
        {
            connDlg = new ConnectDialog();
            connect();
        }
        catch (Exception exp)
        {
            JOptionPane.showMessageDialog(jPanel, "Couldn't open connection!!",
                                "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

      /**
       * This method connects to the server
       */
      private void connect() throws IOException, PlayerException, TeamException, Exception
      {
          JRobot.client.setRobotName(robotNames);
          JRobot.client.setRobotType(robotTypes);

          connDlg.connectClient(connDlg);
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
            if (JRobot.client != null)
                jTextField.setText(JRobot.client.getHostName());
            else
                jTextField.setText("N/A");
            jTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jTextField.setBackground(new java.awt.Color(200, 238, 238));
            jTextField.setPreferredSize(new java.awt.Dimension(200, 23));
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
            jTextField1.setColumns(15);
            jTextField1.setBackground(new java.awt.Color(200, 238, 238));
            jTextField1.setEditable(false);
            if (JRobot.client != null)
                jTextField1.setText(JRobot.client.getTeamName());
             else
                jTextField1.setText("N/A");
            jTextField1.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
            jTextField1.setPreferredSize(new java.awt.Dimension(172, 23));
        }
        return jTextField1;
    }
}
