package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.monopoly.TestMonopolyModule;
import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.ChanceCard;
import de.htwg.monopoly.entities.impl.ChanceCardsStack;
import de.htwg.monopoly.entities.impl.CommunityCard;
import de.htwg.monopoly.entities.impl.CommunityCardsStack;
import de.htwg.monopoly.entities.impl.FieldObject;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.util.IMonopolyFields;
import de.htwg.monopoly.util.IMonopolyUtil;

public class ControllerTest {

	private IController testController;
	ResourceBundle bundle = ResourceBundle.getBundle("Messages", Locale.GERMAN);

	@Before
	public void setUp() throws Exception {

		/* Initialization */
		Injector injector = Guice.createInjector(new TestMonopolyModule());

		testController = injector.getInstance(IController.class);

		testController.startNewGame(2, new String[] { "0", "1" });
	}

	@Test
	public void testStartTurn() {
		testController.getCurrentPlayer().setInPrison(true);
		testController.startTurn();
		assertTrue(testController.getCurrentPlayer().isInPrison());
		testController.startTurn();
	}

	@Test
	public void testTurnWithStreet() {
		Street field = new Street("foo", 100, Color.black, 50, 10);
		testController.getField().setFieldAtIndex(0, field);
		testController.getField().setFieldAtIndex(1, field);

		testController.startTurn();
	}

	@Test
	public void testTurnWithPrison() {
		IFieldObject field = new FieldObject("Gehe in das Bsys Labor", 'p', 0,
				1);
		IFieldObject field2 = new FieldObject("Bsys Labor, nur zu Besuch", 'n',
				0, 1);
		testController.getField().setFieldAtIndex(0, field);
		testController.getField().setFieldAtIndex(1, field2);
		testController.startTurn();
	}

	@Test
	public void testTurnWithCommStack1() {
		IFieldObject stack = new CommunityCardsStack(new CommunityCard(
				"Du bekommst 100 � von der Bank", "100", true));
		testController.getField().setFieldAtIndex(0, stack);
		testController.getField().setFieldAtIndex(1, stack);
		Player testplayer = testController.getCurrentPlayer();
		testplayer.setBudget(0);
		testController.startTurn();
		assertEquals(100, testplayer.getBudget());
	}

	@Test
	public void testTurnWithCommStack2() {
		IFieldObject stack = new CommunityCardsStack(new CommunityCard(
				"Gehe zum n�chsten Gemeinschaftsfeld", "Gemeinschaftsfeld",
				true));
		testController.getField().setFieldAtIndex(0, stack);
		testController.getField().setFieldAtIndex(1, stack);
		Player testplayer = testController.getCurrentPlayer();
		testController.startTurn();
		assertEquals("Gemeinschaftsfeld", testController.getField()
				.getCurrentField(testplayer).toString());
	}

	@Test
	public void testTurnWithChanceStack() {
		IFieldObject stack = new ChanceCardsStack(new ChanceCard(
				"Zahle 100 an deinen Freunden", "-100", false));
		testController.getField().setFieldAtIndex(0, stack);
		testController.getField().setFieldAtIndex(1, stack);
		Player testplayer = testController.getCurrentPlayer();
		testplayer.setBudget(100);
		testController.startTurn();
		assertEquals(0, testplayer.getBudget());
	}

	@Test
	public void testTurnWithChanceStack2() {
		IFieldObject stack = new ChanceCardsStack(new ChanceCard(
				"Gehe zum n�chsten Ereignisfeld", "Ereignisfeld", true));
		testController.getField().setFieldAtIndex(0, stack);
		testController.getField().setFieldAtIndex(1, stack);
		Player testplayer = testController.getCurrentPlayer();
		testController.startTurn();
		assertEquals("Ereignisfeld",
				testController.getField().getCurrentField(testplayer)
						.toString());
	}

	@Test
	public void testTurnWithChanceStack3() {
		IFieldObject stack = new ChanceCardsStack(new ChanceCard(
				"Gehe 1 Feld vor", "1", false));
		testController.getField().setFieldAtIndex(0, stack);
		testController.getField().setFieldAtIndex(1, stack);
		Player testplayer = testController.getCurrentPlayer();
		testplayer.setPosition(0);
		testController.startTurn();
		assertEquals("Ereignisfeld",
				testController.getField().getCurrentField(testplayer)
						.toString());
	}

	@Test
	public void testRollDice() {
		testController.rollDice();
	}

	@Test
	public void testEndTurn() {
		testController.endTurn();
		assertEquals("1", testController.getCurrentPlayer().getName());
	}

	@Test
	public void testPerformCommCardAction() {

	}

	@Test
	public void testExitGame() {
		testController.exitGame();
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
	public void testPayRent() {
		testController.getPlayers().getCurrentPlayer().setPosition(1);
		testController.buyStreet();
		testController.getPlayers().getCurrentPlayer().setPosition(1);
		testController.payRent();
		testController.getField();

	}

	@Test
	public void testReceiveLosMoney() {
		testController.receiveGoMoney();
		assertEquals(IMonopolyUtil.LOS_MONEY + IMonopolyUtil.INITIAL_MONEY,
				testController.getCurrentPlayer().getBudget());
	}

	@Test
	public void testGetPlayers() {

	}

	@Test
	public void testGetField() {

	}

	@Test
	public void testGetOptions() {
		/* case 3 */
		List<String> options = new ArrayList<String>();
		options = testController.getOptions(3);
		assertEquals("(b) " + bundle.getString("contr_finish"), options.get(0));
		/* case 1 */
		testController.getCurrentPlayer().setInPrison(true);
		options = testController.getOptions(1);
		assertEquals("(f) " + bundle.getString("contr_free") + " ("
				+ IMonopolyUtil.FREIKAUFEN + ")", options.get(0));

		testController.getCurrentPlayer().setInPrison(false);
		options = testController.getOptions(1);
		assertEquals("(d) " + bundle.getString("dice"), options.get(0));
		/* case 2 */

	}

	@Test
	public void testGetOptions2() {
		List<String> options = new ArrayList<String>();
		/* set player to street */
		testController.startTurn();

		testController.setCurrentField(new FieldObject(IMonopolyFields.NAME[0],
				IMonopolyFields.TYP[0], 0));

		options = testController.getOptions(2);
		assertEquals("(b) " + bundle.getString("contr_finish"), options.get(0));
		Street street = new Street(IMonopolyFields.NAME[1],
				IMonopolyFields.PRICE_FOR_STREET[1], IMonopolyFields.COLOUR[1],
				IMonopolyFields.RENT[1], IMonopolyFields.HOTEL[1]);
		testController.setCurrentField(street);
		options = testController.getOptions(2);
		assertEquals("(y) " + bundle.getString("contr_buy"), options.get(0));

		street.setOwner(testController.getCurrentPlayer());
		options = testController.getOptions(2);
		assertEquals("(b) " + bundle.getString("contr_finish"), options.get(0));

		/* default */
		testController.getOptions(99);

	}

	@Test
	public void testIsCorrectOption() {
		testController.getOptions(3);
		assertTrue(testController.isCorrectOption("b"));
		assertFalse(testController.isCorrectOption("f"));
	}

	@Test
	public void testGetMessage() {
		testController.getMessage();
	}

	@Test
	public void testNumberOfPlayer() {
		testController.getNumberOfPlayer();
	}

	@Test
	public void testGetPlayer() {
		testController.getPlayer(0);
	}

	@Test
	public void testGetDice() {
		testController.getDice();
	}
}
