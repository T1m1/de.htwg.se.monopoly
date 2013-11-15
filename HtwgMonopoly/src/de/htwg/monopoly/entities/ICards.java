package de.htwg.monopoly.entities;

public interface ICards {
	
	/*
	 * Return the Text on the Card, which will be shown to the User(s)
	 * For example: "Go to prison!" 
	 */
	String getDescription();
	
	/*
	 * Return the type of action which will be performed by the controller.
	 * There are two Types of Action: 
	 * - Money is transferred to or from the Player. //TODO Boolean oder String als rückgabetyp??
	 * - The Player is transferred to another Place.
	 */
	String getActionType();
	
	/*
	 * Return the type of the Card, weather its a Chance or a Community Chest Card
	 */
	String getType();
	
	

}
