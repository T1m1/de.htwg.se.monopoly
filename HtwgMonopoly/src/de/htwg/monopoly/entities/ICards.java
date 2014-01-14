package de.htwg.monopoly.entities;

public interface ICards {

	// Klasse evtl doch lieber abstract machen, wegen den genau gleichen
	// implementierungen in den Unterklassen ???

	/**
	 * Return the Text on the Card, which will be shown to the User(s) For
	 * example: "Go to prison!"
	 */
	String getDescription();

	/**
	 * Checks if the money is transferred from/to Bank OR to the other Players
	 * 
	 * @return boolean
	 */
	boolean isReceiveFromToBank();

	/**
	 * Return the Target, where the Player has to move or an Integer which
	 * indicate the money, that the player receives or has to pay.
	 * 
	 * @return String
	 */
	String getTarget();

}
