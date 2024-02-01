
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log.txt");

        int uniqueIPs = analyzer.countUniqueIps();
        System.out.println("Number of unique IPs: " + uniqueIPs);
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");

        System.out.println("Logs with status code higher than 300:");
        analyzer.printAllHigherThanNum(300);
    }
    
    public void testUniqueIpVisitsOnDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();

        logAnalyzer.readFile("weblog-short_log");

        String testDate = "Sep 14";

        ArrayList<String> uniqueVisits = logAnalyzer.uniqueIpVisitsOnDay(testDate);

        System.out.println("Unique IP visits on " + testDate + ":");
        for (String ip : uniqueVisits) {
            System.out.println(ip);
        }
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();

        logAnalyzer.readFile("short-test_log");

        int low = 200;
        int high = 299;

        int uniqueIPsInRange = logAnalyzer.countUniqueIPsInRange(low, high);

        System.out.println("Number of unique IPs in range " + low + " to " + high + ": " + uniqueIPsInRange);
    }
}
