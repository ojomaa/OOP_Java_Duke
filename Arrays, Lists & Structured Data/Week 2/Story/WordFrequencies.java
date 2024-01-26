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
    
    public int findIndexOfMax(){
        int largest = 0;
        int indx = 0;
        for(int i=0; i<myFreqs.size();i++){
            int val = myFreqs.get(i);
            if(val > largest){
                largest = val;
                indx = i;
            }
        }
        
        return indx;
    }
    
    public void tester(){
        findUnique();

        for (int i = 0; i < myWords.size(); i++) {
            String word = myWords.get(i);
            int freq = myFreqs.get(i);
            System.out.println(freq + "\t" + word);
        }
        int max = findIndexOfMax();
        System.out.println("Number of unique words: " + myWords.size());
        System.out.println("The word that occurs most often and its count are: " + myWords.get(max) + myFreqs.get(max));
    }
}
