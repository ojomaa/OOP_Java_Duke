
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

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
}

