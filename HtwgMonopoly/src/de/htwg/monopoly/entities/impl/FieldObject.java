package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.util.FieldType;

public class FieldObject implements IFieldObject {

	private final int priceToPay;
	private final FieldType type;
	private final String name;
	private final int guiPosition;

	/**
	 * Simple Field in Monopoly. Either Go, Prison, Go in Prison, Free Parking
	 * or a taxes.
	 * 
	 * @param name
	 * @param type
	 * @param priceToPay
	 */
	public FieldObject(String name, FieldType type, int priceToPay, int guiPosition) {
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
	public FieldObject(String name, FieldType type, int priceToPay) {
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
	public FieldType getType() {
		return type;
	}

	@Override
	public int getPosition() {
		return this.guiPosition;
	}
	
	

}
