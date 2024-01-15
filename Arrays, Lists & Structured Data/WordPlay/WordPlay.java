import edu.duke.*;

public class WordPlay {
    public boolean isVowel(char ch){
        String vowels = "aeiou";
        char lch = Character.toLowerCase(ch);
        String StrCh = String.valueOf(lch);
        return vowels.contains(StrCh);
    }
    
    public void testIsVowel(){
        System.out.println("Is your letter a vowel? " + isVowel('I'));
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder replacedString = new StringBuilder(phrase);
        for(int i=0; i < phrase.length(); i++){
            char currChar = phrase.charAt(i);
            if(isVowel(currChar)){
                replacedString.setCharAt(i, ch);
            }
        }
        return replacedString.toString();
    }
    
    public void testReplaceVowels(){
        System.out.println("Is your letter a vowel? " + replaceVowels("Hello, My name is Omar", 'x'));
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder emph = new StringBuilder(phrase);
        for(int i=0; i < phrase.length(); i++){
            char currChar = phrase.charAt(i);
            if(Character.toLowerCase(currChar) == Character.toLowerCase(ch)){
                if(i % 2 != 0){
                    emph.setCharAt(i, '+');
                } else {
                    emph.setCharAt(i, '*');
                }
            }
        }
        return emph.toString();
    }
    
    public void testEmphasize(){
        System.out.println("Emphasize: " + emphasize("dna ctgaaactga", 'a'));
    }
}