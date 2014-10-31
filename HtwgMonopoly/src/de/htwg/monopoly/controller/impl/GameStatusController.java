/**
 * 
 */
package de.htwg.monopoly.controller.impl;

import java.util.LinkedList;
import java.util.List;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Player;
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
	 * Initialize a new game status controller
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

	public void update() {
		options.clear();

		if (controller.getPlayers() == null) {
			// no game is started
			phase = GameStatus.STOPPED;
			return;
		}

		Player currentPlayer = controller.getCurrentPlayer();

		// if it is before/after a turn the message is empty
		if (controller.getMessage().isEmpty()) {
			phase = GameStatus.BEFORE_TURN;

			// if current player is in prison
			if (currentPlayer.isInPrison()) {
				options.add(UserAction.REDEEM_WITH_CARD);
				options.add(UserAction.REDEEM_WITH_MONEY);
				
				//FIXME if user selects dice...,
				//options.add(UserAction.REDEEM_WITH_DICE);
			} else {
				options.add(UserAction.START_TURN);
			}
			// the user has started his turn
		} else {
			phase = GameStatus.DURING_TURN;

			IFieldObject currentField = controller.getCurrentField();

		}

	}

}
