/**
 * OGL test
 *
 * @author Malf
 * @version $Id: oglMain.java,v 1.3 2005/07/03 01:47:59 savio Exp $
 */


package org.jrobot.tst;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import net.java.games.jogl.*;
import org.jrobot.gui.ogl.oglPanel;
import org.jrobot.game.GameUtils;


public class oglMain {
    public static JFrame frame;
    public static oglPanel CanvasPanel;


    public static void main(String[] args) {
        CanvasPanel = new oglPanel();
        frame = new JFrame(GameUtils.getFrameTitle());


        Container contentPane = frame.getContentPane();
        contentPane.add(CanvasPanel);
        frame.setSize((int) Game.wndW, (int) Game.wndH);

        final Animator animator = new Animator(CanvasPanel.getCanvas());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                });
                thread.start();
            }
        });
        frame.show();
        animator.start();

        CanvasPanel.newMap(Game.mapW, Game.mapH, Game.cellSize, Game.wndW, Game.wndH, Game.terrain);
    }
}
