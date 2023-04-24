
##The Educational Model Program Readme
The Educational Model program is a quiz application that tests the user's knowledge on various topics related to technology and media literacy. The quiz consists of five multiple-choice questions, each with four options to choose from.
##Prerequisites
The Educational Model program requires a Java Development Kit (JDK) to run. It has been tested on JDK version 8 or higher.
##How to Run
VSC
1. Download and extract the EducationalModel.java file to a directory on your computer. 2. Open VSC.
3. Download Java Development Kit.
3. Run the program by clicking “Run” and “Run Without Debugging”
The quiz application window will appear.
Intillij
1. Download JDK package from Oracle
2. Create new project in Intillij (It will create a new folder in the C-Drive called “IdeaProjects”) 3. Put the runnable Java code inside of the folder.
Should look like this
This Pc->OS(C:)->Users->”name”->IdeaProjects->”folder name you created”->runnable Java file
4. Open Intillij and the folder you created
5. On the left pane you should see the Java file
6. Press the green play button in “EducationalModel” Class
Terminal
1. Download and extract the EducationalModel.java file to a directory on your computer. 2. Open a terminal or cmd prompt
3. Compile the program by entering the following command:
javac EducationalModel.java
4. Run the program by entering the following command:
java EducationalModel
The quiz application window will appear.
##How to Use
When the quiz application window appears, the user will see an introductory screen providing information about the program. The user can click the "Next" button to move to the next screen.
On the following screens, the user will be presented with multiple-choice questions and four options to choose from. The user can select an answer by clicking on the corresponding radio button and then click the "Submit" button to submit their answer.
After submitting an answer, the user will see a screen displaying whether their answer was correct or incorrect. They can click the "Next" button to move to the next question.
After answering all five questions, the user will see a screen displaying their final score and a button to exit the program.
Program Structure
The Educational Model program is written in Java and uses the Swing library to create the graphical user interface. The program is structured as follows:
- EducationalModel class: This is the main class of the program that contains the main method to start the application.
- createAndShowGUI method: This method creates the main JFrame window for the quiz application and sets its size, layout, and other properties. It also creates three JPanel screens for displaying information, questions, and results.
- createInformationScreens method: This method creates the first information screen that provides information about the program and another information screen that provides information about the importance of the program.
- createQuestionScreens method: This method creates five question screens, each with a multiple-choice question and four answer options.
- createResultScreen method: This method creates a result screen that displays the user's final score and a button to exit the program.
- ActionListener inner class: This inner class handles the user's actions when they click the "Submit" and "Next" buttons.
The program also includes instance variables for storing the questions, answer options, correct answers, user answers, and explanations. These variables are used to display the appropriate information on each screen and calculate the user's score at the end of the quiz.
