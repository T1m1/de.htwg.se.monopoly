package de.htwg.monopoly.entities.impl;


public class CommunityCard extends Card {

	/**
	 * Create a new Card with the text on it. The parameter "action" defines the
	 * action, which will be performed in the game after the card is drawn. The
	 * String target is either the target where the player will be moved or an
	 * integer. This integer defines the money, which the player receives or has
	 * to pay.
	 * 
	 * 
	 * @param descr
	 *            A simple text describing the action for the user.
	 * @param target
	 *            Indicates the target. This can be the name of a street, where
	 *            the player should be moved or an integer indicating the amount
	 *            of money the player receives or has to pay.
	 * @param toFromBank
	 *            if set true, the money is transferred to/from the bank.
	 *            Otherwise the money is transferred to the other players.
	 */
	public CommunityCard(String descr, String target, boolean toFromBank) {
		super(descr, target, toFromBank);
	}

}
