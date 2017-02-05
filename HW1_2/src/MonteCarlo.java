import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Anton Skudarnov on 04.02.2017.
 */
public class MonteCarlo {


    private ArrayList<Point2D> array;

    MonteCarlo() {
        array = new ArrayList();
    }

    private double xMin, xMax, yMin, yMax;


    public void display() {
        for (int i = 0; i < array.count; i++) {
            System.out.print(array.get(i));
        }
        System.out.println();
    }

    public double calculateSquare() throws FileNotFoundException {


        xMin = xMax = array.get(0).getX();
        yMin = yMax = array.get(0).getY();
        Point2D temp;
        for (int i = 1; i < array.count; i++) {
            temp = array.get(i);
            if (temp.getX() < xMin)
                xMin = temp.getX();
            if (temp.getX() > xMax)
                xMax = temp.getX();
            if (temp.getY() < yMin)
                yMin = temp.getY();
            if (temp.getY() > yMax)
                yMax = temp.getY();
        }

        double xLength, yLength;
        xLength = xMax - xMin;
        yLength = yMax - yMin;
        double parSquare = xLength * yLength;

        double oldSquare = 0;
        double newSquare = xLength * yLength;
        double randomX, randomY;
        Random random = new Random();
        int K;
        int accuracy = 10;
        boolean flag = false;
        while(!flag) {
            K = 0;
            int N =(int) Math.pow(2, accuracy); // amount of points;
            System.out.println(N);
            for (int i = 0; i < N; i++) {
                randomX = xMin + xLength * random.nextDouble();
                randomY = yMin + yLength * random.nextDouble();

                if (isInside(new Point2D.Double(randomX, randomY)))
                    K++;

            }
            oldSquare = newSquare;
            newSquare = parSquare * K / N;
            accuracy++;

            if(Math.abs(oldSquare - newSquare) < 10E-4)
                flag = true;


        }

        return newSquare;
    }

    private boolean isInside(Point2D point) {
        int intersections = 0;
        Random random = new Random();
        Point2D directionPoint = new Point2D.Double(xMax, random.nextInt(100)+yMax+1);
        for (int i = 0; i < array.count - 1; i++) {
            if (isIntersects(array.get(i), array.get(i + 1), point, directionPoint))
                intersections++;

        }
        if (isIntersects(array.get(array.count-1),array.get(0),point,directionPoint))
            intersections++;

        if (intersections % 2 == 0)
            return false;
        return true;

    }


    private boolean isIntersects(Point2D a, Point2D b, Point2D c, Point2D d) {
        double[][] A = new double[2][2];
        A[0][0] = b.getX() - a.getX();
        A[1][0] = b.getY() - a.getY();
        A[0][1] = c.getX() - d.getX();
        A[1][1] = c.getY() - d.getY();

        double det0 = A[0][0] * A[1][1] - A[1][0]*A[0][1];
        if ((int)det0 == 0) return false;
        double detU = ((c.getX() - a.getX())* A[1][1] - (c.getY()- a.getY())*A[0][1]);
        double detV = A[0][0]*(c.getY() - a.getY()) - A[1][0] * (c.getX() - a.getX());

        double u = detU / det0;
        double v = detV / det0;

        return u > 0 && u < 1 && v > 0 && v < 1;
    }


    public void ReadPoints() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input.txt"));
        scan.useLocale(Locale.US);
        while (scan.hasNextDouble()) {
            array.add(new Point2D.Double(scan.nextDouble(), scan.nextDouble()));

        }

    }
}
