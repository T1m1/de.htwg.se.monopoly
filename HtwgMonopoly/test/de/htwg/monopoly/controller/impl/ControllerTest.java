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
		ByteArrayInputStream testStream = new ByteArrayInputStream(IMonopolyUtil.testInputStream.getBytes());
		System.setIn(testStream);
		testController = new Controller();
		testController.setNumberofPlayer();
		testController.setNameofPlayer(0);
		testController.setNameofPlayer(1);
		testController.initGame(IMonopolyUtil.TEST_PLAYFIELD_SIZE);
		testController.startNewGame();
		System.setIn(System.in);		
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
	}

	@Test
	public void testEndTurn() {
		testController.endTurn();
		assertEquals("2", testController.getCurrentPlayer().getName());
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
	
	@Test 
	public void testGetOptions() {
		
	}
}
