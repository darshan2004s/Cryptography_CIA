public class AtbashCipher {

    // Applies the Atbash cipher to the input string
    public static String atbash(String input) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                // For uppercase letters: 'A' <-> 'Z', 'B' <-> 'Y', ...
                result.append((char) ('Z' - (ch - 'A')));
            } else if (Character.isLowerCase(ch)) {
                // For lowercase letters: 'a' <-> 'z', 'b' <-> 'y', ...
                result.append((char) ('z' - (ch - 'a')));
            } else {
                // Preserve non-alphabetic characters
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String plainText = "Hello World!";
        String cipherText = atbash(plainText);

        System.out.println("Original : " + plainText);
        System.out.println("Atbash   : " + cipherText);
    }
}
