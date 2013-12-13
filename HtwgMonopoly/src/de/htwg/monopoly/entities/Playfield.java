package de.htwg.monopoly.entities;

import de.htwg.monopoly.cards.ChanceCardsStack;
import de.htwg.monopoly.cards.CommunityCardsStack;
import de.htwg.monopoly.util.IMonopolyFields;
import de.htwg.monopoly.util.IMonopolyUtil;

public class Playfield {

	private IFieldObject[] playfield;
	private CommunityCardsStack commStack;
	private ChanceCardsStack chanStack;
	private int fieldSize;
	private boolean wentOverGo = false;

	public Playfield() {

	}

	public final void initialize(int fieldSize) {
		// initialize the playfield. set the size, fill it with streets and card
		// stacks etc.
		// TODO actual initializing. dabei muss evtl beachtet werden, dass es
		// alles irgendwie variabel sein sollte. Stichwort: skalierbarkeit

		this.playfield = new IFieldObject[fieldSize];
		this.fieldSize = fieldSize;
		this.commStack = new CommunityCardsStack();
		this.chanStack = new ChanceCardsStack();
		for (int i = 0; i < fieldSize; i++) {
			switch (IMonopolyFields.typ[i]) {
			case 'l':
				playfield[i] = new FieldObject(IMonopolyFields.name[i],
						IMonopolyFields.typ[i], 0);
				break;
			case 's':
				playfield[i] = new Street(IMonopolyFields.name[i],
						IMonopolyFields.prizeForStreet[i],
						IMonopolyFields.coulor[i], IMonopolyFields.rent[i],
						IMonopolyFields.hotel[i]);
				break;
			case 'g':
				playfield[i] = this.commStack;
				break;
			case 'z':
				playfield[i] = new FieldObject("Zusatzsteuer",
						IMonopolyFields.typ[i], 100);
				break;
			case 'b':

			case 'e':
				playfield[i] = this.chanStack;
				break;
			case 'n':

			case 'p':
				playfield[i] = new FieldObject("Gehe in das BsysLabor",
						IMonopolyFields.typ[i], 0);
				break;
			case 'f':
			}

		}

	}

	/**
	 * Move the Player to the new Field according to the result of the dice
	 * roll.
	 * 
	 * @param currentPlayer
	 *            which will be moved
	 * @param diceResult
	 *            : a Number between 2 and 12 modulo the playfield size.
	 * @return true if Player moved over or stays on "Los" otherwise return
	 *         false.
	 */
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

	/**
	 * Get the current Field where the Player is standing on.
	 * 
	 * @param currentPlayer
	 * @return An Object of Type IFieldObject
	 */
	public IFieldObject getCurrentField(Player currentPlayer) {
		return playfield[currentPlayer.getPosition()];
	}

	public String getFieldNameAtIndex(int i) {
		return playfield[i].toString();
	}

	public int getfieldSize() {
		return this.fieldSize;
	}

	public String appendInfo(IFieldObject currentField, Player currentPlayer) {
		StringBuilder sb = new StringBuilder();
		if (wentOverGo) {
			sb.append("Sie sind über Los gegangen und erhalten Geld\n");
			Bank.receiveMoney(currentPlayer, IMonopolyUtil.LOS_MONEY);
		}
		switch (currentField.getType()) {
		case 's':
			Street street = (Street) currentField;
			if (street.getOwner() == null) {
				sb.append("Diese Straße ist frei. Wollen sie die Straße für ")
						.append(street.getPriceForStreet()).append(" kaufen\n");
			} else if (street.getOwner().equals(currentPlayer)) {
				sb.append("Ihnen gehört die Straße. Gut gemacht\n");
			} else {
				sb.append("Diese Straße gehört ").append(street.getOwner())
						.append(".\nSie müssen ihm jetzt ")
						.append(street.getRent()).append(" Miete zahlen.\n");
				Bank.payRent(currentPlayer, currentField);
			}
			break;
		case 'l':
			sb.delete(0, sb.length());
			sb.append("Sie stehen auf Los und erhalten sehr viel Geld\n");
			Bank.receiveMoney(currentPlayer, IMonopolyUtil.LOS_MONEY);
			break;
		case 'z':
			FieldObject field = (FieldObject) currentField;
			sb.append("Sie müssen ").append(field.getPriceToPay())
					.append(" Zusatzsteuer zahlen\n");
			Bank.receiveMoney(currentPlayer, -field.getPriceToPay());
			break;
		case 'p':
			sb.append("Sie müssen jetzt in das Bsys Labor und setzen verdammt lang aus :(\n");
			currentPlayer.setInPrison(true);
			break;
		case 'e':
			sb.append("Auf der Karte steht: ").append(
					this.chanStack.getNextCard().getDescription());
			break;
		case 'g':
			sb.append("Auf der Karte steht: ").append(
					this.commStack.getNextCard().getDescription());

		}
		return sb.toString();
	}
}
