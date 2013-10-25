package de.htwg.monopoly;

public class Player {
	String name;

	public Player() {
		this("player");
	}

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
