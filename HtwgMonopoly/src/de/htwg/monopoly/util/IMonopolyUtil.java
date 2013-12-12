package de.htwg.monopoly.util;

/**
 * Interface to avoid magic numbers
 * @author RuprechtT
 *
 */
public interface IMonopolyUtil {
	/**
	 *  defines to avoid magic numbers 
	 *  */
	int NULL = 0;
	int ONE = 1;
	int DICE = 6;
	int FIRST_PLAYER = 0;
	int MIN_NUMBER_OF_PLAYER = 2;
	int MAX_NUMBER_OF_PLAYER = 6;
	int INITIAL_MONEY = 600;
	int MAX_PRISON_ROUND = 4;
	int LOS_MONEY = 2000;
	
	/***
	 * Magic number for Tests
	 */
	int TEST_PRICE_ONE = 250;
	int TEST_PRICE_TWO = 230;
	int TEST_PRICE_THREE = 50;
	int TEST_PLAYFIELD_SIZE = 20;

	/* Strings */
	String gameName = "HTWG Monopoly";
	String qNumberOfPlayer = "Bitte Anzahl der Spieler eingeben: ";
	String qNamePlayer = "bitte Name eingeben: ";
	String start = "Los gehts!";
	
	String errNumberOfPlayer = "Ungültige Anzahl von Spieler eingegeben (2 - 6)";
	String errNameOfPlayer = "Kann das passieren?";
	

}
