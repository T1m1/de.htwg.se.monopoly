/**
 * 
 */
package de.htwg.monopoly.actor;

import de.htwg.monopoly.controller.IController;

/**
 * @author Steffen
 *
 */
public class ActorMessage {

	private IController controller;
	private String message;

	public ActorMessage(String message, IController controller) {
		this.controller = controller;
		this.message = message;
	}
	
	public IController getController() {
		return controller;
	}
	
	public String getMessage() {
		return message;
	}
}
