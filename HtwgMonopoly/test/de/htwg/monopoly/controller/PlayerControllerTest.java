package de.htwg.monopoly.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.Player;

public class PlayerControllerTest {
	PlayerController players;
	
	@Before
	public void setUp() throws Exception {
		players = new PlayerController(3);
	}

	@Test
	public void testPlayerController() {
		PlayerController playersController = new PlayerController(3);
		Player player1 = playersController.getNextPlayer();
		player1.setName("Peter");
		assertEquals("Peter", player1.getName());
	}

	@Test
	public void testGetNextPlayer() {
		players = new PlayerController(4);
		Player player1 = players.getNextPlayer();
		player1.setName("Peter");
		assertEquals("Peter", player1.getName());
	}
	
	@Test
	public void testGetNextPlayerSecondBranch() {
		players = new PlayerController(2);
		Player player1 = players.getNextPlayer();
		player1.setName("Peter");
		player1 = players.getNextPlayer();
		player1.setName("Jürgen");
		player1 = players.getNextPlayer();
		assertEquals("Peter", player1.getName());
	}
	


}
