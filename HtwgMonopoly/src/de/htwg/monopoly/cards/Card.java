package de.htwg.monopoly.cards;

public abstract class Card implements ICards {

	private String description;
	private String actionType;

	/**
	 * Create a new Card with the text on it. The parameter "action" defines the
	 * action, which will be performed in the game after the card is drawn.
	 * 
	 * @param descr
	 * @param action
	 */
	public Card(String descr, String action) {
		this.description = descr;
		this.actionType = action;
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
	public String getActionType() {
		return this.actionType;
	}

}
