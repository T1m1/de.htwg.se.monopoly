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

	// @Inject @Named("FieldSize") private int fieldSize;
	private int fieldSize = 10;
	private boolean wentOverGo = false;

	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	public Playfield() {
		// this.fieldSize = IMonopolyUtil.TUI_FIELD_SIZE;
		this.playfield = new IFieldObject[this.fieldSize];
		this.commStack = new CommunityCardsStack();
		this.chanStack = new ChanceCardsStack();

		for (int i = 0; i < fieldSize; i++) {
			createField(i);
		}
	}

	/**
	 * help function for testing, later it will be replaced by google juice
	 * (maybe)
	 * 
	 * @param size
	 */
	public void initialize(int size) {
		this.fieldSize = size;
		this.playfield = new IFieldObject[fieldSize];
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
					IMonopolyFields.TYP[i], IMonopolyUtil.ZUSATZSTEUER);
			break;
		case 'b':

		case 'e':
			this.chanStack.setPosition(IMonopolyFields.POSITION[i]);
			playfield[i] = (IFieldObject) this.chanStack;
			break;
		case 'n':
			/* BSYS -> zu besuch */
			playfield[i] = new FieldObject("Bsys Labor: nur zu Besuch",
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
		}
	}

	public void movePlayer(Player currentPlayer, int diceResult) {
		// calculate the new position of the player within the playfield range
		// and save its old position
		int position = (currentPlayer.getPosition() + diceResult)
				% playfield.length;
		int oldPosition = currentPlayer.getPosition();

		// Move the player
		currentPlayer.setPosition(position);

		// saves true, if the Player went over or stays on "Los"
		wentOverGo = (position < oldPosition);
	}

	public IFieldObject getCurrentField(Player currentPlayer) {
		return playfield[currentPlayer.getPosition()];
	}

	public String getFieldNameAtIndex(int i) {
		return playfield[i].toString();
	}
	
	
	public IFieldObject getFieldAtIndex(int i) {
		return playfield[i];
	}
	

	public int getfieldSize() {
		return this.fieldSize;
	}

	public String appendInfo(IFieldObject currentField, Player currentPlayer) {
		StringBuilder sb = new StringBuilder();
		String output;
		if (wentOverGo) {
			sb.append(bundle.getString("play_over_los"));
			Bank.receiveMoney(currentPlayer, IMonopolyUtil.LOS_MONEY);
		}
		switch (currentField.getType()) {
		case 's':
			sb.append(addStreetInfo(currentField, currentPlayer));
			break;
		case 'l':
			sb.delete(0, sb.length());
			sb.append(bundle.getString("play_los"));
			Bank.receiveMoney(currentPlayer, IMonopolyUtil.LOS_MONEY);
			break;
		case 'z':
			FieldObject field = (FieldObject) currentField;
			output = MessageFormat.format(bundle.getString("play_pay"),
					field.getPriceToPay());
			sb.append(output);
			Bank.receiveMoney(currentPlayer, -field.getPriceToPay());
			Bank.addParkingMoney(field.getPriceToPay());
			break;
		case 'p':
			sb.append(bundle.getString("play_bsys"));
			currentPlayer.setInPrison(true);
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

		if (target.equalsIgnoreCase("Bsys Labor")) {
			currentPlayer.setInPrison(true);
			return bundle.getString("play_bsys");
		}

		for (int i = 0; i < fieldSize; ++i) {
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
		return appendInfo(playfield[position], currentPlayer);
	}

	public CommunityCardsStack getCommStack() {
		return commStack;
	}

	public ChanceCardsStack getChanStack() {
		return chanStack;
	}
}
