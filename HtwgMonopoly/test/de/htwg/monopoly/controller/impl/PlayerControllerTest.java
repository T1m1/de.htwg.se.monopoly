package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.*;

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
		players = new PlayerController(2, new String[] { "2", "2", "2" });
	}

	@Test
	public void testGetNextPlayer() {
		assertEquals("2", players.getNextPlayer().getName());
		assertEquals("2", players.getNextPlayer().getName());
	}

	@Test
	public void testCurrentPlayer() {
		assertEquals("2", players.getCurrentPlayer().getName());
		assertEquals(2, players.getNumberOfPlayer());
		assertEquals("2", players.getPlayer(1).getName());
	}

	@Test
	public void testTransferMoney() {
		Card testCard1 = new CommunityCard("Zahle geld an Bank", "-100", true);
		Card testCard2 = new CommunityCard("bekomme geld von alle Spieler",
				"100", false);
		Player testplayer = players.getNextPlayer();
		testplayer.setBudget(100);
		players.transferMoney(testplayer, testCard1);
		assertEquals(0, testplayer.getBudget());

		players.transferMoney(testplayer, testCard2);
		assertEquals(100, testplayer.getBudget());
	}
	
	@Test
	public void receiveMoney() {
		Card testCard1 = new CommunityCard("Bekomme geld von der Bank", "100", true);
		Player testplayer = players.getNextPlayer();
		testplayer.setBudget(100);
		players.transferMoney(testplayer, testCard1);

		assertEquals(200, testplayer.getBudget());
	}

	@Test(expected = AssertionError.class)
	public void testTrasferMoney() {
		Player testplayer = players.getNextPlayer();
		ICards testCard = new CommunityCard("Keine Geld Karte", "KEIN GELD",
				false);
		players.transferMoney(testplayer, testCard);
	}

	@Test
	public void getFirstPlayer() {
		assertEquals("2", players.getFirstPlayer().getName());
	}

}
