public class AtbashCipher {

    public static String atbash(String input) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                result.append((char) ('Z' - (ch - 'A')));
            } else if (Character.isLowerCase(ch)) {
                result.append((char) ('z' - (ch - 'a')));
            } else {
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
