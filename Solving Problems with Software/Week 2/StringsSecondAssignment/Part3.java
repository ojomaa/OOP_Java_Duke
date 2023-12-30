
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public static void main(String[] args) {
        testCountGenes();    
    }
    
    public static int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        while (stopIndex != -1){
            if((stopIndex - startIndex) % 3 == 0){
                return stopIndex;
            } else {
                stopIndex = dna.indexOf(stopCodon, stopIndex +1);
            }
            
        }
        return -1;
    }
    
    public static String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int findTAA = findStopCodon(dna, startIndex, "TAA");
        int findTAG = findStopCodon(dna, startIndex, "TAG");
        int findTGA = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = 0;
        
        if (findTAA == -1 || (findTAG != -1 && findTAG < findTAA)) {
            minIndex = findTAG;
        } else {
            minIndex = findTAA;
        }

        if (minIndex == -1 || (findTGA != -1 && findTGA < minIndex)) {
            minIndex = findTGA;
        }

        if (minIndex == -1) {
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public static void printAllGenes(String dna){
        while ( true ) {
            String gene = findGene(dna);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println("Gene: " + gene);
            int startIndex = dna.indexOf(gene) + gene.length();
            dna = dna.substring(startIndex);   
        }
    }
    
    public static int countGenes (String dna) {
        int count = 0;
        int startIndex = 0;
        
        while ( true ) {
            String gene = findGene(dna.substring(startIndex));
            if (gene.isEmpty()) {
                break;
            }
            count ++;
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return count;
    }
    
    public static void testCountGenes() {
        String dna1 = "ATGTAAGATGCCCTAGT";
        String dna2 = "ATGTAGTAAATGCCCTAGTAA";
        String dna3 = "ATGTAAGATGCCCTAGTAAATGTAAGATGCCCTAGTAA";

        System.out.println("DNA sequence: " + dna1);
        System.out.println("Number of genes: " + countGenes(dna1));
        System.out.println();

        System.out.println("DNA sequence: " + dna2);
        System.out.println("Number of genes: " + countGenes(dna2));
        System.out.println();

        System.out.println("DNA sequence: " + dna3);
        System.out.println("Number of genes: " + countGenes(dna3));
        System.out.println();
    }
}
