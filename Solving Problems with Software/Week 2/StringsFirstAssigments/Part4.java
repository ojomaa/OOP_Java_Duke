import edu.duke.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public static void main(String[] args){
        test();
    }
    
    public static List<String> search() {
        List<String> urlWithQuotes = new ArrayList<>();
        URLResource url = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");

        for (String yt : url.words()) {
            String lowercaseYT = yt.toLowerCase(); // Convert to lowercase for case-insensitive comparison

            if (lowercaseYT.contains("youtube.com")) {
                int startIndex = lowercaseYT.indexOf("youtube.com");

                if (startIndex != -1) {
                    int leftQuoteIndex = yt.lastIndexOf("\"", startIndex);
                    int rightQuoteIndex = yt.indexOf("\"", startIndex + "youtube.com".length());

                    if (leftQuoteIndex != -1 && rightQuoteIndex != -1) {
                        String urlQuote = yt.substring(leftQuoteIndex, rightQuoteIndex + 1);
                        urlWithQuotes.add(urlQuote);
                    }
                }
            }
        }
        return urlWithQuotes;
    }
    
    public static void test(){
        System.out.println(search());
    }
}