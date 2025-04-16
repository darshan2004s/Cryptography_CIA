public class AugustCipher {

    // Encrypts by shifting each alphabet character one position forward
    public static String encrypt(String input) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                result.append((char) ((ch - 'A' + 1) % 26 + 'A'));
            } else if (Character.isLowerCase(ch)) {
                result.append((char) ((ch - 'a' + 1) % 26 + 'a'));
            } else {
                result.append(ch); // Preserve non-alphabetic characters
            }
        }

        return result.toString();
    }

    // Decrypts by shifting each alphabet character one position backward
    public static String decrypt(String input) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                result.append((char) ((ch - 'A' - 1 + 26) % 26 + 'A'));
            } else if (Character.isLowerCase(ch)) {
                result.append((char) ((ch - 'a' - 1 + 26) % 26 + 'a'));
            } else {
                result.append(ch); // Preserve non-alphabetic characters
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String plainText = "August Cipher!";
        String encrypted = encrypt(plainText);
        String decrypted = decrypt(encrypted);

        System.out.println("Original  : " + plainText);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}
