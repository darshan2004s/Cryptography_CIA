public class AutoclaveCipher {

    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = (key + text).toUpperCase(); 
        int keyIndex = 0;

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int shift = key.charAt(keyIndex) - 'A';
                result.append((char) ((Character.toUpperCase(ch) - 'A' + shift) % 26 + base));
                keyIndex++;
            } else {
                result.append(ch); 
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        StringBuilder fullKey = new StringBuilder(key.toUpperCase());
        int keyIndex = 0;

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int shift = fullKey.charAt(keyIndex) - 'A';
                int plainChar = (Character.toUpperCase(ch) - 'A' - shift + 26) % 26;
                char decrypted = (char) (plainChar + base);
                result.append(decrypted);
                fullKey.append(Character.toUpperCase(decrypted)); 
                keyIndex++;
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String plaintext = "Autoclave Cipher";
        String key = "KEY";

        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Original  : " + plaintext);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}
