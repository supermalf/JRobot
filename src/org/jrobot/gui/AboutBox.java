/**
 * AboutBox.java
 *
 * Displays the AboutBox
 *
 * @author $Author: Malf
 * @version $Id: AboutBox.java,v 1.4 2005/07/03 01:47:57 savio Exp $
 */

package org.jrobot.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;

import org.jrobot.game.GameUtils;


public class AboutBox extends JDialog {
    /**
     * Context objects
     */
    /**
     * panel has menubar and subpanel
     */
    private JPanel Panel = null;
    private JPanel PanelLabels = null;
    private JPanel Panel2 = null;
    private JPanel PanelButton = null;

    /**
     * MainFrame constructor
     */
    public AboutBox(String AppTitle) {
        setTitle(AppTitle);
        Panel = new JPanel();
        PanelLabels = new JPanel();
        Panel2 = new JPanel();
        PanelButton = new JPanel();

        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            configurePanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pack();

    }

    /**
     * Set main panel configurations/attributes
     */
    private void configurePanel() {
        this.setTitle("About Jrobot");
        setResizable(false);
        this.setModal(true);

        /* Configure main panel */
        Panel.setLayout(new BorderLayout());
        Panel.setBackground(Color.white);
        Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        /* Configure & Add components to NorthPanel */
        ImageIcon LegendImage = new ImageIcon(GameUtils.getIconPath() + "jrobot_big.gif");
        JLabel label = new JLabel("- " + GameUtils.getFrameTitle() + " -", LegendImage, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setForeground(Color.blue);

        PanelLabels.setLayout(new GridLayout(4, 1));
        PanelLabels.setBorder(BorderFactory.createEmptyBorder(10, 80, 30, 80));
        PanelLabels.setBackground(Color.white);

        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();

        label2.setFont(new Font("Arial", Font.PLAIN, 16));
        label3.setFont(new Font("Arial", Font.PLAIN, 16));
        label4.setFont(new Font("Arial", Font.PLAIN, 16));

        label2.setForeground(Color.black);
        label3.setForeground(Color.black);
        label4.setForeground(Color.black);

        /* Configure & Add components to CenterPanel */
        label2.setText("Thank you for using JRobot!");
        label3.setText("Authors: Malf, Savio, Tucano");
        label4.setText("Copyright (c) 2005 Freeware");

        Panel2.setLayout(new BorderLayout());
        Panel2.setBackground(Color.LIGHT_GRAY);

        /* Configure & Add components SouthPanel */

        JButton ButtonClose = new JButton("Close");

        ButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });

        Panel.add(PanelButton, BorderLayout.SOUTH);
        PanelButton.add(ButtonClose, null);
        Panel2.add(PanelLabels, BorderLayout.CENTER);
        PanelLabels.add(label, null);
        PanelLabels.add(label2, null);
        PanelLabels.add(label3, null);
        PanelLabels.add(label4, null);
        this.getContentPane().add(Panel, null);
        Panel.add(Panel2, BorderLayout.NORTH);
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            dispose();
        }
        super.processWindowEvent(e);
    }
}