import java.io.*;
import java.util.*;

public class QuizGame {

    static class Question {
        String question;
        String[] options;
        int correctOption;

        public Question(String question, String[] options, int correctOption) {
            this.question = question;
            this.options = options;
            this.correctOption = correctOption;
        }

        public boolean isCorrect(int userAnswer) {
            return userAnswer == correctOption;
        }
    }

    private static final String DATA_FILE = "user_data.txt";
    private static final List<Question> QUESTIONS = Arrays.asList(
            new Question("What is the size of int in Java?", new String[]{"1 byte", "2 bytes", "4 bytes", "8 bytes"}, 3),
            new Question("Which keyword is used to inherit a class in Java?", new String[]{"extend", "extends", "implements", "inherit"}, 2),
            new Question("What does JVM stand for?", new String[]{"Java Virtual Machine", "Java Variable Machine", "Java Verified Machine", "None of these"}, 1),
            new Question("Which method is the entry point for a Java program?", new String[]{"start()", "begin()", "main()", "run()"}, 3),
            new Question("Which operator is used for addition in Java?", new String[]{"+", "-", "*", "/"}, 1),
            new Question("What is the default value of a boolean variable?", new String[]{"true", "false", "0", "null"}, 2),
            new Question("Which keyword is used to define a constant in Java?", new String[]{"static", "final", "const", "constant"}, 2),
            new Question("What does OOP stand for?", new String[]{"Object-Oriented Programming", "Online Object Programming", "Object-Oriented Procedure", "None of these"}, 1),
            new Question("Which of these is a reserved keyword in Java?", new String[]{"goto", "constant", "null", "main"}, 1),
            new Question("Which exception is thrown when a dividing by zero occurs?", new String[]{"ArithmeticException", "NullPointerException", "NumberFormatException", "ClassCastException"}, 1)
    );

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(YELLOW + "Welcome to the Java Quiz Game!" + RESET);

        System.out.print(BLUE + "Enter your name: " + RESET);
        String playerName = scanner.nextLine();

        System.out.println(YELLOW + "\nInstructions: \n- You will be asked 10 questions.\n- Each correct answer awards 10 points.\n- Good luck!\n" + RESET);

        int score = 0;

        for (int i = 0; i < QUESTIONS.size(); i++) {
            Question q = QUESTIONS.get(i);
            System.out.println(BLUE + (i + 1) + ". " + q.question + RESET);
            for (int j = 0; j < q.options.length; j++) {
                System.out.println(BLUE + (j + 1) + ". " + q.options[j] + RESET);
            }

            int userAnswer;
            do {
                System.out.print(BLUE + "Your answer (1-4): " + RESET);
                while (!scanner.hasNextInt()) {
                    System.out.print(RED + "Please enter a valid option (1-4): " + RESET);
                    scanner.next();
                }
                userAnswer = scanner.nextInt();
            } while (userAnswer < 1 || userAnswer > 4);

            if (q.isCorrect(userAnswer)) {
                System.out.println(GREEN + "Correct!\n" + RESET);
                score += 10;
            } else {
                System.out.println(RED + "Wrong! The correct answer was: " + q.correctOption + "\n" + RESET);
            }
        }

        System.out.println(YELLOW + "Quiz Over!\n" + playerName + ", your final score is: " + score + "/100\n" + RESET);

        saveUserData(playerName, score);

        System.out.println(YELLOW + "Thank you for playing! Your result has been saved." + RESET);
    }

    private static void saveUserData(String playerName, int score) {
        try (FileWriter writer = new FileWriter(DATA_FILE, true)) {
            writer.write(playerName + "," + score + "," + new Date() + "\n");
        } catch (IOException e) {
            System.out.println(RED + "An error occurred while saving your data." + RESET);
        }
    }
}
