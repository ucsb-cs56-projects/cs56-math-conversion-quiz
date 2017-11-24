package edu.ucsb.cs56.projects.math.cs56_math_conversion_quiz;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A command line interface for a binary/octal/hex conversion Quiz.
 * Original program by Erick Valle & George Barrios for Mantis 0000391
 * @author Andrew Berls
 * @version CS56, Spring 2012, Mantis 0000611
 * @authors Nikhil Patil & Aryaman Das
 * @version CS56, Fall 2016
 */

public class Quiz {
	
	private ArrayList<Question> questions = new ArrayList<Question> ();
    
    private ArrayList<Boolean> scores = new ArrayList<Boolean>();
    
    /*
     * mode is a magic number to represent the current quiz radix.
     * For example, you can practice conversions to binary by setting it to 2
     * The same is true of radixes 2, 8, 10, and 16
     * -1 (default) is random mode: converts from any radix to any radix
     */
    private int mode;
    
    /**
     * Constructor initializes a fixed number of random questions
     * @param numQuestions The number of questions to initialize
     * @param type The type of quiz questions to encounter
     */
    public Quiz(int numQuestions, int type) {
    	this.mode = type; // selects type of game to play
    	
		for (int i=0; i<numQuestions; i++) {
			// Generate a random Question and push it to the beginning of the list
			Question q = new Question(this.mode);
			this.questions.add(0, q);
		}
	}
    /**
     * Constructor intializes a quiz for a fixed number of random questions
     * @param numQuestions The number of questions to initialize
     */
    public Quiz(int numQuestions) {
	this.mode = -1; // Random mode by default

	for (int i=0; i<numQuestions; i++) {
	    // Generate a random Question and push it to the beginning of the list
		Question q = new Question(this.mode);
	    this.questions.add(0, q);
	}
    }
    
    /**
     * @return The mode/type of questions in the quiz
     */
    public int getMode() {
    	return this.mode;
    }

    public int currentQuestionNum;
    
    /**
     * Set the mode to a specific radix, e.g., 2, 8, 10, 16, 18
     * in order to practice conversions to that radix
     * @param mode The type of questions to be asked
     */
    public void setMode(int mode) {
    	this.mode = mode;
    }
    /**
     * @return the number of questions in the quiz
     */
    public int getNumQuestions() {    	
    	return this.questions.size();
    }
    
    /**
     * Retrieve a particular question from the questions ArrayList
     * @param idx index of question in ArrayList
     * @return Question located at index idx
     */
    public Question getQuestion(int idx) {
    	return this.questions.get(idx);
    }
    
    /**
     * Insert a score into the list of user answers
     * @param correct A boolean indicating if the answer is correct
     */
    public void insertScore(boolean correct) {
    	this.scores.add(0, correct);
    }
    
    /**
     * Get the number of correct answers from the scores array
     * True indicates correct, false for incorrect
     * @return The number of correct answers
     */
    public int numCorrect() {
    	int result = 0;
    	for (boolean correct : this.scores) {
    		if (correct) {
    			result++;
    		}
    	}
    	return result;
    }
    
    /**
     * Get the user's percentage of correct answers
     * @return The percentage of correct answers as an integer
     */
    public int getPercentage() {
    	// numCorrect returns an integer, but must be coerced to a double
    	// so that (numCorrect/size) works correctly
    	
    	double numCorrect = (double) this.numCorrect();
    	int size = this.scores.size();
    	double result = Math.round((numCorrect / size)*100);
    	return (int) result;
    }
    
    /**
     * A readout showing the score percentage, e.g., "Your score was 67%!"
     * @return A string readout
     */
    public String getReadout() {
	String readout = "";
	readout = "<html>Your total score was " + this.getPercentage() + "%!<br>";
	return readout;
    }
  
    /**
     * Run the Quiz game on the command line
     * For each question, display its prompt and track the user's answer
     * At the end, display the percentage of correct answers.
     */
    public void run() {
    	Scanner scanner = new Scanner(System.in);
	
	for (Question q : this.questions) {
	    String Q = q.generatePrompt();
	    System.out.println("Question " + (++currentQuestionNum) + " of " + getNumQuestions() + ": " +  Q);
    		String userAnswer = scanner.next();
	     
		while(!(userAnswer.matches("^[a-fA-F0-9]+$")))
		    {
			System.out.println("Invalid input. Please only use characters A-F and/or digits 0-9");
			userAnswer = scanner.next();
		    }
		
    		if (q.checkAnswer(userAnswer)) {
    			System.out.println("Correct!");
    			this.insertScore(true);
    		} else {
		    System.out.println("Incorrect!");
		    System.out.print(Q + " ... ");
		    System.out.println("Actual answer was: " + q.convertTo(q.getIntRadix()));
		    this.insertScore(false);
    		}
    	}
    	System.out.println("Your score was " + this.getPercentage() + "%!");

	// NEW WORK FOR ISSUE 47: Will start a new quiz with the same mode
	System.out.println("Enter number of questions to start a new quiz (enter 0 to quit):");

	String s = scanner.next();
	int newNumQuestions = Integer.parseInt(s);

	if (newNumQuestions == 0) return;

	currentQuestionNum = 0;
	questions.clear();
	scores.clear();
	
	for (int i=0; i<newNumQuestions; i++) {
	    // Generate a random Question and push it to the beginning of the list
	    Question q = new Question(this.mode);
	    this.questions.add(0, q);
	}
	this.run();
	// END WORK FOR ISSUE 47
	
    }
	
    /**
     * main prompts the user for a number of questions and runs a new Quiz
     * @param args Unused
     */
    public static void main (String [] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of questions:");
	    String s = scanner.next();
		int numQuestions = Integer.parseInt(s);
		System.out.println("Select which mode you would like to test. Press 2 for binary, 8 for octal, 10 for decimal, 16 for hexadecimal, or -1 for random");
		s = scanner.next();
		int qtype = Integer.parseInt(s);
		Quiz quiz = new Quiz(numQuestions, qtype);
		quiz.setMode(qtype);
		quiz.run();		
    }
}
