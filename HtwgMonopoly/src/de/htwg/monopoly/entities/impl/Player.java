package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.PlayerIcon;

import java.util.LinkedList;
import java.util.List;

public class Player {

	/* variables representing a player object */
	private String name;
	private int budget;
	private int position = 0;
	private int prisonRound = 0;
	private boolean inPrison = false;
	private List<IFieldObject> ownership;
	private int prisonFreeCard = 0;
	private PlayerIcon icon;

	/**
	 * Newest Constructor for creating a player Object. Introduced for the web
	 * Version of this game.
	 * 
	 * @param name
	 * @param playerIcon
	 */
	public Player(String name, PlayerIcon playerIcon) {
		this.name = name;
		this.icon = playerIcon;
		this.budget = IMonopolyUtil.INITIAL_MONEY;
		ownership = new LinkedList<IFieldObject>();
	}

	/**
	 * get name of player
	 * 
	 * @return
	 */
	public String getName() {
		return this.toString();
	}

	/**
	 * get current budget for player
	 * 
	 * @return
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * get current position of player
	 * 
	 * @return
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * set position
	 * 
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * get the current number of rounds in prison of this player. If the
	 * returning number is 0, the current player is not in prison.
	 * 
	 * @return integer;
	 */
	public int getPrisonRound() {
		return prisonRound;
	}

	/**
	 * Increment the prisonRound Number. If three is incremented, the value is
	 * set to 0 and the inPrison status is set to false. 0 means the Player is
	 * not in prison. 1-3 means the Player is in prison and number indicate the
	 * round.
	 * 
	 * @param
	 */
	public void incrementPrisonRound() {
		this.prisonRound = (prisonRound + 1) % IMonopolyUtil.MAX_PRISON_ROUND;
		this.inPrison = (prisonRound != 0);
	}

	/**
	 * If the Player is in prison this method returns true, otherwise false.
	 * 
	 * @return
	 */
	public boolean isInPrison() {
		return inPrison;
	}

	/**
	 * If argument is true, put the Player in prison.
	 * 
	 * @param inPrison
	 */
	public void setInPrison(boolean inPrison) {
		this.inPrison = inPrison;

	}

	/**
	 * override to string method to return the name of the player
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * 
	 * @return a list with the ownership of player
	 */
	public List<IFieldObject> getOwnership() {
		return ownership;
	}

	/**
	 * add a new ownership for player e.g. a street
	 * 
	 * @param street
	 */
	public void addOwnership(IFieldObject street) {
		ownership.add(street);
	}

	/**
	 * decrement money of player e.g. to buy a street or redeem from prison.
	 * Throws an Assertion if the player doesn't have enough money.
	 * 
	 * @param money
	 *            will be subtracted from the players budget.
	 */
	public void decrementMoney(int money) {
		if (this.budget < money) {
			throw new AssertionError("Not enough Money!!");
		}
		this.budget -= money;
	}

	/**
	 * Adds money to the budget of the player.
	 * 
	 * @param money
	 *            will be added to the player budget
	 */
	public void incrementMoney(int money) {
		this.budget += money;
	}

	/**
	 * Increments the number of "Prison-free" - cards, which a player possess.
	 */
	public void incrementPrisonFreeCard() {
		prisonFreeCard++;
	}

	/**
	 * Just checks if the player has at least one prison free card.
	 * 
	 * @return true if the number of prison free cards, which the player possess
	 *         is greater than 0.
	 */
	public boolean hasPrisonFreeCard() {
		return (prisonFreeCard > 0);
	}

	/**
	 * Decrements a prison free card
	 */
	public void usePrisonFreeCard() {
		if (prisonFreeCard < 1) {
			throw new AssertionError("Player has no such card");
		}
		prisonFreeCard--;

	}

	/**
	 * Returns the number of "prison free cards" this user holds.
	 * 
	 * @return integer
	 */
	public int getNumberOfPrisonFreeCards() {
		return prisonFreeCard;
	}

	/**
	 * @return the icon
	 */
	public PlayerIcon getIcon() {
		return icon;
	}

	/**
	 * The current Player buys this current Street. Returns false if the Player
	 * has not enough money. Otherwise true if the purchase was successful.
	 * 
	 * 
	 * @param currentStreet
	 * @return
	 */
	public boolean buyStreet(Street currentStreet) {
		if (currentStreet.isSold()) {
			throw new AssertionError("Street is already sold to someone else");
		}

		// check if the player has enough money
		if (currentStreet.getPriceForStreet() <= this.budget) {

			// subtract the money from player
			decrementMoney(currentStreet.getPriceForStreet());

			// set new Owner of street and add street to the possession of the
			// player.
			currentStreet.setOwner(this);
			currentStreet.setSold(true);

			addOwnership(currentStreet);
			return true;
		}
		return false;

	}

}
