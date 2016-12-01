# cs56-math-conversion-quiz

Project Description:

The cs56 Math Conversin Quiz contains both a command-line version, and a GUI in which a user can practice number conversions between binary (including masking), decimal, octal, and hexadecimal. The command-line allows the user to request a certain amount of questions which will be asked in a random conversion (from any radix to any radix). The GUI has several modes including: binary conversion (from any radix to binary), decimal conversion (from any radix to decimal), octal conversion (from any radix to octal), hexadecimal conversion (from any radix to hexadecimal), random conversion (the default mode, from any radix to any radix), and a masking conversion (only with binary).


project history
===============
```
YES | bronhuston | ashedden,l-y-s | GUI for binary/octal/hex/decimal conversion quiz 
 W14 | bronhuston 5pm | ashedden,l-y-s | GUI for binary/octal/hex/decimal conversion quiz
```

Running the Quiz:

To run the command-line version of the game, use "ant run". To run the GUI version of the game, use "ant runGui".


![](http://imgur.com/oEDviAw.png)


Important Information for Future Developers:

* Within src/edu/ucsb/cs56/projects/math/cs56_math_conversion_quiz, the files beginning with Question contain code to determine what question will be asked to the user, the files beginning with Quiz contain code for the command-line version of the quiz, and the files beginning with QuizGui contain code for the GUI version of the quiz.

* The QuizGUI files depend on the Quiz files. The QuizGui.java mainly contain window presentaion of the data produced by Quiz.java. QuizGui.java has very little funcitionality besides accepting and displaying user feedback.

* Current issues include not being able to check the user's input and print a line explaining that the format of the submission was incorrect in the GUI version, and after playing a full 10 question quiz in masking mode of the GUI version, the first question when beginning a new quiz always has the answer an incorrect answer of "0".
 
F16 Final Remarks (Nikhil Patil & Aryaman Das)

As stated above in the section "Important Information for Future Developers", the directory src/edu/ucsb/cs56/projects/math/cs56_math_conversion_quiz contains the main files responsible for the operation of this program. The main files responsible for the math conversion quiz are Question.java, Quiz.java, and QuizGui.java.
The Question.java file is responsible for the creation of each type of question that will appear during a quiz.
The Quiz.java file contains the code responsible for generating and running a commandline version of the quiz.
The QuizGui.java file relies on the methods and behavior of Quiz objects (implemented in Quiz.java) in order to run the GUI version of the quiz.
We recommend browsing through and understanding the code in these files in the order written. Reviewing these files in such an order will make it easier to understand the hierarchy that exists relative to Question objects, Quiz objects, and QuizGui objects. A Quiz object consists of Question objects, and a QuizGui object consists of both Question and Quiz objects. Spend time understanding the state and behavior of each object (instance variables and methods) and how these are used to generate particular aspects of the quiz. Ultimately, this will make life easier when it comes to tracking down which portions of code need to be modified when addressing future issues.

For the next developer(s) who approach this project, there are several issues and ideas that could implemented in order to ameliorate both the performance of the quiz and the experience of the user. Several features that can be added and modified revolve around the current GUI format of the frames and widgets.
For example, the quiz's opening window currently requires the user to select a difficulty mode, and then a separate window pops up requesting the user to then select the length of the quiz. A possible task would be to redesign the start of the quiz so that there is only ONE opening window that allows the user to select both the quiz difficulty and the quiz length.
Two other tasks could enhance the current performance of the quiz by creating widgets (like buttons) that allow the user to change the difficulty of the quiz and the length of the quiz. Currently, once a quiz is created and runs, the difficulty mode remains locked on whatever the user entered in the opening menu and so does the length of the quiz. Currently, these aspects can only be changed by running a new quiz (closing out current quiz and entering "ant runGui" from commandline). 


