package de.htwg.monopoly.observer.impl;

import java.util.LinkedList;
import java.util.List;

import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObservable;
import de.htwg.monopoly.observer.IObserver;

public class Observable implements IObservable {
	
	private List<IObserver> obsList = new LinkedList<IObserver>();

	@Override
	public void addObserver(IObserver s) {
		obsList.add(s);
	}

	@Override
	public void removeOberserver(IObserver s) {
		obsList.remove(s);
	}

	@Override
	public void removeAllObserver() {
		obsList.clear();
	}

	@Override
	public void notifyObservers() {
		notifyObservers(null);
	}

	@Override
	public void notifyObservers(Event e) {
		for (IObserver current: obsList) {
			current.update(e);
		}
	}

}
