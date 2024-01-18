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
        /**
         * Write the method decryptTwoKeys in the CaesarBreaker class that has one parameter,
         * a String parameter named encrypted that represents a String that was encrypted
         * with the two key algorithm discussed in the previous lesson.
         * This method attempts to determine the two keys used to encrypt the message,
         * prints the two keys, and then returns the decrypted String with those two keys.
         * More specifically, this method should:

        - Calculate a String of every other character starting with the first character
        of the encrypted String by calling halfOfString. 

        - Calculate a String of every other character starting with the second character
        of the encrypted String. 

        - Then calculate the key used to encrypt each half String.

        - You should print the two keys found.

        - Calculate and return the decrypted String using the encryptTwoKeys method
        from your CaesarCipher class, again making sure it is in the
        same folder as your CaesarBreaker class.
         */
    }
}
