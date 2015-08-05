package org.jrobot.ctrl.manual;

import org.jrobot.ctrl.Action;

/**
 * Class Key
 *
 * @author Malf
 * @version $Id: Key.java,v 1.2 2005/07/03 21:33:25 savio Exp $
 */


public class Key {
    /* Key Code */
    private int keyCode;

    /* Key Action */
    private Action action;

    /**
     * Class constructor.
     * Creates a new instance of a Key
     *
     * @param keyCode   Key Code
     * @param keyAction Key Action
     */
    public Key(int keyCode, Object keyAction) {
        this.keyCode = keyCode;
        this.action = (Action) keyAction;
    }

    /**
     * Get Key Code
     *
     * @return Key Code
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * Get a action of a key
     *
     * @return Key Action
     */
    public Action getKeyAction() {
        return action;
    }

    /**
     * Sets the Key action
     *
     * @param action Key Action
     */
    public void setKeyAction(Action action) {
        this.action = action;
    }

    /**
     * Sets the Key code
     *
     * @param code Key Code
     */
    public void setKeyCode(int code) {
        keyCode = code;
    }
}