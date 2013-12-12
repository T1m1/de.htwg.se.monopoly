package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.util.IMonopolyUtil;

public class ControllerTest {
	
	private Controller testController;
	

	@Before
	public void setUp() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(IMonopolyUtil.testInputStream.getBytes());
		System.setIn(in);
		testController = new Controller();
		testController.setNameofPlayer(2);
		testController.setNumberofPlayer();
		testController.startNewGame();
	}

	@Test
	public void testStartTurn() {
		testController.getCurrentPlayer().setInPrison(true);
		testController.startTurn();
		assertTrue(!testController.getCurrentPlayer().isInPrison());
		assertTrue(testController.getCurrentPlayer().getPosition() != 0);
		testController.startTurn();
		assertTrue(testController.getCurrentPlayer().isInPrison());
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
		testController.getCurrentPlayer().setPosition(1);
		assertTrue(testController.buyStreet());
		testController.getCurrentPlayer().setBudget(0);
		assertFalse(testController.buyStreet());
	}

	@Test
	public void testAddPlayer() {
	}

	@Test
	public void testCheckFieldType() {
		testController.checkFieldType();
		
	}

	@Test
	public void testPayRent() {
		testController.getPlayers().currentPlayer().setPosition(1);
		testController.buyStreet();
		testController.startTurn();
		testController.getPlayers().currentPlayer().setPosition(1);
		testController.payRent();
		testController.getField();
		
	}

	@Test
	public void testReceiveLosMoney() {
		testController.receiveGoMoney();
		assertEquals(IMonopolyUtil.LOS_MONEY+IMonopolyUtil.INITIAL_MONEY, testController.getCurrentPlayer().getBudget());
	}
	 
	@Test
	public void testGetPlayers() {
		
	}

	@Test
	public void testGetField() {
		
	}
}
