package org.jrobot.gui;

/**
 *  Log Dialog
 *
 * @author Malf
 * @version $Id: LogDialog.java,v 1.3 2005/07/04 22:51:27 savio Exp $
 *
 * TODO: XXX Terminar... Ler do log.txt
 */

import javax.swing.*;


public class LogDialog extends JDialog {

    private JPanel jContentPane = null;
    private JScrollPane jScrollPane = null;
    private static JTextArea jTextArea = null;

    public static String line = null;
    public static String newline = "\n";


    /**
     * This method initializes
     */
    public LogDialog() {
        super();
        initialize();
    }


    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(new java.awt.BorderLayout());
            jContentPane.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
            jContentPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }
        return jContentPane;
    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.setContentPane(getJContentPane());
        this.setSize(480, 284);
        this.setTitle("");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();
            }
        });

    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private javax.swing.JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new javax.swing.JScrollPane();
            jScrollPane.setViewportView(getJTextArea());
        }
        return jScrollPane;
    }

    /**
     * This method initializes jTextArea
     *
     * @return javax.swing.JTextArea
     */
    private javax.swing.JTextArea getJTextArea() {
        if (jTextArea == null) {
            jTextArea = new javax.swing.JTextArea();
            jTextArea.setEditable(false);
        }

        return jTextArea;
    }

    public static void setStringLog(String log)
    {
        jTextArea.append(log + newline);
    }

}
