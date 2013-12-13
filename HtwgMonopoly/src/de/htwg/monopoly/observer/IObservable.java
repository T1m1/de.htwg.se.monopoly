package de.htwg.monopoly.observer;

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
	 * 
	 */
	void notifyObservers();

	/**
	 * Calls the method update() from every Observer in the list with the
	 * argument e.
	 * 
	 * 
	 * @param e
	 */
	void notifyObservers(Event e);

}
