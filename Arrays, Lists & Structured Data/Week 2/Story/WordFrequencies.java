import edu.duke.*;
import java.util.*;
public class WordFrequencies {
    ArrayList<String> myWords;
    ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }
    
    public void findUnique(){
        FileResource fr = new FileResource();
        myWords.clear();
        myFreqs.clear();
        
        for( String word : fr.words()){
            word = word.toLowerCase();
            
            if(! myWords.contains(word)){
                myWords.add(word);
                myFreqs.add(1);
            } else{
                int indx = myWords.indexOf(word);
                int freqs = myFreqs.get(indx);
                myFreqs.set(indx, freqs + 1);
            }
        }
    }
    
    public void tester(){
        findUnique();

        System.out.println("Number of unique words: " + myWords.size());

        for (int i = 0; i < myWords.size(); i++) {
            String word = myWords.get(i);
            int freq = myFreqs.get(i);
            System.out.println(freq + "\t" + word);
        }
    }
}
