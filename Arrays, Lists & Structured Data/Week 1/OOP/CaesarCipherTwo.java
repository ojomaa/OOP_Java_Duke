import edu.duke.*;

public class CaesarCipherTwo {
    private String alphabet;
    private String shifted1;
    private String shifted2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shifted1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shifted2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i=0; i<input.length(); i++){
            char currChar = input.charAt(i);
            int indx = alphabet.indexOf(Character.toLowerCase(currChar));
            String encryptAlphabet = (i % 2 == 0) ? shifted1 : shifted2;
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
    
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26-mainKey2);
        return cc.encrypt(input);
    }
}
