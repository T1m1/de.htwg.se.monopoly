package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.impl.PlayerController;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.impl.Card;
import de.htwg.monopoly.entities.impl.CommunityCard;
import de.htwg.monopoly.entities.impl.Player;

public class PlayerControllerTest {
	IPlayerController players;

	@Before
	public void setUp() throws Exception {
		
		List<String> playerList = new ArrayList<String>();
		playerList.add("0");
		playerList.add("1");
		playerList.add("2");
		

		players = new PlayerController(playerList);
	}

	@Test
	public void testGetNextPlayer() {
		assertEquals("1", players.getNextPlayer().getName());
		assertEquals("2", players.getNextPlayer().getName());
		assertEquals("0", players.getNextPlayer().getName());
		assertEquals("1", players.getNextPlayer().getName());
	}

	@Test
	public void testCurrentPlayer() {
		assertEquals("0", players.getCurrentPlayer().getName());
		assertEquals(3, players.getNumberOfPlayer());
		assertEquals("1", players.getPlayer(1).getName());
	}

	@Test
	public void testTransferMoney() {
		Card testCard1 = new CommunityCard("Zahle geld an Bank", "-100", true);
		Card testCard2 = new CommunityCard("bekomme geld von alle Spieler",
				"100", false);
		Player testplayer = players.getNextPlayer();
		testplayer.setBudget(100);

		// perform test method
		players.transferMoney(testplayer, testCard1);

		// verify
		assertEquals(0, testplayer.getBudget());

		// perform other test method
		players.transferMoney(testplayer, testCard2);
		// verify
		assertEquals(200, testplayer.getBudget());
	}

	@Test
	public void receiveMoney() {
		Card testCard1 = new CommunityCard("Bekomme geld von der Bank", "100",
				true);
		Player testplayer = players.getNextPlayer();
		testplayer.setBudget(100);
		players.transferMoney(testplayer, testCard1);

		assertEquals(200, testplayer.getBudget());
	}

	@Test
	public void getFirstPlayer() {
		assertEquals("0", players.getFirstPlayer().getName());
	}

	@Test(expected = AssertionError.class)
	public void expectException() {
		new PlayerController(new ArrayList<String>());
	}

	@Test(expected = AssertionError.class)
	public void testTrasferMoney() {
		Player testplayer = players.getNextPlayer();
		ICards testCard = new CommunityCard("Keine Geld Karte", "KEIN GELD",
				false);
		players.transferMoney(testplayer, testCard);
	}

}
