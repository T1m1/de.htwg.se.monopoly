/**
 * 
 */
package de.htwg.monopoly.actor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.monopoly.util.GameStatus;
import akka.actor.UntypedActor;

/**
 * @author Steffen
 *
 */
public class Actor extends UntypedActor {
	

    /* logger */
    private final Logger logger = LogManager.getLogger("htwgMonopoly");
	
	public Actor() {
		logger.info("Actor instanciated");
		
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		
		logger.info("Actor received message");
		
		

		if (msg instanceof ActorMessage &&
				"update" == ((ActorMessage) msg).getMessage()) {
			
			logger.info("Actor will update all Observers");
			
			GameStatus phase = ((ActorMessage) msg).getGameStatus();
			((ActorMessage) msg).getController().notifyObservers(phase);
		} else {
			unhandled(msg);
		}
	}

}
