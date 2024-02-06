import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        // Create initial StringBuilder
        StringBuilder sb = new StringBuilder();
        
        //Loop over the message
        for(int i= whichSlice; i < message.length(); i+= totalSlices){
            // add character to the String
            char ch = message.charAt(i);
            sb.append(ch);
        }
        
        // return StringBuilder.toString()
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i=0; i < klength; i++){
            String sliced = sliceString(encrypted, i, klength);
            int tempKey = cc.getKey(sliced);
            key[i] = tempKey;
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String contents = fr.asString();
        int[] keyLength = tryKeyLength(contents, 4, 'e');
        VigenereCipher vc = new VigenereCipher(keyLength);
        String decrypt = vc.decrypt(contents);
        System.out.println("Decrypted Message: " + decrypt);
    }
    
    public void test(){
        FileResource fr = new FileResource();
        String contents = fr.asString();
        int[] keyLength = tryKeyLength(contents, 4, 'e');
        System.out.print("Key Length: ");
        for (int i = 0; i < keyLength.length; i++) {
            System.out.print(keyLength[i] + " ");
        }
        System.out.println();
    }
}
