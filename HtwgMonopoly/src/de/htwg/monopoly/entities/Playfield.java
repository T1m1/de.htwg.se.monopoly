package de.htwg.monopoly.entities;


import de.htwg.monopoly.cards.ChanceCardsStack;
import de.htwg.monopoly.cards.CommunityCardsStack;
import de.htwg.monopoly.util.IMonopolyFields;

public class Playfield {

	private IFieldObject[] playfield;
	private CommunityCardsStack commStack;
	private ChanceCardsStack chanStack;
	private int fieldSize;

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
				playfield[i] = new FieldObject(IMonopolyFields.name[i], IMonopolyFields.typ[i], 0);
				break;
			case 's':
				playfield[i] = new Street(IMonopolyFields.name[i],
						IMonopolyFields.prizeForStreet[i],
						IMonopolyFields.coulor[i],
						IMonopolyFields.rent[i],
						IMonopolyFields.hotel[i]);
				break;
			case 'g':
				playfield[i] = this.commStack;
				break;
			case 'z':

			case 'b':

			case 'e':
				playfield[i] = this.chanStack;
				break;
			case 'n':

			case 'p':

			case 'f':
			}

		}

	}

	/**
	 * Move the Player to the new Field according to the result of the dice roll
	 * 
	 * @param currentPlayer
	 *            which will be moved
	 * @param diceResult
	 *            : a Number between 2 and 12;
	 * @return true if Player moved over or stays on "Los" otherwise return
	 *         false
	 */
	public boolean movePlayer(Player currentPlayer, int diceResult) {
		// calculate the new position of the player within the playfield range
		// and save its old position
		int position = (currentPlayer.getPosition() + diceResult)
				% playfield.length;
		int oldPosition = currentPlayer.getPosition();

		// Move the player
		currentPlayer.setPosition(position);

		// return true, if the Player went over or stays on "Los"
		return (position < oldPosition);
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

	public int getfieldSize() {
		// TODO Auto-generated method stub
		return this.fieldSize;
	}
}
