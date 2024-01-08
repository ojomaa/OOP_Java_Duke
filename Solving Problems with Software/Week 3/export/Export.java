import edu.duke.*;
import org.apache.commons.csv.*;

public class Export {
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //countryInfo
        String result1 = countryInfo(parser, "Nauru");
        System.out.println(result1);
        
        //listExportersTwoProducts
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        //numberOfExporters
        parser = fr.getCSVParser();
        int count = numberOfExporters(parser, "gold");
        System.out.println("Number of exporters: " + count);
        
        //bigExporters
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord r : parser) {
            if(r.get("Country").equals(country)){
                String exports = r.get("Exports");
                String value = r.get("Value (dollars)");
                return country + ": " + exports + ": " + value;
            } 
        }
        return "Not Found";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord r: parser) {
            String exports = r.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                String country = r.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord r: parser){
            String exports = r.get("Exports");
            if(exports.contains(exportItem)){
                count ++;
            }
        }
        return count;
    }
    
    public void bigExporters( CSVParser parser, String amount){
        String amountRemove = amount.replace("$", "").replace(",", "");
        long a = Long.parseLong(amountRemove);
        
        for(CSVRecord r: parser){
            String country = r.get("Country");
            String value = r.get("Value (dollars)");
            String valueRemove = value.replace("$", "").replace(",", "");
            long valueInt = Long.parseLong(valueRemove);
            if(valueInt > a){
                System.out.println(country + " " + value);
            }
        }
    }
}
