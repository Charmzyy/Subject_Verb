import java.util.Scanner;

public class BottomUpParser {
    public static void main(String[] args) {
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        // Read the user input as a sentence
        String sentence = scanner.nextLine();

        // Split the sentence into words
        String[] chart = sentence.split(" ");

        // Check if the entered sentence is grammatically correct
        if (parse(chart)) {
            System.out.println("Sentence is grammatically correct.");
        } else {
            System.out.println("Sentence violates grammar rules.");
        }
    }

    // The parse function checks whether the sentence is grammatically correct
    private static boolean parse(String[] chart) {
        // Get the number of words in the sentence
        int chartSize = chart.length;

        // Create a parse chart to store intermediate parsing results
        String[][] parseChart = new String[chartSize][chartSize];

        // Initialize the diagonal entries of the parse chart with individual words
        for (int i = 0; i < chartSize; i++) {
            parseChart[i][i] = chart[i];
        }

        // Bottom-up parsing logic
        for (int span = 2; span <= chartSize; span++) {
            for (int start = 0; start <= chartSize - span; start++) {
                int end = start + span - 1;

                for (int split = start; split < end; split++) {
                    // Apply grammar rules to determine if a valid sentence can be formed
                    if (applyRules(parseChart, start, split, end)) {
                        parseChart[start][end] = "S"; // Mark the span as a complete sentence
                        break;
                    }
                }
            }
        }

        // Check if the entire sentence is a valid sentence
        return parseChart[0][chartSize - 1] != null && parseChart[0][chartSize - 1].equals("S");
    }

    // The applyRules function to determine whether a specific span of words meet grammar rules
    private static boolean applyRules(String[][] parseChart, int start, int split, int end) {
        
        if (parseChart[start][split] != null && parseChart[split + 1][end] != null) {
            parseChart[start][end] = "S"; // Marks the span as a complete sentence
            return true;
        }

        return false;
    }
}
