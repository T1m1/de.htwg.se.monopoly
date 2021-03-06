package de.htwg.monopoly.util;

/**
 * These enums represent the various player icons you can choose from.
 * 
 * @author stgorenf
 *
 */
public enum PlayerIcon {

	BITTEL("Bittel"),

	NEUSCHWANDER("Neuschwander"),

	SCHOPPA("Schoppa"),

	ECK("Eck"),

	MAECHTEL("Maechtel"),

	BOGER("Boger");

	private final String description;

	private PlayerIcon(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return description;
	}

}
