package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.monopoly.TestMonopolyModule;
import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.PlayerIcon;

public class ControllerTest {

	private IController testController;
	ResourceBundle bundle = ResourceBundle.getBundle("Messages", Locale.GERMAN);
	private Injector injector;

	@Before
	public void setUp() throws Exception {

		/* Initialization */
		injector = Guice.createInjector(new TestMonopolyModule());

		testController = injector.getInstance(IController.class);

		List<String> playerList = new ArrayList<String>();
		playerList.add("0");
		playerList.add("1");
		
		testController.startNewGame(playerList);
	}

	@Test
	public void testStartTurn() {
		testController.getCurrentPlayer().setInPrison(true);
		testController.startTurn();
		assertTrue(testController.getCurrentPlayer().isInPrison());
		testController.startTurn();
	}

	@Test
	public void testRollDice() {
		testController.rollDiceToRedeem();
	}

	@Test
	public void testEndTurn() {
		System.out.println("current player:" + testController.getCurrentPlayer());
		testController.endTurn();
		System.out.println("current player:" + testController.getCurrentPlayer());
		assertEquals("1", testController.getCurrentPlayer().getName());
	}

	@Test
	public void startGame() {
		IController contr = injector.getInstance(IController.class);
		
		// start game with wrong parameter
		contr.startNewGame(new LinkedList<String>());
		assertEquals("Wrong number of players!!", contr.getMessage());
		
		// start game with wrong parameter
		contr.startNewGame(new HashMap<String, PlayerIcon>());
		assertEquals("Wrong number of players!!", contr.getMessage());
	}

	@Test
	public void testExitGame() {
		testController.exitGame();
	}

	@Test
	public void testBuyStreet() {
		testController.getCurrentPlayer().setPosition(1);
		assertTrue(testController.buyStreet());
		testController.getCurrentPlayer().decrementMoney(1440);
		assertFalse(testController.buyStreet());
	}

	@Test
	public void testGetMessage() {
		testController.getMessage();
	}

	@Test
	public void testNumberOfPlayer() {
		testController.getNumberOfPlayers();
	}

	@Test
	public void testGetPlayer() {
		testController.getPlayer(0);
	}

	@Test
	public void testGetDice() {
		testController.getDice();
	}
}
