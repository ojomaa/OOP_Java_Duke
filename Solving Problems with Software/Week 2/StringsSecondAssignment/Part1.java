
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public static void main(String[] args) {
        testFindStopCodon();
        testFindGene();
        testPrintAllGenes();
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
    
    public static void testFindStopCodon() {
        // Test with DNA containing a valid gene
        String dna1 = "ATGAAATAA";
        int result1 = findStopCodon(dna1, 0, "TAA");
        System.out.println("Result 1: " + result1);

        // Test with DNA containing no valid gene
        String dna2 = "ATGACGACTG";
        int result2 = findStopCodon(dna2, 0, "TAA");
        System.out.println("Result 2: " + result2);

        // Test with DNA containing multiple genes
        String dna3 = "ATGAAATAGATGAAATAG";
        int result3 = findStopCodon(dna3, 0, "TAG");
        System.out.println("Result 3: " + result3);

        // Test with different stop codon
        String dna4 = "ATGAAGTAGATGAACTAG";
        int result4 = findStopCodon(dna4, 0, "TAG");
        System.out.println("Result 4: " + result4);
    }
    
    public static void testFindGene() {
        // DNA with no "ATG"
        String dnaNoATG = "GCTAGCTAGCTA";
        System.out.println("DNA: " + dnaNoATG);
        System.out.println("The gene is: " + findGene(dnaNoATG));

        // DNA with "ATG" and one valid stop codon
        String dnaOneStopCodon = "ATGGCTTAGCTAATAG";
        System.out.println("DNA: " + dnaOneStopCodon);
        System.out.println("The gene is: " + findGene(dnaOneStopCodon));

        // DNA with "ATG" and multiple valid stop codons
        String dnaMultipleStopCodons = "ATGGCTTAGCTAATAGTGA";
        System.out.println("DNA: " + dnaMultipleStopCodons);
        System.out.println("The gene is: " + findGene(dnaMultipleStopCodons));

        // DNA with "ATG" and no valid stop codons
        String dnaNoValidStopCodon = "ATGGCTTAGCATTAATAG";
        System.out.println("DNA: " + dnaNoValidStopCodon);
        System.out.println("The gene is: " + findGene(dnaNoValidStopCodon));

        // Additional test case
        String dnaMultipleStartCodons = "ATGGCTTAGATGATGATAGTGA";
        System.out.println("DNA: " + dnaMultipleStartCodons);
        System.out.println("The gene is: " + findGene(dnaMultipleStartCodons));
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
    
    public static void testPrintAllGenes() {
        // Test with DNA containing multiple genes
        String dna1 = "ATGAAATAATAGATGAAATAGTAA";
        System.out.println("DNA: " + dna1);
        printAllGenes(dna1);

        // Test with DNA containing no genes
        String dna2 = "GCTAGCTAGCTA";
        System.out.println("DNA: " + dna2);
        printAllGenes(dna2);

        // Test with DNA containing one gene
        String dna3 = "ATGGCTTAGCTAATAG";
        System.out.println("DNA: " + dna3);
        printAllGenes(dna3);

        // Additional test cases
        String dna4 = "ATGGCTTAGATGATGATAGTGA";
        System.out.println("DNA: " + dna4);
        printAllGenes(dna4);

        String dna5 = "ATGGCTTAGATGATGATAGTGATAAATGAAATAA";
        System.out.println("DNA: " + dna5);
        printAllGenes(dna5);
    }
}
