package de.htwg.monopoly.observer.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;

public class ObservableTest {
	private boolean ping = false;
	private TestObserver testObserver;
	private Observable testObservable;

	class TestObserver implements IObserver {
		@Override
		public void update(Event e) {
			ping = true;

		}

		@Override
		public void update(int e) {
			// TODO Auto-generated method stub
			
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
        testObservable.notifyObservers();
        assertTrue(ping);
	}

	@Test
	public void testNotifyObservers() {
		assertFalse(ping);
        testObservable.removeObserver(testObserver);
        testObservable.notifyObservers();
        assertFalse(ping);
	}

	@Test
	public void testNotifyObserversEvent() {
		assertFalse(ping);
        testObservable.removeAllObservers();
        testObservable.notifyObservers();
        testObservable.notifyObservers(2);
        
        assertFalse(ping);
        
	}

}
