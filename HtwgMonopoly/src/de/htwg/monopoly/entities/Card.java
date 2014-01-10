package de.htwg.monopoly.entities;

public abstract class Card implements ICards {

	private String description;
	private String actionType;
	private int money;
	private int move;
	private boolean moneyTransferredToFromBank;
	private String target;

	/**
	 * Create a new Card with the text on it. The parameter "action" defines the
	 * action, which will be performed in the game after the card is drawn.
	 * 
	 * @param descr
	 * @param action
	 */
	public Card(String descr, String action, String target, int money, int move,
			boolean toFromBank) {
		this.description = descr;
		this.actionType = action;
		this.money = money;
		this.move = move;
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
	public String getActionType() {
		return this.actionType;
	}

	@Override
	public String toString() {
		return getDescription();
	}

	@Override
	public int getMoney() {
		return money;
	}

	@Override
	public int getMove() {
		return move;
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
