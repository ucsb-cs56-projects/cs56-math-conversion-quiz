package edu.ucsb.cs56.projects.math.cs56_math_conversion_quiz;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Andrew Berls
 * @version CS56, Spring 2012, Mantis 0000611
 * @author Nikhil Patil & Aryaman Das
 * @version CS56, Fall 2016 
 * @see Quiz
 *
 */

public class QuizTest {

	/**
	 * test numCorrect()
	 * @see Quiz#numCorrect()
	 * Seed a quiz with values and check numCorrect()
	 */
	@Test
	public void testNumCorrect1() {
		Quiz q = new Quiz(5);
		q.insertScore(true);
		q.insertScore(true);
		q.insertScore(true);
		q.insertScore(false);
		q.insertScore(false);
		assertEquals(3, q.numCorrect());
	}
	
	/**
	 * test numCorrect()
	 * @see Quiz#numCorrect()
	 * A quiz has no correct answers by default
	 */
	@Test
	public void testNumCorrect2() {
		Quiz q = new Quiz(5);		
		assertEquals(0, q.numCorrect());
	}
	
	/**
	 * test getPercentage()
	 * @see Quiz#getPercentage()
	 * Test an entirely correct quiz
	 */
	@Test
	public void testGetPercentage1() {
		Quiz q = new Quiz(3);
		q.insertScore(true);
		q.insertScore(true);
		q.insertScore(true);
		assertEquals(100, q.getPercentage());
	}
	
	/**
	 * test getPercentage()
	 * @see Quiz#getPercentage()
	 * Test fractional rounding correctness
	 */
	@Test
	public void testGetPercentage2() {
		Quiz q = new Quiz(3);
		q.insertScore(true);
		q.insertScore(false);
		q.insertScore(true);
		assertEquals(67, q.getPercentage());
	}
	
	/**
	 * test getPercentage()
	 * @see Quiz#getPercentage()
	 */
	@Test
	public void testGetPercentage3() {
		Quiz q = new Quiz(3);
		q.insertScore(true);
		q.insertScore(true);
		q.insertScore(false);
		q.insertScore(false);
		assertEquals(50, q.getPercentage());
	}
	
}
