package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.util.FieldType;

public final class Bank {

	private static int parkingMoney = 0;

	/**
	 * Static class for handling various Money transfer.
	 */
	private Bank() {
	}

	/**
	 * The given Player pays the rent of the current Street to its owner. Note
	 * that the currentField-Object must be a Street-Object. Otherwise a
	 * assertion error will be thrown.
	 * 
	 * @param currentPlayer
	 * @param currentField
	 * @throws assertionError
	 */
	public static void payRent(Player currentPlayer, IFieldObject currentField) {
		if (currentField.getType() != FieldType.STREET) {
			throw new AssertionError("Field is not a street --> no rent to pay");
		}
		Street currentStreet = (Street) currentField;
		int rent = currentStreet.getRent();
		Player owner = currentStreet.getOwner();

		currentPlayer.decrementMoney(rent);
		owner.incrementMoney(rent);

	}

	/**
	 * The current Player receives money from the Bank. It is possible, that the
	 * amount of money is negative. In that case, the player pays money to the
	 * Bank.
	 * 
	 * @param currentPlayer
	 * @param money
	 */
	public static void receiveMoney(Player currentPlayer, int money) {
		currentPlayer.incrementMoney(money);
	}

	/**
	 * Returns and <b>removes</b> the money in the middle ("Frei Parken") of the
	 * playfield.
	 * 
	 * @return int
	 */
	public static int getParkingMoney() {
		int returnvalue = parkingMoney;
		parkingMoney = 0;
		return returnvalue;
	}

	/**
	 * priceToPay is added to the parking Money (Which is in the "middle" of the
	 * playfield)
	 * 
	 * 
	 * @param priceToPay
	 * @return void
	 */
	public static void addParkingMoney(Player currentPlayer, int priceToPay) {
		currentPlayer.decrementMoney(priceToPay);
		parkingMoney += priceToPay;
	}

	/**
	 * Money is transferred from [receive] to [send]. If Money is a negative
	 * number, its the other way.
	 * 
	 * @param receive
	 * @param send
	 * @param money
	 */
	public static void receiveMoneyFromPlayer(Player receive, Player send,
			int money) {
		receive.incrementMoney(money);
		send.decrementMoney(money);
	}

	/**
	 * This method parses the String [money] and calls, if successful,
	 * {@link Bank#receiveMoneyFromPlayer(Player, Player, int)} with the parsed
	 * number.
	 * 
	 * @param receive
	 * @param send
	 * @param money
	 */
	public static void receiveMoneyFromPlayer(Player receive, Player send,
			String money) {
		int actualMoney;
		try {
			actualMoney = Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new AssertionError("String ist keine Zahl" + e);
		}
		receiveMoneyFromPlayer(receive, send, actualMoney);
	}

	public static void setParkingMoney(int i) {
		parkingMoney = i;
	}
}
