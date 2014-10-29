/**
 * 
 */
package de.htwg.monopoly.util;

/**
 * @author stgorenf
 *
 */
public class MonopolyUtils {

	/**
	 * Hide utility constructor
	 */
	private MonopolyUtils() {
		super();
	}

	/**
	 * Verifies that the given number is between the correct boundaries.
	 * 
	 * @param number
	 * @return true if the number is between
	 *         {@link IMonopolyUtil.MIN_NUMBER_OF_PLAYER} and
	 *         {@link IMonopolyUtil.MAX_NUMBER_OF_PLAYER}
	 */
	public static boolean verifyPlayerNumber(int number) {
		return (number >= IMonopolyUtil.MIN_NUMBER_OF_PLAYER && number <= IMonopolyUtil.MAX_NUMBER_OF_PLAYER);
	}

}
