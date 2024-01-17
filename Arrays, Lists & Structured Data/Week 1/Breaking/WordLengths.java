import edu.duke.*;


public class WordLengths {
    
    public void countWordLengths(FileResource res, int[] counts){
        for(String word : res.words()){
            int wordLength = 0;
                
            for(int k=0; k<word.length();k++){
                char currentChar = word.charAt(k);
                    
                if(Character.isLetter(currentChar)){
                    wordLength++;
                }
            } 
            counts[wordLength] ++;
        }  
        
    }
    
    public int indexOfMax(int[] values){
        int maxValue = 0;
        int maxIndex = -1;
        for(int i=0; i<values.length; i++){
            if(values[i] > maxValue){
                maxValue = values[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[20];
        countWordLengths(fr, counts);
        int IOM = indexOfMax(counts);
        for(int i=0; i<counts.length; i++){
            System.out.println("Length " + i + ": " + counts[i]); 
        }
        System.out.println("Max Index: " + IOM);
    }
}
