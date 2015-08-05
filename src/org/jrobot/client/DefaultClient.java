/**
 * Default Client implementation
 *
 * @author savio
 * @version $Id: DefaultClient.java,v 1.9 2005/07/04 06:52:28 savio Exp $
 */

package org.jrobot.client;

import java.rmi.*;

import org.jrobot.game.*;

public class DefaultClient implements Client {
    Player player = null;
    private GameConfig gcfg;
    private ClientConfig ccfg;
    private RemoteGame game;

    public DefaultClient() throws Exception
    {
        try {
            /*login*/
            gcfg = Game.getConfig();
            //ccfg = Game.getClientConfig();

            //RemoteGame game=(RemoteGame)Naming.lookup("rmi://localhost/"+GameUtils.getGameName());
            game=(RemoteGame)Naming.lookup("rmi://"+ccfg.getHostName()+"/"+gcfg.getName());

            //player = game.login("dm","core.cx",color);
            //player = game.login(ccfg.getPlayerName(), ccfg.getTeamName(), ccfg.getColor());

            for (int i=0; i<gcfg.getNumbRobots(); i++)
            {
                /* adiciona robo manual*/
                if (ccfg.getRobotType(i) == "Manual")
                {
                    //game.addManualRobot(ccfg.getRobotName(i), player);
                    //Robot robot = (Robot) Naming.lookup("rmi://"+ccfg.getHostName()+"/"+ccfg.RobotName[i]+"."+player.getPass());
                    System.out.println("robot's name: "+ccfg.getRobotName(i));
                }

                /* adiciona robo manual*/
                else if (ccfg.getRobotType(i) == "Remote")
                {
                    //game.addRemoteRobot(ccfg.getRobotName(i), player);
                    //Robot robot = (Robot) Naming.lookup("rmi://"+ccfg.getHostName()+"/"+ccfg.RobotName[i]+"."+player.getPass());
                    System.out.println("robot's name: "+ccfg.getRobotName(i));
                }

                /* adiciona robo manual*/
                else if (ccfg.getRobotType(i) == "Programmed")
                {
                    //game.addProgrammedRobot(ccfg.getRobotName(i), player);
                    //Robot robot = (Robot) Naming.lookup("rmi://"+ccfg.getHostName()+"/"+ccfg.RobotName[i]+"."+player.getPass());
                    System.out.println("robot's name: "+ccfg.getRobotName(i));
                }
                else
                {
                    System.out.println("Sem robo ");
                }
            }

         }
        catch (RemoteException e)
        {
            //XXX
        }

    }

     /**
     * Unbind and unexport object from rmi registry, close log file
     * and exit.
     */
    public boolean logout(Player player) throws RemoteException, PlayerException
    {
         try
         {
            return game.logout(player);
         }
         catch(RemoteException e)
         {
             //XXX
         }
         catch(PlayerException e)
         {
             //XXX
         }

        return false;
    }

}
