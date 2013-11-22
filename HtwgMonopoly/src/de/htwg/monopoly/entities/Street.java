package de.htwg.monopoly.entities;

import java.awt.Color;

import de.htwg.monopoly.util.IMonopolyUtil;

/**
 * class to represent a street
 * @author RuprechtT
 *
 */
public class Street {
	/* street atributes */
	private String name;
	private int priceForStreet;
	private Color color;
	private int rent;
	private int pricePerHotel;
	private int numberOfHotels;

	/***
	 * Constructor 
	 * 
	 * @param name
	 * @param priceForStreet
	 * @param color
	 * @param rent
	 * @param pricePerHotel
	 * @param numberOfHotels
	 */
	public Street(String name, int priceForStreet, Color color, int rent,
			int pricePerHotel, int numberOfHotels) {
		this.name = name;
		this.priceForStreet = priceForStreet;
		this.color = color;
		this.rent = rent;
		this.pricePerHotel = pricePerHotel;
		this.numberOfHotels = numberOfHotels;
	}

	/**
	 * empty Constructor
	 */
	public Street() {
		this.name = "empty";
		this.priceForStreet = IMonopolyUtil.NULL;
		this.color = Color.black;
		this.rent = IMonopolyUtil.NULL;
		this.pricePerHotel = IMonopolyUtil.NULL;
		this.numberOfHotels = IMonopolyUtil.NULL;
	}

	/**
	 * get name of street
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name of street
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get price of street
	 * @return
	 */
	public int getPriceForStreet() {
		return priceForStreet;
	}

	/**
	 * set price of street
	 * @param priceForStreet
	 */
	public void setPriceForStreet(int priceForStreet) {
		this.priceForStreet = priceForStreet;
	}

	/**
	 * get color of street
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * set color of street
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * get rent of street
	 * @return
	 */
	public int getRent() {
		return rent;
	}

	/**
	 * set rent of street
	 * @param rent
	 */
	public void setRent(int rent) {
		this.rent = rent;
	}

	/**
	 * get price per hotel
	 * @return
	 */
	public int getPricePerHotel() {
		return pricePerHotel;
	}

	/**
	 * set price per hotel
	 * @param pricePerHotel
	 */
	public void setPricePerHotel(int pricePerHotel) {
		this.pricePerHotel = pricePerHotel;
	}

	/**
	 * get number of hotels contains the street
	 * @return
	 */
	public int getNumberOfHotels() {
		return numberOfHotels;
	}
	

	/**
	 * set number of hotels
	 * @param numberOfHotels
	 */
	public void setNumberOfHotels(int numberOfHotels) {
		this.numberOfHotels = numberOfHotels;
	}

}
