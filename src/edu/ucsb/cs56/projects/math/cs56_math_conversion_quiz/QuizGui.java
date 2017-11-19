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
 * @authors Nikhil Patil & Aryaman Das
 * @version CS56, Fall 2016
 */

public class QuizGui {
       
    // Window components
    JFrame frame = new JFrame("Binary/Decimal/Octal/Hex Conversion Quiz");

    // Used for creating/inserting vertical structs to act as spacer margins
    Box box = new Box(BoxLayout.X_AXIS);
    //Runnable scanHint = new Hint();
    
    int binq = 0;
    int octq = 0;
    int decq = 0;
    int hexq = 0;
    int bina = 0;
    int octa = 0;
    int deca = 0;
    int hexa = 0;
    int lowest = -1;
    
    // Quiz-related variables
    
    static QuizGui quizGui = new QuizGui();
    private int numQuestions = 10;
    boolean difficultySelected = false;
    boolean readyForQuiz = true;
    boolean AskAgain = true;
    String difficulty;
    int lvl; //global variable for mode
    int mode = 0;
    Quiz quiz = new Quiz(numQuestions);	
    private boolean refresh = false;
    private String lastAnswer = "";
    private boolean correct = false;
    private int maxMatch =0 ;
    // Sidebar references
    JPanel sidebar                 = new JPanel();
    JLabel currentQuestionNumLabel = new JLabel("  Current Question: ");
    JLabel currentQuestionNum      = new JLabel(String.format("            %d/%d", current+1, quiz.getNumQuestions())); 
    JLabel numCorrectLabel         = new JLabel("  Number Correct: ");
    JLabel numCorrect              = new JLabel(String.format("            0/%d", quiz.getNumQuestions()));

    JLabel currentDifficultyLabel = new JLabel("  Current Difficulty: ");
    JLabel currentDifficulty = new JLabel("");

    JPanel difficulties = new JPanel();
    JLabel changeDifficulty = new JLabel("  Change Difficulty: ");
    JButton easyButton = new JButton("Easy");
    JButton regularButton = new JButton("Regular");
    JButton hardButton = new JButton("Hard");
    
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

    // ISSUE 30 
    JLabel welcomeLabel = new JLabel("<html><font color='white'>Welcome to the Math Conversion Quiz!",SwingConstants.CENTER);
    // ISSUE 30 
    
    JPanel results  = new JPanel();
    JLabel feedback = new JLabel("");
    
    JPanel scorePanel   = new JPanel();
    JLabel scoreReadout = new JLabel("");

    // ISSUE 47 WORK
    JLabel newNumQuestionsLabel = new JLabel("<html>How many questions would you like for the new quiz? Select a number from 1 to 20");
    JTextField newNumQuestions = new JTextField(7);
    // END ISSUE 47 WORK
    
    JButton tryAgain    = new JButton("Try Again!");
    
