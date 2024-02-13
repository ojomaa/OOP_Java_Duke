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
        
        HashMap<String, HashSet<String>> dictionaries = new HashMap<>();

        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for (String language : languages) {
            FileResource dictResource = new FileResource("./dictionaries/" + language);
            HashSet<String> dictionary = readDictionary(dictResource);
            dictionaries.put(language, dictionary);
            System.out.println("Dictionary for " + language + " has been loaded.");
        }

        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        breakForAllLangs(encrypted, dictionaries);
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
        char mostCommonChar = mostCommonCharIn(dictionary);
        int bestKeyLength = 0;

        for (int i = 1; i <= 100; i++) {
            int[] keyLength = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keyLength);
            String decrypt = vc.decrypt(encrypted);
            int wordCount = countWords(decrypt, dictionary);

            if (wordCount > maxCount) {
                maxCount = wordCount;
                bestDecryption = decrypt;
                bestKeyLength = i;
            }
        }
    
        System.out.println("Best key length: " + bestKeyLength);
        return bestDecryption;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        char mostCommonLetter = ' ';
        HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
        int count = 0;
        for(String word : dictionary){
            for(int i=0; i< word.length(); i++){
                char letter = word.charAt(i);
                if(!letters.containsKey(letter)){
                    letters.put(letter, 1);
                }
                else{
                    letters.put(letter, letters.get(letter) + 1);
                }
            }
        }
        
        for( char letter : letters.keySet()){
            if(letters.get(letter) > count){
                mostCommonLetter = letter;
                count = letters.get(letter);
            }
        }
        
        return mostCommonLetter;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxWords = 0;
        String decryptedMessage = "";
        HashMap<String, Integer> langsMostWords = new HashMap<String, Integer>();
        for(String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            decryptedMessage = breakForLanguage(encrypted, dictionary);
            int count = countWords(decryptedMessage, dictionary);
            langsMostWords.put(language, count);
        }
        for(String lang : langsMostWords.keySet()) {
            if(langsMostWords.get(lang) > maxWords) {
                maxWords = langsMostWords.get(lang);
                System.out.println(decryptedMessage);
                System.out.println("Language: " + lang + " with " + maxWords + " # of words\n");
            }
        }
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
