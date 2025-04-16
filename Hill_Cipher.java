import java.util.*;

public class HillCipher {

    private static int[][] matrixInverse(int[][] matrix, int mod) {
        int n = matrix.length;
        int det = determinant(matrix, mod);
        int detInv = modInverse(det, mod);

        int[][] adj = new int[n][n];
        adjugate(matrix, adj, mod);

        int[][] inverse = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = (adj[i][j] * detInv) % mod;
            }
        }

        return inverse;
    }

    private static void adjugate(int[][] matrix, int[][] adj, int mod) {
        int n = matrix.length;
        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                getCofactor(matrix, temp, i, j, n, mod);
                adj[j][i] = (mod + (determinant(temp, mod) % mod)) % mod;
            }
        }
    }

    private static void getCofactor(int[][] matrix, int[][] temp, int p, int q, int n, int mod) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = matrix[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    private static int determinant(int[][] matrix, int mod) {
        int n = matrix.length;
        int det = 0;

        if (n == 1) {
            return matrix[0][0];
        }

        int[][] temp = new int[n][n];
        int sign = 1;

        for (int f = 0; f < n; f++) {
            getCofactor(matrix, temp, 0, f, n, mod);
            det = (det + sign * matrix[0][f] * determinant(temp, mod)) % mod;
            sign = -sign;
        }

        return det;
    }

    private static int modInverse(int a, int mod) {
        a = a % mod;
        for (int x = 1; x < mod; x++) {
            if ((a * x) % mod == 1) {
                return x;
            }
        }
        return 1;
    }

    private static int[] textToMatrix(String text) {
        int[] matrix = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            matrix[i] = text.charAt(i) - 'A';
        }
        return matrix;
    }

    private static String matrixToText(int[] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i : matrix) {
            sb.append((char) (i + 'A'));
        }
        return sb.toString();
    }

    public static String encrypt(String plaintext, int[][] keyMatrix) {
        int n = keyMatrix.length;
        int[] textMatrix = textToMatrix(plaintext);
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < textMatrix.length; i += n) {
            int[] cipherMatrix = new int[n];
            for (int j = 0; j < n; j++) {
                cipherMatrix[j] = 0;
                for (int k = 0; k < n; k++) {
                    cipherMatrix[j] += keyMatrix[j][k] * textMatrix[i + k];
                }
                cipherMatrix[j] %= 26;
            }
            encryptedText.append(matrixToText(cipherMatrix));
        }
        return encryptedText.toString();
    }

    public static String decrypt(String ciphertext, int[][] keyMatrix) {
        int n = keyMatrix.length;
        int[][] keyMatrixInverse = matrixInverse(keyMatrix, 26);
        int[] textMatrix = textToMatrix(ciphertext);
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < textMatrix.length; i += n) {
            int[] plainMatrix = new int[n];
            for (int j = 0; j < n; j++) {
                plainMatrix[j] = 0;
                for (int k = 0; k < n; k++) {
                    plainMatrix[j] += keyMatrixInverse[j][k] * textMatrix[i + k];
                }
                plainMatrix[j] = (plainMatrix[j] + 26) % 26;
            }
            decryptedText.append(matrixToText(plainMatrix));
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String plaintext = "HELLO";
        int[][] keyMatrix = {
                {6, 24, 1},
                {13, 16, 10},
                {20, 17, 15}
        };

        String encrypted = encrypt(plaintext, keyMatrix);
        String decrypted = decrypt(encrypted, keyMatrix);

        System.out.println("Original  : " + plaintext);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}
