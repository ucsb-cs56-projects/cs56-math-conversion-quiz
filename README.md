# cs56-math-conversion-quiz

Project Description:

The cs56 Math Conversin Quiz contains both a command-line version, and a GUI in which a user can practice number conversions between binary (including masking), decimal, octal, and hexadecimal. The command-line allows the user to request a certain amount of questions which will be asked in a random conversion (from any radix to any radix). The GUI has several modes including: binary conversion (from any radix to binary), decimal conversion (from any radix to decimal), octal conversion (from any radix to octal), hexadecimal conversion (from any radix to hexadecimal), random conversion (the default mode, from any radix to any radix), and a masking conversion (only with binary).

Running the Quiz:

To run the command-line version of the game, use "ant run". To run the GUI version of the game, use "ant runGUI".


![](http://i.imgur.com/PaGacFJ.png)


Important Information for Future Developers:

* Within src/edu/ucsb/cs56/projects/math/cs56_math_conversion_quiz, the files beginning with Question contain code to determine what question will be asked to the user, the files beginning with Quiz contain code for the command-line version of the quiz, and the files beginning with QuizGui contain code for the GUI version of the quiz.

* The QuizGUI files depend on the Quiz files. The QuizGui.java mainly contain window presentaion of the data produced by Quiz.java. QuizGui.java has very little funcitionality besides accepting and displaying user feedback.

* Current issues include not being able to check the user's input and print a line explaining that the format of the submission was incorrect in the GUI version, and after playing a full 10 question quiz in random mode of the GUI version, the first question when beginning a new quiz always has the answer an incorrect answer of "0".
 

