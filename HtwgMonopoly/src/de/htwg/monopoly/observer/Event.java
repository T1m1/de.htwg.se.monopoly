package de.htwg.monopoly.observer;

import de.htwg.monopoly.util.GameStatus;

//TODO: is this class used??
public final class Event {

	private GameStatus status;

	public Event(GameStatus status) {
		this.status = status;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status2) {
		this.status = status2;
	}
}
