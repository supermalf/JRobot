/**
 * Client implementation trivia example
 *
 * @author savio
 * @version $Id: ClientMain.java,v 1.13 2005/07/05 04:56:35 savio Exp $
 */
package org.jrobot.tst;

import org.jrobot.game.*;
import org.jrobot.game.robot.*;
import org.jrobot.log.*;

import java.rmi.*;
import java.io.*;

public class ClientMain
{
    public static void main(String[] args) throws PlayerException, TeamException, Exception {
        Player player = null;
        float[] color = {0,0,0,0};

        try {
            /* OBS: o cliente tambem tem que startar o log :-) XXX */

            /*login*/
            RemoteGame game=(RemoteGame)Naming.lookup("rmi://localhost/"+GameUtils.getGameName());

            player = game.login("dm","core.cx",color);             
            System.out.println("Loading remote robot...");
            
            Log.start(GameUtils.getDefLogPath());

            /* adiciona robo */
            if(game.addRemoteRobot("RoboNome", player) != true) {
                Log.warning("Could not create robot #1");
            }
            if(game.addRemoteRobot("RoboNome", player) != true) {
                Log.warning("Could not create robot #2");                
            }
            if(game.addLuaRobot("RoboNome2", player, luaToString("dumb.lua")) != true) {
                Log.warning("Could not create robot #3");            
            }
            Log.debug(">> "+player.getPass());
            Robot robot = (Robot) Naming.lookup("rmi://localhost/RoboNome."+player.getPass());
            System.out.println("robot name: "+robot.getRName());
                
            /*logout*/
            if(game.logout(player)) {
                System.out.println("Logout 1...");
            }
            if(!game.logout(player)) {
                System.out.println("Logout 2... (failed)");
            }

        } 
        catch (PlayerException e) {
            System.out.println("error : "+e);
            
        } 
        catch (TeamException e) {
            System.out.println("error (color already taken?): "+e);
            
        } 
        catch (RemoteException e) {
            System.out.println("error RMI: "+e);            
        } 
        catch(NotBoundException e) {
            System.out.println("Exception received: "+e);
            System.exit(0);
        }
    }
    
    public static String luaToString(String luaFile) {
        BufferedReader in = null;
        StringBuffer buf = new StringBuffer();        
        String line = null;
        
        try {
            in = new BufferedReader(new FileReader(GameUtils.getScriptPath() 
                                                   + luaFile));
            while( (line = in.readLine()) != null) {
                buf.append(line).append('\n'); // XXX lineSeparator!?
            }
        }catch(Exception e) {
        }
        
        return buf.toString();
    }
}
