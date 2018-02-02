# cs56-math-conversion-quiz

Testing...

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

* One current issue with the code is the layout of the results page. The results page could use some improvements to the layout. Compared to the other parts of the quiz, the results page lacks the same overall design/layout.
 
F17 Final Remarks (Kai Labasan & Roberto Nares)

Despite the several improvements added to this legacy code, there are many improvements that could be made to improve the program. The layout and overall appearance of the GUI can be improved. A suggestion would be possibly adding a .jpg file of something that pertains to math to make the quiz more fun. In addition, it would drastically increase the user’s experience if sound effects were added when entering an answer and finishing the quiz. There could be different sounds for getting questions wrong and right. 

Another suggestion would be to port all of the functionality from the GUI to the command-line version such as being able to change modes mid-quiz. On the same note, further improvements could be made to the GUI version such as adding more statistics on how the user is doing to improve their abilities. More modes could also be added such as addition, multiplication, or two’s complement. 

As you will find out, the quiz questions are made to stay within a certain bounds. After using the program several times, it gets repetitive and easy to complete the quiz with a good score. The different difficulties in the GUI do not increase the numbers but rather just provides less of a hint and less tolerance in incorrect answers. A great addition would be to fix it so different difficulties also affects the questions.



