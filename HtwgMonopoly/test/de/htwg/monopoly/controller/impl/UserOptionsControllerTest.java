/**
 * 
 */
package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.monopoly.TestMonopolyModule;
import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.UserAction;

/**
 * @author stgorenf
 *
 */
public class UserOptionsControllerTest {

	private IController testController;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new TestMonopolyModule());

		testController = injector.getInstance(IController.class);

	}

	/**
	 * Test method for
	 * {@link de.htwg.monopoly.controller.impl.UserOptionsController#getCurrentPlayerOptions()}
	 * .
	 */
	@Test
	public void testGetCurrentPlayerOptions() {
		assertTrue(testController.getOptions().isEmpty());

		List<String> playerList = new ArrayList<String>();
		playerList.add("0");
		playerList.add("1");

		testController.startNewGame(playerList);

		assertTrue(testController.getOptions().contains(UserAction.START_TURN));
		assertTrue(testController.getOptions().contains(UserAction.SURRENDER));

		testController.startTurn();
		assertTrue(testController.getOptions().contains(UserAction.END_TURN));
	}

}
