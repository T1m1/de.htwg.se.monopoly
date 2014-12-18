package de.htwg.monopoly.util;

/**
 * Interface to avoid magic numbers
 * @author RuprechtT
 *
 */
public interface IMonopolyUtil {
	
	
	/**
	 * CONFIGURATIONS
	 */
	int FIELD_SIZE = 28;
	
	
	/**
	 *  defines to avoid magic numbers 
	 *  */
	int NULL = 0;
	int ONE = 1;
	int DICE = 6;
	int FIRST_PLAYER = 0;
	int MIN_NUMBER_OF_PLAYER = 2;
	int MAX_NUMBER_OF_PLAYER = 6;
	int INITIAL_MONEY = 1500;
	int MAX_PRISON_ROUND = 4;
	int LOS_MONEY = 200;
	int TWICE_LOS_MONEY = 400;

	int ZUSATZSTEUER = 100;
	int TUI_FIELD_SIZE = 28;
	int TUI_HIGH = 5;
	int FREIKAUFEN = 50;
	int OPTION_FINISH = 3;

	
	/***
	 * Magic number for Tests
	 */
	int TEST_PRICE_ONE = 250;
	int TEST_PRICE_TWO = 230;
	int TEST_PRICE_THREE = 50;

	/* Strings */
	String GAME_NAME = "HTWG Monopoly";
	String Q_NUMBER_OF_PLAYER = "Bitte Anzahl der Spieler eingeben: ";
	String Q_NAME_PLAYER = "bitte Name eingeben: ";
	String START = "Los gehts!";
	
	String ERR_NUMBER_OF_PLAYER = "Ung&uuml;ltige Anzahl von Spieler eingegeben (2 - 6)";

	/* GUI */
	int COLORSIZE = 20;
	int WEST = 270;
	int SOUTH = 180;
	int EAST = 90;


	int MAX_NUMBER_OF_STEPS = 5;

	int POSITION_OF_PRISON = 7;


	int NUMBER_OF_CARDS_IN_STACK = 24;


	int TIMES_TO_ROLL_DICE = 3;


	

}
