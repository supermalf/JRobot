# $Id: Makefile,v 1.31 2005/07/04 21:11:20 savio Exp $
default: all

all:
	@echo "compiling org.jrobot.*..."
	javac -deprecation -classpath classes/ -d classes src/org/jrobot/*.java src/org/jrobot/log/*.java src/org/jrobot/game/time/*.java src/org/jrobot/game/proper/*.java src/org/jrobot/game/robot/*.java src/org/jrobot/game/*.java src/org/jrobot/game/robot/cmd/*.java src/org/jrobot/server/*.java src/org/jrobot/ogl/de/LinkedList/*.java  src/org/jrobot/ogl/*.java src/org/jrobot/gui/ogl/*.java src/org/jrobot/gui/*.java src/org/jrobot/client/*.java src/org/jrobot/tst/*.java src/org/jrobot/ctrl/*.java src/org/jrobot/ctrl/manual/*.java src/org/jrobot/ctrl/programmed/*.java
	rmic -d classes -classpath ./classes/ org.jrobot.game.RemoteGameImpl org.jrobot.game.robot.RobotImpl
	@echo "JRobot built successfully!"

game:
	@echo "compiling org.jrobot.game..."
	javac -classpath classes/ -d classes src/org/jrobot/game/time/*.java
	javac -classpath classes/ -d classes src/org/jrobot/game/robot/*.java
	javac -classpath classes/ -d classes src/org/jrobot/game/robot/cmd/*.java
	javac -classpath classes/ -d classes src/org/jrobot/game/*.java
	rmic -d classes -classpath ./classes/ org.jrobot.game.RemoteGameImpl org.jrobot.game.robot.RobotImpl
	@echo "JRobot built successfully!"

gametime:
	@echo "compiling org.jrobot.game.time..."
	javac -classpath classes/ -d classes src/org/jrobot/game/time/*.java
	@echo "JRobot built successfully!"
gamerobot:
	@echo "compiling org.jrobot.game.robot..."
	javac -classpath classes/ -d classes src/org/jrobot/game/robot/*.java
	javac -classpath classes/ -d classes src/org/jrobot/game/robot/cmd/*.java
	@echo "JRobot built successfully!"
gameproper:
	@echo "compiling org.jrobot.game.proper..."
	javac -classpath classes/ -d classes src/org/jrobot/game/proper/*.java
	@echo "JRobot built successfully!"
gameroot:
	@echo "compiling org.jrobot.game..."
	javac -classpath classes/ -d classes src/org/jrobot/game/*.java
	@echo "JRobot built successfully!"
log:
	@echo "compiling org.jrobot.log..."
	javac -classpath classes/ -d classes src/org/jrobot/log/*.java
	@echo "JRobot built successfully!"
serv:
	@echo "compiling org.jrobot.server..."
	javac -classpath classes/ -d classes src/org/jrobot/serv/*.java
	@echo "JRobot built successfully!"
stub:
	@echo "compiling stubs..."
	rmic -d classes -classpath ./classes/ org.jrobot.game.RemoteGameImpl org.jrobot.game.robot.RobotImpl
	@echo "JRobot stubs built successfully!"
test:
	@echo "compiling org.jrobot.tst..."
	javac -classpath classes/ -d classes src/org/jrobot/tst/*.java
	@echo "Tests built successfully!"
client:
	@echo "compiling org.jrobot.client..."
	javac -classpath classes/ -d classes src/org/jrobot/client/*.java
	@echo "JRobot built successfully!"

javadoc:
	javadoc -d docs/javadoc/ -sourcepath src/ -doctitle "JRobot Documentation" -breakiterator -version -author -private org.jrobot.game org.jrobot.game.robot org.jrobot.game.robot.cmd org.jrobot.game.proper org.jrobot.game.time org.jrobot.ctrl org.jrobot.server org.jrobot.client org.jrobot.log org.jrobot.tst 
clean:
	rm -rf classes/org
	rm -rf docs/javadoc/[a-z]*
run-server:
	cd classes; java org.jrobot.tst.ServerMain
run-client:
	cd classes; java org.jrobot.tst.ClientMain
run-reg:
	cd classes; /usr/lib/j2sdk1.4.2_04/bin/rmiregistry&
