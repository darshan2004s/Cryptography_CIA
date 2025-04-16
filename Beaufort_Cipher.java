public class BeaufortCipher {

    public static String encryptDecrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int charIndex = Character.toUpperCase(ch) - 'A';
                int keyCharIndex = key.charAt(keyIndex % key.length()) - 'A';
                int cipherIndex = (keyCharIndex - charIndex + 26) % 26;
                result.append((char) (cipherIndex + base));
                keyIndex++;
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String plaintext = "Beaufort Cipher";
        String key = "KEY";

        String encrypted = encryptDecrypt(plaintext, key);
        String decrypted = encryptDecrypt(encrypted, key);

        System.out.println("Original  : " + plaintext);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}
