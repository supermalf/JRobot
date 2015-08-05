/**
 * FileDialog.java
 *
 * Displays the AboutBox
 *
 * @author $Author: savio $
 * @version $Id: FileDialog.java,v 1.3 2005/07/03 01:47:57 savio Exp $
 */

package org.jrobot.gui;

import javax.swing.*;
import java.io.File;
import java.awt.event.ActionEvent;

import org.jrobot.game.GameUtils;


public class FileDialog extends JFrame {
    public static JFileChooser fc = null;

    public FileDialog() {
        String filename = GameUtils.getMapPath();
        fc = new JFileChooser(new File(filename));
        fc.addChoosableFileFilter(new MyFilter());
    }

    /* Open File */
    public Action OpenFile() {
        Action openAction = new OpenFileAction(this, fc);
        return openAction;
    }

    public class OpenFileAction extends AbstractAction {
        JFrame frame;
        JFileChooser chooser;

        public OpenFileAction(JFrame frame, JFileChooser chooser) {
            super("OpenMap ...");
            this.chooser = chooser;
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent evt) {
            int result = chooser.showOpenDialog(frame);
            File file = chooser.getSelectedFile();

            switch (result) {
                case JFileChooser.APPROVE_OPTION:
                    NewGameDialog.jTextField.setText(chooser.getName(file));
                    NewGameDialog.filename = chooser.getName(file);
                    break;
            }
        }
    }

    /* Save File */
    public Action SaveFile() {
        Action SaveAction = new SaveFileAction(this, fc);
        return SaveAction;
    }

    public class SaveFileAction extends AbstractAction {
        JFileChooser chooser;
        JFrame frame;

        public SaveFileAction(JFrame frame, JFileChooser chooser) {
            super("Save As...");
            this.chooser = chooser;
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent evt) {
            int result = chooser.showSaveDialog(frame);
            File file = chooser.getSelectedFile();

            switch (result) {
                case JFileChooser.APPROVE_OPTION:
                    //MainFrame.canvaspanel.saveMapAs( chooser.getName(file) );
                    break;
            }
        }
    }

    /* File Filter */
    class MyFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            String filename = file.getName();
            return filename.endsWith(".lua");
        }

        public String getDescription() {
            return "*.lua (Map Files)";
        }
    }
}



