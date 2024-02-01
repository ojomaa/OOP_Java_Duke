
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
     
    public LogAnalyzer() {
         records = new ArrayList<>();
    }
        
    public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
             LogEntry log = WebLogParser.parseEntry(line);
             records.add(log);
            }
    }
        
    public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
    }
     
    public int countUniqueIps(){
        ArrayList<String> Ips = new ArrayList<String>();
        
        for(LogEntry le : records){
            String IpAdd = le.getIpAddress();
            if(Ips.contains(IpAdd)){
                Ips.add(IpAdd);
            }
        }
        
        return Ips.size();
    }
}
