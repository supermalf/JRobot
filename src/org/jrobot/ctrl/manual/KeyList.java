package org.jrobot.ctrl.manual;

/**
 * Class Key list
 *
 * @author Malf
 * @version $Id: KeyList.java,v 1.2 2005/07/03 21:33:25 savio Exp $
 */

import org.jrobot.ctrl.manual.ManualImpl;
import org.jrobot.ctrl.manual.Key;
import org.jrobot.ctrl.Action;
import org.jrobot.ctrl.*;

import java.awt.event.KeyEvent;


public class KeyList {
    /* Keys */
    private ManualImpl manual;

    /* Avaliable Key List */
    private static Key[] key_list;

    /* Number of driver keys */
    private int numKeys = 9;

    /**
     * Class constructor.
     * Creates a new instance of a KeyList
     * List the avaliable keys
     */
    public KeyList() {
        key_list = new Key[numKeys];

        /* Keyboard command list */
        key_list[0] = new Key(KeyEvent.VK_UP,    new MoveForward());
        key_list[1] = new Key(KeyEvent.VK_DOWN,  new MoveBackward());
        key_list[2] = new Key(KeyEvent.VK_LEFT,  new TurnLeft());
        key_list[3] = new Key(KeyEvent.VK_RIGHT, new TurnRight());
        key_list[4] = new Key(KeyEvent.VK_G,     new GetGradient());
        key_list[5] = new Key(KeyEvent.VK_L,     new GetPosition());
        key_list[6] = new Key(KeyEvent.VK_P,     new GetPressure());
        key_list[7] = new Key(KeyEvent.VK_SPACE, new GetProspect());
        key_list[8] = new Key(KeyEvent.VK_T,     new GetTime());
    }

    /**
     * Fecth the next action
     *
     * @param keyCode Key Code
     */
    public static Action KeyFetchCommand(int keyCode) {

        for (int i = 0; i < key_list.length; i++) {
            if (key_list[i].getKeyCode() == keyCode) {
                return key_list[i].getKeyAction();
            }
        }

        return null;
    }
}