    JFrame startWindow = new JFrame("Welcome to the Math Conversion Quiz!");
    JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,30));
    JLabel welcomeTitle = new JLabel("<html><font color='white'>Start a new Quiz!",SwingConstants.CENTER);

   
    JButton startButton = new JButton("Start!");

    JLabel chooseDifficultyLabel = new JLabel("Choose your difficulty:");
    JCheckBox easyCheckBox = new JCheckBox("Easy");
    JCheckBox regularCheckBox = new JCheckBox("Regular");
    JCheckBox hardCheckBox = new JCheckBox("Hard");
   
    JButton quitButton = new JButton("Quit");

    JLabel chooseQuestionsLabel = new JLabel("<html>How many questions would you like? <br> Select a number from 1 to 20</html>");
    JTextField chooseQuestionsInput = new JTextField(7);
    
    public QuizGui start() {
	startWindow.setSize(400,250);
	startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	java.awt.Color startPanelColor = new java.awt.Color(245,222,179);  // R, G, B values.
	startPanel.setBackground(startPanelColor);
	startWindow.add(startPanel);
	startPanel.add(welcomeTitle);
	startPanel.add(chooseDifficultyLabel);
	startPanel.add(easyCheckBox);
	startPanel.add(regularCheckBox);
	startPanel.add(hardCheckBox);

	startWindow.getContentPane().add(BorderLayout.NORTH,welcomeTitle);
	java.awt.Color welcomeColor = new java.awt.Color(000,204,255);  // R, G, B values.
	welcomeTitle.setBackground(welcomeColor);
	welcomeTitle.setOpaque(true);
	welcomeTitle.setFont(welcomeLabel.getFont().deriveFont(25.0f));


	java.awt.Color easyColor = new java.awt.Color(255,204,000);  // R, G, B values.
	easyCheckBox.setBackground(easyColor);
	java.awt.Color regColor = new java.awt.Color(255,153,000);  // R, G, B values.
	regularCheckBox.setBackground(regColor);
	java.awt.Color hardColor = new java.awt.Color(255,000,000);  // R, G, B values.
	hardCheckBox.setBackground(hardColor);

	
	
	easyCheckBox.addActionListener(new easyListener());
	regularCheckBox.addActionListener(new regularListener());
	hardCheckBox.addActionListener(new hardListener());
	
	startPanel.add(chooseQuestionsLabel);
	startPanel.add(chooseQuestionsInput);
	java.awt.Color startColor = new java.awt.Color(255,255,000);   // R, G, B values.
	java.awt.Color quitColor = new java.awt.Color(255,255,255);   // R, G, B values.
	startButton.setBackground(startColor);
	quitButton.setBackground(quitColor);
	startPanel.add(startButton);
	startPanel.add(quitButton);
	
	startButton.addActionListener(new questionsListener());
	quitButton.addActionListener(new quitListener());
	
	startWindow.setVisible(true);
	return this;
    }
	
    // Specific question references
    private static int current;
    private Question currentQuestion = new Question();
    
    /**
     * Build the Quiz GUI window
     */
    public QuizGui build(int mode) {
	lvl = mode;
	//	questionLabel.setPreferredSize(new Dimension(400, 20));
	
	int bottomMargin = 15;
	
	//---------------------
	//-- Sidebar
	//---------------------
	sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
	
	// Adding color
	java.awt.Color bgColor = new java.awt.Color(245,222,179);  // R, G, B values.
	sidebar.setBackground(bgColor);
	
	sidebar.add(box.createVerticalStrut(bottomMargin-7));
	
	currentQuestionNum.setText(String.format("            %d/%d", current+1, quiz.getNumQuestions()));
	numCorrect.setText(String.format("            0/%d", quiz.getNumQuestions()));
	
	sidebar.add(currentQuestionNumLabel);
	sidebar.add(currentQuestionNum);
	
	sidebar.add(box.createVerticalStrut(bottomMargin));
	
	sidebar.add(numCorrectLabel);
	sidebar.add(numCorrect);

	// WORK FOR ISSUE #46
	sidebar.add(box.createVerticalStrut(bottomMargin));
	
       	if (mode == 1)
	    difficulty = "           Easy";
	else if (mode == 2)
	    difficulty = "        Regular";
	else if (mode == 3)
	    difficulty = "           Hard";
	
	currentDifficulty.setText(difficulty);

	sidebar.add(currentDifficultyLabel);
	sidebar.add(currentDifficulty);
	sidebar.add(box.createVerticalStrut(bottomMargin));

	sidebar.add(changeDifficulty);
	sidebar.add(box.createVerticalStrut(bottomMargin));
	easyButton.addActionListener(new easyClickListener());

	// Adding color
	java.awt.Color easyColor = new java.awt.Color(255,204,000);  // R, G, B values.
	easyButton.setBackground(easyColor);
	easyButton.setOpaque(true);

	sidebar.add(easyButton);
	sidebar.add(box.createVerticalStrut(bottomMargin-10));
	regularButton.addActionListener(new regularClickListener());

	// Adding color
	java.awt.Color regColor = new java.awt.Color(255,153,000);  // R, G, B values.
	regularButton.setBackground(regColor);
	regularButton.setOpaque(true);
	
	sidebar.add(regularButton);
	sidebar.add(box.createVerticalStrut(bottomMargin-10));
	hardButton.addActionListener(new hardClickListener());

	// Adding color
	java.awt.Color hardColor = new java.awt.Color(255,000,000);  // R, G, B values.
	hardButton.setBackground(hardColor);
	hardButton.setOpaque(true);
	
	sidebar.add(hardButton);
	// END WORK 
	
	// START ISSUE 30
	//	modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));


	// Adding color
	modePanel.setBackground(bgColor);
	
	//	sidebar.add(box.createVerticalStrut(bottomMargin*2+10));
	
	//	modePanel.add(practiceLabel);
	
	//	modePanel.add(box.createVerticalStrut(bottomMargin));
	
	binaryMode.addActionListener(new binaryModeListener());
	modePanel.add(binaryMode);
	
	// Adding color
	java.awt.Color bColor = new java.awt.Color(102,255,153);  // R, G, B values.
	binaryMode.setBackground(bColor);
	binaryMode.setOpaque(true);
	
	//modePanel.add(box.createVerticalStrut(bottomMargin-10));
	
	octalMode.addActionListener(new octalModeListener());
	modePanel.add(octalMode);
	
	// Adding color
	java.awt.Color oColor = new java.awt.Color(000,204,102);   // R, G, B values.
	octalMode.setBackground(oColor);
	octalMode.setOpaque(true);
	
	//modePanel.add(box.createVerticalStrut(bottomMargin-10));
	
	decimalMode.addActionListener(new decimalModeListener());
	modePanel.add(decimalMode);
	
	// Adding color
	java.awt.Color dColor = new java.awt.Color(000,204,153);   // R, G, B values.
	decimalMode.setBackground(dColor);
	decimalMode.setOpaque(true);
	
	//modePanel.add(box.createVerticalStrut(bottomMargin-10));
	
	hexadecimalMode.addActionListener(new hexadecimalModeListener());
	modePanel.add(hexadecimalMode);
	
	// Adding color
	java.awt.Color hColor = new java.awt.Color(102,204,204);   // R, G, B values.
	hexadecimalMode.setBackground(hColor);
	hexadecimalMode.setOpaque(true);
	
	//modePanel.add(box.createVerticalStrut(bottomMargin-10));
	
	randomMode.addActionListener(new randomModeListener());
	modePanel.add(randomMode);
	
	// Adding color
	java.awt.Color rColor = new java.awt.Color(051,153,204);   // R, G, B values.
	randomMode.setBackground(rColor);
	randomMode.setOpaque(true);
	
	//modePanel.add(box.createVerticalStrut(bottomMargin-10));
	
	maskMode.addActionListener(new maskModeListener());
	modePanel.add(maskMode);
	
	// Adding color
	java.awt.Color mColor = new java.awt.Color(102,102,204);   // R, G, B values.
	maskMode.setBackground(mColor);
	maskMode.setOpaque(true);
	
	//sidebar.add(modePanel);
	
	frame.getContentPane().add(BorderLayout.WEST, sidebar);

	// ADDING THIS
	frame.getContentPane().add(BorderLayout.SOUTH,modePanel);
	// END ISSUE 30 
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
	if(mode == 3)
	    switchHint.setVisible(false);
 
	
	// Results sub-pane
	results.setLayout(new BoxLayout(results, BoxLayout.Y_AXIS));
	
	// Adding color
	results.setBackground(bgColor);
	
	// Score readout sub-pane (Visible at end)

	scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
	
	// Adding color
	scorePanel.setBackground(bgColor);
	
	scorePanel.add(scoreReadout);
	scorePanel.add(box.createVerticalStrut(20));
	tryAgain.addActionListener(new tryAgainListener());

	// WORK FOR ISSUE 47
	newNumQuestions.setMaximumSize( newNumQuestions.getPreferredSize() );
	scorePanel.add(newNumQuestionsLabel);
	scorePanel.add(newNumQuestions);
	scorePanel.add(box.createVerticalStrut(10));
	// END WORK FOR ISSUE 47


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

	// ISSUE 30
	//---------------------
	//--Title
	//---------------------
	java.awt.Color welcomeColor = new java.awt.Color(000,204,255);  // R, G, B values.
	welcomeLabel.setBackground(welcomeColor);
	welcomeLabel.setOpaque(true);
	welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(32.0f));
	frame.getContentPane().add(BorderLayout.NORTH,welcomeLabel);
	// ISSUE 30 
	
	
	//---------------------
	//-- Window setup
	//---------------------
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(850,400);
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
		 // currentQuestion might be the issue for #16; may need to reference a new question when tryAgain button is pressed or something is wrong with the getAnswer method for question
		 for(int i=0; i<currentQuestion.getAnswer().length(); i++)
			h+="_ ";
		 if (lvl == 2)
		 	hintLable.setText("Hint: "+h+"  Not enough digits");
		 else
		 	hintLable.setText("Hint: "+h);
	 }

    class easyListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {

	    if (easyCheckBox.isSelected()) {
		regularCheckBox.setSelected(false);
		hardCheckBox.setSelected(false);
		mode = 1;
		difficultySelected = true;
	    } else {
		difficultySelected = false;
		mode = 0;
	    } 
	}
    }
    
    class regularListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {

	    if (regularCheckBox.isSelected()) {
		easyCheckBox.setSelected(false);
		hardCheckBox.setSelected(false);
		mode = 2;
		difficultySelected = true;
	    } else {
		difficultySelected = false;
		mode = 0;
	    }
	}
    }

    class hardListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {

	    if (hardCheckBox.isSelected()) {
		regularCheckBox.setSelected(false);
		easyCheckBox.setSelected(false);
		mode = 3;
		difficultySelected = true;
	    } else {
		difficultySelected = false;
		mode = 0;
	    }
	}
    }
    
    class quitListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    
	    System.exit(0);
	}
    }

    class questionsListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {

	    readyForQuiz = true;
	    
	    if (!difficultySelected) {
		chooseDifficultyLabel.setText("<html>Invalid input!<br>Choose your difficulty");
		readyForQuiz = false;
	    } else 
		chooseDifficultyLabel.setText("Choose your difficulty");

	    String response = chooseQuestionsInput.getText();

	    try { 
		numQuestions = Integer.parseInt(response);
	    } catch (NumberFormatException ex) {
		chooseQuestionsLabel.setText("<html>Invalid input! <br> Please select a number from 1 to 20</html>");
		chooseQuestionsInput.setText("");
		return;
	    }

	    if (numQuestions < 1 || numQuestions > 20 || response.length() == 0) {
		chooseQuestionsLabel.setText("<html>Invalid input!<br>Please select a number from 1 to 20</html>");
		chooseQuestionsInput.setText("");
		readyForQuiz = false;
	    } else {
		chooseQuestionsLabel.setText("<html>How many questions would you like? <br> Select a number from 1 to 20</html>");
	    }
	    
	    if (!readyForQuiz) return;
	    
	    quiz = new Quiz(numQuestions);
	    startWindow.setVisible(false);
	    
	    quizGui.build(mode).ask();
	}
    }

    class easyClickListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    switchHint.setVisible(true);
	    currentDifficulty.setText("           Easy");
	    mode = 1;
	    lvl = 1;
	    userAnswer.setText("");
	    hintLable.setVisible(false);
					switchHint.setText("Show Hint");
	    refreshHint();
	}
    }

    class regularClickListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    switchHint.setVisible(true);
	    currentDifficulty.setText("        Regular");
	    mode = 2;
	    lvl = 2;
	    userAnswer.setText("");
	    hintLable.setVisible(false);
					switchHint.setText("Show Hint");
	    refreshHint();
	}
    }

    class hardClickListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    switchHint.setVisible(false);
	    hintLable.setVisible(false);
	    currentDifficulty.setText("           Hard");
	    mode = 3;
	    lvl = 3;
	    userAnswer.setText("");
	    hintLable.setVisible(false);
					switchHint.setText("Show Hint");
	    refreshHint();
	}
    }
	    
	     
	class submitListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {			
		    String answer = userAnswer.getText();
		    if(quiz.getMode() == -1)
			{
			    if(currentQuestion.getIntRadix() == 2)
				binq++;
			    else if(currentQuestion.getIntRadix() == 8)
				octq++;
			    else if(currentQuestion.getIntRadix() == 10)
				decq++;
			    else if(currentQuestion.getIntRadix() == 16)
				hexq++;
			}
			if(!(answer.matches("^[a-fA-F0-9]+$")))
			    {
				feedback.setText("Invalid input. Please only use characters A-F and/or digits 0-9");
				userAnswer.setText("");
				return;
			    }
			 
			    if (currentQuestion.checkAnswer(answer)) {
				feedback.setText("Correct!");
				
				if(currentQuestion.getIntRadix() == 2)
				    bina++;
				else if(currentQuestion.getIntRadix() == 8)
				    octa++;
				else if(currentQuestion.getIntRadix() == 10)
				    deca++;
				else if(currentQuestion.getIntRadix() == 16)
				    hexa++;
				quiz.insertScore(true);
				correct = true;
				AskAgain = true;
			    } else {
				if(AskAgain == true && lvl == 1)
				    {
					feedback.setText("Incorrect, please try again");
					AskAgain = false;
					return;
				    }
				else
				    {
					feedback.setText("<html>Incorrect!<br> Previous Question:<br>" + questionLabel.getText() + "<br> Answer was: " + currentQuestion.getAnswer());
					quiz.insertScore(false);
					AskAgain = true;
				    }
			    }

			    lastAnswer = currentQuestion.getAnswer();
		
			    String numCorrectStr = String.format("            %d/%d", quiz.numCorrect(), quiz.getNumQuestions());
			    numCorrect.setText(numCorrectStr);
			    current++;
			    currentQuestionNum.setText(String.format("            %d/%d", current+1, quiz.getNumQuestions()));
			    
			    currentQuestion = new Question(quiz.getMode());

			    refreshHint();
			    quizGui.ask();
			    correct = false;
		}
	}
	/**
	 * tryAgainListener class
	 * Allow user to restart and run new Quiz
	 * Overrides actionPerformed() method of ActionListener interface
	 */
	class tryAgainListener implements ActionListener {
	    /**
	     * Called when the user clicks the "Try Again" button at end of current quiz.
	     * @param e ActionEvent object that gives information about the event and its source.
	     */
		public void actionPerformed (ActionEvent e) {

		    // WORK FOR ISSUE 47 
		    String response = newNumQuestions.getText();

		    try { 
			numQuestions = Integer.parseInt(response);
		    } catch (NumberFormatException ex) {
			newNumQuestionsLabel.setText("<html>Invalid input! Please select a number from 1 to 20</html>");
			newNumQuestions.setText("");
			return;
		    }
		    
		    if (numQuestions < 1 || numQuestions > 20 || response.length() == 0) {
			newNumQuestionsLabel.setText("<html>Invalid input! Please select a number from 1 to 20</html>");
			newNumQuestions.setText("");
			return;
		    }   
		    // END WORK FOR ISSUE 47 
		    quiz = new Quiz(numQuestions);

		    // THINK THIS WILL SOLVE ISSUE 16
		    currentQuestion = new Question();
		    // THINK THIS WILL SOLVE ISSUE 16 
			
			// Re-enable quiz inputs		
			numCorrect.setText((String.format("            0/%d", quiz.getNumQuestions())));
			sidebar.setVisible(true);
			userInput.setVisible(true);
			modePanel.setVisible(true);
			scorePanel.setVisible(false);
			feedback.setText("");
			
			// Restart the quiz
			welcomeLabel.setText("<html><font color='white'>Welcome to the Math Conversion Quiz!");
			current = 0;
			currentQuestionNum.setText(String.format("            %d/%d", current+1, quiz.getNumQuestions()));
			refreshHint(); // currentQuestion might be the issue #16; may need to reference new question object
			quizGui.ask();
		}
	}
	
	/**
	 * binaryModeListener class
	 * Sets the quiz to practice questions  converting only to binary
	 * Overrides actionPerformed() method of ActionListener interface
	 */
	class binaryModeListener implements ActionListener {
	    /**
	     * Called when user clicks the "Binary Mode" button
	     * @param e ActionEvent object that gives info about source
	     */
		public void actionPerformed(ActionEvent e) {
			feedback.setText("");
			currentQuestion = new Question(currentQuestion.getRandomNum(), 2);
			quiz.setMode(2);
			refreshHint();			
			quizGui.ask();
		}
	}
	
	/**
	 * octalModeListener class
	 * Set the quiz to practice converting only to octal
	 * Overrides actionPerformed() method of ActionListener interface
	 */
	class octalModeListener implements ActionListener {
	    /**
	     * Called when the user clicks "Octal Mode" button
	     * @param e ActionEvent object that gives info about source
	     */
		public void actionPerformed(ActionEvent e) {
		  quiz.setMode(8);			
			feedback.setText("");
			currentQuestion = new Question(currentQuestion.getRandomNum(), 8);
			refreshHint();
			quizGui.ask();
		}
	}
	
	/**
	 * decimalModeListener class
	 * Set the quiz to practice converting only to decimal
	 * Overrides actionPerformed() method of ActionListener interface
	 */
	class decimalModeListener implements ActionListener {
	    /**
	     * Called when the user clicks the "Decimal Mode" button
	     * @param e ActionEvent object that gives info about source
	     */
		public void actionPerformed(ActionEvent e) {
		  quiz.setMode(10);
			feedback.setText("");
			currentQuestion = new Question(currentQuestion.getRandomNum(), 10);	
			refreshHint();			
			quizGui.ask();
		}
	}
	
	/**
	 * hexadecimalModeListener class
	 * Set the quiz to practice converting only to hexadecimal
	 * Overrides actionPerformed() method of ActionListener interface
	 */
	class hexadecimalModeListener implements ActionListener {
	    /**
	     * Called when the user clicks the "Hexadecimal Mode" button
	     * @param e ActionEvent object that gives info about source
	     */
		public void actionPerformed(ActionEvent e) {
  		quiz.setMode(16);
			feedback.setText("");
			currentQuestion = new Question(currentQuestion.getRandomNum(), 16);	
			refreshHint();				
			quizGui.ask();	
		}
	}
	
	/**
	 * randomModeListener class
	 * Set the quiz back to random conversions
	 * Overrides actionPerformed() method of ActionListener interface
	 */
	class randomModeListener implements ActionListener {
	    /**
	     * Called when the user clicks the "Random Mode" button
	     * @param e ActionEvent object that gives info about source
	     */
		public void actionPerformed(ActionEvent e) {
  		quiz.setMode(-1);			
		currentQuestion = new Question(quiz.getMode());
			refreshHint();			
			quizGui.ask();
		}
	}

    /**
     * maskModeListener class
     * Set the quiz to masking conversions
     * Overrides actionPerformed() method of ActionListener interface
     */
    class maskModeListener implements ActionListener {
	/**
	 * Called when the user clicks the "Masking Mode" button
	 * @param e ActionEvent object that gives info about source
	 */
	public void actionPerformed(ActionEvent e) {
	    quiz.setMode(18);
	    feedback.setText("");
	    currentQuestion = new Question(currentQuestion.getRandomNum(), 18);
	    refreshHint();
	    quizGui.ask();
	}
    }

    class practiceListener implements ActionListener {
	/**                                                                                                                                                       
	 * Called when the user clicks the "Try Again" button at end of current quiz.                                                                             
	 * @param e ActionEvent object that gives information about the event and its source.                                                                     
	 */
	public void actionPerformed (ActionEvent e) {
	    quiz = new Quiz(numQuestions);
	    quiz.setMode(lowest);
	    
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
     * guiQuestions class
     * Creates a quiz based of the number of questions requested by user 
     * Overrides actionPerformed() method of ActionListener interface
     
    class guiQuestions implements ActionListener {
	/**
	 * Called when the user clicks "begin" after entering number of questions
	 * @param e ActionEvent object that gives info about source
	 
	public void actionPerformed(ActionEvent e) {}
    */   
	
	/**
	 * Update fields to ask the current question, and end the quiz if necessary
	 */
	public void ask() {
	    userAnswer.setText(""); // Clear the text field
	    if (current >= quiz.getNumQuestions()) {
		    
		    // If we're through with the questions, hide the inputs and show the final readout			
		sidebar.setVisible(false);
		userInput.setVisible(false);
		modePanel.setVisible(false);
		welcomeLabel.setText("<html><font color='white'>Your Results:");
		String result = "";
		String worst = "";
		if (correct){
		    result = "<html>Correct! <br>";
		}
		else {
		    result = "<html>Incorrect!<br> Previous Question: " + questionLabel.getText() + "<br>Correct answer was: " + lastAnswer + "<br>";
		}
		if(quiz.getMode() == -1)
		    {
			int binPercent  = 0;
			int octPercent = 0;
			int decPercent = 0;
			int hexPercent = 0;

			ArrayList<Integer> percentages = new ArrayList<Integer>();
			
			if(binq != 0)
			    {
				binPercent = getPercentage(bina, binq);
				percentages.add(binPercent);
				String bin = "<html>You scored " + binPercent + "% on binary questions. <br>";
				result = result + bin;
			    }
			if(octq != 0)
			    {
				octPercent = getPercentage(octa, octq);
				percentages.add(octPercent);
				String oct = "<html>You scored " + octPercent + "% on octal questions. <br>";
				result = result + oct;
			    }
			if(decq != 0)
			    {
				decPercent = getPercentage(deca, decq);
				percentages.add(decPercent);
				String dec = "<html>You scored " + decPercent + "% on decimal questions. <br>";
				result = result + dec;
			    }
			if(hexq != 0)
			    {
				hexPercent = getPercentage(hexa, hexq);
				percentages.add(hexPercent);
				String hex = "<html>You scored " + hexPercent + "% on hexadecimal questions. <br>";
				result = result + hex;
			    }
			
			int lowestPercent = percentages.get(0);
			for (int i = 1; i < percentages.size(); i++) {
			    if (percentages.get(i) < lowestPercent)
				lowestPercent = percentages.get(i);
			}
		    
		       
			
			
			    /*    
			int lowestPercent = Math.min(binPercent, octPercent);
			lowestPercent = Math.min(lowestPercent, decPercent);
			lowestPercent = Math.min(lowestPercent, hexPercent);
			    */
			// Issue: Find a way that lowestPercent is a nonzero value...

			if(lowestPercent == binPercent && binq != 0)
			    {
				worst = "<HTML>Your worst score was in binary. Click the binary button to practice. <br>";
				JButton binButton = new JButton("Binary");
				lowest = 2;
				binButton.addActionListener(new practiceListener());
				scorePanel.add(binButton);
			    }
			else if(lowestPercent == octPercent && octq != 0)
			    {
				worst ="<HTML>Your worst score was in octal. Click the octal button to practice. <br>";
				JButton octButton = new JButton("Octal");
				lowest = 8;
				octButton.addActionListener(new practiceListener());
				scorePanel.add(octButton);
			    }
			else if(lowestPercent == decPercent && decq != 0)
			    {
				worst ="<HTML>Your worst score was in decimal. Click the decimal button to practice. <br>";
				JButton decButton = new JButton("Decimal");
				lowest = 10;
				decButton.addActionListener(new practiceListener());
				scorePanel.add(decButton);
			    }
			else if(lowestPercent == hexPercent && hexq != 0)
			    {
				worst ="<HTML>Your worst score was in hexadecimal. Click the hexadecimal button to practice. <br>";
				JButton hexButton = new JButton("Hexadecimal");
				lowest = 16;
				hexButton.addActionListener(new practiceListener());
				scorePanel.add(hexButton);
			    }
			
		    }
				    
		scoreReadout.setText(result + worst + quiz.getReadout());
		scorePanel.setVisible(true);

		// WORK FOR ISSUE 47
		
		// END WORK FOR ISSUE 47
		

	    } else {
		    // Else ask the current question
		String prompt = currentQuestion.generatePrompt(quiz.getMode());
		questionLabel.setText(prompt);
		refreshHint();
			
	    }
	}
		
	/**
	 * switchHintListener class
	 * Switch the hint on/off
	 * Overrides the actionPerformed() method of the ActionListener interface
	 */
	
    public class switchHintListener implements ActionListener	{
	/**
	 * Called when the user clicks the "Show Hint / Hide Hint" button
	 * Alters label on the button on each click
	 * @param e ActionEvent object that gives info about source and event
	 **/
	
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
	 * hintListener class
	 * Listen to any change of user input and prompt hint to user according to the input
	 * Overrides insertUpdate(), removeUpdate(), and changedUpdate() so that notifications are sent
	 * if an attribute or attributes have been modified, inserted, or removed. 
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
			int hit = 0;
			if (lvl == 1){
				if(correctAnswer.matches(regex) || answer == correctAnswer)	{
					if(refresh)	{
						refresh = false;
						hint = answer;
						maxMatch = answer.length();
						for(int i=0; i<correctAnswer.length()-answer.length(); i++)
							hint+="_ ";
						hintLable.setText("Hint: "+ hint);
					}
					
					else
					{
						if(answer.length()>maxMatch)	{
							hint = answer;
							maxMatch = answer.length();
							for(int i=0; i<correctAnswer.length()-answer.length(); i++)
								hint+="_ ";
						}
						hintLable.setText("Hint: "+hint);
					}
				}
			}
			if (lvl == 2){
				if (refresh) refresh = false;
				String h = "";
				if (answer.length() == correctAnswer.length()){
					for (int i=0; i<correctAnswer.length(); i++){
						if (answer.charAt(i) == correctAnswer.charAt(i)){
							h+= answer.charAt(i);	
							++hit;
						}
						else if (h.endsWith("_"))
							h+=" _";
						else
							h+="_";
					}
					if (hit == answer.length())
						hintLable.setText("Hint: "+h+" Correct!");
					else
						hintLable.setText("Hint: "+h+"  You hit: "+ hit+"/"+ answer.length());
				}
				else{
					for (int i=0; i<correctAnswer.length(); i++)
						h+="_ ";
					if (answer.length() > correctAnswer.length())
						hintLable.setText("Hint: "+h+"  Too many digits");
					else if (answer.length() < correctAnswer.length())
						hintLable.setText("Hint: "+h+"  Not enough digits");	
				}
			}
			}

	}

    /**
     * Creates the GUI window for quiz to execute on
     * @param frame Jframe object which has dimensions set
     */
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

    public int getPercentage(int num, int denom) {
	// numCorrect returns an integer, but must be coerced to a double
	// so that (numCorrect/size) works correctly

	double numCorrect = (double) num;
	double result = Math.round((numCorrect / denom)*100);
	return (int) result;
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
	    quizGui.start();
	    //quizGui.build().ask();
		
	}
    
	
}
