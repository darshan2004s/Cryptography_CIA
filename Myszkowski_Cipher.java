import java.util.*;

public class MyszkowskiCipher {

    public static String encrypt(String text, String key) {
        int[] keyArray = createKeyArray(key);
        int columns = key.length();
        int rows = (int) Math.ceil((double) text.length() / columns);

        while (text.length() < rows * columns) {
            text += 'X';
        }

        char[][] matrix = new char[rows][columns];
        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = text.charAt(index++);
            }
        }

        StringBuilder encrypted = new StringBuilder();

        for (int i : keyArray) {
            for (int j = 0; j < rows; j++) {
                encrypted.append(matrix[j][i]);
            }
        }

        return encrypted.toString();
    }

    public static String decrypt(String text, String key) {
        int[] keyArray = createKeyArray(key);
        int columns = key.length();
        int rows = text.length() / columns;

        char[][] matrix = new char[rows][columns];
        int index = 0;

        for (int i : keyArray) {
            for (int j = 0; j < rows; j++) {
                matrix[j][i] = text.charAt(index++);
            }
        }

        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                decrypted.append(matrix[i][j]);
            }
        }

        return decrypted.toString().replaceAll("X", "");
    }

    private static int[] createKeyArray(String key) {
        int[] keyArray = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyArray[i] = key.charAt(i) - 'A';
        }

        Integer[] sortedIndexes = new Integer[key.length()];
        for (int i = 0; i < key.length(); i++) {
            sortedIndexes[i] = i;
        }

        Arrays.sort(sortedIndexes, (a, b) -> Character.compare(key.charAt(a), key.charAt(b)));

        int[] sortedKeyArray = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            sortedKeyArray[i] = sortedIndexes[i];
        }

        return sortedKeyArray;
    }

    public static void main(String[] args) {
        String plaintext = "MYSZKOWSKICIPHEREXAMPLE";
        String key = "ZEBRAS";

        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Original  : " + plaintext);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}
