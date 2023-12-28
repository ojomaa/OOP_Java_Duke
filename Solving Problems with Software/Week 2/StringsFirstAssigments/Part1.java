
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public static void main(String[] args){
        testSimpleGene();
    }
    
    public static String findSimpleGene( String dna ) {
        String gene = "";
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1 ) {
            return "";
        }
        int stopCodon = dna.indexOf("TAA", startCodon + 3);
        if (stopCodon == -1) {
            return "";
        }
        gene = dna.substring(startCodon, stopCodon + 3);
        if (gene.length() % 3 != 0){
            return "";
        }
        return gene;
    }
    
    
    public static void testSimpleGene() {
        // DNA with no "ATG"
        String dnaNoATG = "GCTAGCTAGCTA";
        System.out.println("DNA: " + dnaNoATG);
        System.out.println( "The gene is: " + findSimpleGene(dnaNoATG));

        // DNA with no "TAA"
        String dnaNoTAA = "ATGGCTAGCTAGCTAGCT";
        System.out.println("DNA: " + dnaNoTAA);
        System.out.println( "The gene is: " + findSimpleGene(dnaNoTAA));

        // DNA with no "ATG" or "TAA"
        String dnaNoATGorTAA = "GCTAGCTAGCTAGCTAGCT";
        System.out.println("DNA: " + dnaNoATGorTAA);
        System.out.println( "The gene is: " + findSimpleGene(dnaNoATGorTAA));

        // DNA with ATG, TAA, and a gene (substring multiple of 3)
        String dnaWithGene = "ATGGCTTAGCATTAATAG";
        System.out.println("DNA: " + dnaWithGene);
        System.out.println( "The gene is: " + findSimpleGene(dnaWithGene));

        // DNA with ATG, TAA, and a non-multiple of 3 substring
        String dnaWithNonMultipleGene = "ATGGCTTAGCTAATAAG";
        System.out.println("DNA: " + dnaWithNonMultipleGene);
        System.out.println( "The gene is: " + findSimpleGene(dnaWithNonMultipleGene));
    }
}
