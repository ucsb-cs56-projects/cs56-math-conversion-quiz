package edu.ucsb.cs56.projects.math.conversion_quiz;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Andrew Berls
 * @version CS56, Spring 2012, Mantis 0000611
 * @see Question
 *
 */

public class QuestionTest {
	
	/**
	 * test constructor with num/radix arguments
	 * @see Question#Question(int, int)
	 */
	@Test
	public void testBinaryConstructor() {
		Question q = new Question(50, 2);
		assertEquals(50, q.getNum());
		assertEquals("Binary", q.getStrRadix());
	}
	
	/**
	 * test setRadixes
	 * @see Question#setRadixes()
	 */
	@Test
	public void testSetRadixes() {
		Question q = new Question(50, 2);
		q.setRadixes(8);
		assertEquals(8, q.getIntRadix());
		assertEquals("Octal", q.getStrRadix());
	}
	
	/**
	 * test convertTo() -> binary
	 * @see Question#convertTo(int)
	 */
	@Test
	public void testConvertToBinary1() {
		Question q = new Question(50, 2);
		assertEquals("110010", q.convertTo(2));
	}
	
	/**
	 * test convertTo() -> octal
	 * @see Question#convertTo(int)
	 */
	@Test
	public void testConvertToOctal1() {
		Question q = new Question(50, 8);
		assertEquals("62", q.convertTo(8));
	}
	
	/**
	 * test convertTo() -> hex
	 * @see Question#convertTo(int)
	 */
	@Test
	public void testConvertToHex1() {
		Question q = new Question(14, 16);
		assertEquals("e", q.convertTo(16));
	}
	
	/**
	 * test convertTo() -> decimal
	 * @see Question#convertTo(int)
	 */
	@Test
	public void testConvertToDecimal1() {
		Question q = new Question(15, 10);
		assertEquals("1111", q.convertTo(2));
	}
	
	/**
	 * test convertTo() -> decimal
	 * @see Question#convertTo(int)
	 */
	@Test
	public void testConvertToDecimal2() {
		Question q = new Question(15, 10);
		assertEquals("f", q.convertTo(16));
	}
	
	/**
	 * test checkAnswer
	 * @see Question#checkAnswer(String)
	 */
	@Test
	public void testBinaryAnswer1() {
		Question q = new Question(50, 2);
		assertTrue(q.checkAnswer("110010"));
	}
	
	/**
	 * test checkAnswer
	 * @see Question#checkAnswer(String)
	 */
	@Test
	public void testBinaryAnswer2() {
		Question q = new Question(78, 2);
		assertTrue(q.checkAnswer("1001110"));
	}
	
	/**
	 * test checkAnswer
	 * @see Question#checkAnswer(String)
	 */
	@Test
	public void testOctalAnswer1() {
		Question q = new Question(50, 8);
		assertTrue(q.checkAnswer("62"));
	}
	
	/**
	 * test checkAnswer
	 * @see Question#checkAnswer(String)
	 */
	@Test
	public void testHexAnswer1() {
		Question q = new Question(50, 16);
		assertTrue(q.checkAnswer("32"));
	}
	
	/**
	 * test checkAnswer
	 * @see Question#checkAnswer(String)
	 */
	@Test
	public void testHexAnswer2() {
		Question q = new Question(14, 16);		
		assertTrue(q.checkAnswer("e"));
	}
	
	/**
	 * test sanitize - strip leading zeroes (1)
	 * @see Question#sanitize(String s)
	 */
	 @Test
	 public void testSanitize1() {
	 Question q = new Question(46, 2);
	  assertEquals("101110", q.sanitize("00101110"));
	  assertTrue(q.checkAnswer("00101110"));
	 }
	 
	 /**
	 * test sanitize  -strip leading zeroes (2)
	 * @see Question#sanitize(String s)
	 */
	 @Test
	 public void testSanitize2() {
	 Question q = new Question(2, 2);
	  assertEquals("10", q.sanitize("000000000000010"));
	  assertTrue(q.checkAnswer("000000000000010"));
	 }
	 
	 /**
	 * test sanitize - remove embedded spaces
	 * @see Question#sanitize(String s)
	 */
	 @Test
	 public void testSanitize3() {
	 Question q = new Question(51, 2);
	  assertEquals("110011", q.sanitize("110 011"));
	  assertTrue(q.checkAnswer("110 011"));
	 }
	 
	 /**
	 * test sanitize - convert to lowercase
	 * @see Question#sanitize(String s)
	 */
	 @Test
	 public void testSanitize4() {
	 Question q = new Question(42, 16);
	  assertEquals("2a", q.sanitize("02A"));
	  assertTrue(q.checkAnswer("02A"));
	 }
	
}
