package de.htwg.monopoly.controller.impl;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.*;
import de.htwg.monopoly.factory.IControllerFactory;
import de.htwg.monopoly.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ControllerTest {

	private IController testController;

	@Mock
	private Playfield mockField;
	@Mock
	private PlayerController mockPlayerController;
	@Mock
	private Dice mockDice;
	@Mock
	private PrisonQuestion mockQuestion;
	@Mock
	private CommunityCardsStack mockComm;
	@Mock
	private ChanceCardsStack mockChance;
	@Mock
	private IControllerFactory mockFactory;

	private Player dummyPlayer;

	private TreeMap<String, PlayerIcon> players;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		players = new TreeMap<String, PlayerIcon>();
		players.put("Player 1", PlayerIcon.BITTEL);
		players.put("Player 2", PlayerIcon.BOGER);

		dummyPlayer = new Player("Player 1", PlayerIcon.BITTEL);

		// define behavior of the mockfactory
		when(mockFactory.createPlayfield()).thenReturn(mockField);
		when(
				mockFactory.createPlayerController(anyMapOf(String.class,
						PlayerIcon.class))).thenReturn(mockPlayerController);

		when(mockFactory.createDice()).thenReturn(mockDice);
		when(mockFactory.createPrisonQuestions()).thenReturn(mockQuestion);
		when(mockQuestion.getCurrentQuestion()).thenReturn("Dummy question");
		when(mockChance.getType()).thenReturn(FieldType.CHANCE_STACK);
		when(mockComm.getType()).thenReturn(FieldType.COMMUNITY_STACK);

		// create a test instance of the test controller
		testController = new Controller(mockFactory, null);

	}

	@Test
	public void startNewGameWithList() {
		List<String> playerlist = new LinkedList<String>();

		// test wrong number of players.
		testController.startNewGame(playerlist);
		assertEquals("Wrong number of players!!", testController.getMessage());
		assertEquals(GameStatus.NOT_STARTED, testController.getPhase());

		// test correct number
		playerlist.add("Player 1");
		playerlist.add("Player 2");

		// define behavior of player controller.
		when(mockPlayerController.getFirstPlayer()).thenReturn(dummyPlayer);

		// perform
		testController.startNewGame(playerlist);
		assertEquals("Spiel gestartet!", testController.getMessage());
		assertEquals(GameStatus.STARTED, testController.getPhase());
		assertTrue(testController.isCorrectOption(UserAction.START_TURN));
		assertTrue(testController.isCorrectOption(UserAction.SURRENDER));
	}

	@Test
	public void startNewGameWithMap() {

		// test wrong number of players.
		testController.startNewGame(new TreeMap<String, PlayerIcon>());
		assertEquals("Wrong number of players!!", testController.getMessage());
		assertEquals(GameStatus.NOT_STARTED, testController.getPhase());

		// define behavior of player controller.
		when(mockPlayerController.getFirstPlayer()).thenReturn(dummyPlayer);

		// perform
		testController.startNewGame(players);
		assertEquals("Spiel gestartet!", testController.getMessage());
		assertEquals(GameStatus.STARTED, testController.getPhase());
		assertTrue(testController.isCorrectOption(UserAction.START_TURN));
		assertTrue(testController.isCorrectOption(UserAction.SURRENDER));
	}

	@Test
	public void startTurn1() {
		setUpGame();

		// 1. Testcase: player lands on normal field

		// define behavior
		doNothing().when(mockField).movePlayer(any(Player.class), anyInt());
		when(mockField.getFieldOfPlayer(any(Player.class))).thenReturn(
				new Street());
		when(
				mockField.performActionAndAppendInfo(isA(IFieldObject.class),
						isA(Player.class))).thenReturn("Confirmed");

		// perform
		testController.startTurn();

		// assert
		assertEquals("Confirmed", testController.getMessage());
		assertEquals(GameStatus.DURING_TURN, testController.getPhase());
		assertTrue(testController.isCorrectOption(UserAction.END_TURN));
		assertTrue(testController.isCorrectOption(UserAction.BUY_STREET));
		assertTrue(testController.isCorrectOption(UserAction.SURRENDER));

		// 2. Testcase: player lands on Community Stack field
		when(mockField.getFieldOfPlayer(any(Player.class))).thenReturn(
				new CommunityCardsStack());

		// perform
		testController.startTurn();

		// assert
		assertEquals(
				"Du bist auf einem Gemeinschaftsfeld gelandet. Ziehe eine Karte",
				testController.getMessage());
		assertEquals(GameStatus.DURING_TURN, testController.getPhase());

		assertTrue(testController.isCorrectOption(UserAction.SURRENDER));
		assertTrue(testController.isCorrectOption(UserAction.DRAW_CARD));

		// 3. Testcase: player lands on Chance Stack field
		// define behavior
		when(mockField.getFieldOfPlayer(any(Player.class))).thenReturn(
				new ChanceCardsStack());

		// perform
		testController.startTurn();

		// assert
		assertEquals(
				"Du bist auf einem Ereignisfeld gelandet. Ziehe eine Karte",
				testController.getMessage());
		assertEquals(GameStatus.DURING_TURN, testController.getPhase());
		assertTrue(testController.isCorrectOption(UserAction.DRAW_CARD));
		assertTrue(testController.isCorrectOption(UserAction.SURRENDER));
	}

	@Test(expected = AssertionError.class)
	public void buyStreetAssertion() {
		// set up
		setUpGame();
		when(mockField.getFieldOfPlayer(isA(Player.class))).thenReturn(
				new FieldObject("notAStreet", FieldType.GO, 0));

		// perform
		testController.buyStreet();
	}

	@Test
	public void buyStreetEnoughMoney() {
		// set up
		setUpGame();
		when(mockField.getFieldOfPlayer(isA(Player.class))).thenReturn(
				new Street());

		// perform & assert
		assertTrue(testController.buyStreet());
		assertEquals("Erfolgreich gekauft.", testController.getMessage());
		assertEquals(GameStatus.DURING_TURN, testController.getPhase());
		assertTrue(testController.isCorrectOption(UserAction.END_TURN));
		assertTrue(testController.isCorrectOption(UserAction.SURRENDER));
	}

	@Test
	public void buyStreetNoMoney() {
		// set up
		setUpGame();
		when(mockField.getFieldOfPlayer(isA(Player.class))).thenReturn(
				new Street("Test", 500, Color.black, 10, 10));
		dummyPlayer.decrementMoney(IMonopolyUtil.INITIAL_MONEY);

		// perform & assert
		assertFalse(testController.buyStreet());
		assertEquals("Du hast nicht genug Geld!", testController.getMessage());
		assertEquals(GameStatus.DURING_TURN, testController.getPhase());
		assertTrue(testController.isCorrectOption(UserAction.END_TURN));
		assertTrue(testController.isCorrectOption(UserAction.SURRENDER));
	}

	@Test
	public void endTurn() {
		// set up
		setUpGame();
		dummyPlayer.setInPrison(true);
		when(mockPlayerController.getNextPlayer()).thenReturn(dummyPlayer);

		// perform
		testController.endTurn();
		assertEquals(GameStatus.BEFORE_TURN_IN_PRISON,
				testController.getPhase());
		assertEquals(1, dummyPlayer.getPrisonRound());
		assertEquals("Du bist im Gefängnis", testController.getMessage());

		dummyPlayer.incrementPrisonRound();
		dummyPlayer.incrementPrisonRound();
		dummyPlayer.incrementPrisonRound();

		testController.endTurn();
		assertEquals(GameStatus.BEFORE_TURN, testController.getPhase());
		assertEquals(0, dummyPlayer.getPrisonRound());
		assertEquals("", testController.getMessage());
	}

	@Test
	public void reedemWithCard() {
		// set up
		setUpGame();
		dummyPlayer.setInPrison(true);

		// perform & assert
		assertFalse(testController.redeemWithCard());
		assertEquals(GameStatus.BEFORE_TURN_IN_PRISON,
				testController.getPhase());
		assertEquals("Keine Karte im Besitz", testController.getMessage());
		assertTrue(dummyPlayer.isInPrison());

		// give player a prison free card
		dummyPlayer.incrementPrisonFreeCard();
		assertTrue(testController.redeemWithCard());
		assertEquals(GameStatus.BEFORE_TURN, testController.getPhase());
		assertEquals("Erfolgreich frei gekommen.", testController.getMessage());
		assertFalse(dummyPlayer.isInPrison());

	}

	@Test
	public void redeemWithMoney() {
		// set up
		setUpGame();
		dummyPlayer.decrementMoney(IMonopolyUtil.INITIAL_MONEY);
		dummyPlayer.setInPrison(true);

		// perform & assert
		assertFalse(testController.redeemWithMoney());
		assertEquals("Kein Geld zum Freikaufen!", testController.getMessage());
		assertEquals(GameStatus.BEFORE_TURN_IN_PRISON,
				testController.getPhase());
		assertTrue(dummyPlayer.isInPrison());

		// give player enough money
		dummyPlayer.incrementMoney(IMonopolyUtil.FREIKAUFEN);

		assertTrue(testController.redeemWithMoney());
		assertEquals("Erfolgreich freigekauft!", testController.getMessage());
		assertEquals(GameStatus.BEFORE_TURN, testController.getPhase());
		assertFalse(dummyPlayer.isInPrison());
	}

	@Test
	public void redeemWithDice() {
		// set up
		setUpGame();
		dummyPlayer.setInPrison(true);

		// perform
		testController.redeemWithDice();

		// assert
		assertEquals(GameStatus.DICE_ROLL_FOR_PRISON, testController.getPhase());
		assertEquals(
				"Versuchen sie bei 3 W&uuml;rfen einen Pasch zu w&uuml;rfeln.",
				testController.getMessage());

		// roll dice 3 times with no pasch
		when(mockDice.isPasch()).thenReturn(false);
		testController.rollDiceToRedeem();
		assertEquals("Leider kein Pasch gew&uuml;rfelt. Noch 2 Versuch(e).",
				testController.getMessage());
		assertTrue(dummyPlayer.isInPrison());
		assertEquals(GameStatus.DICE_ROLL_FOR_PRISON, testController.getPhase());

		testController.rollDiceToRedeem();
		testController.rollDiceToRedeem();
		assertEquals(
				"Leider 3 mal kein Pasch gew&uuml;rfelt. Der N&auml;chste ist dran.",
				testController.getMessage());
		assertTrue(dummyPlayer.isInPrison());
		assertEquals(GameStatus.DICE_ROLL_FOR_PRISON, testController.getPhase());

		// roll dice and get a pasch
		when(mockDice.isPasch()).thenReturn(true);

		testController.rollDiceToRedeem();
		assertEquals("Erfolgreich frei gekommen", testController.getMessage());
		assertFalse(dummyPlayer.isInPrison());
		assertEquals(GameStatus.BEFORE_TURN, testController.getPhase());

	}

	@Test
	public void checkPlayerAnswer() {
		// set up
		setUpGame();
		when(mockQuestion.isTrue(isA(String.class), eq(true))).thenReturn(true);
		dummyPlayer.setInPrison(false);

		// perform
		assertTrue(testController.checkPlayerAnswer(true));
		assertEquals("Frage korrekt beantwortet.", testController.getMessage());
		assertEquals(GameStatus.BEFORE_TURN, testController.getPhase());
		assertFalse(dummyPlayer.isInPrison());
	}

	@Test
	public void checkPlayerAnswer2() {
		// set up
		setUpGame();
		when(mockQuestion.isTrue(isA(String.class), eq(false))).thenReturn(
				false);
		when(mockPlayerController.getNextPlayer()).thenReturn(dummyPlayer);
		dummyPlayer.setInPrison(false);

		// perform
		assertFalse(testController.checkPlayerAnswer(false));
		assertEquals(GameStatus.BEFORE_TURN, testController.getPhase());
		assertFalse(dummyPlayer.isInPrison());
	}

	@Test(expected = AssertionError.class)
	public void drawCardAssert() {
		// set up
		setUpGame();
		when(mockField.getFieldOfPlayer(dummyPlayer)).thenReturn(new Street());
		testController.startTurn();
		testController.drawCard();
	}

	@Test
	public void drawCard() {
		// set up
		setUpGame();
		when(mockField.getFieldOfPlayer(any(Player.class)))
				.thenReturn(mockComm);
		testController.startTurn();

		// set up method
		when(mockField.getCommStack()).thenReturn(mockComm);
		when(mockComm.getNextCard()).thenReturn(
				new CommunityCard("go to frei parken", "Frei parken", false));
		when(mockField.movePlayerTo(dummyPlayer, "Frei parken")).thenReturn(
				"dummyReturn");
		when(mockField.getFieldOfPlayer(dummyPlayer)).thenReturn(
				new FieldObject("Frei parkent", FieldType.FREE_PARKING, 0));

		testController.drawCard();
		assertEquals(GameStatus.DURING_TURN, testController.getPhase());
		assertEquals(FieldType.FREE_PARKING, testController.getCurrentField()
				.getType());

	}

	@Test
	public void drawCardCommInteger() {
		// set up
		setUpGame();
		when(mockField.getFieldOfPlayer(dummyPlayer)).thenReturn(mockComm);
		testController.startTurn();

		// set up method
		when(mockField.getCommStack()).thenReturn(mockComm);
		when(mockComm.getNextCard()).thenReturn(
				new CommunityCard("Pay money", "-20", true));
		doCallRealMethod().when(mockPlayerController).transferMoney(eq(dummyPlayer), isA(ICards.class));
		
		testController.drawCard();
		assertEquals(IMonopolyUtil.INITIAL_MONEY - 20, dummyPlayer.getBudget());
		assertEquals("Auf der Karte steht: Pay money", testController.getMessage());
		assertEquals(GameStatus.DURING_TURN, testController.getPhase());

		// set up method
		when(mockField.getCommStack()).thenReturn(mockComm);
		when(mockComm.getNextCard()).thenReturn(
				new CommunityCard("Go forward", "2", false));
		when(mockField.movePlayerTo(dummyPlayer, "2")).thenReturn("");

		testController.drawCard();

		assertEquals("Auf der Karte steht: Go forward", testController.getMessage());
		assertEquals(GameStatus.DURING_TURN, testController.getPhase());
	}

	@Test
	public void drawCardChance() {
		// set up
		setUpGame();
		when(mockField.getFieldOfPlayer(any(Player.class))).thenReturn(
				mockChance);
		testController.startTurn();

		// set up method
		when(mockField.getChanStack()).thenReturn(mockChance);
		when(mockChance.getNextCard()).thenReturn(
				new ChanceCard("go to frei parken", "Frei parken", false));
		when(mockField.movePlayerTo(dummyPlayer, "Frei parken")).thenReturn(
				"dummyReturn");
		when(mockField.getFieldOfPlayer(dummyPlayer)).thenReturn(
				new FieldObject("Frei parkent", FieldType.FREE_PARKING, 0));

		testController.drawCard();
		assertEquals(GameStatus.DURING_TURN, testController.getPhase());
		assertEquals(FieldType.FREE_PARKING, testController.getCurrentField()
				.getType());

	}

	@Test
	public void testExitGame() {
		testController.exitGame();
		assertEquals("", testController.getMessage());
		assertEquals(GameStatus.STOPPED, testController.getPhase());
	}

	@Test
	public void getFieldSize() {
		when(mockFactory.createPlayfield()).thenReturn(
				new Playfield(20));
		setUpGame();
		assertEquals(20, testController.getFieldSize());
		assertEquals("Bafög-Amt", testController.getFieldAtIndex(0).toString());
	}

	@Test
	public void getCurrentPlayer() {
		setUpGame();
		assertEquals(dummyPlayer, testController.getCurrentPlayer());
	}

	@Test
	public void getDice() {
		assertEquals(mockDice, testController.getDice());
	}

	@Test
	public void getOptions() {
		setUpGame();
		List<UserAction> expectedList = new LinkedList<UserAction>();
		expectedList.add(UserAction.START_TURN);
		expectedList.add(UserAction.SURRENDER);
		expectedList.add(UserAction.LOAD_GAME);
		expectedList.add(UserAction.SAVE_GAME);
		expectedList.add(UserAction.DELETE_GAME);
		assertEquals(expectedList, testController.getOptions());
	}

	@Test
	public void getNumberOfPlayers() {
		when(
				mockFactory.createPlayerController(anyMapOf(String.class,
						PlayerIcon.class))).thenReturn(
				new PlayerController(players));
		setUpGame();
		assertEquals(2, testController.getNumberOfPlayers());
		assertEquals("Player 1", testController.getPlayer(0).getName());
	}

	@Test
	public void getQuestion() {
		setUpGame();
		assertEquals("Dummy question", testController.getPrisonQuestion());
	}

	@After
	public void tearDown() {
		testController = null;
	}

	private void setUpGame() {
		// setUp
		when(mockPlayerController.getFirstPlayer()).thenReturn(dummyPlayer);
		testController.startNewGame(players);
	}
}
