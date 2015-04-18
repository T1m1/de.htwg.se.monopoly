/**
 * 
 */
package de.htwg.monopoly.controller.impl;

import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.UserAction;

import java.util.LinkedList;
import java.util.List;

/**
 * @author stgorenf
 *
 */
public final class UserOptionsController {

	private GameStatus phase;
	private final List<UserAction> options;
	private final Controller controller;

	/**
	 * @param controller
	 * 
	 *            Initialize a new game status controller
	 */
	public UserOptionsController(Controller controller) {
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
		case NOT_STARTED:
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
			options.add(UserAction.REDEEM_WITH_DICE);
			options.add(UserAction.REDEEM_WITH_QUESTION);
			break;
		case DICE_ROLL_FOR_PRISON:
			if (controller.isDiceFlagSet()) {
				options.add(UserAction.ROLL_DICE);
			} else {
				options.add(UserAction.END_TURN);
			}
			break;
		case DURING_TURN:
			addDuringTurnOptions();
			break;
		case DICE_RESULT:
			options.add(UserAction.ROLL_DICE);
			break;
		}

		// always add surrender, save and load option, if game is running.
		options.add(UserAction.SURRENDER);
		options.add(UserAction.LOAD_GAME);
		options.add(UserAction.SAVE_GAME);

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
			if (controller.hasDrawnCard()) {
				// add no option
			} else {
				options.add(UserAction.DRAW_CARD);
				return;
			}
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
