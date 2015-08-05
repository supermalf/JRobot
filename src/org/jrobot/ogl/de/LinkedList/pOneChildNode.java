/**
 * @(#)pOneChildNode.java 1.00 06/06/05
 *
 */

package org.jrobot.ogl.de.LinkedList;

/**
 * *****************************************
 * <p/>
 * This class implements a liked list node
 *
 * @author MALF
 *         <p/>
 *         *****************************************
 * @version 1.00, 06/06/05
 * @since JBobot v0.4, JDK v1.4
 */


public class pOneChildNode {
    protected float x;
    protected float y;
    protected float z;
    protected pOneChildNode next;

    public pOneChildNode() {
        next = null;
        x = -1;
        y = -1;
        z = -1;
    }

    public pOneChildNode(float x, float y, float z, pOneChildNode n) {
        this.x = x;
        this.y = y;
        this.z = z;
        next = n;
    }

    public void setNext(pOneChildNode n) {
        next = n;
    }

    public void setData(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public pOneChildNode getNext() {
        return next;
    }

    public float getData(char data) {
        if (data == 'x')
            return x;

        else if (data == 'y')
            return y;

        else if (data == 'z')
            return z;

        else
            return -1;
    }

    public String toString() {
        return "" + x + " - " + y + " - " + z;
    }
}
