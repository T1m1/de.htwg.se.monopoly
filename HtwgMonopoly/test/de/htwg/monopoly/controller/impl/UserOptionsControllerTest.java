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
	public void testPhases() {
		// Game not started, no expected Actions
		when(mockController.getPhase()).thenReturn(GameStatus.STOPPED);
		expectAction();
		verifyActions();
		
		when(mockController.getPhase()).thenReturn(GameStatus.NOT_STARTED);
		expectAction();
		verifyActions();

		// Game started
		when(mockController.getPhase()).thenReturn(GameStatus.STARTED);
		expectAction(UserAction.SURRENDER, UserAction.START_TURN);
		verifyActions();
		
		when(mockController.getPhase()).thenReturn(GameStatus.AFTER_TURN);
		expectAction(UserAction.SURRENDER, UserAction.START_TURN);
		verifyActions();

		when(mockController.getPhase()).thenReturn(GameStatus.BEFORE_TURN);
		expectAction(UserAction.SURRENDER, UserAction.START_TURN);
		verifyActions();

		when(mockController.getPhase()).thenReturn(
				GameStatus.BEFORE_TURN_IN_PRISON);
		expectAction(UserAction.SURRENDER, UserAction.REDEEM_WITH_CARD,
				UserAction.REDEEM_WITH_DICE, UserAction.REDEEM_WITH_MONEY,
				UserAction.REDEEM_WITH_QUESTION);
		verifyActions();

		when(mockController.getPhase()).thenReturn(
				GameStatus.DICE_ROLL_FOR_PRISON);
		when(mockController.isDiceFlagSet()).thenReturn(true);
		expectAction(UserAction.SURRENDER, UserAction.ROLL_DICE);
		verifyActions();

		when(mockController.getPhase()).thenReturn(
				GameStatus.DICE_ROLL_FOR_PRISON);
		when(mockController.isDiceFlagSet()).thenReturn(false);
		expectAction(UserAction.SURRENDER, UserAction.END_TURN);
		verifyActions();

		when(mockController.getPhase()).thenReturn(GameStatus.DICE_RESULT);
		expectAction(UserAction.SURRENDER, UserAction.ROLL_DICE);
		verifyActions();

		Street dummyStreet = new Street();

		when(mockController.getPhase()).thenReturn(GameStatus.DURING_TURN);
		when(mockController.getCurrentField()).thenReturn(dummyStreet);
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.BUY_STREET);
		verifyActions();

		dummyStreet.setSold(true);
		when(mockController.getCurrentField()).thenReturn(dummyStreet);
		expectAction(UserAction.SURRENDER, UserAction.END_TURN);
		verifyActions();

		when(mockController.getCurrentField()).thenReturn(
				new CommunityCardsStack());
		expectAction(UserAction.SURRENDER, UserAction.DRAW_CARD);
		verifyActions();

		when(mockController.getCurrentField()).thenReturn(
				new ChanceCardsStack());
		expectAction(UserAction.SURRENDER, UserAction.DRAW_CARD);
		verifyActions();

		when(mockController.hasDrawnCard()).thenReturn(true);
		expectAction(UserAction.SURRENDER, UserAction.END_TURN);
		verifyActions();

		when(mockController.getCurrentField()).thenReturn(
				new FieldObject("", FieldType.FREE_PARKING, 0));
		expectAction(UserAction.SURRENDER, UserAction.END_TURN);
		verifyActions();

		when(mockController.getCurrentField()).thenReturn(
				new FieldObject("", FieldType.GO, 0));
		expectAction(UserAction.SURRENDER, UserAction.END_TURN);
		verifyActions();

		when(mockController.getCurrentField()).thenReturn(
				new FieldObject("", FieldType.GO_TO_PRISON, 0));
		expectAction(UserAction.SURRENDER, UserAction.END_TURN,
				UserAction.REDEEM_WITH_CARD, UserAction.REDEEM_WITH_MONEY);
		verifyActions();

		when(mockController.getCurrentField()).thenReturn(
				new FieldObject("", FieldType.PRISON_VISIT_ONLY, 0));
		expectAction(UserAction.SURRENDER, UserAction.END_TURN);
		verifyActions();

		when(mockController.getCurrentField()).thenReturn(
				new FieldObject("", FieldType.TAX, 0));
		expectAction(UserAction.SURRENDER, UserAction.END_TURN);
		verifyActions();

	}

	/**
	 * Verify that the expected Actions match the actual actions and that there
	 * are no not expected actions.
	 */
	private void verifyActions() {
		userOptions.update();
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
