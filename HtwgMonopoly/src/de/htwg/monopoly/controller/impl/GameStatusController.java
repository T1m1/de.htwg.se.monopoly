/**
 * 
 */
package de.htwg.monopoly.controller.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.FieldObject;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.UserAction;

/**
 * @author stgorenf
 *
 */
public final class GameStatusController {

	private GameStatus phase;
	private List<UserAction> options;
	private IController controller;

	/**
	 * @param controller
	 * 
	 */
	public GameStatusController(IController controller) {
		this.controller = controller;
		this.options = new LinkedList<UserAction>();
		update();
	}

	public List<UserAction> getOptions() {
		throw new UnsupportedOperationException("not implemented yet");

		// return options;
	}

	public GameStatus getPhase() {
		return phase;
	}

	/**
	 * Sets the status and the options.
	 */
	public void update() {

		if (controller.getPlayers() == null) {
			phase = GameStatus.STOPPED;
			return;
		}

		if (controller.getMessage().isEmpty()) {
			phase = GameStatus.USER_BEFORE;
		}

		options = retrieveOptions();

	}

	/**
	 * This method retrieves all available options of the current player.
	 * 
	 * @return a list with all options.
	 */
	private List<UserAction> retrieveOptions() {
		options.clear();

		IFieldObject currentField = controller.getCurrentField();

		// TODO what kind of fields are there???
		if (currentField instanceof Street) {
			options.addAll(getStreetOptions());
		} else if (currentField instanceof FieldObject) {
			options.addAll(getFieldOptions());
		}
		
		return options;
	}

	private Collection<UserAction> getFieldOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	private Collection<UserAction> getStreetOptions() {
		// TODO Auto-generated method stub
		return null;
	}

}
