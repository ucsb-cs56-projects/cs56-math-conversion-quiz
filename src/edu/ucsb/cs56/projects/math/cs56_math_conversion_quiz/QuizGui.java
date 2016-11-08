package edu.ucsb.cs56.projects.math.cs56_math_conversion_quiz;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * GUI for binary/octal/hex conversion Quiz
 * Original program by Erick Valle & George Barrios for Mantis 0000391
 * @author Andrew Berls
 * @version CS56, Spring 2012, Mantis 0000611 
 * @authors Allison Shedden & Logan Schmidt
 * @version CS56, Winter 2014
 */

public class QuizGui {
	
	// Window components
        JFrame frame = new JFrame("Binary/Decimal/Octal/Hex Conversion Quiz");

        // Used for creating/inserting vertical structs to act as spacer margins
	Box box = new Box(BoxLayout.X_AXIS);
	//Runnable scanHint = new Hint();
	
	
	// Quiz-related variables
	static QuizGui quizGui = new QuizGui();
	private int numQuestions = 10;
	Quiz quiz = new Quiz(numQuestions);	
	private boolean refresh = false;
	private int maxMatch =0 ;
	// Sidebar references
	JPanel sidebar                 = new JPanel();
	JLabel currentQuestionNumLabel = new JLabel("  Current Question: ");
	JLabel currentQuestionNum      = new JLabel(String.format("            %d/%d", current+1, quiz.getNumQuestions())); 
	JLabel numCorrectLabel         = new JLabel("  Number Correct: ");
	JLabel numCorrect              = new JLabel(String.format("            0/%d", quiz.getNumQuestions()));
	
	JPanel modePanel        = new JPanel();
	JLabel practiceLabel    = new JLabel("  I want to practice: ");
	JButton binaryMode      = new JButton("Binary Mode");
	JButton octalMode       = new JButton("Octal Mode");
	JButton decimalMode     = new JButton("Decimal Mode");
	JButton hexadecimalMode = new JButton("Hexadecimal Mode");
	JButton randomMode      = new JButton("Random Mode");
        JButton maskMode        = new JButton("Masking Mode");
	
	// Content references
	JPanel content = new JPanel();
	JPanel userInput = new JPanel();
	JLabel questionLabel = new JLabel("");
	JTextField userAnswer = new JTextField(25);
	JLabel hintLable = new JLabel("Hint: ");
	JButton submit = new JButton("Submit");
	JButton switchHint = new JButton("Show Hint");
	
	JPanel results  = new JPanel();
	JLabel feedback = new JLabel("");
	
	JPanel scorePanel   = new JPanel();
	JLabel scoreReadout = new JLabel("");
	JButton tryAgain    = new JButton("Try Again!");
	
	// Specific question references
	private static int current;
	private Question currentQuestion = new Question();

