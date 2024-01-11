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
        " Time: " + coldest.get("DateUTC"));
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
        FileResource fr = new FileResource("./2013/" + coldestFile);
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was: " + coldestRecord.get("TemperatureF")
        + " at " + coldestRecord.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        
        //Iterate over each line in parser
        for(CSVRecord r : parser){
            // If lowestHumidity is null, set lowestHumidity to r
            if(lowestHumidity == null){
                lowestHumidity = r;
            } else {
                // else, if lowestHumidity < r, set lowestHumidity = r
                if ("N/A".equals(r.get("Humidity"))){
                    continue;
                }
                else{
                    int current = Integer.parseInt(r.get("Humidity"));
                    int lowest = Integer.parseInt(lowestHumidity.get("Humidity"));
                        if(current < lowest){
                            lowestHumidity = r;
                        }
                }
            }
        }
        //return coldest
        return lowestHumidity;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidity = lowestHumidityInFile(parser);

        
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") +
        " at " + lowestHumidity.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        // set the fileresource and directory resource
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord r = lowestHumidityInFile(fr.getCSVParser());
            if(lowestHumidity == null){
                lowestHumidity = r;
            } else {
                // else, if lowestHumidity < r, set lowestHumidity = r
                if ("N/A".equals(r.get("Humidity"))){
                    continue;
                }
                else{
                    int current = Integer.parseInt(r.get("Humidity"));
                    int lowest = Integer.parseInt(lowestHumidity.get("Humidity"));
                        if(current < lowest){
                            lowestHumidity = r;
                        }
                }
            }
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();

        System.out.println("Lowest Humidity: " + lowestHumidity.get("Humidity"));
        System.out.println("Time: " + lowestHumidity.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
       // set temp and length to 0 
       double totalTemp = 0;
       int fileLength = 0;
       
       // Iterate through each record and add the temp to the totalTemp, increase file by 1
       for(CSVRecord r : parser){
            double current = Double.parseDouble(r.get("TemperatureF"));
            totalTemp += current;
            fileLength ++;
        } 
       
        // get the average temp
       double average = totalTemp / fileLength;
       
       // return the average
       return average;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average Temperature: " + average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double totalTemp = 0;
        int fileLength = 0;
        
        for(CSVRecord r : parser){
            double current = Double.parseDouble(r.get("TemperatureF"));
            if( current >= value){
                totalTemp += current;
                fileLength ++;
            }
        } 
        double average;
        if(fileLength == 0){
            average = 0;
        } else{
            average = totalTemp / fileLength;
        }
        
        return average;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        int humidityThreshold = 80;

        double averageTemperature = averageTemperatureWithHighHumidityInFile(parser, humidityThreshold);

        if (averageTemperature == 0) {
            System.out.println("No temperatures with high humidity found.");
        } else {
            System.out.println("Average temperature with high humidity: " + averageTemperature + " F");
        }
    }
}
