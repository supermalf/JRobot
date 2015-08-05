package org.jrobot.gui;

import org.jrobot.game.*;
import org.jrobot.game.robot.Robot;
import org.jrobot.server.ServImpl;
import org.jrobot.JRobot;
import org.jrobot.GUILoad;
import org.jrobot.ctrl.manual.ManualImpl;
import org.jrobot.log.Log;
import org.jrobot.ogl.oglRenderer;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;

/**
 * Connect Dialog
 *
 * @author Malf
 * @version $Id: ConnectDialog.java,v 1.13 2005/07/05 08:37:18 savio Exp $
 */

public class ConnectDialog extends JDialog {

    private JPanel jPanel = null;
    private JButton jButton = null;
    private JPanel jPanel1 = null;
    public static JProgressBar jProgressBar = null;
    public static JLabel jLabel = null;
    public static JButton jButton1 = null;
    private static int id = 0;

    //public static int progress = 0;
    /**
     * @throws java.awt.HeadlessException
     */
    public ConnectDialog() throws HeadlessException {
        super();
        initialize();
    }

    /**
     * @param arg0
     * @throws java.awt.HeadlessException
     */
    public ConnectDialog(Frame arg0) throws HeadlessException {
        super(arg0);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public ConnectDialog(Frame arg0, boolean arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public ConnectDialog(Frame arg0, String arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws java.awt.HeadlessException
     */
    public ConnectDialog(Frame arg0, String arg1, boolean arg2)
            throws HeadlessException {
        super(arg0, arg1, arg2);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    public ConnectDialog(Frame arg0, String arg1, boolean arg2,
                         GraphicsConfiguration arg3) {
        super(arg0, arg1, arg2, arg3);
        initialize();
    }

    /**
     * @param arg0
     * @throws java.awt.HeadlessException
     */
    public ConnectDialog(Dialog arg0) throws HeadlessException {
        super(arg0);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public ConnectDialog(Dialog arg0, boolean arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @throws java.awt.HeadlessException
     */
    public ConnectDialog(Dialog arg0, String arg1) throws HeadlessException {
        super(arg0, arg1);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws java.awt.HeadlessException
     */
    public ConnectDialog(Dialog arg0, String arg1, boolean arg2)
            throws HeadlessException {
        super(arg0, arg1, arg2);
        initialize();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @throws java.awt.HeadlessException
     */
    public ConnectDialog(Dialog arg0, String arg1, boolean arg2,
                         GraphicsConfiguration arg3) throws HeadlessException {
        super(arg0, arg1, arg2, arg3);
        initialize();
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        this.setContentPane(getJPanel());
        this.setResizable(false);
        this.setTitle("Conneting...");
        this.setSize(388, 215);

    }

    /**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel() {
        if (jPanel == null) {
            jLabel = new JLabel();
            jPanel = new JPanel();
            jPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jLabel.setText("Connecting server... Please Wait!");
            jLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 18));
            jLabel.setPreferredSize(new java.awt.Dimension(289, 60));
            jPanel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 18));
            jPanel.add(jLabel, null);
            jPanel.add(getJProgressBar(), null);
            jPanel.add(getJPanel1(), null);
        }
        return jPanel;
    }

    /**
     * This method initializes jProgressBar
     *
     * @return javax.swing.JProgressBar
     */
    private JProgressBar getJProgressBar() {
        if (jProgressBar == null) {
            jProgressBar = new JProgressBar();
            jProgressBar.setStringPainted(true);
            jProgressBar.setPreferredSize(new java.awt.Dimension(220, 25));
        }
        return jProgressBar;
    }


    /**
     * This method initializes jPanel1
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel1() {
        if (jPanel1 == null) {
            FlowLayout flowLayout1 = new FlowLayout();
            jPanel1 = new JPanel();
            jPanel1.setLayout(flowLayout1);
            jPanel1.setPreferredSize(new java.awt.Dimension(300, 70));
            flowLayout1.setVgap(20);
            jPanel1.add(getJButton1(), null);
        }
        return jPanel1;
    }


    /**
     * Connect Dialog to create server.
     *
     * @param _connDlg    Connection Dialog
     * @param _map        Lua map file to be loaded
     * @param _properList List of dynamic property names to be loaded
     * @param _name       Game name. This will be used as label to JPanel
     * @param _teams      Maximum number of teams allowed in this game
     * @param _robots     Exact number of robots a team need to be accepted
     * @param _gametime   Game time in seconds
     * @param _allow      Does this game allows a manual engine?
     */
    public void connectServer(ConnectDialog _connDlg, String _map, String[] _properList, String _name,
                              int _teams, int _robots, int _gametime, boolean _allow) throws IOException
    {


        id = 0;

        /* Display window */
        show();

        /* 0% Complete */
        progress(0);
        _connDlg.paint(_connDlg.getGraphics());


        /* Destroy previews window */
        MenuBar.newDlg.dispose();

        /* 10% Complete */
        progress(10);
        _connDlg.paint(_connDlg.getGraphics());

        /* Configurating Game */
        JRobot.cfg = new GameConfig(_map, _properList, _name, _teams, _robots, _gametime, _allow);


        /* 40% Complete */
        progress(40);
        _connDlg.paint(_connDlg.getGraphics());


        /* Setting Dynamics proper list */
        JRobot.cfg.setProperList(_properList);


        /* 60% Complete */
        progress(60);
        _connDlg.paint(_connDlg.getGraphics());


        /* Creating Server */
        JRobot.serv = new ServImpl(GameUtils.getDefLogPath());


        /* 90% Complete */
        progress(90);
        _connDlg.paint(_connDlg.getGraphics());


        /* 100% Complete */
        progress(100);
        _connDlg.paint(_connDlg.getGraphics());
    }


    /**
     * This method...
     */
    public void connectClient(ConnectDialog _connDlg) throws PlayerException, TeamException, Exception
    {
        id = 1;

        /* Display window */
        show();

        /* 0% Complete */
        progress(0);
        _connDlg.paint(_connDlg.getGraphics());


        /* Destroy previews window */
        MenuBar.ctrlDlg.dispose();

        /* 10% Complete */
        progress(10);
        _connDlg.paint(_connDlg.getGraphics());


        /* Creating Player */
        JRobot.player = new Player();

        /* Getting things */
        String gamename     = GameConfig.gameName;
        String hostname     = JRobot.client.getHostName();
        String playername   = JRobot.client.getPlayerName();
        String teamname     = JRobot.client.getTeamName();
        String[] robotNames = JRobot.client.getRobotName();
        String[] robotTypes = JRobot.client.getRobotType();
        float[] color       = JRobot.client.getColor();

        /* 15% Complete */
        progress(15);
        _connDlg.paint(_connDlg.getGraphics());


        /* Login */

        JRobot.player     = JRobot.remoteGame.login(playername,teamname,color);


        /* 30% Complete */
        progress(30);
        _connDlg.paint(_connDlg.getGraphics());

        /* Log */
        Log.start(GameUtils.getDefLogPath());
        Log.notice("Player: "+ JRobot.player.getName()+" is online.");

        /* 50% Complete */
        progress(50);
        _connDlg.paint(_connDlg.getGraphics());

        /* Adding Robots */
        for (int i = 0; i < robotNames.length; i++)
        {
            /* Manual or Remote robots */
            if((robotTypes[i] == "Remote") || robotTypes[i] == "Manual")
            {
                if(JRobot.remoteGame.addRemoteRobot(robotNames[i], JRobot.player) != true) {
                    Log.warning("Could not create robot "+robotNames[i]+".");
                    break;
                }


                /* Start manual controler */
                if (robotTypes[i] == "Manual")
                {
                    Robot manualRobot = (Robot) Naming.lookup("rmi://"+hostname+"/"+robotNames[i]+"."+
                                                JRobot.player.getPass());

                    JRobot.manualControl = new ManualImpl(manualRobot);
                    JRobot.manualControl.start();
                }
            }

            /* Programmed Robot */
            else if (robotTypes[i] == "Programmed")
            {
                // TODO: XXX
                System.out.println("Programmed Robot!!");
            }
        }

        /* 90% Complete */
        progress(90);
        _connDlg.paint(_connDlg.getGraphics());

        /* Set frame name connected */
        GUILoad.setTitle("Connected@"+hostname);

        /* 100% Complete */
        progress(100);
        _connDlg.paint(_connDlg.getGraphics());

    }


    /**
     * This method ...
     */
    private void createGame()
    {
        /* Set frame name connected */
        if (id == 0)
        {
            GUILoad.setTitle("Server created at localhost");
            MenuBar.startServer();
        }

        else if (id == 1)
        {
            GUILoad.setTitle("Connected at "+JRobot.client.getHostName());
            MenuBar.startGame();

            /* Draw Game */
            try
            {
                oglRenderer.terrain  = JRobot.remoteGame.getDepthMap();
                oglRenderer.mapW     = JRobot.remoteGame.getMapBounds()[0];
                oglRenderer.mapH     = JRobot.remoteGame.getMapBounds()[1];
                oglRenderer.cellSize = JRobot.remoteGame.getCellSize();
                oglRenderer.makeTerrain();
                oglRenderer.setDraw(true);
            }
            catch (RemoteException e)
            {
                System.err.println(e);
            }


        }
          dispose();/* XXX todo Nao dah dispose mais de 1 vez ?!*/
    }



    /**
     * This method sets a value to the progress bar
     */
    public static void progress(int p) {
        jProgressBar.setValue(p);
        jProgressBar.setString(p + "%");

        if (p == 0) {
            jLabel.setText("Connecting server... Please Wait!");
            jButton1.setEnabled(false);
        }

        if (p == 100) {
            jLabel.setText("       Connected to the Server!");
            jButton1.setEnabled(true);
        }

    }


    /**
     * This method initializes jButton1
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton1()
    {
        if (jButton1 == null) {
            jButton1 = new JButton();
            jButton1.setEnabled(false);
            jButton1.setText("OK");
            jButton1.setPreferredSize(new java.awt.Dimension(100, 26));
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e)
                {

                   if(id == 0 )
                   {
                       //iniciar log
                       LogDialog logdialog = new LogDialog();
                       logdialog.show();
                   }

                   createGame();
                }
            });
        }


        return jButton1;
    }


}