        /**
	 * Build the Quiz GUI window
	 */
	public QuizGui build() {

	    questionLabel.setPreferredSize(new Dimension(400, 20));
		
		int bottomMargin = 15;
		
		//---------------------
		//-- Sidebar
		//---------------------
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

		// Adding color
		java.awt.Color bgColor = new java.awt.Color(245,222,179);  // R, G, B values.
		sidebar.setBackground(bgColor);
		
		sidebar.add(box.createVerticalStrut(bottomMargin-7));

		sidebar.add(currentQuestionNumLabel);
		sidebar.add(currentQuestionNum);
		
		sidebar.add(box.createVerticalStrut(bottomMargin));
		
		sidebar.add(numCorrectLabel);
		sidebar.add(numCorrect);
		
		modePanel.add(box.createVerticalStrut(bottomMargin));
		
		modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
		
		// Adding color
		modePanel.setBackground(bgColor);

		sidebar.add(box.createVerticalStrut(bottomMargin*2+10));

		modePanel.add(practiceLabel);
		
		modePanel.add(box.createVerticalStrut(bottomMargin));

		binaryMode.addActionListener(new binaryModeListener());
		modePanel.add(binaryMode);

		// Adding color
		java.awt.Color bColor = new java.awt.Color(102,255,153);  // R, G, B values.
		binaryMode.setBackground(bColor);
		binaryMode.setOpaque(true);
		
		modePanel.add(box.createVerticalStrut(bottomMargin-10));
		
		octalMode.addActionListener(new octalModeListener());
		modePanel.add(octalMode);
		
		// Adding color
		java.awt.Color oColor = new java.awt.Color(000,204,102);   // R, G, B values.
		octalMode.setBackground(oColor);
		octalMode.setOpaque(true);
		
		modePanel.add(box.createVerticalStrut(bottomMargin-10));
		
		decimalMode.addActionListener(new decimalModeListener());
		modePanel.add(decimalMode);

		// Adding color
		java.awt.Color dColor = new java.awt.Color(000,204,153);   // R, G, B values.
		decimalMode.setBackground(dColor);
		decimalMode.setOpaque(true);

		modePanel.add(box.createVerticalStrut(bottomMargin-10));
		
		hexadecimalMode.addActionListener(new hexadecimalModeListener());
		modePanel.add(hexadecimalMode);
		
		// Adding color
		java.awt.Color hColor = new java.awt.Color(102,204,204);   // R, G, B values.
		hexadecimalMode.setBackground(hColor);
		hexadecimalMode.setOpaque(true);

		modePanel.add(box.createVerticalStrut(bottomMargin-10));
		
		randomMode.addActionListener(new randomModeListener());
		modePanel.add(randomMode);
		
		// Adding color
		java.awt.Color rColor = new java.awt.Color(051,153,204);   // R, G, B values.
		randomMode.setBackground(rColor);
		randomMode.setOpaque(true);

		modePanel.add(box.createVerticalStrut(bottomMargin-10));

		maskMode.addActionListener(new maskModeListener());
		modePanel.add(maskMode);

		// Adding color
		java.awt.Color mColor = new java.awt.Color(102,102,204);   // R, G, B values.
		maskMode.setBackground(mColor);
		maskMode.setOpaque(true);
		
		sidebar.add(modePanel);
		
		frame.getContentPane().add(BorderLayout.WEST, sidebar);
		
		//---------------------
		//-- Main Content
		//---------------------
	
		// userInput sub-pane
		userInput.setLayout(new BoxLayout(userInput, BoxLayout.Y_AXIS));
		
		// Adding color
		userInput.setBackground(bgColor);

		userInput.add(questionLabel);
		userInput.add(box.createVerticalStrut(5));
		
		userAnswer.getDocument().addDocumentListener(new hintListener());
		userInput.add(userAnswer); // ----------------------------------------------
		
		userInput.add(box.createVerticalStrut(5));
		userInput.add(hintLable);

		hintLable.setVisible(false);
		
		userInput.add(box.createVerticalStrut(5));
		submit.addActionListener(new submitListener());
		userInput.add(feedback);
		userInput.add(box.createVerticalStrut(5));
		userInput.add(submit);

		// Adding color
		java.awt.Color sColor = new java.awt.Color(255,255,000);   // R, G, B values.
		submit.setBackground(sColor);
		submit.setOpaque(true);

		userInput.add(box.createVerticalStrut(5));
		switchHint.addActionListener(new switchHintListener());
		userInput.add(switchHint);

		// Adding color
		java.awt.Color hintColor = new java.awt.Color(255,255,255);   // R, G, B values.
		switchHint.setBackground(hintColor);
		switchHint.setOpaque(true);

		// Results sub-pane
		results.setLayout(new BoxLayout(results, BoxLayout.Y_AXIS));
		
		// Adding color
		results.setBackground(bgColor);

		// Score readout sub-pane (Visible at end)
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
		
		// Adding color
		scorePanel.setBackground(bgColor);

		scorePanel.add(scoreReadout);
		scorePanel.add(box.createVerticalStrut(10));
		tryAgain.addActionListener(new tryAgainListener());
		scorePanel.add(tryAgain);

		// Adding color
		java.awt.Color tColor = new java.awt.Color(255,255,255);   // R, G, B values.
		tryAgain.setBackground(tColor);
		tryAgain.setOpaque(true);

		scorePanel.setVisible(false); // Enabled at end of quiz
		frame.getContentPane().add(BorderLayout.CENTER, scorePanel);
		
		// Adding color
		frame.getContentPane().setBackground(bgColor);
	      
		// Attach sub-panes to content pane
		content.add(userInput);
		content.add(results);

		// Adding color
		content.setBackground(bgColor);
		
		frame.getContentPane().add(BorderLayout.EAST, content);
		
		
		//---------------------
		//-- Window setup
		//---------------------
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,400);
		frame.setVisible(true);
		refreshHint();

		center(frame);

