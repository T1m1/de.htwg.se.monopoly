/**
 * 
 */
package de.htwg.monopoly.controller.impl;

import java.util.LinkedList;
import java.util.List;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.UserAction;

/**
 * @author stgorenf
 *
 */
public final class UserOptionsController {

	private GameStatus phase;
	private List<UserAction> options;
	private IController controller;

	/**
	 * @param controller
	 * 
	 *            Initialize a new game status controller
	 */
	public UserOptionsController(IController controller) {
		this.controller = controller;
		this.options = new LinkedList<UserAction>();
		phase = controller.getPhase();
		update();
	}

	/**
	 * Returns all available options for the current user.
	 * 
	 * @return
	 */
	public List<UserAction> getCurrentPlayerOptions() {
		return options;
	}

	/**
	 * This method updates the option for a player. Should be called every time
	 * there is a change in the game.
	 */
	public void update() {
		options.clear();
		phase = controller.getPhase();

		switch (phase) {
		case STOPPED:
			// add no user option
			return;
		case STARTED:
		case AFTER_TURN:
		case BEFORE_TURN:
			options.add(UserAction.START_TURN);
			break;
		case BEFORE_TURN_IN_PRISON:

			options.add(UserAction.REDEEM_WITH_CARD);
			options.add(UserAction.REDEEM_WITH_MONEY);

			// FIXME if user selects dice...,
			// options.add(UserAction.REDEEM_WITH_DICE);
			break;
		case DURING_TURN:
			addDuringTurnOptions();
			break;
		case DICE_RESULT:
			//TODO
			break;
		}

		// always add the surrender option, if game is running.
		options.add(UserAction.SURRENDER);

	}

	private void addDuringTurnOptions() {

		switch (controller.getCurrentField().getType()) {
		case STREET:
			if (((Street) controller.getCurrentField()).isSold()) {
				// add no user options
			} else {
				options.add(UserAction.BUY_STREET);
			}
			break;
		case GO_TO_PRISON:
			options.add(UserAction.REDEEM_WITH_CARD);
			options.add(UserAction.REDEEM_WITH_MONEY);
			break;
		case CHANCE_STACK:
		case COMMUNITY_STACK:
		case FREE_PARKING:
		case GO:
		case PRISON_VISIT_ONLY:
		case TAX:
			// add no user options
			break;
		}

		// always add the end turn option if its during the turn of the player
		options.add(UserAction.END_TURN);

	}
}
