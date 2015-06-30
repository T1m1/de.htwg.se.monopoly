/**
 * 
 */
package de.htwg.monopoly.actor;

import de.htwg.monopoly.util.GameStatus;
import akka.actor.UntypedActor;

/**
 * @author Steffen
 *
 */
public class Actor extends UntypedActor {
	
	public Actor() {
		
	}

	@Override
	public void onReceive(Object msg) throws Exception {

		if (msg instanceof ActorMessage &&
				"update" == ((ActorMessage) msg).getMessage()) {
			
			GameStatus phase = ((ActorMessage) msg).getGameStatus();
			((ActorMessage) msg).getController().notifyObservers(phase);
		}
	}

}
