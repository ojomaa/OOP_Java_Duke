
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int totalBoyNames = 0;
        int totalGirlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames ++;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoyNames++;
            }
            else {
                totalGirls += numBorn;
                totalGirlNames++;
            }
        }
        System.out.println("total births = " + totalBirths + " Total Names: " + totalNames);
        System.out.println("female girls = " + totalGirls + " Total Names: " + totalGirlNames);
        System.out.println("male boys = " + totalBoys + " Total Names: " + totalBoyNames);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("./testing/yob2012short.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fr = new FileResource("./testing/yob" + year + "short.csv");
        
        for(CSVRecord rec : fr.getCSVParser(false)){
            String currentGender = rec.get(1);
            
            if (currentGender.equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank() {
        System.out.println("rank: " + getRank(2012, "William", "M"));
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("./testing/yob" + year + "short.csv");
        int currentRank = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                currentRank++;
                if(currentRank == rank){
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void testGetName(){
        System.out.println("Name: " + getName(2012, 4, "M"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        // Isabella born in 2012 would be Sophia if she was born in 2014.
        System.out.println(name + " born in " + year + " would be " + newName
                            + " if she was born in " + newYear);
        
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int rank = Integer.MAX_VALUE;
        int fileYear = -1;
        
        for(File f : dr.selectedFiles()){
            String currentFile = f.getName();
            String yearStr = currentFile.substring(currentFile.indexOf("yob") + 3, currentFile.indexOf("yob") + 7);
            int yearInt = Integer.parseInt(yearStr);
            
            int currentRank = getRank(yearInt, name, gender);
            if(currentRank < rank && currentRank != -1){
                rank = currentRank;
                fileYear = yearInt;
            }
        }
        
        return fileYear;
    }
    
    public void testYearOfHighestRank(){
        System.out.println("Year of highest rank for name: " + yearOfHighestRank("Mason", "M"));
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int totalFiles = 0;
        
        for(File f : dr.selectedFiles()){
            String currentFile = f.getName();
            String yearStr = currentFile.substring(currentFile.indexOf("yob") + 3, currentFile.indexOf("yob") + 7);
            int yearInt = Integer.parseInt(yearStr);
            int currentRank = getRank(yearInt, name, gender);
            
            totalFiles++;
            totalRank += currentRank;
        }
        double average = (double) totalRank / totalFiles;
        return average;
    }
    
    public void testGetAverageRank(){
        System.out.println("Average Rank: " + getAverageRank("Jacob", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("./testing/yob" + year + "short.csv");
        int totalBirthsRankedHigher = 0;
        int rank = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currentGender = rec.get(1);
            if (currentGender.equals(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                    break; 
                }
                totalBirthsRankedHigher += Integer.parseInt(rec.get(2));
            }
        }

        return totalBirthsRankedHigher;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        System.out.println("Total Higher Ranked Births: " + getTotalBirthsRankedHigher(2012, "Ethan", "M"));
    }
}

