package de.htwg.monopoly.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
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
import de.htwg.monopoly.entities.impl.FieldObject;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.util.IMonopolyFields;
import de.htwg.monopoly.util.IMonopolyUtil;

public class ControllerTest {

	private IController testController;
	ResourceBundle bundle = ResourceBundle.getBundle("Messages", Locale.GERMAN);

	@Before
	public void setUp() throws Exception {
		ByteArrayInputStream testStream = new ByteArrayInputStream(
				IMonopolyUtil.TEST_INPUT_STREAM.getBytes());
		/* Initialization */

		System.setIn(testStream);
		Injector injector = Guice.createInjector(new TestMonopolyModule());
		

		testController = injector.getInstance(IController.class);
		
		testController.setNumberofPlayer();
		testController.setNameofPlayer(0);
		testController.setNameofPlayer(1);
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
		testController.rollDice();
	}

	@Test
	public void testEndTurn() {
		testController.endTurn();
		assertEquals("2", testController.getCurrentPlayer().getName());
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
		testController.getPlayers().currentPlayer().setPosition(1);
		testController.buyStreet();
		testController.getPlayers().currentPlayer().setPosition(1);
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
	
}
