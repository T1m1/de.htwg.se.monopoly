/**
 * 
 */
package de.htwg.monopoly.controller.impl;

import de.htwg.monopoly.entities.impl.ChanceCardsStack;
import de.htwg.monopoly.entities.impl.CommunityCardsStack;
import de.htwg.monopoly.entities.impl.FieldObject;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.util.FieldType;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.UserAction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author stgorenf
 *
 */
public class UserOptionsControllerTest {
	private UserOptionsController userOptions;
	private List<UserAction> expectedList;
	private List<UserAction> notExpectedList;
	private List<UserAction> backUpList;

	@Mock
	private Controller mockController;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		backUpList = new LinkedList<UserAction>(Arrays.asList(UserAction
				.values()));

		expectedList = new LinkedList<UserAction>();
		notExpectedList = backUpList;

		when(mockController.getPhase()).thenReturn(GameStatus.NOT_STARTED);
		userOptions = new UserOptionsController(mockController);
	}

	@Test
	public void phaseStopped() {
		// Game not started, no expected Actions
		when(mockController.getPhase()).thenReturn(GameStatus.STOPPED);

		// set up the expected user actions
		expectAction(UserAction.LOAD_GAME, UserAction.DELETE_GAME);

		// verify
		verifyActions();
	}

	@Test
	public void phaseNotStarted() {

		when(mockController.getPhase()).thenReturn(GameStatus.NOT_STARTED);

		// set up the expected user actions
		expectAction(UserAction.LOAD_GAME, UserAction.DELETE_GAME);

		// verify
		verifyActions();
	}

	@Test
	public void phaseStarted() {
		// Game started
		when(mockController.getPhase()).thenReturn(GameStatus.STARTED);

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.START_TURN,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);

		// verify
		verifyActions();

	}

	@Test
	public void phaseAfterTurn() {
		when(mockController.getPhase()).thenReturn(GameStatus.AFTER_TURN);
		expectAction(UserAction.SURRENDER, UserAction.START_TURN,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);

		// verify
		verifyActions();
	}

	@Test
	public void phaseBeforeTurn() {
		when(mockController.getPhase()).thenReturn(GameStatus.BEFORE_TURN);

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.START_TURN,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);

		// verify
		verifyActions();
	}

	@Test
	public void phaseBeforeTurnInPrison() {

		when(mockController.getPhase()).thenReturn(
				GameStatus.BEFORE_TURN_IN_PRISON);

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.REDEEM_WITH_CARD,
				UserAction.REDEEM_WITH_DICE, UserAction.REDEEM_WITH_MONEY,
				UserAction.REDEEM_WITH_QUESTION, UserAction.LOAD_GAME,
				UserAction.SAVE_GAME, UserAction.DELETE_GAME);

		// verify
		verifyActions();
	}

	@Test
	public void phaseDiceRollForPrison() {

		// user still needs to roll the dice
		when(mockController.getPhase()).thenReturn(
				GameStatus.DICE_ROLL_FOR_PRISON);
		when(mockController.isDiceFlagSet()).thenReturn(true);

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.ROLL_DICE,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);

		// verify
		verifyActions();

		// user is finished with rolling the dice 3 times
		when(mockController.getPhase()).thenReturn(
				GameStatus.DICE_ROLL_FOR_PRISON);
		when(mockController.isDiceFlagSet()).thenReturn(false);

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);

		// verify
		verifyActions();
	}

	@Test
	public void phaseDiceResult() {
		when(mockController.getPhase()).thenReturn(GameStatus.DICE_RESULT);

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.ROLL_DICE,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);

		// verify
		verifyActions();
	}

	@Test
	public void phasesDuringTurnStreet() {

		// 1.Testcase: street is not sold
		Street dummyStreet = new Street();

		when(mockController.getPhase()).thenReturn(GameStatus.DURING_TURN);
		when(mockController.getCurrentField()).thenReturn(dummyStreet);

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.BUY_STREET, UserAction.LOAD_GAME,
				UserAction.SAVE_GAME, UserAction.DELETE_GAME);

		// verify
		verifyActions();

		// 2. Testcase: street is sold
		dummyStreet.setSold(true);
		when(mockController.getCurrentField()).thenReturn(dummyStreet);

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);

		// verify
		verifyActions();

	}

	@Test
	public void phaseDuringTurnCardStack() {

		when(mockController.getPhase()).thenReturn(GameStatus.DURING_TURN);

		// 1. Testcase: CommunityCardStack
		when(mockController.getCurrentField()).thenReturn(
				new CommunityCardsStack());

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.DRAW_CARD,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);
		verifyActions();

		// 2. Testcase: ChanceCardStack
		when(mockController.getCurrentField()).thenReturn(
				new ChanceCardsStack());

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.DRAW_CARD,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);
		verifyActions();

		// 3. Testcase: ChanceCardStack but has already drawn a card
		when(mockController.hasDrawnCard()).thenReturn(true);

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);
		verifyActions();

	}

	@Test
	public void phaseDuringTurnFreeParking() {

		when(mockController.getPhase()).thenReturn(GameStatus.DURING_TURN);

		when(mockController.getCurrentField()).thenReturn(
				new FieldObject("", FieldType.FREE_PARKING, 0));

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);
		verifyActions();

	}

	@Test
	public void phaseDuringTurnGo() {

		when(mockController.getPhase()).thenReturn(GameStatus.DURING_TURN);

		when(mockController.getCurrentField()).thenReturn(
				new FieldObject("", FieldType.GO, 0));

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);
		verifyActions();

	}

	@Test
	public void phaseDuringTurnGoToPrison() {

		when(mockController.getPhase()).thenReturn(GameStatus.DURING_TURN);

		when(mockController.getCurrentField()).thenReturn(
				new FieldObject("", FieldType.GO_TO_PRISON, 0));

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.REDEEM_WITH_CARD, UserAction.REDEEM_WITH_MONEY,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);
		verifyActions();

	}

	@Test
	public void phaseDuringTurnPrisonVisitonly() {

		when(mockController.getPhase()).thenReturn(GameStatus.DURING_TURN);

		when(mockController.getCurrentField()).thenReturn(
				new FieldObject("", FieldType.PRISON_VISIT_ONLY, 0));

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);
		verifyActions();
	}

	@Test
	public void phaseDuringTurnTax() {

		when(mockController.getPhase()).thenReturn(GameStatus.DURING_TURN);

		when(mockController.getCurrentField()).thenReturn(
				new FieldObject("", FieldType.TAX, 0));

		// set up the expected user actions
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.LOAD_GAME, UserAction.SAVE_GAME,
				UserAction.DELETE_GAME);
		verifyActions();

	}

	/**
	 * This method compares the list with the expected user actions and the
	 * actual returned list
	 */
	private void verifyActions() {
		userOptions.update();

		// perform and get the actual list
		List<UserAction> actualList = userOptions.getCurrentPlayerOptions();

		for (UserAction actual : actualList) {
			assertTrue("Actual Action was not in the expected list: " + actual,
					expectedList.contains(actual));
			assertFalse(
					"Actual Action was in the not expected list: " + actual,
					notExpectedList.contains(actual));
		}

		for (UserAction actual : expectedList) {
			assertTrue("Expected Action was not in the actual list: " + actual,
					actualList.contains(actual));
		}

		for (UserAction actual : notExpectedList) {
			assertFalse(
					"Not Expected Action was in the actual list: " + actual,
					actualList.contains(actual));
		}
		expectedList.clear();
		notExpectedList = backUpList;
	}

	/**
	 * Add and remove the expected action(s) to/from the temp. lists.
	 * 
	 * @param action
	 */
	private void expectAction(UserAction... action) {
		for (UserAction current : action) {
			expectedList.add(current);
			notExpectedList.remove(current);
		}
	}

}
