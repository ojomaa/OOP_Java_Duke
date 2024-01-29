import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> declared;
    private ArrayList<String> categoriesUsed;
    
    private Random myRandom;
    private int wordsReplaced;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        declared = new ArrayList<>();
        categoriesUsed = new ArrayList<>();
        wordsReplaced = 0;
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) { 
        String[] labels = {"adjective","animal","color","country","fruit","name","noun","timeframe","verb"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        ArrayList<String> category = myMap.get(label);
        
        if (!categoriesUsed.contains(label)) {
            categoriesUsed.add(label);
        }
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return randomFrom(category);
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while( declared.contains(prefix + sub + suffix) ){
            sub = getSubstitute(w.substring(first+1,last));
        }
        declared.add(prefix+sub+suffix);      
        wordsReplaced ++;
        return prefix + sub + suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap(){
        int totalWords = 0;
        for (Map.Entry<String, ArrayList<String>> entry : myMap.entrySet()) {
            ArrayList<String> words = entry.getValue();
            int size = words.size();
            totalWords += size;
        }
        
        return totalWords;
    }
    
    public int totalWordsConsidered(){
        int totalWords = 0;

        for (String category : categoriesUsed) {
            if (myMap.containsKey(category)) {
                totalWords += myMap.get(category).size();
            }
        }

        return totalWords;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nTotal Words Replaced: " + wordsReplaced);
        System.out.println("\nTotal Words in Map: " + totalWordsInMap());
        System.out.println("\nTotal Words Considered: " + totalWordsConsidered());
    }
    
}
