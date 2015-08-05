javac -classpath classes/ -d classes src/org/jrobot/log/*.java src/org/jrobot/game/time/*.java src/org/jrobot/game/proper/*.java src/org/jrobot/game/robot/*.java src/org/jrobot/game/*.java src/org/jrobot/game/robot/cmd/*.java src/org/jrobot/server/*.java src/org/jrobot/client/*.java src/org/jrobot/tst/*.java 

rmic -d classes -classpath ./classes/ org.jrobot.game.RemoteGameImpl
