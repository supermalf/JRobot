/**
 * Linked List test
 *
 * @author Malf
 * @version $Id: LinkedListMain.java,v 1.2 2005/07/03 01:47:58 savio Exp $
 */

package org.jrobot.tst;

import org.jrobot.ogl.de.LinkedList.pLinkedList;


public class LinkedListMain
{
    public static void main(String[] args)
    {
        pLinkedList l = new pLinkedList();
        Integer x = null;
        Integer y = null;
        Float z = null;
        int i;

        /*
       x = new Integer(0); y = new Integer(0); z = new Float(0.0f);
       l.insertEnd(x,y,z);

       x = new Integer(1); y = new Integer(0); z = new Float(0.1f);
       l.insertEnd(x,y,z);

       x = new Integer(1); y = new Integer(1); z = new Float(0.2f);
       l.insertEnd(x,y,z);

       x = new Integer(0); y = new Integer(1); z = new Float(0.3f);
       l.insertEnd(x,y,z);

      for(i=0;i<l.size();i++)
          System.out.println("peek "+i+": [x]"+l.peek(i, 'x') + " - [y]"+l.peek(i, 'y') + " - [z]"+l.peek(i, 'z'));
          */

        /*

        System.out.println("Iniciando...");

        for(i=0;i<5;i++)
        {
            j = new Integer((int)(Math.random() * 100));
            l.insert(j);
            System.out.println("insert: " + j);
        }

        for(i=0;i<l.size();i++)
            System.out.println("peek "+i+": "+l.peek(i));

        for(;i<10;i++)
        {
            j = new Integer((int)(Math.random() * 100));
            l.insertEnd(j);
            System.out.println("insertEnd: " + j);
        }

        for(i=0;i<l.size();i++)
            System.out.println("peek "+i+": "+l.peek(i));

       // for(i=0;i<5;i++)
         //   System.out.println("remove: " + ((Integer)l.remove()));

        while(!l.isEmpty())
            System.out.println("removeEnd: " + ((Integer)l.removeEnd()));

        System.out.println("Fim!!!");

        */
    }
}
