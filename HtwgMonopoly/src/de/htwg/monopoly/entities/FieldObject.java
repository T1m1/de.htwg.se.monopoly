package de.htwg.monopoly.entities;


public class FieldObject implements IFieldObject {

	private int priceToPay;
	private char type;
	private String name;

	/**
	 * Simple Field in Monopoly. Either Go, Prison, Go in Prison, Free Parking
	 * or a taxes.
	 * 
	 * @param name
	 * @param type
	 * @param priceToPay
	 */
	public FieldObject(String name, char type, int priceToPay) {
		this.priceToPay = priceToPay;
		this.type = type;
		this.name = name;
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

}
