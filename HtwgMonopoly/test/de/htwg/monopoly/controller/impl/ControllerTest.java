package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.factory.IControllerFactory;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.PlayerIcon;

public class ControllerTest {

	private IController testController;

	@Mock
	private IPlayfield mockField;
	@Mock
	private IPlayerController mockPlayerController;
	@Mock
	private IControllerFactory mockFactory;

	private Player dummyPlayer;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		dummyPlayer = new Player("Player 1", PlayerIcon.BITTEL);

		// define behavior of the mockfactory
		when(mockFactory.createPlayfield(anyInt())).thenReturn(mockField);
		when(
				mockFactory.createPlayerController(anyMapOf(String.class,
						PlayerIcon.class))).thenReturn(mockPlayerController);
		
		when(mockFactory.createDice()).thenReturn(new Dice());
		when(mockFactory.createPrisonQuestions()).thenReturn(new PrisonQuestion());

		// create a test instance of the test controller
		testController = new Controller(IMonopolyUtil.FIELD_SIZE, mockFactory);

	}

	@Test
	public void startNewGameWithList() {
		List<String> players = new LinkedList<String>();

		// test wrong number of players.
		testController.startNewGame(players);
		assertEquals("Wrong number of players!!", testController.getMessage());
		assertEquals(GameStatus.NOT_STARTED, testController.getPhase());

		// test correct number
		players.add("Player 1");
		players.add("Player 2");

		// define behavior of player controller.
		when(mockPlayerController.getFirstPlayer()).thenReturn(dummyPlayer);
		
		testController.startNewGame(players);
		assertEquals("Spiel gestartet!", testController.getMessage());
		assertEquals(GameStatus.STARTED, testController.getPhase());
	}

	
	@Test
	public void startNewGameWithMap() {
		Map<String, PlayerIcon> players = new TreeMap<String, PlayerIcon>();

		// test wrong number of players.
		testController.startNewGame(players);
		assertEquals("Wrong number of players!!", testController.getMessage());
		assertEquals(GameStatus.NOT_STARTED, testController.getPhase());
		
		// test correct number
		players.put("Player 1", PlayerIcon.BITTEL);
		players.put("Player 2", PlayerIcon.BOGER);

		// define behavior of player controller.
		when(mockPlayerController.getFirstPlayer()).thenReturn(dummyPlayer);
		
		testController.startNewGame(players);
		assertEquals("Spiel gestartet!", testController.getMessage());
		assertEquals(GameStatus.STARTED, testController.getPhase());
	}
	
	

	@Test
	public void testRollDice() {
		testController.rollDiceToRedeem();
	}

	@Test
	public void testEndTurn() {
		testController.endTurn();
		assertEquals("1", testController.getCurrentPlayer().getName());
	}

	@Test
	public void startGame() {

		// // start game with wrong parameter
		// contr.startNewGame(new LinkedList<String>());
		// assertEquals("Wrong number of players!!", contr.getMessage());
		//
		// // start game with wrong parameter
		// contr.startNewGame(new TreeMap<String, PlayerIcon>());
		// assertEquals("Wrong number of players!!", contr.getMessage());
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

	@After
	public void tearDown() {
		testController = null;
	}
}
