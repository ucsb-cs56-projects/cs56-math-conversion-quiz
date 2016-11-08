package edu.ucsb.cs56.projects.math.cs56_math_conversion_quiz;
import java.lang.Math;

/**
 * A class to represent a Question in the conversion quiz
 * Original program by Erick Valle & George Barrios for Mantis 0000391
 * @author Andrew Berls
 * @version CS56, Spring 2012, Mantis 0000611
 * @authors Allison Shedden & Logan Schmidt
 * @version CS56, Winter 2014
 * @authors Aryaman Das & Nikhil Patil
 * @version CS56, Fall 2016
 */

public class Question {

	private int num;

        private int num2; // For masking

	private String strRadix; // The radix represented as a string, e.g. "Binary"

	private int intRadix;

	/**
	 * Construct a question with a random number and radix
	 */
	public Question() {
		this.num = this.getRandomNum();
		this.setRadixes(this.getRandomRadix());
	}
	
	/**
	 * Construct a question with a set radix but random number
	 * @param radix The radix to set as an integer
	 */
	public Question(int radix) {

	    if(radix == -1){
		int num = this.getRandomNum();
		this.num = num;
		this.setRadixes(this.getRandomRadix());
	    }else{
	       	int num = this.getRandomNum();
		this.num = num;
		this.setRadixes(radix);
	    }	
	}
	
	/**
	 * Construct a question with a specified number and conversion radius
	 * @param num the number to convert
	 * @param radix the radix to convert to
	 */
	public Question(int num, int radix) {
	    if(radix == 18){
		this.num = num;
		this.setIntRadix(18);
		this.setStrRadix(2);
	    }else{
		this.num = num;
		this.setRadixes(radix);
	    }
	}

	public String getStrRadix() {		
		return getRadixString(this.intRadix);
	}

	public void setStrRadix(String radix) {
		this.strRadix = radix;
	}
	
	/**
	 * Setter for string radix converts integer to appropriate string
	 * @param radix The radix as an integer
	 */
	public void setStrRadix(int radix) {
		this.strRadix = getRadixString(radix);
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int x) {
		this.num = x;
	}

	public int getIntRadix() {
		return this.intRadix;
	}

	public void setIntRadix(int x) {
		this.intRadix = x;
	}
	
	/**
	 * Set both the int and string radixes
	 * @param radix the radix to set(as an int)
	 */
	public void setRadixes(int radix) {
		this.setIntRadix(radix);
		this.setStrRadix(radix);
	}

	/**
	 * Return a random integer between 0 and 99
	 @return random integer between 0 and 99
	 */
	public int getRandomNum() {		
		return (int) (Math.random() * 100);
	}
	
	/**
	 * Randomly return 2, 8, 10, or 16
	 * @return a randomly selected radix
	 */
	public int getRandomRadix() {
		int rand = (int) (Math.random() * 4); // 0-3
		int result = -1;
		switch(rand) {
		case 0:
			result = 2;
			break;
		case 1:
			result = 8;
			break;
		case 2:
			result = 10;
			break;
		case 3:
			result = 16;
			break;
		default:
		    result = 42;
		    break;
		}
		return result;
	}
	
	/**
	 * Take an integer radix and return its corresponding String representation
	 * @param an integer radix
	 * @return The radix string
	 */
	public String getRadixString(int radix) {
	    String result = "";
		switch (radix) {
		case 2:
			result = "Binary";
			break;
		case 8:
			result = "Octal";
			break;
		case 10:
			result = "Decimal";
			break;
		case 16:
			result = "Hexadecimal";
			break;
		case 18:
		    result = "Binary";
		    break;
		default:
		    result = "Error with radix string";
		    break;
		}
		//System.out.println(result);
		return result;
	}

	/**
	 * Generate a prompt string using a random number and representation radix
	 * @return the complete question prompt
	 */
	public String generatePrompt() {

	    // Set a 'from' radix that is not equal to the 'to' radix,
	    // which is contained in this.intRadix

	    int radix = this.getRandomRadix();
	    while (radix == this.getIntRadix())
		radix = this.getRandomRadix();
	    	    
	    String num = Integer.toString(this.num, radix);
	    String srcRadix = getRadixString(radix);

	    return String.format("Convert %s from %s to %s:", num, srcRadix, this.getStrRadix()); 
       	}
	
	/**
	 * Practice converting random radixes to a specific radix
	 * @param destRadix The radix to convert to
	 * @return 
	 */
    public String generatePrompt(int destRadix) {
		
		if (destRadix == -1) {
			// In the GUI, quiz.mode is passed in which specifies a destination radix
			// -1 indicates random mode, so we in that case we just generate a prompt from the
			// non-overloaded method
			return this.generatePrompt();
		} 
		    // Mask Question
		    if (destRadix == 18){
			String number = Integer.toString(this.num, 2);
			this.num2 = this.getRandomNum();
			String number2 = Integer.toString(num2, 2);
			return String.format("What is the binary output when applying the mask %s to %s?", number, number2);
		    }
		else {
			// Set a 'from' radix that is not equal to the 'to' radix
			int srcRadix = this.getRandomRadix();
			while (srcRadix == destRadix) srcRadix = this.getRandomRadix();
			
			String num = Integer.toString(this.num, srcRadix);
			
			return String.format("Convert %s from %s to %s:", num, getRadixString(srcRadix), this.getRadixString(destRadix));
		}
	}
	
	/**
	 * Strip all leading zeroes, spaces, and convert to lowercase
	 * @param s - the string to sanitize
	 * @return the sanitized string
	 */
    public String sanitize(String s){
	String sanString = "";
	if(s.equals("0")){
	    sanString = s;
	}else{
	    sanString = s.replaceAll("^0*", "").replaceAll(" ", "").toLowerCase();
	}
	return sanString;
    }
	
	/**
	 * Return a string representation of the number in the specified radix
	 * @param radix The radix to convert to
	 * @return number in string representation
	 */
	public String convertTo(int radix) {

	    if (radix == 18){
                Integer mask = this.num & this.num2;
                String answer = Integer.toBinaryString(mask);
                return answer;
            }

		return Integer.toString(this.getNum(),radix);
	}
	
	/**
	 * Test if a user submitted answer matches the correct conversion
	 * Leading zeroes are stripped for flexibility
	 * @param userAnswer The string answer submitted by the user
	 * @return true if answer correct, false if answer incorrect
	 */
	public boolean checkAnswer(String userAnswer) {
	    userAnswer = sanitize(userAnswer);
	    String answer = this.convertTo(this.getIntRadix());
	    return (userAnswer.equals(answer)) ? true : false;
	}
	
	/**
	 * Return the correct conversion as a string
	 * @return The number converted to the radix (as a string)
	 */
	public String getAnswer() {
	    if(this.intRadix == 18){
		Integer mask = this.num & this.num2;
		String answer = Integer.toBinaryString(mask);
		return answer;
	    }else{
		return Integer.toString(this.num, this.intRadix);
	    }
	}
	
}
