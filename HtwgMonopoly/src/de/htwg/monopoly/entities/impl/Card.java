package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.entities.ICards;

public abstract class Card implements ICards {

	private String description;
	private boolean moneyTransferredToFromBank;
	private String target;

	/**
	 * Create a new Card with the text on it. The parameter "action" defines the
	 * action, which will be performed in the game after the card is drawn. The
	 * String target is either the target where the player will be moved or an
	 * integer. This integer defines the money, which the player receives or has
	 * to pay.
	 * 
	 * 
	 * @param descr
	 * @param action
	 */
	public Card(String descr, String target, boolean toFromBank) { 
		this.description = descr;
		this.moneyTransferredToFromBank = toFromBank;
		this.target = target;
	}

	/**
	 * Set the description of this Card
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return this.description;
	}


	@Override
	public String toString() {
		return getDescription();
	}

	@Override
	public boolean isReceiveFromToBank() {
		return moneyTransferredToFromBank;
	}

	@Override
	public String getTarget() {
		return target;
	}
}
