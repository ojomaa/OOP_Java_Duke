
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
        analyzer.readFile("weblog2_log");

        int uniqueIPs = analyzer.countUniqueIps();
        System.out.println("Number of unique IPs: " + uniqueIPs);
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");

        System.out.println("Logs with status code higher than 400:");
        analyzer.printAllHigherThanNum(400);
    }
    
    public void testUniqueIpVisitsOnDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();

        logAnalyzer.readFile("weblog2_log");

        String testDate = "Sep 27";

        ArrayList<String> uniqueVisits = logAnalyzer.uniqueIpVisitsOnDay(testDate);

        System.out.println("Unique IP visits on " + testDate + ":");
        System.out.println("ArrayList size: " + uniqueVisits.size());
        for (String ip : uniqueVisits) {
            System.out.println(ip);
        }
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();

        logAnalyzer.readFile("weblog2_log");

        int low = 200;
        int high = 299;

        int uniqueIPsInRange = logAnalyzer.countUniqueIPsInRange(low, high);

        System.out.println("Number of unique IPs in range " + low + " to " + high + ": " + uniqueIPsInRange);
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();

        // Assuming you have a file named "weblog-short_log" with some log entries
        logAnalyzer.readFile("weblog-short_log");

        HashMap<String, Integer> visitsPerIP = logAnalyzer.countVisitsPerIP();

        System.out.println("Visits per IP:");
        for (String ip : visitsPerIP.keySet()) {
            int count = visitsPerIP.get(ip);
            System.out.println(ip + ": " + count + " visit(s)");
        }
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");

        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIP();

        int maxVisits = logAnalyzer.mostNumberVisitsByIP(counts);

        System.out.println("Most number of visits by a single IP: " + maxVisits);
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
    
        // Obtain a HashMap of IP counts
        HashMap<String, Integer> IPCounts = logAnalyzer.countVisitsPerIP();
    
        // Find and print the IP addresses with the most visits
        ArrayList<String> mostVisitsIPs = logAnalyzer.iPsMostVisits(IPCounts);
    
        System.out.println("IP addresses with the most visits:");
        for (String ip : mostVisitsIPs) {
            System.out.println(ip);
        }
    }
    
    public void testIPsForDays() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");

        HashMap<String, ArrayList<String>> IPDays = logAnalyzer.iPsForDays();

        for (String date : IPDays.keySet()) {
            System.out.println("Date: " + date);
            ArrayList<String> IPs = IPDays.get(date);
            System.out.println("IP Addresses: " + IPs);
            System.out.println();
        }
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");

        HashMap<String, ArrayList<String>> IPDays = logAnalyzer.iPsForDays();

        String dayWithMostVisits = logAnalyzer.dayWithMostIPVisits(IPDays);

        System.out.println("Day with most IP visits: " + dayWithMostVisits);
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");

        HashMap<String, ArrayList<String>> IPDays = logAnalyzer.iPsForDays();

        String testDay = "Sep 29";

        ArrayList<String> IPsOnDay = logAnalyzer.iPsWithMostVisitsOnDay(IPDays, testDay);

        System.out.println("IP addresses with most visits on " + testDay + ":");
        for (String ip : IPsOnDay) {
            System.out.println(ip);
        }
    }
}
