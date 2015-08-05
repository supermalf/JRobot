package org.jrobot.game.robot.cmd;

/**
 * Command list
 *
 * @author savio
 * @version $Id: CommandList.java,v 1.8 2005/07/03 06:49:49 savio Exp $
 */

public class CommandList {

    /* commands */
    private Cmd[] cmd_list = {new Cmd("turnLeft", new CmdTurn("l")),
                              new Cmd("turnRight", new CmdTurn("r")),
                              new Cmd("moveForward", new CmdMove("f")),
                              new Cmd("moveBackward", new CmdMove("b")),
                              new Cmd("position", new CmdPosition()),
                              new Cmd("pressure", new CmdPressure()),
                              new Cmd("gradient", new CmdGradient()),
                              new Cmd("time", new CmdTime()),
                              new Cmd("prospect", new CmdProspect())};

    /**
     *
     */
    public Command cmdGet(String cmd_str) {
        for (int i = 0; i < cmd_list.length; i++) {
            if (cmd_list[i].strcmp(cmd_str)) {
                return cmd_list[i].getcmd();
            }
        }
        return null;
    }

    /**
     *
     */
    private class Cmd {
        private String cmd_str;
        private Command cmd;

        protected Cmd(String str, Object _cmd) {
            cmd = (Command) _cmd;
            cmd_str = str;
        }

        protected boolean strcmp(String str) {
            return str.equals(cmd_str);
        }

        protected Command getcmd() {
            return cmd;
        }
    }
}
