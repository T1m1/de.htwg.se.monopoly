package de.htwg.monopoly.cards;

public interface ICards {

	// Klasse evtl doch lieber abstract machen, wegen den genau gleichen
	// implementierungen in den Unterklassen ???

	/**
	 * Return the Text on the Card, which will be shown to the User(s) For
	 * example: "Go to prison!"
	 */
	String getDescription();

	/**
	 * Return the type of action which will be performed by the controller.
	 * There are two Types of Action: - Money is transferred to or from the
	 * Player: "money" - The Player is transferred to another Place: "move"
	 */
	String getActionType();

}
