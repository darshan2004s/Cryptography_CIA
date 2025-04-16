public class RouteCipher {

    public static String encrypt(String text, int rows, int cols) {
        char[][] matrix = new char[rows][cols];
        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = index < text.length() ? text.charAt(index++) : 'X';
            }
        }

        StringBuilder encrypted = new StringBuilder();

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                encrypted.append(matrix[i][j]);
            }
        }

        return encrypted.toString();
    }

    public static String decrypt(String text, int rows, int cols) {
        char[][] matrix = new char[rows][cols];
        int index = 0;

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                matrix[i][j] = text.charAt(index++);
            }
        }

        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                decrypted.append(matrix[i][j]);
            }
        }

        return decrypted.toString().replaceAll("X", "");
    }

    public static void main(String[] args) {
        String plaintext = "ROUTECIPHEREXAMPLE";
        int rows = 4;
        int cols = 5;

        String encrypted = encrypt(plaintext, rows, cols);
        String decrypted = decrypt(encrypted, rows, cols);

        System.out.println("Original  : " + plaintext);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}
