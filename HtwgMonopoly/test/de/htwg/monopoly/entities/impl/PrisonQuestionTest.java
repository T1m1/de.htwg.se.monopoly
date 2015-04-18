/**
 * 
 */
package de.htwg.monopoly.entities.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Steffen
 *
 */
public class PrisonQuestionTest {
	
	private PrisonQuestion testQuestions;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testQuestions = new PrisonQuestion();
	}
	
	
	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.PrisonQuestion#isTrue(java.lang.String, boolean)}.
	 */
	@Test
	public void testIsTrue() {
		assertTrue(testQuestions.isTrue("ISRs sollten eher lang sein.", false));
		assertFalse(testQuestions.isTrue("ISRs sollten eher lang sein.", true));
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.PrisonQuestion#drawNextQuestion()}.
	 */
	@Test
	public void testGetNextQuestion() {
		String question1 = testQuestions.getCurrentQuestion();
		
		testQuestions.drawNextQuestion();
		
		assertFalse(question1.equals(testQuestions.getCurrentQuestion()));
		
	}

}
