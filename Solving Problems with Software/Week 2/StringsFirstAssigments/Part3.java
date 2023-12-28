
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public static void main(String[] args){
       testing();
    }
    
    public static boolean twoOccurrences(String a, String b) {
        int occurrences = 0;
        int index = b.indexOf(a);
        
        while ( index != -1) {
            occurrences ++;
            index = b.indexOf(a, index + 1);
        }
        
        return occurrences >= 2;
    }
    
    public static String lastPart(String a, String b) {
        int startPos = b.indexOf(a);
        if(startPos == -1) {
            return b;
        } else {
            String snippet = b.substring(startPos);
            return snippet;
        }
    }
    
    public static void testing () {
        // Test Case 1
        System.out.println("a: ab, " + "b: ababc");
        System.out.println("2 occurences: " + twoOccurrences("ab", "ababc"));
        System.out.println("Last Part: " + lastPart("ab", "ababc"));

        // Test Case 2
        System.out.println("a: cd, " + "b: abcdef");
        System.out.println("2 occurences: " + twoOccurrences("cd", "abcdef")); 
        System.out.println("Last Part: " + lastPart("cd", "abcdef"));

        // Test Case 3
        System.out.println("a: abc, " + "b: abcabcabc");
        System.out.println("2 occurences: " + twoOccurrences("abc", "abcabcabc"));
        System.out.println("Last Part: " + lastPart("abc", "abcabcabc"));

        // Test Case 4
        System.out.println("a: xyz, " + "b: lmnop");
        System.out.println("2 occurences: " + twoOccurrences("xyz", "lmnop"));
        System.out.println("Last Part: " + lastPart("xyz", "lmnop"));
    }

}
