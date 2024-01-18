import edu.duke.*;
import java.lang.Math;

public class CaesarBreaker {
    
    public int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabet.indexOf(ch);
            if (dex != -1){ 
                counts[dex]++;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals){
        int maxDex=0;
        for(int k=0; k < vals.length; k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k; 
            }
        }
        return maxDex;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int key = getKey(encrypted);
        return cc.encrypt(encrypted, 26-key);
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String originalMessage = fr.asString();
        System.out.println("Original Message:\n" + originalMessage);

        int key = 15;

        // Encrypt the message
        CaesarCipher cc = new CaesarCipher();
        String encryptedMessage = cc.encrypt(originalMessage, key);
        System.out.println("Encrypted Message:\n" + encryptedMessage);

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage);
        System.out.println("Decrypted Message:\n" + decryptedMessage);
    }
    
    public String halfOfString(String message, int start){
        
        StringBuilder everyOther = new StringBuilder();
        
        for(int k=start; k<message.length();k+=2){
            char currChar = message.charAt(k);
            everyOther.append(currChar);
        }
        
        return everyOther.toString();
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int key = maxDex - 4;
        
        if(maxDex < 4){
            key = 26 - (4 - maxDex);
        }
        
        return key;
    }
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        
        System.out.println("Key1: " + key1 + " Key2: " + key2);
        
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }
    
    public void testdecryptTwoKeys(){
        FileResource fr = new FileResource();
        String originalMessage = fr.asString();
        System.out.println("Original Message:\n" + originalMessage);

        int key1 = 2;
        int key2 = 20;

        // Encrypt the message
        CaesarCipher cc = new CaesarCipher();
        String encryptedMessage = cc.encryptTwoKeys(originalMessage, key1, key2);
        System.out.println("Encrypted Message:\n" + encryptedMessage);

        // Decrypt the message
        String decryptedMessage = decryptTwoKeys(originalMessage);
        System.out.println("Decrypted Message:\n" + decryptedMessage);
    }
}
