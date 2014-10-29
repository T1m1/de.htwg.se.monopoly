package de.htwg.monopoly.controller.impl;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Bank;
import de.htwg.monopoly.entities.impl.ChanceCardsStack;
import de.htwg.monopoly.entities.impl.CommunityCardsStack;
import de.htwg.monopoly.entities.impl.FieldObject;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.util.IMonopolyFields;
import de.htwg.monopoly.util.IMonopolyUtil;

public class Playfield implements IPlayfield {

	private IFieldObject[] playfield;
	private CommunityCardsStack commStack;
	private ChanceCardsStack chanStack;
	private int fieldSize;
	private boolean wentOverGo = false;

	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	/**
	 * constructor
	 * 
	 * @param size
	 */
	public Playfield(int size) {
		this.fieldSize = size;
		this.playfield = new IFieldObject[this.fieldSize];
		this.commStack = new CommunityCardsStack();
		this.chanStack = new ChanceCardsStack();
		
		for (int i = 0; i < fieldSize; i++) {
			createField(i);
		}
	}

	/**
	 * TODO for each case statement a new function!!! -> MAYBE
	 * 
	 * @param i
	 */
	private void createField(int i) {
		switch (IMonopolyFields.TYP[i]) {
		case 'l':
			playfield[i] = new FieldObject(IMonopolyFields.NAME[i],
					IMonopolyFields.TYP[i], IMonopolyFields.POSITION[i]);
			break;
		case 's':
			playfield[i] = new Street(IMonopolyFields.NAME[i],
					IMonopolyFields.PRICE_FOR_STREET[i],
					IMonopolyFields.COLOUR[i], IMonopolyFields.RENT[i],
					IMonopolyFields.HOTEL[i], IMonopolyFields.POSITION[i]);
			break;
		case 'g':
			this.commStack.setPosition(IMonopolyFields.POSITION[i]);
			playfield[i] = this.commStack;

			break;
		case 'z':
			playfield[i] = new FieldObject("Zusatzsteuer",
					IMonopolyFields.TYP[i], IMonopolyUtil.ZUSATZSTEUER,
					IMonopolyFields.POSITION[i]);
			break;
		case 'e':
			this.chanStack.setPosition(IMonopolyFields.POSITION[i]);
			playfield[i] = (IFieldObject) this.chanStack;
			break;
		case 'n':
			/* BSYS -> zu besuch */
			playfield[i] = new FieldObject("Bsys Labor, nur zu Besuch",
					IMonopolyFields.TYP[i], 0, IMonopolyFields.POSITION[i]);
			break;
		case 'p':
			/* gehe ins Bsys labor */
			playfield[i] = new FieldObject("Gehe in das Bsys Labor",
					IMonopolyFields.TYP[i], 0, IMonopolyFields.POSITION[i]);
			break;
		case 'f':
			playfield[i] = new FieldObject("Mensa", IMonopolyFields.TYP[i], 0,
					IMonopolyFields.POSITION[i]);
			break;

		}
	}

	/**
	 * Move the Player according to the dice Result. If the Player went over Go,
	 * it is saved in a boolean variable.
	 * 
	 * 
	 */
	public void movePlayer(Player currentPlayer, int diceResult) {
		int position = (currentPlayer.getPosition() + diceResult)
				% playfield.length;
		int oldPosition = currentPlayer.getPosition();
		currentPlayer.setPosition(position);
		wentOverGo = (position < oldPosition);
	}

	/**
	 * get current field
	 */
	public IFieldObject getCurrentField(Player currentPlayer) {
		return playfield[currentPlayer.getPosition()];
	}

	/**
	 * get field name at comitted index
	 */
	public String getFieldNameAtIndex(int i) {
		return playfield[i].toString();
	}

	/**
	 * get field at commited index
	 */
	public IFieldObject getFieldAtIndex(int i) {
		return playfield[i];
	}

	/**
	 * get field size
	 */
	public int getfieldSize() {
		return this.fieldSize;
	}

