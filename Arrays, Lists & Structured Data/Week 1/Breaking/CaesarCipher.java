import edu.duke.*;

public class CaesarCipher {
    
    public String encrypt(String input, int key){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String encryptAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i=0; i<input.length(); i++){
            char currChar = input.charAt(i);
            int indx = alphabet.indexOf(Character.toLowerCase(currChar));
            
            if (indx != -1) {
                char encrChar = encryptAlphabet.charAt(indx);

                if (Character.isUpperCase(currChar)) {
                    encrChar = Character.toUpperCase(encrChar);
                }
                encrypted.setCharAt(i, encrChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testEncrypt(){
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Original Message: " + message);
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder encrypted = new StringBuilder(input);
        String encryptAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String encryptAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for(int i=0; i<input.length(); i++){
            char currChar = input.charAt(i);
            int indx = alphabet.indexOf(Character.toLowerCase(currChar));
            String encryptAlphabet = (i % 2 == 0) ? encryptAlphabet1 : encryptAlphabet2;
            if (indx != -1) {
                char encrChar = encryptAlphabet.charAt(indx);

                if (Character.isUpperCase(currChar)) {
                    encrChar = Character.toUpperCase(encrChar);
                }
                encrypted.setCharAt(i, encrChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys(){
        int key1 = 8;
        int key2 = 21;
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Original Message: " + message);
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("keys are " + key1 + " " + key2 + "\n" + encrypted);
    }
}
