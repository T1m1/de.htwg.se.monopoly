package de.htwg.monopoly.observer.impl;

import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ObservableTest {
	private boolean ping = false;
	private TestObserver testObserver;
	private Observable testObservable;

	class TestObserver implements IObserver {

		@Override
		public void update(GameStatus e) {
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

		assertFalse(ping);
	}

	@Test
	public void testNotifyObserversIntEvent() {
		assertFalse(ping);
		testObserver = new TestObserver();
		testObservable.addObserver(testObserver);
		testObservable.notifyObservers(GameStatus.BEFORE_TURN);
		assertTrue(ping);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWrongParameter() {
		assertFalse(ping);
		testObserver = new TestObserver();
		testObservable.addObserver(testObserver);
		
		// test wrong argument
		testObservable.notifyObservers(null);

	}
}
