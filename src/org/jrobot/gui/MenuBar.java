/**
 * MenuBar.java
 *
 * Extends and configures a new JMenuBar to fit the application.
 *
 * @author $Author: savio $
 * @version $Id: MenuBar.java,v 1.11 2005/07/05 08:37:18 savio Exp $
 *
 */
package org.jrobot.gui;

import org.jrobot.ogl.oglRenderer;
import org.jrobot.tst.Game;
import org.jrobot.JRobot;
import org.jrobot.game.time.GameClock;
import org.jrobot.game.GameConfig;
import org.jrobot.log.Log;

import javax.swing.*;
import java.awt.event.*;

public class MenuBar extends JMenuBar implements BarProperty, ActionListener {
    private static JMenu menuFile;
    private static JMenu menuGame;
    private static JMenu menuServer;
    private static JMenu menuView;
    private static JMenu menuLog;

    private static JMenu menuHelp;

    /* File Itens */
    private static JMenuItem _exit = new JMenuItem();

    /* Game Itens */
    private static JMenuItem _newgame = new JMenuItem();
    private static JMenuItem _closegame = new JMenuItem();
    private static JMenuItem _joingame = new JMenuItem();

    /* View Itens */
    private static JMenuItem _score = new JMenuItem();
    private static JMenuItem _time = new JMenuItem();

     /* Server Itens */
    private static JMenuItem _startserv = new JMenuItem();
    private static JMenuItem _endserv = new JMenuItem();

    /* Log Itens */
    private static JMenuItem _logd = new JMenuItem();
    private static JMenuItem _logf = new JMenuItem();

    /* Help Itens */
    private static JMenuItem _help = new JMenuItem();
    private static JMenuItem _about = new JMenuItem();

    /**/
    public static NewGameDialog newDlg;
    public static JoinDialog joinDlg;
    public static ControlerDialog ctrlDlg;


    /**
     * MenuBar constructor
     */
    public MenuBar() {
        menuFile   = new JMenu();
        menuGame   = new JMenu();
        menuView   = new JMenu();
        menuServer = new JMenu();
        menuLog    = new JMenu();
        menuHelp   = new JMenu();

        configureMenuFile();
        configureMenuGame();
        configureMenuView();
        configureMenuServer();
        configureMenuLog();
        configureMenuHelp();

        add(menuFile);
        add(menuGame);
        add(menuView);
        add(menuServer);
        add(menuLog);
        add(menuHelp);
        setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
    }

    /**
     * Handle actions
     */
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();

        /* File Itens */
        if (obj == _exit) {
            MainFrame.Exit();
        }

        /* Game Itens */
        else if (obj == _newgame) {
            newDlg = new NewGameDialog();
            newDlg.show();

        } else if (obj == _joingame) {
            joinDlg = new JoinDialog();
            joinDlg.show();
        } else if (obj == _closegame) {

        }

        /* View Itens */
        else if (obj == _score)
        {
            oglRenderer.showScore();
        }
        else if (obj == _time)
        {
            oglRenderer.showTime();
        }

        /* Server itens */

        else if (obj == _startserv)
        {
           JRobot.serv.startGame();
        }

        else if (obj == _endserv)
        {
           JRobot.serv.shutdown();
        }

        /* Log Itens */
        else if (obj == _logd) {
            oglRenderer.showCommandList();
        } else if (obj == _logf) {
            LogDialog logdlg = new LogDialog();
            logdlg.show();
        }

