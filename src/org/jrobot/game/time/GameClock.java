/*
 * @author savio	
 * @version $Id: GameClock.java,v 1.5 2005/07/05 03:42:27 savio Exp $
 */
package org.jrobot.game.time;

import java.util.Timer;
import java.util.TimerTask;
import java.lang.Thread;
import org.jrobot.game.*;
import org.jrobot.log.*;

public class GameClock {

    /**
     * Timer
     */
    private static Timer timer;
     
    public GameClock(int _gameTime) {
        if(GameTime.gameTime == 0) {
            timer = new Timer();
 
            GameTime.gameTime = _gameTime;
            GameTime.remaining = _gameTime;
            GameTime.elapsed = 0;
            timer.schedule(new TickTack(), 0, 1*1000);
        }
    }
    
    class TickTack extends TimerTask {
        public void run() {
            GameClock.update(); 
         }
    }

    public static void update() {
        if(Game.isGameStarted()) {
            GameTime.elapsed += 1;
            GameTime.remaining -= 1;
            if(GameTime.remaining == 0) {
                timer.cancel();
                Game.endGame();
            }
            Game.visualData.updateClock(GameTime.remaining);
        }
        //XXX: Log.debug("time: "+elapsed+" remaining: "+remaining); 
    }

    /**
     * Get elapsed
     *
     * @return elapsed time
     */
    public int getElapsed() {
        return GameTime.elapsed;
    }

    /**
     * Get remaining
     *
     * @return remaining time
     */
    public int getRemaining() {
        return GameTime.remaining;
    }
    
}