		return this; // For chaining method calls
	}
	
	/**
	 * When the user clicks submit, send feedback on their answer
	 * and update appropriate counters
	 */
	 
	 public void refreshHint()
	 {
		 String h = "";
		 refresh = true;
		 maxMatch = 0;
		 for(int i=0; i<currentQuestion.getAnswer().length(); i++)
			h+="_ ";
		 hintLable.setText("Hint: "+h+"  You hit: "+0+"/"+ currentQuestion.getAnswer().length());
	 }
		 
	 
	class submitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {			
			String answer = userAnswer.getText();
			//System.out.println(answer);
			//if (testAnswer(answer)){

			if(!(answer.matches("^[a-fA-F0-9]+$")))
			    {
				feedback.setText("Invalid input. Please only use characters A-F and/or digits 0-9");
				userAnswer.setText("");
				return;
			    }
			 
			    if (currentQuestion.checkAnswer(answer)) {
				feedback.setText("Correct!");
				quiz.insertScore(true);
			    } else {
				feedback.setText("Incorrect! Answer was: " + currentQuestion.getAnswer());
				quiz.insertScore(false);
			    }
		
			    String numCorrectStr = String.format("            %d/%d", quiz.numCorrect(), quiz.getNumQuestions());
			    numCorrect.setText(numCorrectStr);
			    current++;
			    currentQuestionNum.setText(String.format("            %d/%d", current+1, quiz.getNumQuestions()));
			    
			    currentQuestion = new Question(quiz.getMode()); 
			    refreshHint();
			    quizGui.ask();
			    //}
			    //else {
			    //feedback.setText("Answer was not in proper format, please try again.");
			    //quizGui.ask();
			    //}
		}
	}
	/**
	 * Try Again button - run a new Quiz on click
	 */
	class tryAgainListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			quiz = new Quiz(numQuestions);
			
			// Re-enable quiz inputs		
			numCorrect.setText((String.format("            0/%d", quiz.getNumQuestions())));
			sidebar.setVisible(true);
			userInput.setVisible(true);
			scorePanel.setVisible(false);
			feedback.setText("");
			
			// Restart the quiz
			current = 0;
			currentQuestionNum.setText(String.format("            %d/%d", current+1, quiz.getNumQuestions()));
			refreshHint();
			quizGui.ask();
		}
	}
	
	/**
	 * Set the quiz to practice converting only to binary
	 */
	class binaryModeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			feedback.setText("");
			currentQuestion = new Question(currentQuestion.getRandomNum(), 2);
			quiz.setMode(2);
			refreshHint();			
			quizGui.ask();
		}
	}
	
	/**
	 * Set the quiz to practice converting only to octal
	 */
	class octalModeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		  quiz.setMode(8);			
			feedback.setText("");
			currentQuestion = new Question(currentQuestion.getRandomNum(), 8);
			refreshHint();
			quizGui.ask();
		}
	}
	
	/**
	 * Set the quiz to practice converting only to decimal
	 */
	class decimalModeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		  quiz.setMode(10);
			feedback.setText("");
			currentQuestion = new Question(currentQuestion.getRandomNum(), 10);	
			refreshHint();			
			quizGui.ask();
		}
	}
	
	/**
	 * Set the quiz to practice converting only to hexadecimal
	 */
	class hexadecimalModeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
  		quiz.setMode(16);
			feedback.setText("");
			currentQuestion = new Question(currentQuestion.getRandomNum(), 16);	
			refreshHint();				
			quizGui.ask();	
		}
	}
	
	/**
	 * Set the quiz back to random conversions
	 */
	class randomModeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
  		quiz.setMode(-1);			
		currentQuestion = new Question(quiz.getMode());
			refreshHint();			
			quizGui.ask();
		}
	}

    /**
     * Set the quiz to masking conversions
     */
    class maskModeListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {

	    quiz.setMode(18);
	    feedback.setText("");
	    currentQuestion = new Question(currentQuestion.getRandomNum(), 18);
	    refreshHint();
	    quizGui.ask();
	}
    }

	
	/**
	 * Update fields to ask the current question, and end the quiz if necessary
	 */
	public void ask() {
	  userAnswer.setText(""); // Clear the text field
		
		if (current >= quiz.getNumQuestions()) {
			// If we're through with the questions, hide the inputs and show the final readout			
			sidebar.setVisible(false);
			userInput.setVisible(false);
			scoreReadout.setText(quiz.getReadout());
			scorePanel.setVisible(true);
		} else {
			// Else ask the current question
		    String prompt = currentQuestion.generatePrompt(quiz.getMode());
			questionLabel.setText(prompt);
			refreshHint();
			
		}
	}
	
	/**
	 * Switch the hint on/off
	 */
	
	public class switchHintListener implements ActionListener	{
		public void actionPerformed(ActionEvent e)	{
				if(hintLable.isVisible())	{
				    hintLable.setVisible(false);
					switchHint.setText("Show Hint");
				}
				else
				{
					hintLable.setVisible(true);
					switchHint.setText("Hide Hint");
				}
		}
	}
	
	/**
	 * Listen to any change of user input and prompt hint to user according to the input
	 */
	
	public class hintListener implements DocumentListener	{
		 private String answer;
		 private String hint;
		 
		 public void insertUpdate(DocumentEvent e) {
				go();
			}
		 public void removeUpdate(DocumentEvent e) {
				go();
				
			}
			public void changedUpdate(DocumentEvent e) {
			
       
			}
			
			public void go()
			{
			answer = userAnswer.getText();
			if(answer.equals("0")){
			}else{
			    answer = answer.replaceAll("^0*", "").replaceAll(" ", "").toLowerCase();
			}
			String regex = "^" + answer +".*$";
			String correctAnswer = currentQuestion.getAnswer();
			if(correctAnswer.matches(regex) || answer == correctAnswer)	{
				if(refresh)	{
					refresh = false;
					hint = answer;
					maxMatch = answer.length();
					for(int i=0; i<correctAnswer.length()-answer.length(); i++)
						hint+="_ ";
					hintLable.setText("Hint: "+hint+"  You hit: "+answer.length()+"/"+ currentQuestion.getAnswer().length());
				}
				
				else
				{
					if(answer.length()>maxMatch)	{
						hint = answer;
						maxMatch = answer.length();
						for(int i=0; i<correctAnswer.length()-answer.length(); i++)
							hint+="_ ";
					
					}
					hintLable.setText("Hint: "+hint+"  You hit: "+answer.length()+"/"+ currentQuestion.getAnswer().length());
					
				}
			}
				
			}

	}

    public static void center(JFrame frame) {

	// Gets the size of the screen
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	// Calculates the new location of the window
	int w = frame.getSize().width;
	int h = frame.getSize().height;

	int x = (d.width - w) / 2;
	int y = (d.height - h) / 2;

	// Moves this component to a new location

	frame.setLocation(x, y);
    }

    // Test format of user answer

    /*    public boolean testFormattingBinary(String answer){
	System.out.print("Answer length is " + answer.length());
	for(int i = 0; i < answer.length(); i++){
	    char test = answer.charAt(i);
	    System.out.println(test);
	    if( test != '0' || test != '1'){
		return false;
	    }else{continue;}
	}
	return true;
    }
    
    public boolean testFormattingOctalAndDecimal(String answer){
	for(int i = 0; i < answer.length(); i++){
            char test = answer.charAt(i);
            System.out.println(test);
            if(test != '0' || test != '1' || test != '2' || test != '3' || test != '4' ||
               test != '5' || test != '6' || test != '7' || test != '8' || test != '9'){
                return false;
            }else{continue;}
        }
	return true;
    }

    public boolean testFormattingHex(String answer){
	for(int i = 0; i < answer.length(); i++){
	    char test = answer.charAt(i);
	    System.out.println(test);
	    if(test != 'a' || test != 'b' || test != 'c' || test != 'd' || test != 'e' || test != 'f' || test != '0' || test != '1' ||
	       test != '2' || test != '3' || test != '4' || test != '5' || test != '6' || test != '7' || test != '8' || test != '9'){
		return false;
	    }else{continue;}
	}
	return true;
    }

    public boolean testAnswer(String answer){
	int curRadix = this.currentQuestion.getIntRadix();
        switch(curRadix){

        case 2:
	    if(testFormattingBinary(answer)){
		return true;
	    }else{return false;}
	    
	case 8:
	    if(testFormattingOctalAndDecimal(answer)){
		return true;
	    }else{return false;}

	case 10:
	    if(testFormattingOctalAndDecimal(answer)){
		return true;
	    }else{return false;}

	case 16:
	    if(testFormattingHex(answer)){
		return true;
	    }else{return false;}

	case 18:
	    if(testFormattingBinary(answer)){
		return true;
	    }else{return false;}

	default: 
	    return true;
	}
    }
    */
	/**
	 * Build and run the GUI
	 */
	public static void main (String [] args) {		
		quizGui.build().ask();
		
  }
	
}
