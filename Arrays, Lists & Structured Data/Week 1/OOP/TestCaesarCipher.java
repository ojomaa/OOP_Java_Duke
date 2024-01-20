import edu.duke.*;

public class TestCaesarCipher {
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
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherOOP cc = new CaesarCipherOOP(18);
        
        // Encrypt
        String encrypted = cc.encrypt(message);
        System.out.println("Encrypted: " + encrypted);
        
        // Decrypt
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        // BreakCaesarCipher
        breakCaesarCipher(encrypted);
    }
    
    public void breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int key1 = maxIndex(freqs);

        CaesarCipherOOP cc = new CaesarCipherOOP(key1);
        String decrypted = cc.decrypt(input);
        
        System.out.println("Key: " + key1);
        System.out.println("Decrypted Message: " + decrypted);
    }
}
