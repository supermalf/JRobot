/**
 * AboutBox frame test
 *
 * @author malf
 * @version $Id: aboutMain.java,v 1.2 2005/07/03 01:47:59 savio Exp $
 */
package org.jrobot.tst;

import org.jrobot.gui.AboutBox;

public class aboutMain {
    public static void main(String[] args) {
        AboutBox a = new AboutBox("About");
        a.show();

    }
}