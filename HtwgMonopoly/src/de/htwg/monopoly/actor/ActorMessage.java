/**
 * 
 */
package de.htwg.monopoly.actor;

import java.io.Serializable;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.GameStatus;

/**
 * @author Steffen
 *
 */
public class ActorMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private IController controller;
	private String message;
	private GameStatus status;

	public ActorMessage(String message, IController controller, GameStatus phase) {
		this.controller = controller;
		this.message = message;
		this.status = phase;
	}
	
	public IController getController() {
		return controller;
	}
	
	public String getMessage() {
		return message;
	}
	
	public GameStatus getGameStatus() {
		return status;
	}
}
