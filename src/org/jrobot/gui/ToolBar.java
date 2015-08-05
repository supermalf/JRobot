/**
 * ToolBar.java
 *
 * Implements toolbar icons and functions
 * 
 * @author $Author: savio $
 * @version $Id: ToolBar.java,v 1.5 2005/07/05 02:12:37 savio Exp $
 *
 */
package org.jrobot.gui;


import org.jrobot.game.GameUtils;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


/**
 * TODO: acessar do MenuBar a instancia das janelas de dispose
 *
 */


public class ToolBar extends JToolBar implements BarProperty, ActionListener {
    /**
     * ToolBar constructor
     */
    private JButton _new = null;
    private JButton _open = null;
    private JButton _save = null;
    private JButton _close = null;
    private JButton _zoomin = null;
    private JButton _zoomout = null;
    private JButton _fit = null;
    private JButton _next = null;
    private JButton _back = null;
    private JButton _exit = null;

    public ToolBar() {
        Dimension dim = new Dimension(50, 70);
        setPreferredSize(dim);
        setLocation(0, 0);
        setToolTipText("Tools");
        addNewtoBar();
        addExittoBar();
        setForeground(new java.awt.Color(255, 51, 204));
        setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 5, 1, java.awt.Color.DARK_GRAY));

    }

    public void actionPerformed(ActionEvent e) {
        if ("_new".equals(e.getActionCommand())) {
            MenuBar.newDlg = new NewGameDialog();
            MenuBar.newDlg .show();
        } else if ("_exit".equals(e.getActionCommand())) {
            MainFrame.Exit();
        }
    }

    public void addNewtoBar() {

        Icon icon = new ImageIcon(GameUtils.getIconPath() + "new.gif");
        _new = new JButton(icon);
        _new.setToolTipText("New Map");
        _new.setActionCommand("_new");
        _new.addActionListener(this);
        add(_new);
    }

    public void addOpentoBar() {
        Icon icon = new ImageIcon(GameUtils.getIconPath() + "open.gif");
        _open = new JButton(icon);
        _open.setToolTipText("Open Map");

//        FileDialog of = new FileDialog();
//        Action action = of.OpenFile();
        //       _open.addActionListener(action);
        add(_open);
    }

    public void addClosetoBar() {
        Icon icon = new ImageIcon(GameUtils.getIconPath() + "close.gif");
        Icon iconDis = new ImageIcon(GameUtils.getIconPath() + "close_dis.gif");
        _close = new JButton(icon);
        _close.setDisabledIcon(iconDis);
        _close.setEnabled(false);
        _close.setToolTipText("Close Map");
        _close.setActionCommand("_close");
        _close.addActionListener(this);
        add(_close);
    }

    public void addSavetoBar() {
        Icon icon = new ImageIcon(GameUtils.getIconPath() + "save.gif");
        Icon iconDis = new ImageIcon(GameUtils.getIconPath() + "save_dis.gif");
        _save = new JButton(icon);
        _save.setDisabledIcon(iconDis);
        _save.setEnabled(false);
        _save.setToolTipText("Save Map");
        _save.setActionCommand("_save");
        _save.addActionListener(this);
        add(_save);
    }

    public void addZoomIntoBar() {
        Icon icon = new ImageIcon(GameUtils.getIconPath() + "zoomin.gif");
        Icon iconDis = new ImageIcon(GameUtils.getIconPath() + "zoomin_dis.gif");
        _zoomin = new JButton(icon);
        _zoomin.setDisabledIcon(iconDis);
        _zoomin.setEnabled(false);
        _zoomin.setToolTipText("Zoom In");
        _zoomin.setActionCommand("_zoomin");
        _zoomin.addActionListener(this);
        add(_zoomin);
    }

    public void addZoomOuttoBar() {
        Icon icon = new ImageIcon(GameUtils.getIconPath() + "zoomout.gif");
        Icon iconDis = new ImageIcon(GameUtils.getIconPath() + "zoomout_dis.gif");
        _zoomout = new JButton(icon);
        _zoomout.setDisabledIcon(iconDis);
        _zoomout.setEnabled(false);
        _zoomout.setToolTipText("Zoom Out");
        _zoomout.setActionCommand("_zoomout");
        _zoomout.addActionListener(this);
        add(_zoomout);
    }

    public void addFittoBar() {
        Icon icon = new ImageIcon(GameUtils.getIconPath() + "fit.gif");
        Icon iconDis = new ImageIcon(GameUtils.getIconPath() + "fit_dis.gif");
        _fit = new JButton(icon);
        _fit.setDisabledIcon(iconDis);
        _fit.setEnabled(false);
        _fit.setToolTipText("Fit Map to page");
        _fit.setActionCommand("_fit");
        _fit.addActionListener(this);
        add(_fit);
    }

    public void addNexttoBar() {
        Icon icon = new ImageIcon(GameUtils.getIconPath() + "next.gif");
        Icon iconDis = new ImageIcon(GameUtils.getIconPath() + "next_dis.gif");
        _next = new JButton(icon);
        _next.setDisabledIcon(iconDis);
        _next.setEnabled(false);
        _next.setToolTipText("View next property");
        _next.setActionCommand("_next");
        _next.addActionListener(this);
        add(_next);
    }

    public void addBacktoBar() {
        Icon icon = new ImageIcon(GameUtils.getIconPath() + "prev.gif");
        Icon iconDis = new ImageIcon(GameUtils.getIconPath() + "prev_dis.gif");
        _back = new JButton(icon);
        _back.setDisabledIcon(iconDis);
        _back.setEnabled(false);
        _back.setToolTipText("View previews property");
        _back.setActionCommand("_back");
        _back.addActionListener(this);
        add(_back);
    }

    public void addExittoBar() {
        Icon icon = new ImageIcon(GameUtils.getIconPath() + "exit.gif");
        _exit = new JButton(icon);
        _exit.setToolTipText("Exit");
        _exit.setActionCommand("_exit");
        _exit.addActionListener(this);
        add(_exit);
    }

    public void setButtons(boolean set) {
        _close.setEnabled(set);
        _save.setEnabled(set);
        _zoomin.setEnabled(set);
        _zoomout.setEnabled(set);
        _fit.setEnabled(set);
        _save.setEnabled(set);
        _next.setEnabled(set);
        _back.setEnabled(set);
    }
}
