
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public static void main ( String[] args) {
        testHowMany();
    }
    
    public static int howMany (String a, String b) {
        int count = 0;
        int index = b.indexOf(a);
        
        while ( index != -1 ) {
            count ++;
            index = b.indexOf(a, index + a.length());
        }
        
        return count;
    }
    
    public static void testHowMany() {
        // Test cases
        String a1 = "GAA";
        String b1 = "ATGAACGAATTGAATC";
        System.out.println("Example 1: howMany(\"" + a1 + "\", \"" + b1 + "\") returns " + howMany(a1, b1));

        String a2 = "AA";
        String b2 = "ATAAAA";
        System.out.println("Example 2: howMany(\"" + a2 + "\", \"" + b2 + "\") returns " + howMany(a2, b2));
    }

}
