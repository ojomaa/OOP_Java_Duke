
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public static void main(String[] args){
        testSimpleGene();
    }
    
    public static String findSimpleGene( String dna, String startCodon, String stopCodon) {
        String gene = "";
        
        String dnaUpper = dna.toUpperCase();
        String dnaLower = dna.toLowerCase();
        
        int startIndex = dnaUpper.indexOf(startCodon);
        if (startIndex == -1 ) {
            return "";
        }
        int stopIndex = dnaUpper.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1) {
            return "";
        }
        gene = dnaUpper.substring(startIndex, stopIndex + 3);
        if (gene.length() % 3 != 0){
            return "";
        }
        
        if (dna.equals(dnaUpper)) {
            return gene.toUpperCase();
        } else if (dna.equals(dnaLower)) {
            return gene.toLowerCase();
        } else {
            return gene;
        }
    }
    
    
    public static void testSimpleGene() {
        // DNA with no "ATG"
        String dnaNoATG = "GCTAGCTAGCTA";
        System.out.println("DNA: " + dnaNoATG);
        System.out.println( "The gene is: " + findSimpleGene(dnaNoATG, "ATG", "TAA"));

        // DNA with no "TAA"
        String dnaNoTAA = "ATGGCTAGCTAGCTAGCT";
        System.out.println("DNA: " + dnaNoTAA);
        System.out.println( "The gene is: " + findSimpleGene(dnaNoTAA, "ATG", "TAA"));

        // DNA with no "ATG" or "TAA"
        String dnaNoATGorTAA = "GCTAGCTAGCTAGCTAGCT";
        System.out.println("DNA: " + dnaNoATGorTAA);
        System.out.println( "The gene is: " + findSimpleGene(dnaNoATGorTAA, "ATG", "TAA"));

        // DNA with ATG, TAA, and a gene (substring multiple of 3)
        String dnaWithGene = "ATGGCTTAGCATTAATAG";
        System.out.println("DNA: " + dnaWithGene);
        System.out.println( "The gene is: " + findSimpleGene(dnaWithGene, "ATG", "TAA"));

        // DNA with ATG, TAA, and a non-multiple of 3 substring
        String dnaWithNonMultipleGene = "ATGGCTTAGCTAATAAG";
        System.out.println("DNA: " + dnaWithNonMultipleGene);
        System.out.println( "The gene is: " + findSimpleGene(dnaWithNonMultipleGene, "ATG", "TAA"));
    }
}
