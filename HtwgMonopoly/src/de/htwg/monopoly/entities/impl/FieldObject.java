package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.entities.IFieldObject;

public class FieldObject implements IFieldObject {

	private int priceToPay;
	private char type;
	private String name;
	private final int guiPosition;

	/**
	 * Simple Field in Monopoly. Either Go, Prison, Go in Prison, Free Parking
	 * or a taxes.
	 * 
	 * @param name
	 * @param type
	 * @param priceToPay
	 */
	public FieldObject(String name, char type, int priceToPay, int guiPosition) {
		this.priceToPay = priceToPay;
		this.type = type;
		this.name = name;
		this.guiPosition = guiPosition;
	}

	/**
	 * Simple Field in Monopoly. Either Go, Prison, Go in Prison, Free Parking
	 * or a taxes.
	 * 
	 * @param name
	 * @param type
	 * @param priceToPay
	 * 
	 */
	public FieldObject(String name, char type, int priceToPay) {
		this(name, type, priceToPay, 0);
	}


	@Override
	public String toString() {
		return name;
	}

	public int getPriceToPay() {
		return priceToPay;
	}

	@Override
	public char getType() {
		return type;
	}

	@Override
	public int getPosition() {
		return this.guiPosition;
	}
	
	

}
