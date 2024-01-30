import edu.duke.*;
import java.util.*;
public class CharactersInPlay {
    
    ArrayList<String> names;
    ArrayList<Integer> count;
    
    public CharactersInPlay() {
        names = new ArrayList<>();
        count = new ArrayList<>();
    }
    
    private void update(String person){
        
        if(! names.contains(person)){
            names.add(person);
            count.add(1);
        } else{
            int indx = names.indexOf(person);
            int freqs = count.get(indx);
            count.set(indx, freqs + 1);
        }
    }
    
    public void findAllCharacters(){
        names.clear();
        count.clear();
        FileResource fr = new FileResource();
        
        for(String line : fr.lines()){
            String possibleName = extractName(line);

            if (possibleName != null) {
                update(possibleName);
            }
        }
    }
    
    private String extractName(String line) {
        int periodIndex = line.indexOf(".");
        if (periodIndex != -1) {
            return line.substring(0, periodIndex).trim();
        }
        return null;
    }
    
    public void charactersWithNumParts(int num1, int num2){
        System.out.println("Characters with Speaking Parts between " + num1 + " and " + num2 + ":");
        for (int i = 0; i < names.size(); i++) {
            String character = names.get(i);
            int speakingParts = count.get(i);

            // Check if the character has speaking parts within the specified range
            if (speakingParts >= num1 && speakingParts <= num2) {
                System.out.println(character + ": " + speakingParts + " speaking parts");
            }
        }
    }
    
    public void tester() {

        findAllCharacters();
        int speakingPartsThreshold = 60;
        
        /*
        System.out.println("Main Characters with More than " + speakingPartsThreshold + " Speaking Parts:");
        
        for (int i = 0; i < names.size(); i++) {
            String character = names.get(i);
            int speakingParts = count.get(i);

            if (speakingParts > speakingPartsThreshold) {
                System.out.println(character + ": " + speakingParts + " speaking parts");
            }
        }
        */
        charactersWithNumParts(10,15);
    }
}


