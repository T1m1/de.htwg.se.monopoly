/**
 * 
 */
package de.htwg.monopoly.controller.impl;

import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.ChanceCardsStack;
import de.htwg.monopoly.entities.impl.CommunityCardsStack;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.Street;

/**
 * @author Steffen
 *
 */
public class MockPlayfield implements IPlayfield {

	private IFieldObject[] playfield;
	private CommunityCardsStack commStack;
	private ChanceCardsStack chanStack;
	private final int fieldSize;
	private boolean wentOverGo = false;
	private IFieldObject fieldObject;
	private String message;
	private boolean canBuy;

	public MockPlayfield(int size) {
		fieldSize = size;
	}

	public IFieldObject getFieldObject() {
		return fieldObject;
	}

	public void setFieldObject(IFieldObject fieldObject) {
		this.fieldObject = fieldObject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isCanBuy() {
		return canBuy;
	}

	public void setCanBuy(boolean canBuy) {
		this.canBuy = canBuy;
	}

	public IFieldObject[] getPlayfield() {
		return playfield;
	}

	public void setPlayfield(IFieldObject[] playfield) {
		this.playfield = playfield;
	}

	public boolean isWentOverGo() {
		return wentOverGo;
	}

	public void setWentOverGo(boolean wentOverGo) {
		this.wentOverGo = wentOverGo;
	}

	public int getFieldSize() {
		return fieldSize;
	}

	public void setCommStack(CommunityCardsStack commStack) {
		this.commStack = commStack;
	}

	public void setChanStack(ChanceCardsStack chanStack) {
		this.chanStack = chanStack;
	}


	@Override
	public void movePlayer(Player currentPlayer, int diceResult) {

		// do nothing
	}

	@Override
	public IFieldObject getFieldOfPlayer(Player currentPlayer) {
		return fieldObject;
	}

	@Override
	public IFieldObject getFieldAtIndex(int i) {
		return fieldObject;
	}

	@Override
	public int getfieldSize() {
		return fieldSize;
	}

	@Override
	public String performActionAndAppendInfo(IFieldObject currentField,
			Player currentPlayer) {
		// do nothing
		return message;
	}

	@Override
	public String movePlayerTo(Player currentPlayer, String target) {
		// do nothing
		return message;
	}

	@Override
	public CommunityCardsStack getCommStack() {
		return commStack;
	}

	@Override
	public ChanceCardsStack getChanStack() {
		return chanStack;
	}

	@Override
	public boolean buyStreet(Player currentPlayer, Street currentStreet) {
		// do nothing
		return canBuy;
	}

}