        /* Help Itens */
        else if (obj == _help) {
            oglRenderer.showHelp();
        } else if (obj == _about) {
            AboutBox about = new AboutBox("About JRobot");
            about.show();
        }

    }

    private void configureMenuFile() {
        /** 
         *  set mnemonic, text and action for each menu item
         */
        addExittoBar();

        /* add them all */
        menuFile.setMnemonic('F');
        menuFile.setText("File");
        menuFile.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        menuFile.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
    }

    private void configureMenuServer() {
        /**
         *  set mnemonic, text and action for each menu item
         */
        addStartServtoBar();
        addEndServtoBar();

        /* add them all */
        menuServer.setMnemonic('S');
        menuServer.setText("Server");
        menuServer.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        menuServer.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        menuServer.setEnabled(false);
    }


    private void configureMenuView() {

        addScoreIntoBar();
        addTimeIntoBar();

        /* add them all */
        menuView.setMnemonic('V');
        menuView.setText("View");
        menuView.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        menuView.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        menuView.setEnabled(false);

    }

    private void configureMenuHelp() {
        addHelptoBar();
        addAbouttoBar();

        menuHelp.setMnemonic('H');
        menuHelp.setText("Help");
        menuHelp.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        menuHelp.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
    }

    private void configureMenuLog() {
        addLogDtoBar();
        addLogFtoBar();

        menuLog.setMnemonic('L');
        menuLog.setText("Log");
        menuLog.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        menuLog.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
    }

    private void configureMenuGame() {
        addNewGametoBar();
        addCloseGametoBar();
        addJoinGametoBar();

        menuGame.setMnemonic('G');
        menuGame.setText("Game");
        menuGame.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        menuGame.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
    }


    /**
     * Implementer BarProperty interface to grant
     * that ToolBar is analog to MenuBar.
     */

    /* File Itens */
    public void addExittoBar() {
        _exit.setMnemonic('E');
        _exit.setText("Exit");
        _exit.addActionListener(this);
        menuFile.add(_exit);
    }

    /* Game Itens */
    public void addNewGametoBar() {
        _newgame.setMnemonic('N');
        _newgame.setText("New game");
        _newgame.addActionListener(this);
        menuGame.add(_newgame);
    }

    public void addJoinGametoBar() {
        _joingame.setMnemonic('J');
        _joingame.setText("Join game");
        _joingame.addActionListener(this);
        menuGame.add(_joingame);
    }

    public void addCloseGametoBar() {
        _closegame.setMnemonic('C');
        _closegame.setText("Close game");
        _closegame.addActionListener(this);
        menuGame.add(_closegame);
    }

    /* View Itens */
    public void addScoreIntoBar() {
        _score.setMnemonic('S');
        _score.setText("Score (F2)");
        _score.addActionListener(this);
        //_score.setEnabled(false);
        menuView.add(_score);
    }

    public void addTimeIntoBar() {
        _time.setMnemonic('T');
        _time.setText("GetTime (F4)");
        _time.addActionListener(this);
        //_score.setEnabled(false);
        menuView.add(_time);
    }

    public void addHelptoBar() {
        _help.setMnemonic('p');
        _help.setText("Help (F1)");
        _help.addActionListener(this);
        _help.setEnabled(false);
        menuHelp.add(_help);
    }

    public void addAbouttoBar() {
        _about.setMnemonic('A');
        _about.setText("About");
        _about.addActionListener(this);
        menuHelp.add(_about);
    }

    public void addLogDtoBar() {
        _logd.setMnemonic('D');
        _logd.setText("Display Game Log (F3)");
        _logd.addActionListener(this);
        _logd.setEnabled(false);
        menuLog.add(_logd);
    }

    public void addLogFtoBar() {
        _logf.setMnemonic('O');
        _logf.setText("Open Log File");
        _logf.addActionListener(this);
        menuLog.add(_logf);
    }

    public void addStartServtoBar() {
        _startserv.setMnemonic('S');
        _startserv.setText("Start Server");
        _startserv.addActionListener(this);
        menuServer.add(_startserv);
    }

    public void addEndServtoBar() {
        _endserv.setMnemonic('E');
        _endserv.setText("End Server");
        _endserv.addActionListener(this);
        menuServer.add(_endserv);
    }

    public void setMenu(boolean set) {
        _help.setEnabled(set);
    }

    public static void startServer()
    {
       menuServer.setEnabled(true);
    }

    public static void startGame()
    {
       menuView.setEnabled(true);
       _logd.setEnabled(true);
       _help.setEnabled(true);

    }

    public static void endServer()
    {
       menuServer.setEnabled(false);
    }

    public static void endGame()
    {
       menuView.setEnabled(false);
       _logd.setEnabled(false);
       _help.setEnabled(false);
    }


}
