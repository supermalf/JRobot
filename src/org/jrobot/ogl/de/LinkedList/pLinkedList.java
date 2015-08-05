/**
 * @(#)pLinkedList.java 1.00 06/06/05
 *
 */

package org.jrobot.ogl.de.LinkedList;


/**
 * *****************************************
 * <p/>
 * This class implements a liked list
 *
 * @author MALF
 *         <p/>
 *         *****************************************
 * @version 1.00, 06/06/05
 * @since JBobot v0.4, JDK v1.4
 */


public class pLinkedList {
    protected pOneChildNode head;
    protected int number;

    public pLinkedList() {
        head = null;
        number = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return number;
    }

    public void insert(float x, float y, float z) {
        head = new pOneChildNode(x, y, z, head);
        number++;
    }

    public float remove() {
        if (isEmpty())
            return -1;
        pOneChildNode tmp = head;
        head = tmp.getNext();
        number--;
        return tmp.getData('z');
    }

    public void insertEnd(float x, float y, float z) {
        if (isEmpty())
            insert(x, y, z);
        else {
            pOneChildNode t = head;
            while (t.getNext() != null)
                t = t.getNext();
            pOneChildNode tmp =
                    new pOneChildNode(x, y, z, t.getNext());
            t.setNext(tmp);
            number++;
        }
    }

    public float removeEnd() {
        if (isEmpty())
            return -1;
        if (head.getNext() == null)
            return remove();
        pOneChildNode t = head;
        while (t.getNext().getNext() != null)
            t = t.getNext();
        float obj = t.getNext().getData('z');
        t.setNext(t.getNext().getNext());
        number--;
        return obj;
    }

    public float peek(int n, char data) {
        pOneChildNode t = head;
        for (int i = 0; i < n && t != null; i++)
            t = t.getNext();
        return t.getData(data);
    }
}
