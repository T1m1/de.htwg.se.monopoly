package de.htwg.monopoly.observer.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;

public class ObservableTest {
	private boolean ping = false;
	private TestObserver testObserver;
	private Observable testObservable;

	class TestObserver implements IObserver {
		@Override
		public void update(GameStatus e) {
			ping = true;

		}

		@Override
		public void update(int e) {
			ping = true;
			
		}
	}

	@Before
	public void setUp() throws Exception {
		testObserver = new TestObserver();
		testObservable = new Observable();
		testObservable.addObserver(testObserver);
	}

	@Test
	public void testRemoveOberserver() {
		assertFalse(ping);
        testObservable.notifyObservers(GameStatus.BEFORE_TURN);
        assertTrue(ping);
	}

	@Test
	public void testNotifyObservers() {
		assertFalse(ping);
        testObservable.removeObserver(testObserver);
        testObservable.notifyObservers(GameStatus.BEFORE_TURN);
        assertFalse(ping);
	}

	@Test
	public void testNotifyObserversEvent() {
		assertFalse(ping);
        testObservable.removeAllObservers();
        testObservable.notifyObservers(GameStatus.BEFORE_TURN);
        testObservable.notifyObservers(2);
        
        assertFalse(ping);
	}
	
	@Test
	public void testNotifyObserversIntEvent() {
		assertFalse(ping);
		testObserver = new TestObserver();
		testObservable.addObserver(testObserver);
        testObservable.notifyObservers(GameStatus.BEFORE_TURN);
        testObservable.notifyObservers(2);
        assertTrue(ping);
	}

}
