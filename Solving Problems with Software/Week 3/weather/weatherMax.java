import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class weatherMax {
    
    public CSVRecord coldestHourInFile( CSVParser parser) {
        // Set initial to null
        CSVRecord coldest = null;
        
        //Iterate over each line in parser
        for(CSVRecord r : parser){
            // If coldest is null, set coldest to temp
            if(coldest == null){
                coldest = r;
            } else {
                // else, if coldest < temp, set coldest = temp
                double current = Double.parseDouble(r.get("TemperatureF"));
                double largest = Double.parseDouble(coldest.get("TemperatureF"));
                if(current < largest){
                    coldest = r;
                }
            }
        }
        
        //return coldest
        return coldest;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest: " + coldest.get("TemperatureF") + 
        " Time: " + coldest.get("TimeEST"));
    }
    
    public String fileWithColdestTemperature(){
        // set the fileresource and directory resource
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldest = null;
        String filename = null;
        
        //iterate over the files in the directory
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord r = coldestHourInFile(fr.getCSVParser());
            // if coldest = null, set coldest = temp
            if(coldest == null){
                coldest = r;
            } else {
                // else, if temp < coldest, set temp = coldest, and coldestfile = file
                double current = Double.parseDouble(r.get("TemperatureF"));
                double smallest = Double.parseDouble(coldest.get("TemperatureF"));
                if(current < smallest){
                    coldest = r;
                    filename = f.getName();
                }
            }
        }
        //return the name of the file that is the coldest
        return filename;
    }
    
    public void testFileWithColdestTemperature() {
        String coldestFile = fileWithColdestTemperature();
        System.out.println("Coldest temperature was recorded in the file: " + coldestFile);
        FileResource fr = new FileResource("./2014/" + coldestFile);
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was: " + coldestRecord.get("TemperatureF")
        + " at " + coldestRecord.get("TimeEST"));
    }
}
