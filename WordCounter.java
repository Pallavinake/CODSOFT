import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Word Counting Program");
        System.out.println("1. Enter '1' to input text");
        System.out.println("2. Enter '2' to input a file");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        
        String inputText = "";
        
        switch (choice) {
            case 1:
                System.out.print("Enter the text: ");
                inputText = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter the file path: ");
                String filePath = scanner.nextLine();
                try {
                    inputText = readFile(filePath);
                } catch (IOException e) {
                    System.out.println("Error reading the file.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        // Split the input text into words
        String[] words = inputText.split("[\\s\\p{Punct}]+");
        
        // Initialize counters and data structures
        int totalWordCount = words.length;
        Map<String, Integer> wordFrequency = new HashMap<>();
        
        // Count word frequency
        for (String word : words) {
            String lowercaseWord = word.toLowerCase();
            if (!isStopWord(lowercaseWord)) {
                wordFrequency.put(lowercaseWord, wordFrequency.getOrDefault(lowercaseWord, 0) + 1);
            }
        }
        
        // Display total word count
        System.out.println("Total word count: " + totalWordCount);
        
        // Display unique word count
        System.out.println("Unique word count: " + wordFrequency.size());
        
        // Display word frequency statistics
        System.out.println("Word frequency statistics:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    private static boolean isStopWord(String word) {
        // Define a list of common stop words
        String[] stopWords = {"a", "an", "and", "the", "in", "on", "of", "to", "for", "with"};
        return Arrays.asList(stopWords).contains(word);
    }
    
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }
}
