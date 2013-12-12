package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import de.htwg.monopoly.util.IMonopolyUtil;

public class ControllerTest {
	
	Controller controller;

	@Before
	public void setUp() throws Exception {		
		controller = new Controller();
		controller = new Controller(2);
		controller.initGame(2);
		controller.startNewGame();
	}

	@Test
	public void testStartTurn() {
		controller.getCurrentPlayer().setInPrison(true);
		controller.startTurn();
		assertTrue(!controller.getCurrentPlayer().isInPrison());
		assertTrue(controller.getCurrentPlayer().getPosition() != 0);
		controller.startTurn();
		assertTrue(controller.getCurrentPlayer().isInPrison());
	}

	@Test
	public void testRollDice() {
	}

	@Test
	public void testEndTurn() {
	}

	@Test
	public void testExitGame() {
	}

	@Test
	public void testBuyStreet() {
		controller.getCurrentPlayer().setPosition(1);
		assertTrue(controller.buyStreet());
		controller.getCurrentPlayer().setBudget(0);
		assertFalse(controller.buyStreet());
	}

	@Test
	public void testAddPlayer() {
	}

	@Test
	public void testCheckFieldType() {
		controller.checkFieldType();
		
	}

	@Test
	public void testPayRent() {
		controller.getPlayers().currentPlayer().setPosition(1);
		controller.buyStreet();
		controller.startTurn();
		controller.getPlayers().currentPlayer().setPosition(1);
		controller.payRent();
		controller.getField();
		
	}

	@Test
	public void testReceiveLosMoney() {
		controller.receiveGoMoney();
		assertEquals(IMonopolyUtil.LOS_MONEY+IMonopolyUtil.INITIAL_MONEY, controller.getCurrentPlayer().getBudget());
	}
	 
	@Test
	public void testGetPlayers() {
		
	}

	@Test
	public void testGetField() {
		
	}
}
