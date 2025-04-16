import java.util.ArrayList;
import java.util.List;

public class NgramOperations {

    public static List<String> generateNGrams(String text, int n) {
        List<String> nGrams = new ArrayList<>();
        text = text.replaceAll("[^a-zA-Z]", "").toUpperCase();  // Remove non-alphabetic characters and convert to uppercase

        for (int i = 0; i <= text.length() - n; i++) {
            nGrams.add(text.substring(i, i + n));
        }

        return nGrams;
    }

    public static void main(String[] args) {
        String text = "Ngram Operations";
        int n = 3;  // Set the value of n for N-grams

        List<String> nGrams = generateNGrams(text, n);

        System.out.println("Original Text : " + text);
        System.out.println("N-Grams: " + nGrams);
    }
}
