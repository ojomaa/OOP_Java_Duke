import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> WordMap;
    
    public WordsInFiles(){
        WordMap = new HashMap<>();
    }
    
    private void addWordsFromFile(File f ){
        FileResource fr = new FileResource(f);
        String filename = f.getName();
        for(String word : fr.words()){
            if(WordMap.containsKey(word)){
                ArrayList<String> filenames = WordMap.get(word);
                
                if (!filenames.contains(filename)) {
                    filenames.add(filename);
                }
                
            }
            else{
                ArrayList<String> filenames = new ArrayList<>();
                filenames.add(filename);
                WordMap.put(word, filenames);
            }
        }
    }
    
    public void buildWordFileMap(){
        WordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        buildWordFileMap();
        int num = 0;
        for(Map.Entry<String, ArrayList<String>> entry : WordMap.entrySet()){
            ArrayList<String> value = entry.getValue();
            int temp = value.size();
            if(temp > num){
                num = temp;
            }
        }
        
        return num;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        buildWordFileMap();
        ArrayList<String> words = new ArrayList<>();
        for(Map.Entry<String, ArrayList<String>> entry : WordMap.entrySet()){
            ArrayList<String> value = entry.getValue();
            String word = entry.getKey();
            if(value.size() == number){
                words.add(word);
            }
        }
        
        return words;
    }
    
    public void printFilesIn(String word){
        buildWordFileMap();
        ArrayList<String> filenames = WordMap.get(word);
    
        if (filenames != null) {
            for (String filename : filenames) {
                System.out.println(filename);
            }
        } else {
            System.out.println("The word '" + word + "' is not found in any files.");
        }
    }
    
    public void tester() {
        buildWordFileMap();

        // Print the complete map (optional, uncomment if needed)
        //printWordFileMap();

        //int maxNumber = maxNumber();
        //System.out.println("Maximum number of files any word is in: " + maxNumber);

        //System.out.println("Words in the number of files (" + 4 + "): " + "num: " + wordsInNumFiles(4).size() + " words: " + wordsInNumFiles(4));
        /*
        for (String word : wordsInMaxFiles) {
            System.out.println("Files containing the word '" + word + "':");
            printFilesIn(word);
            System.out.println();
        }
        */
        System.out.println("Words in files: " + WordMap.get("red"));
    }

    // Helper method to print the complete WordMap (optional)
    private void printWordFileMap() {
        for (Map.Entry<String, ArrayList<String>> entry : WordMap.entrySet()) {
            String word = "red";
            ArrayList<String> filenames = entry.getValue();
            System.out.println("Word: " + word + ", Filenames: " + filenames);
        }
    }
}
