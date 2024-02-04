
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
            if(!Ips.contains(IpAdd)){
                Ips.add(IpAdd);
            }
        }
        
        return Ips.size();
    }
    
    public void printAllHigherThanNum(int num){
        for(LogEntry le : records){
            int stat = le.getStatusCode();
            if(stat > num){
                System.out.println(le);
            }
        }
    }
    
    public ArrayList<String> uniqueIpVisitsOnDay(String someday){
        ArrayList<String> uniqueVisits = new ArrayList<String>();
        for( LogEntry le : records){
            String time = le.getAccessTime().toString();
            String IpAdd = le.getIpAddress();
            if(time.contains(someday) && !uniqueVisits.contains(IpAdd)){
                uniqueVisits.add(IpAdd);
            }
        }
        
        return uniqueVisits;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        int count = 0;
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records) {
            int code = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if((code >= low && code <= high) && 
            !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le: records){
            String IPadd = le.getIpAddress();
            if(!counts.containsKey(IPadd)){
                counts.put(IPadd, 1);
            }
            else{
                counts.put(IPadd, counts.get(IPadd) + 1);
            }
        }
        
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> IPCounts){
        int maxCount = 0;
        
        for(Map.Entry<String, Integer> entry : IPCounts.entrySet()){
            int count = entry.getValue();
            if(count > maxCount){
                maxCount = count;
            }
        }
        
        return maxCount;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> IPCounts){
        int maxCount = 0;
        ArrayList<String> mostIPAdd = new ArrayList<String>();
        
        for(Map.Entry<String, Integer> entry : IPCounts.entrySet()){
            int count = entry.getValue();
            String IPAdd = entry.getKey();
            
            if(count > maxCount){
                maxCount = count;
                mostIPAdd.clear();
                mostIPAdd.add(IPAdd);
            }
            else if(count == maxCount){
                mostIPAdd.add(IPAdd);
            }
        }
        
        return mostIPAdd;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> IPDays = new HashMap<String, ArrayList<String>>();
        
        for(LogEntry le: records){
            String date = le.getAccessTime().toString().substring(4,10);
            String IPAdd = le.getIpAddress();
            
            if(!IPDays.containsKey(date)){
                IPDays.put(date, new ArrayList<String>());
                IPDays.get(date).add(IPAdd);
            }
            else{
                IPDays.get(date).add(IPAdd);
            }
        }
        
        return IPDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> IPDays) {
        int maxVisits = 0;
        String dayWithMostVisits = "";

        for (String day : IPDays.keySet()) {
            int currentVisits = IPDays.get(day).size();
            if (currentVisits > maxVisits) {
                maxVisits = currentVisits;
                dayWithMostVisits = day;
            }
        }

        return dayWithMostVisits;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> IPDays, String day) {
        if (IPDays.containsKey(day)) {
            HashMap<String, Integer> counts = new HashMap<>();

            for (String ip : IPDays.get(day)) {
                counts.put(ip, counts.getOrDefault(ip, 0) + 1);
            }

            return iPsMostVisits(counts);
        }

        return new ArrayList<>();
    }
}
