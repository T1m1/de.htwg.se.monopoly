package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.impl.PlayerController;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.impl.Card;
import de.htwg.monopoly.entities.impl.CommunityCard;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.util.IMonopolyUtil;

public class PlayerControllerTest {
	IPlayerController players;

	@Before
	public void setUp() throws Exception {
		ByteArrayInputStream testStream = new ByteArrayInputStream(
				IMonopolyUtil.TEST_INPUT_STREAM.getBytes());
		System.setIn(testStream);
		players = new PlayerController();
		players.readNumberOfPlayer();
		players.readNameOfPlayer(0);
		players.readNameOfPlayer(1);
		System.setIn(System.in);
	}

	@Test
	public void testGetNextPlayer() {
		assertEquals("2", players.getNextPlayer().getName());
		assertEquals("2", players.getNextPlayer().getName());
	}

	@Test
	public void testCurrentPlayer() {
		assertEquals("2", players.currentPlayer().getName());
		assertEquals(2, players.getNumberOfPlayer());
		assertEquals("2", players.getPlayer(1).getName());
	}

	@Test
	public void testReadNameOfPlayer() {
		System.setIn(null);
		assertTrue(players.readNameOfPlayer(0));
		assertTrue(players.readNameOfPlayer(1));
		System.setIn(System.in);
	}

	@Test
	public void testReadNumberofPlayer() throws AWTException {
		players = new PlayerController();
		String testString = "Hello World";
		ByteArrayInputStream testStream = new ByteArrayInputStream(
				testString.getBytes());
		System.setIn(testStream);
		assertFalse(players.readNumberOfPlayer());

		ByteArrayInputStream testStream2 = new ByteArrayInputStream(
				"5".getBytes());
		System.setIn(testStream2);
		assertFalse(players.readNumberOfPlayer());
		System.setIn(System.in);

		ByteArrayInputStream testStream3 = new ByteArrayInputStream(
				"1\n".getBytes());
		System.setIn(testStream3);
		assertFalse(players.readNumberOfPlayer());
		System.setIn(System.in);

		Robot robot = new Robot();

		ByteArrayInputStream testStream4 = new ByteArrayInputStream(
				"2\n".getBytes());
		System.setIn(testStream4);
		assertFalse(players.readNumberOfPlayer());
		players.readNumberOfPlayer();
		robot.keyPress(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_2);
	}
	
	@Test
	public void testTransferMoney() {
		Card testCard1 = new CommunityCard("Zahle geld an Bank", "-100", true);
		Card testCard2 = new CommunityCard("bekomme geld von alle Spieler", "100", false);
		Player testplayer = players.getNextPlayer();
		testplayer.setBudget(100);
		players.transferMoney(testplayer, testCard1);
		assertEquals(0,testplayer.getBudget());
		
		players.transferMoney(testplayer, testCard2);
		assertEquals(100, testplayer.getBudget());
	}
	
	@Test(expected = AssertionError.class)
	public void testTrasferMoney() {
		Player testplayer = players.getNextPlayer();
		ICards testCard = new CommunityCard("Keine Geld Karte", "KEIN GELD", false);
		players.transferMoney(testplayer, testCard);
	}

}
