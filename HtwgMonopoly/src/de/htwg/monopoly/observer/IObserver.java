package de.htwg.monopoly.observer;

public interface IObserver {

	/**
	 * This method should be called after Data is changed. Than the UI could
	 * display this new data.
	 * 
	 * @param e
	 */
	void update(Event e);
	void update(int e);

}
