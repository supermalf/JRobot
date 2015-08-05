package org.jrobot.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.java.games.jogl.*;
import org.jrobot.gui.ogl.oglPanel;
import org.jrobot.game.GameUtils;
import org.jrobot.tst.Game;

/**
 * MainFrame.java
 *
 * Create, configure and load main Swing frame.
 *
 * @author $Author: Malf
 * @version $Id: MainFrame.java,v 1.4 2005/07/05 04:02:24 savio Exp $
 */


public class MainFrame extends JFrame
{
    /**
     * Context objects
     */
    /**
     * panel has menubar and subpanel
     */
    private JPanel panel = null;
    private JPanel subpanel = null;
    /**
     * the following classes are defined in org.jrobot.gui package
     */
    public static MenuBar menubar = null;
    public static ToolBar toolbar = null;
    public static oglPanel canvaspanel = null;
    /**
     * Static objs to configure frame within the class
     */
    public static int FRAME_WIDTH = 800;
    public static int FRAME_HEIGHT = 800;
    public static boolean SET_DEFLOOKNFEEL = true;
    /**
     * Static objs to configure frame within the class
     */
    //public static GameMap FileMap = null;
    public static boolean Enable = false;

    /**
     * MainFrame constructor
     */
    public MainFrame(String AppTitle) {
        setTitle(AppTitle);
        panel = new JPanel();
        menubar = new MenuBar();
        toolbar = new ToolBar();
        subpanel = new JPanel();
        canvaspanel = new oglPanel();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                GameUtils.handleExit();
            }
        });
        /* Make sure JRobot window has a nice look */
        setDefaultLookAndFeelDecorated(SET_DEFLOOKNFEEL);


        /* Configure Frame and Panel */
        configureFrame();
        configurePanel();
        pack();
    }

    /**
     * Set main frame configurations/attributes
     */
    private void configureFrame() {
        setBackground(Color.white);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocation(0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setContentPane(panel);

/*
        // OGL Configurations
        final Animator animator = new Animator(canvaspanel.getCanvas());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                //   make sure the call to Animator.stop() completes before exiting
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        show();
        animator.start();
         */
    }

    /**
     * Set main panel configurations/attributes
     */
    private void configurePanel() {
        /* Configure main panel */
        panel.setLayout(new java.awt.BorderLayout());
        panel.setBackground(Color.white);
        Dimension dim = new Dimension(getWidth(), getHeight());
        panel.setPreferredSize(dim);
        panel.setOpaque(true);

        /* Configure subpanel */
        subpanel.setLayout(new java.awt.BorderLayout());
        dim = new Dimension(panel.getWidth(), panel.getHeight());
        subpanel.setPreferredSize(dim);

        /* Add components to subpanel */
        canvaspanel.newMap(Game.mapW, Game.mapH, Game.cellSize, Game.wndW, Game.wndH, Game.terrain);
        subpanel.add(canvaspanel, java.awt.BorderLayout.CENTER);
        subpanel.add(toolbar, java.awt.BorderLayout.NORTH);


        /* Add things to main panel */
        panel.add(menubar, java.awt.BorderLayout.NORTH);
        panel.add(subpanel, java.awt.BorderLayout.CENTER);


        /* OGL Configurations */
        final Animator animator = new Animator(canvaspanel.getCanvas());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                /* Run this on another thread than the AWT event queue to
                   make sure the call to Animator.stop() completes before exiting  */
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        show();
        animator.start();


   }

    /* Exit Program */
    public static void Exit() {
        /*
        if (oglPanel.saved == false)
        {
            if (Enable == true){
                int result = JOptionPane.showConfirmDialog(menubar, "Are you sure you want to close without saving?",
                                                                    "Question" , JOptionPane.YES_NO_OPTION);
            if (result == 0)
                GameUtils.handleExit();
            }
        }
        else
        {
            GameUtils.handleExit();
        }
        */
        GameUtils.handleExit();

    }
}
