public class RailFenceCipher {

    public static String encrypt(String text, int rails) {
        char[][] rail = new char[rails][text.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        int row = 0;
        boolean down = false;
        for (int i = 0; i < text.length(); i++) {
            rail[row][i] = text.charAt(i);
            if (row == 0 || row == rails - 1) {
                down = !down;
            }
            row = down ? row + 1 : row - 1;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (rail[i][j] != '\n') {
                    result.append(rail[i][j]);
                }
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int rails) {
        char[][] rail = new char[rails][text.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        int row = 0;
        boolean down = false;
        for (int i = 0; i < text.length(); i++) {
            rail[row][i] = '*';
            if (row == 0 || row == rails - 1) {
                down = !down;
            }
            row = down ? row + 1 : row - 1;
        }

        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (rail[i][j] == '*' && index < text.length()) {
                    rail[i][j] = text.charAt(index++);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        row = 0;
        down = false;
        for (int i = 0; i < text.length(); i++) {
            result.append(rail[row][i]);
            if (row == 0 || row == rails - 1) {
                down = !down;
            }
            row = down ? row + 1 : row - 1;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String plaintext = "RAILFENCECIPHER";
        int rails = 3;

        String encrypted = encrypt(plaintext, rails);
        String decrypted = decrypt(encrypted, rails);

        System.out.println("Original  : " + plaintext);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}