	public String performActionAndAppendInfo(IFieldObject currentField,
			Player currentPlayer) {
		StringBuilder sb = new StringBuilder();
		String output;
		if (wentOverGo) {
			output = MessageFormat.format(bundle.getString("play_over_los"), IMonopolyUtil.LOS_MONEY);
			sb.append(output);
			Bank.receiveMoney(currentPlayer, IMonopolyUtil.LOS_MONEY);
		}
		switch (currentField.getType()) {
		case 's':
			sb.append(addStreetInfo(currentField, currentPlayer));
			break;
		case 'l':
			sb.delete(0, sb.length());
			output = MessageFormat.format(bundle.getString("play_los"),
					IMonopolyUtil.TWICE_LOS_MONEY);
			sb.append(output);
			Bank.receiveMoney(currentPlayer, IMonopolyUtil.LOS_MONEY);
			break;
		case 'z':
			FieldObject field = (FieldObject) currentField;
			output = MessageFormat.format(bundle.getString("play_pay"),
					field.getPriceToPay());
			sb.append(output);
			Bank.addParkingMoney(currentPlayer, field.getPriceToPay());
			break;
		case 'p':
			sb.append(bundle.getString("play_bsys"));
			movePlayerToPrison(currentPlayer);
			break;
		case 'n':
			sb.append(bundle.getString("play_look"));
			break;
		case 'f':
			output = MessageFormat.format(bundle.getString("play_mensa"),
					Bank.getParkingMoney());
			sb.append(output);
			Bank.receiveMoney(currentPlayer, Bank.getParkingMoney());
			break;

		}
		return sb.toString();
	}

	/**
	 * Builds and returns a String for the output depending on the status of the
	 * Street the current Player is standing on.
	 * 
	 * @param currentField
	 * @param currentPlayer
	 * @return String
	 */
	private String addStreetInfo(IFieldObject currentField, Player currentPlayer) {
		StringBuilder sb = new StringBuilder();
		String out;
		Street street = (Street) currentField;
		if (street.getOwner() == null) {
			out = MessageFormat.format(bundle.getString("play_street_free"),
					street.getPriceForStreet());
			sb.append(out);
		} else if (street.getOwner().equals(currentPlayer)) {
			sb.append(bundle.getString("play_street_own"));
		} else {
			out = MessageFormat.format(bundle.getString("play_street_busy"),
					street.getOwner(), street.getRent());
			sb.append(out);
			Bank.payRent(currentPlayer, currentField);
		}
		return sb.toString();
	}

	public String movePlayerTo(Player currentPlayer, String target) {
		int oldPosition = currentPlayer.getPosition();
		int position = -1;
		try {
			movePlayer(currentPlayer, Integer.parseInt(target));
			return performActionAndAppendInfo(
					playfield[currentPlayer.getPosition()], currentPlayer);
		} catch (NumberFormatException e) {

		}

		if (target.equalsIgnoreCase("Bsys Labor, nur zu Besuch")) {
			movePlayerToPrison(currentPlayer);
			return "";
		}

		if (target.equalsIgnoreCase("frei")) {
			currentPlayer.incrementPrisonFreeCard();
			return "";
		}

		for (int i = oldPosition; i < (fieldSize + oldPosition); ++i) {
			i = i % fieldSize;
			if (playfield[i].toString().equalsIgnoreCase(target)) {
				position = i;
				break;
			}
		}

		if (position == -1) {
			throw new AssertionError("Gefordertes Feld existiert nicht");
		}

		// Move the player
		currentPlayer.setPosition(position);

		// saves true, if the Player went over or stays on "Los"
		wentOverGo = (position < oldPosition);

		// Important: the new position must not be a Stack !!! (for now....)
		return performActionAndAppendInfo(playfield[position], currentPlayer);
	}

	private void movePlayerToPrison(Player currentPlayer) {
		currentPlayer.setInPrison(true);
		currentPlayer.setPosition(IMonopolyUtil.POSITION_OF_PRISON);

	}

	public CommunityCardsStack getCommStack() {
		return commStack;
	}

	public ChanceCardsStack getChanStack() {
		return chanStack;
	}

	public void setFieldAtIndex(int i, IFieldObject field) {
		if (field.getType() == 'g') {
			this.commStack = (CommunityCardsStack) field;
		} else if (field.getType() == 'e') {
			this.chanStack = (ChanceCardsStack) field;
		}
		playfield[i] = field;
	}

	public boolean buyStreet(Player currentPlayer, Street currentStreet) {
		if (currentStreet.getPriceForStreet() < currentPlayer.getBudget()) {
			currentPlayer.setBudget(currentPlayer.getBudget()
					- currentStreet.getPriceForStreet());
			currentStreet.setOwner(currentPlayer);
			currentPlayer.addOwnership(currentStreet);
			return true;
		}
		return false;

	}

    @Override
    public IFieldObject[] getPlayfield() {
        return this.playfield;
    }
}