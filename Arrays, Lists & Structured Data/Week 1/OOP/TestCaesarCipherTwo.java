import edu.duke.*;

public class TestCaesarCipherTwo {
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
        int key = maxIndex(freqs);
        
        return key;
    }
    
    public void breakCaesarCipher(String encrypted){
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        
        System.out.println("Key1: " + key1 + " Key2: " + key2);
        
        String decrypted = cc.decrypt(encrypted);
        
        System.out.println("Decrypted Message: " + decrypted);
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        
        // Encrypted
        String encrypted = cc.encrypt(message);
        System.out.println("Encrypted: " + encrypted);
        
        // Decrypted
        breakCaesarCipher(encrypted);
    }
}
