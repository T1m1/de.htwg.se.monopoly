package de.htwg.monopoly.observer.impl;

import java.util.LinkedList;
import java.util.List;

import de.htwg.monopoly.observer.IObservable;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;

public class Observable implements IObservable {
	
	private List<IObserver> obsList = new LinkedList<IObserver>();

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
	public void notifyObservers() {
		notifyObservers(null);
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
	
	@Override
	public void notifyObservers(int e) {
		for (IObserver current: obsList) {
			current.update(e);
		}
	}
}
