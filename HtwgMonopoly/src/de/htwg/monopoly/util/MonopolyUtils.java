/**
 * 
 */
package de.htwg.monopoly.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * Creates a map with names and random corresponding player icons.
	 * 
	 * @param players
	 *            a list with names of players
	 * @return a map with key: player names and value: player icon
	 */
	public static Map<String, PlayerIcon> getPlayersWithIcons(
			List<String> players) {
		Map<String, PlayerIcon> playerMap = new HashMap<String, PlayerIcon>();

		// get all available Icon
		PlayerIcon[] allIcons = PlayerIcon.values();

		// fill the map with Names and icons
		int i = 0;
		for (String currentName : players) {
			playerMap.put(currentName, allIcons[i]);
			i++;
		}

		return playerMap;
	}

}
