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
        String encrypted = fr.asString();
        FileResource dictResource = new FileResource("./dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictResource);
        
        int maxKeyLength = 100; // Set the maximum key length
        int bestKeyLength = 0;
        int maxWordCount = 0;
        String firstLine = null;
        
        for (int i = 1; i <= maxKeyLength; i++) {
            int[] keyLength = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(keyLength);
            String decrypted = vc.decrypt(encrypted);
            int wordCount = countWords(decrypted, dictionary);

            if (wordCount > maxWordCount) {
                maxWordCount = wordCount;
                bestKeyLength = i;
                firstLine = decrypted.split("\\r?\\n")[0];
            }
        }
        
        String decrypt = breakForLanguage(encrypted, dictionary);
        System.out.println("Decrypted Message: " + decrypt);
        int count = countWords(decrypt, dictionary);
        System.out.println("Valid words: " + count);
        System.out.println("Best key length: " + bestKeyLength);
        System.out.println(firstLine);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        
        for(String line : fr.lines()){
            line = line.toLowerCase();
            hs.add(line);
        }
        
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] splitWords = message.split("\\W+");
        int count = 0;
        for(String word : splitWords){
            word = word.toLowerCase();
            if(dictionary.contains(word)){
                count++;
            }
        }
        
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int maxCount = 0;
        String bestDecryption = "";
        int key = 0;
        for(int i=1; i <= 100; i++){
            int[] keyLength = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(keyLength);
            String decrypt = vc.decrypt(encrypted);
            int wordCount = countWords(decrypt, dictionary);
            
            if (wordCount > maxCount) {
                maxCount = wordCount;
                bestDecryption = decrypt;
                key = i;
            }
        }
        System.out.println("Key Length: " + key);
        return bestDecryption;
    }
    
    public void test(){
        
        // Read the contents of secretmessage2.txt into a String
        FileResource fr = new FileResource("./messages/secretmessage2.txt");
        String encrypted = fr.asString();

        // Create a HashSet of Strings containing valid English words
        FileResource dictResource = new FileResource("./dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictResource);

        // Use tryKeyLength to decrypt the message
        int[] key = tryKeyLength(encrypted, 38, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);

        // Count the number of valid words in the decrypted message
        int validWordCount = countWords(decrypted, dictionary);

        // Print the count of valid words
        System.out.println("Number of valid words: " + validWordCount);
    }
    
}
