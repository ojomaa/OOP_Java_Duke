import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int lenShape = 0;
        for (Point p: s.getPoints()){
            lenShape++;
        }
        
        return lenShape;
    }

    public double getAverageLength(Shape s) {
        double averageLen = getPerimeter(s) / getNumPoints(s);
        return averageLen;
    }

    public double getLargestSide(Shape s) {
        
        double largestPoint = 0.0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt: s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if( currDist > largestPoint ){
                largestPoint = currDist;
            }
            prevPt = currPt;
            }
        return largestPoint;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;
        
        for (Point Pt: s.getPoints()){
            double xLength = Pt.getX();
            if( xLength > largestX ){
                largestX = xLength;
            }
            }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0;
        for ( File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length > largestPerim){
                largestPerim = length;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0;
        File temp = null;
        for ( File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length > largestPerim){
                largestPerim = length;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Shape has " + getNumPoints(s) + " points");
        System.out.println("The average length of a side in your shape is " + getAverageLength(s));
        System.out.println("The length of the longest side is " + getLargestSide(s));
        System.out.println("The largest X is " + getLargestX(s));
        getFileWithLargestPerimeter();
        getLargestPerimeterMultipleFiles();
    }
    
    public void testPerimeterMultipleFiles() {
        String length = getFileWithLargestPerimeter();
        System.out.println("Largest file is " + length);
    }

    public void testFileWithLargestPerimeter() {
        double length = getLargestPerimeterMultipleFiles();
        System.out.println("Largest file Perimeter is " + length);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
