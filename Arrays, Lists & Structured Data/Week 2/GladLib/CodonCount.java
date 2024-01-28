import edu.duke.*;
import java.util.*;
import java.io.*;
public class CodonCount {
    private HashMap<String, Integer> CodonMap;
    
    public CodonCount(){
        CodonMap = new HashMap<>();
    }
    
    public void buildCodonMap(int start, String dna){
        CodonMap.clear();
        for(int i=start; i<dna.length() - 3; i+= 3){
            String codon = dna.substring(i, i + 3);
            
            if(CodonMap.containsKey(codon)){
                CodonMap.put(codon, CodonMap.get(codon) + 1);
            }
            else{
                CodonMap.put(codon, 1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        int total= 0;
        String codon = null;
        for(Map.Entry<String, Integer> entry : CodonMap.entrySet()){
            String key = entry.getKey();
            int value = entry.getValue();
            
            if(value > total){
                total = value;
                codon = key;
            }
        }
        return codon;
    }
    
    public void printTotalCounts(int start, int end){
        for(Map.Entry<String, Integer> entry : CodonMap.entrySet()){
            String key = entry.getKey();
            int value = entry.getValue();
            if(value >= start && value <= end){
                System.out.println("Codon: " + key + " Count: " + value);
            }
        }
    }
    
    public void Test() {
       //String dna = "CGTTCAAGTTCAA";
       String s = "";
       String dna = "";
       try(BufferedReader DNA = new BufferedReader(new FileReader("smalldna.txt"));)
       {
           //FileResource DNA = new FileResource("dnaMystery1.txt");
           while((s = DNA.readLine())!= null){
               dna += s;
           }
           //String dna = DNA.asString();

       }
       catch(FileNotFoundException e){
           e.printStackTrace();
       }
       catch(IOException e){
           e.printStackTrace();
       }
      
       
       int start = 1;
       int end = 5;
        
       buildCodonMap(0, dna);
       System.out.println("Reading frame starting with 0 results in "+CodonMap.size()+" unique codons"+"\t");
       String the_largest_count = getMostCommonCodon();
       System.out.println("and most common codon is "+the_largest_count+" with count "+CodonMap.get(the_largest_count)+"\t");  
       System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
       printTotalCounts(start, end);
        
       buildCodonMap(1, dna);
       System.out.println("Reading frame starting with 1 results in "+CodonMap.size()+" unique codons"+"\t");
       the_largest_count = getMostCommonCodon();
       System.out.println("and most common codon is "+the_largest_count+" with count "+CodonMap.get(the_largest_count)+"\t");  
       System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
       printTotalCounts(start, end);
        
       buildCodonMap(2, dna);
       System.out.println("Reading frame starting with 2 results in "+CodonMap.size()+" unique codons"+"\t");
       the_largest_count = getMostCommonCodon();
       System.out.println("and most common codon is "+the_largest_count+" with count "+CodonMap.get(the_largest_count)+"\t");  
       System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
       printTotalCounts(start, end);
    }
}
