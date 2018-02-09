# Gavin Frazar

# Roderic Deichler

### Description

  * A program which quizzes the user on their ability to convert between different numeric bases i.e. binary -> decimal, octal -> binary, etc.

### User Stories

  * As a user I can select a difficulty so that I can practice converting numbers at an appropriate difficulty.
  * As a user, I can select the number of questions I would like, so that I can take a quiz of the length I want.
  * As a user, I can ask for a hint, so that I can get a hint.
  * As a user, I can select a specific numeric base to practice with.
  * As a user, I can choose to play the quiz in the terminal or in a GUI environment, so that I can play it in more than one way.
  
### Software
  * The software runs in both the terminal and in a GUI environment. It allows the user to take a quiz on numreic base conversion systems using an input number of questions.
   
### User Should Stories
  * As a user, I would like to have a timed version of the quiz.
  * As a user, I would like to have an opponent to best.
  * As a user, I would like there to be sounds.
  * As a user, I would like there to be negative reinforcement to help me improve.
   
### README.md Review
  * The README.md is quite well put together. There is a lot of information about the project and how it has improved over the years. One thing that would be nice to improve, however, would be to bold font or emphasize the commands needed to run the code, because it took some close reading to find that. Also, Immediately under the project history are two lines of command line code that we do not understand.
  
  
### Build.XML
  * The build.xml file is in good shape (some comments could be removed). It is based on Ant and all targets currently have descriptions. Ant compiles/builds the program and there is a command to run it in terminal or in gui. Complete build sequence is  [compile, javadoc, clean, runGui, jar, run, test,]. There is no leftover JWS legacy code to be removed.


### Current Issues
  * There are no current issues to speak of, nor are there any documented in the README.md
 
### Additional Issues
  * There are no additional issues we can forsee.
  
### Assessment of the Code
  * Initial looks: Question.java sets a radix to be generated which will randomly initialize radix to either 2, 8, 10, 16, or 42. It is odd that 42 would even be an option, and even weirder is that many of the methods test whether the radix is 18 or -1. Upon further inspection we see that the radix is set to 18 whenever the masking mode is selected which always returns to binary. It is set to -1 whenever the mode is random (which is the default) This is somewhat counterintuitive and still does not explain the 42.
  
#####  Advice to anyone wanting to understand the code:
  * The code itself doesn't have any major malfunctions at first glance, but it could be rewritten to be more intuitive for the next programmer. For example, the switch statements could contain strings instead of numbers that way -1 would be represented by "Random" and 18 "Masking" which is far more intuitive.
  * The overall layout of the code could be revamped as well. Having getters and setters appear in the middle of the file can be a nuissance. 
  * Look at the Question.java first and see what the game modes are (-1 - Random, 2 - Binary, 8 - Octal, 10 - Decimal, 16 - Hex, 18 - Masking Mode).
  * The reason they use a string radix and int radix is because they want to be able to not only send information to the program (the int radix which is used to determine the number base to convert to), but also to the user. The string radix is printed to inform the user of the string version of the number base i.e. "Binary" or "Hexadecimal". This variable could be renamed to something along the lines of game_mode or number_base. This would make the program a lot easier to understand. Unfortunately they store the string version of radix (e.g. "Binary"), but never use that variable in the code. Instead they call a method every time they want to return strRadix that determines what the mode is, which is unintuitive and confusing.

### Test Coverage
There are unit tests and they cover every class method in both the Quiz class and the Question class.  I cannot foresee any additional tests for the project at this time.
