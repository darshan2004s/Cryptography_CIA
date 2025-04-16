public class AffineCipher {

    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        throw new IllegalArgumentException("No modular inverse exists for a = " + a + " under mod " + m);
    }

    public static String encrypt(String input, int a, int b) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                int x = ch - 'A';
                int enc = (a * x + b) % 26;
                result.append((char) (enc + 'A'));
            } else if (Character.isLowerCase(ch)) {
                int x = ch - 'a';
                int enc = (a * x + b) % 26;
                result.append((char) (enc + 'a'));
            } else {
                result.append(ch); 
            }
        }

        return result.toString();
    }

    public static String decrypt(String input, int a, int b) {
        StringBuilder result = new StringBuilder();
        int a_inv = modInverse(a, 26);

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                int y = ch - 'A';
                int dec = (a_inv * (y - b + 26)) % 26;
                result.append((char) (dec + 'A'));
            } else if (Character.isLowerCase(ch)) {
                int y = ch - 'a';
                int dec = (a_inv * (y - b + 26)) % 26;
                result.append((char) (dec + 'a'));
            } else {
                result.append(ch); 
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String text = "Affine Cipher";
        int a = 5, b = 8; 

        String encrypted = encrypt(text, a, b);
        String decrypted = decrypt(encrypted, a, b);

        System.out.println("Original  : " + text);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}
