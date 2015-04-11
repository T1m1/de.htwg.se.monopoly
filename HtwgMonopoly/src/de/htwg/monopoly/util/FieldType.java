package de.htwg.monopoly.util;

/**
 * These enums represent the different types of a certain field in monopoly.
 * 
 * @author Steffen
 *
 */
public enum FieldType {

	/**
	 * Ordinary street, which you can buy and build houses & hotels on it.
	 */
	STREET,

	/**
	 * Start field. You can't buy it.
	 */
	GO,

	/**
	 * You have to pay taxes if you land on that field.
	 */
	TAX,

	/**
	 * If you land on that field, you go immediately to prison.
	 */
	GO_TO_PRISON,

	/**
	 * This field represents the prison. If you land on it, you just visiting.
	 */
	PRISON_VISIT_ONLY,

	/**
	 * This is the opposite field of the start field. There you get the chance
	 * to recieve money from previous tax (or other) payments.
	 */
	FREE_PARKING,

	/**
	 * This field represent a stack of community cards. If you land on that
	 * field, you have to draw a card.
	 */
	COMMUNITY_STACK,

	/**
	 * This field represent a stack of chance cards. If you land on that field,
	 * you have to draw a card.
	 */
	CHANCE_STACK

}
