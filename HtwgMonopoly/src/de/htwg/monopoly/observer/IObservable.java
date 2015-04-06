package de.htwg.monopoly.observer;

import de.htwg.monopoly.util.GameStatus;

public interface IObservable {

	/**
	 * add an Observer s to the list
	 * 
	 * @param s
	 */
	void addObserver(IObserver s);

	/**
	 * remove an Observer s from the list
	 * 
	 * @param s
	 */
	void removeObserver(IObserver s);

	/**
	 * clear the list with all Observer in it.
	 * 
	 * 
	 */
	void removeAllObservers();

	/**
	 * calls the method notifyObservers(Event e) with argument null
	 * 
	 * @deprecated use {@link IObservable#notifyObservers(GameStatus)}
	 */
	@Deprecated
	void notifyObservers();


	/**
	 * Notify all Observers with the current game status.
	 * 
	 * @param e
	 */
	void notifyObservers(GameStatus e);

}
