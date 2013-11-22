package de.htwg.monopoly.cards;

public abstract class Cards {
	
	private String description;
	private String actionType;

	/**
	 * Create a new Card with the text on it. The parameter "action" defines the
	 * action, which will be performed in the game after the card is drawn.
	 * 
	 * @param descr
	 * @param action
	 */
	public Cards(String descr, String action) {
		assert action.equalsIgnoreCase("move")
				|| action.equalsIgnoreCase("money") : "Falsche Initialisierung von einer Karte";
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

	/**
	 * Get the description of this Card
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get the action type of this Card. (move or money)
	 * @return
	 */
	public String getActionType() {
		return this.actionType;
	}

}
