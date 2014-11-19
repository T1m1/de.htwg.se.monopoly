/**
 * 
 */
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
	ROLL_DICE("w�rfeln"),
	
	/**
	 * Buying a street or other fields, which can be bought.
	 */
	BUY_STREET("Stra�e kaufen"),
	
	/**
	 * End the turn of the player
	 */
	END_TURN("Zug beenden"),
	
	/**
	 * Start the turn of a player, by rolling a dice and moving.
	 */
	START_TURN("W�rfeln (Zug starten)"),
	
	/**
	 * End the game, by surrendering.
	 */
	SURRENDER("Aufgeben"),
	
	/**
	 * This option is available if the player wants to redeem from prison with money.
	 */
	REDEEM_WITH_MONEY("Mit Geld freikaufen"),
	
	/**
	* This option is available if the player wants to redeem from prison with a chance or community card.
	*/
	REDEEM_WITH_CARD("\"Du kommst aus dem Gef�ngnis frei\" - Karte einl�sen"),
	
	/**
	* This option is available if the player wants to redeem from prison with trying to roll a "pasch".
	*/
	REDEEM_WITH_DICE("Mit 3 Versuchen ein Pasch w�rfeln um aus dem Gef�ngnis frei zu kommen.");
	
	private final String description;
	
	private UserAction(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return description;
	}
	

}
