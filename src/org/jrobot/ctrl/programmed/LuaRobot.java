package org.jrobot.ctrl.programmed;

import java.util.*;
import java.lang.Thread;

import org.jrobot.log.*;
import org.jrobot.game.*;
import org.jrobot.game.robot.*;
import org.jrobot.game.robot.cmd.*;
import org.keplerproject.luajava.*;

public class LuaRobot extends PreProgImpl {
    
    /** Lua Stack */
    private LuaState L;
    
    /** Player */
    private Player player;
    
    /** Lua code */
    private String luaCode;
    
    /** Robot */
    private Robot robot;

    /** Robot thread */
    private RobotThread robotThread;
    
    /**
     * Class constructor
     *
     * @param _robotThread Robot thread
     * @param _player Player
     * @param _luaCode Lua code
     */
    public LuaRobot(RobotThread _robotThread, Player _player, String _luaCode) {
        L = LuaStateFactory.newLuaState();
        L.openBasicLibraries();
        L.openDebug();
        L.openLoadLib();
        
        robotThread = _robotThread;
        player = _player;
        luaCode = _luaCode;
        robot = robotThread.getRobot();
    }
    
    /**
     * Run method
     *
     */
    public void run() {
        while(true) {
            L.pushString(luaCode);
            int res = L.pcall(0,0,0);
            
            try {
                if(res != 0) {
                    Log.error("Error on file: " + L.toString(-1)+" sent by "
                              +player.getName()+":"+robot.getRName());
                    L.close();
                }
                
                /* penalty for returning from luaCode */            
                Log.debug("Penalty +15 sec: "+player.getName()+"@"+robot.getRName());
                Thread.sleep(15*1000);
            }
            catch(Exception e) {
                //XXX
            }
        }
    }
}
