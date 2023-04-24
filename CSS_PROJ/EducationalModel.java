import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EducationalModel extends JFrame {

    private JPanel mainPanel;
    private JPanel questionPanel;
    private JLabel questionLabel;
    private ButtonGroup choicesGroup;
    private JRadioButton[] choiceButtons;
    private JLabel[] feedbackLabels;
    private JButton submitButton;
    private JButton nextButton;
    private JButton backButton;
    private JLabel explanationLabel;
    private int questionNumber = 1;
    private int[] correctAnswerIndices = {0, 1}; // add more indices as needed
    private boolean[] isAnswerCorrect = new boolean[2]; // add more elements as needed
    private boolean isInSummary = false;
    private boolean isInLastExplanation = false;
    private String[][] questions = {
        {"Question 1: What is threat modeling?", 
        "Answer 1", 
        "Choice 1: The process of identifying potential threats and vulnerabilities from an adversary's point of view, and exploring potential mitigations to these threats.",
        "Choice 2: The process of creating fake news articles using machine learning algorithms.",
        "Choice 3: The process of detecting fake news articles created by machines. ",
        "Choice 4: The process of analyzing patterns in real news articles to identify potential propaganda."},
        {"Question 2: What is reality vertigo?", 
        "Answer 2: The phenomenon where computers can generate such convincing content that regular people may have a hard time figuring out what's true anymore.", 
        "Choice 1: The phenomenon where computers can generate such convincing content that regular people may have a hard time figuring out what's true anymore.",
        "Choice 2: The phenomenon where people become dizzy when they use virtual reality headsets.",
        "Choice 3: The phenomenon where people become confused when reading news articles.",
        "Choice 4: The phenomenon where people have trouble distinguishing between reality and fiction."
    },
    
    
    };
    private String[] explanations = {"Explanation 1", "Explanation 2"}; // add more explanations as needed


    public EducationalModel() {
        setTitle("Multiple Choice Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());

        showQuestion();

        add(mainPanel);
        setVisible(true);
    }

    private void showQuestion() {
        mainPanel.removeAll();
        questionPanel = new JPanel(new GridLayout(5, 2));
    
        String[] currentQuestion = questions[questionNumber - 1];
        questionLabel = new JLabel(currentQuestion[0]);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(questionLabel, BorderLayout.NORTH);
    
        explanationLabel = null;
    
        choicesGroup = new ButtonGroup();
        choiceButtons = new JRadioButton[4];
        feedbackLabels = new JLabel[4];
    
        for (int i = 0; i < 4; i++) {
            choiceButtons[i] = new JRadioButton(currentQuestion[i + 2]);
            choiceButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            choicesGroup.add(choiceButtons[i]);
            questionPanel.add(choiceButtons[i]);
    
            feedbackLabels[i] = new JLabel("");
            feedbackLabels[i].setFont(new Font("Arial", Font.PLAIN, 18));
            questionPanel.add(feedbackLabels[i]);
        }
    
        mainPanel.add(questionPanel, BorderLayout.CENTER);
    
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 24));
        submitButton.addActionListener(new SubmitButtonListener());
        controlPanel.add(submitButton);
    
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 24));
        nextButton.addActionListener(new NextButtonListener());
        nextButton.setVisible(false);
        controlPanel.add(nextButton);
    
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 24));
        backButton.addActionListener(new BackButtonListener());
        backButton.setEnabled(false);
        controlPanel.add(backButton);
    
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
    
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showExplanation() {
        mainPanel.removeAll();

        explanationLabel = new JLabel("<html>Explanation " + questionNumber + ": " + "dud");
        explanationLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        explanationLabel.setForeground(Color.BLUE);
        mainPanel.add(explanationLabel, BorderLayout.CENTER);

        questionLabel = null;

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 24));
        nextButton.addActionListener(new NextButtonListener());
        controlPanel.add(nextButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 24));
        backButton.addActionListener(new BackButtonListener());
        controlPanel.add(backButton);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        mainPanel.revalidate();
        mainPanel.repaint();

        if (questionNumber == 10) {
            isInLastExplanation = true;
        } else {
            isInLastExplanation = false;
        }
    }

    private void showSummary() {
        mainPanel.removeAll();

        int correctAnswers = 0;
        for (boolean correct : isAnswerCorrect) {
            if (correct) correctAnswers++;
        }
        double gradePercentage = ((double) correctAnswers / 10) * 100;

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 10; i++) {
            JLabel resultLabel = new JLabel("Question " + (i + 1) + ": " + (isAnswerCorrect[i] ? "Correct" : "Incorrect"));
            resultLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            summaryPanel.add(resultLabel);
        }

        JLabel gradeLabel = new JLabel("Final Grade: " + gradePercentage + "%");
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        summaryPanel.add(gradeLabel);

        mainPanel.add(summaryPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton retakeQuizButton = new JButton("Retake Quiz");
        retakeQuizButton.setFont(new Font("Arial", Font.BOLD, 24));
        retakeQuizButton.addActionListener(e -> {
            questionNumber = 1;
            showQuestion();
        });
        controlPanel.add(retakeQuizButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 24));
        backButton.addActionListener(new BackButtonListener());
        controlPanel.add(backButton);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = -1;
            for (int i = 0; i < choiceButtons.length; i++) {
                if (choiceButtons[i].isSelected()) {
                    selectedIndex = i;
                    break;
                }
            }

            if (selectedIndex == correctAnswerIndices[questionNumber - 1]) {
                feedbackLabels[selectedIndex].setText("Correct!");
                feedbackLabels[selectedIndex].setForeground(Color.GREEN);
                isAnswerCorrect[questionNumber - 1] = true;
            } else {
                feedbackLabels[selectedIndex].setText("Incorrect!");
                feedbackLabels[selectedIndex].setForeground(Color.RED);
                feedbackLabels[correctAnswerIndices[questionNumber - 1]].setText("Correct answer");
                feedbackLabels[correctAnswerIndices[questionNumber - 1]].setForeground(Color.GREEN);
                isAnswerCorrect[questionNumber - 1] = false;
            }
            submitButton.setEnabled(false);
            nextButton.setVisible(true);
            backButton.setEnabled(true);
        }
    }

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (questionLabel != null) {
                showExplanation();
                backButton.setEnabled(true);
            } else if (isInLastExplanation) {
                showSummary();
                isInLastExplanation = false;
            } else {
                questionNumber++;
                if (questionNumber <= 10) {
                    showQuestion();
                } else {
                    showSummary();
                }
            }
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isInSummary) {
                questionNumber = 10;
                isInSummary = false;
                showExplanation();
            } else if (explanationLabel != null) {
                showQuestion();
            } else if (questionNumber > 1) {
                questionNumber--;
                showExplanation();
            } else {
                // Implement the logic for going back to the question selection screen.
                // For example, you can create a new method called `showQuestionSelection()`
                // and call it here to display a list of questions to choose from.
            }
            mainPanel.revalidate();
            mainPanel.repaint();

            if (questionNumber == 10) {
                nextButton.setVisible(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EducationalModel());
    }
}
