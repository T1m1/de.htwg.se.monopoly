/**
 * 
 */
package de.htwg.monopoly.util;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author stgorenf
 *
 */
public final class MonopolyUtils {

    public static final int MAX_RANDOM = 100;

    /**
	 * Hide utility constructor
	 */
	private MonopolyUtils() {	}

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

		Map<String, PlayerIcon> playerMap = new TreeMap<String, PlayerIcon>();

		// get all available Icon
		PlayerIcon[] allIcons = PlayerIcon.values();

		if (players.size() > allIcons.length) {
			// prevent index out of bound exception
			throw new AssertionError("Wrong numbers of players!!");
		}

		// fill the map with Names and icons
		int i = 0;
		for (String currentName : players) {
            if(playerMap.containsKey(currentName)) {
                // change name because using a map
                Random ran = new Random();
                currentName = currentName + "" + ran.nextInt(MAX_RANDOM);
            }
			playerMap.put(currentName, allIcons[i]);
			i++;
		}
		
		return playerMap;
	}

}
