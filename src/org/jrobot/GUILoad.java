package org.jrobot;

/**
 * GUILoad.java
 *
 * Implements GUI load facilities.
 *
 * @author $Author: savio $
 * @version $Id: GUILoad.java,v 1.5 2005/07/04 06:52:28 savio Exp $
 */


import javax.swing.*;

import org.jrobot.gui.MainFrame;
import org.jrobot.gui.ConnectDialog;
import org.jrobot.game.GameUtils;


public class GUILoad {
    private static MainFrame main_window = null;

    public GUILoad() {
    }

    public boolean load() {
        main_window = new MainFrame(GameUtils.getFrameTitle());
        main_window.setVisible(true);
        main_window.setIconImage(new ImageIcon(GameUtils.getIconPath() + "jrobot.gif").getImage());

        return true;
    }

    public static void setTitle(String s)
    {
       main_window.setTitle("Jrobot v1.8 - "+s);
    }
}
