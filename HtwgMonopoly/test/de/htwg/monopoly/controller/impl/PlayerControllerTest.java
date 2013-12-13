package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.controller.impl.PlayerController;
import de.htwg.monopoly.util.IMonopolyUtil;

public class PlayerControllerTest {
	PlayerController players;
	
	@Before
	public void setUp() throws Exception {
		ByteArrayInputStream testStream = new ByteArrayInputStream(IMonopolyUtil.testInputStream.getBytes());
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
	public void testReadNumberofPlayer() {
		players = new PlayerController();
		String testString = "Hello World";
		ByteArrayInputStream testStream = new ByteArrayInputStream(testString.getBytes());
		System.setIn(testStream);
		assertFalse(players.readNumberOfPlayer());
		ByteArrayInputStream testStream2 = new ByteArrayInputStream("10\n".getBytes());
		System.setIn(testStream2);
		assertFalse(players.readNumberOfPlayer());
		ByteArrayInputStream testStream3 = new ByteArrayInputStream("1\n".getBytes());
		System.setIn(testStream3);
		assertFalse(players.readNumberOfPlayer());
		System.setIn(System.in);
	}
	
	


}
