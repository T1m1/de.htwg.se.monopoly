package de.htwg.monopoly.util;

/**
 * These enums define the options, which a player can use in the game.
 * 
 * @author stgorenf
 *
 */
public enum UserAction {

	/**
	 * A simple dice roll.
	 */
	ROLL_DICE("w&uuml;rfeln"),

	/**
	 * Buying a street or other fields, which can be bought.
	 */
	BUY_STREET("Stra&szlig;e kaufen"),

	/**
	 * End the turn of the player
	 */
	END_TURN("Zug beenden"),

	/**
	 * Start the turn of a player, by rolling a dice and moving.
	 */
	START_TURN("W&uuml;rfeln (Zug starten)"),

	/**
	 * End the game, by surrendering.
	 */
	SURRENDER("Aufgeben"),

	/**
	 * This option is available if the player wants to redeem from prison with
	 * money.
	 */
	REDEEM_WITH_MONEY("Mit Geld freikaufen"),

	/**
	 * This option is available if the player wants to redeem from prison with a
	 * chance or community card.
	 */
	REDEEM_WITH_CARD(
			"\"Du kommst aus dem Gef&auml;ngnis frei\" - Karte einl&ouml;sen"),

	/**
	 * This option is available if the player wants to redeem from prison with
	 * trying to roll a "pasch".
	 */
	REDEEM_WITH_DICE(
			"Mit 3 Versuchen ein Pasch w&uuml;rfeln um aus dem Gef&auml;ngnis frei zu kommen."),

	/**
	 * This option is available if the player is in prison and wants to redeem
	 * himself by trying to answer a question correctly.
	 */
	REDEEM_WITH_QUESTION(
			"Eine Bsys Frage korrekt beantworten um aus dem Gef&auml;ngnis frei zu kommen."),

	/**
	 * This option is available, if the current player stands on a field, where
	 * he can draw a chance or community card.
	 */
	DRAW_CARD("Eine Gemeinschafts-/Ereigniskarte ziehen."),

	/**
	 * Used for saving a current on going game into the database.
	 */
	SAVE_GAME("Spiel speichern"),

	/**
	 * Used for loading a saved game from the database to continue playing.
	 */
	LOAD_GAME("Spiel laden"),

	/**
	 * Used for deleting a previously saved game in the database.
	 */
	DELETE_GAME("Spiel löschen");

	private final String description;

	private UserAction(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return description;
	}

}
