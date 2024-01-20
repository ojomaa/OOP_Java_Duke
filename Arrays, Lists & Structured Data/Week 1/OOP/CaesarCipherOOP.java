import edu.duke.*;

public class CaesarCipherOOP {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipherOOP(int key) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i=0; i<input.length(); i++){
            char currChar = input.charAt(i);
            int indx = alphabet.indexOf(Character.toLowerCase(currChar));
            
            if (indx != -1) {
                char encrChar = shiftedAlphabet.charAt(indx);

                if (Character.isUpperCase(currChar)) {
                    encrChar = Character.toUpperCase(encrChar);
                }
                encrypted.setCharAt(i, encrChar);
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherOOP cc = new CaesarCipherOOP(26 - mainKey);
        return cc.encrypt(input);
    }
    
}
