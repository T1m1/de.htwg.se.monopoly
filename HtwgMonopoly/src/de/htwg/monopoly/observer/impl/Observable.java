package de.htwg.monopoly.observer.impl;

import de.htwg.monopoly.observer.IObservable;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;

import java.util.LinkedList;
import java.util.List;

public class Observable implements IObservable {
	
	private final List<IObserver> obsList = new LinkedList<IObserver>();

	@Override
	public void addObserver(IObserver s) {
		obsList.add(s);
	}

	@Override
	public void removeObserver(IObserver s) {
		obsList.remove(s);
	}

	@Override
	public void removeAllObservers() {
		obsList.clear();
	}

	@Override
	public void notifyObservers(GameStatus e) {
		if (e == null) {
			throw new IllegalArgumentException("null is not permited as a game status");
		}
		for (IObserver current: obsList) {
			current.update(e);
		}
	}
	
}
