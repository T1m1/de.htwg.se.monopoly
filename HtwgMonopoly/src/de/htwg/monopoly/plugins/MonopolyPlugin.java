package de.htwg.monopoly.plugins;


import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.observer.IObserver;

public interface MonopolyPlugin extends IObserver {

	String getName();

	void enable(IController controller);

	void disable();

}
