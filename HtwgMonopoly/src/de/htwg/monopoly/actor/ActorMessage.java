/**
 * 
 */
package de.htwg.monopoly.actor;

import java.io.Serializable;

import de.htwg.monopoly.controller.IController;

/**
 * @author Steffen
 *
 */
public class ActorMessage implements Serializable{

	private static final long serialVersionUID = 1L;
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
