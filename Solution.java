import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Solution {

    // if the point is the right to the vector - false; else - true;
    private static int angleSide(Point u1, Point u2, Point p) {
        double value = (u2.getX() - u1.getX()) * (p.getY() - u1.getY())
                - (p.getX() - u1.getX()) * (u2.getY() - u1.getY());
        return Double.compare(value, 0);
    }

    // bubble sort points by angle (CW)
    private static void bubbleSortPointsByAngle(double[] angleArr, Point[] arr) {
        for (int i = 0; i < angleArr.length; i++) {
            for (int j = 2; j < angleArr.length; j++) {
                if (angleArr[j - 1] < angleArr[j]) {
                    double temp = angleArr[j - 1];
                    angleArr[j - 1] = angleArr[j];
                    angleArr[j] = temp;
                    swapPoints(arr, j, j - 1);
                }
            }
        }
    }

    // find coordinates of crossing point (null - if point doesn'y exist)
    private static Point crossingPoint(Point u1, Point u2, Point v1, Point v2) {
        if (!crossVect(u1, u2, v1, v2)) {
            return null;
        }
        double a1 = u1.getY() - u2.getY();
        double b1 = u2.getX() - u1.getX();
        double a2 = v1.getY() - v2.getY();
        double b2 = v2.getX() - v1.getX();
        double d = a1 * b2 - a2 * b1;
        if (d != 0) {
            double c1 = u2.getY() * u1.getX() - u2.getX() * u1.getY();
            double c2 = v2.getY() * v1.getX() - v2.getX() * v1.getY();
            return new Point(round((b1 * c2 - b2 * c1) / d, 12),
                    round((a2 * c1 - a1 * c2) / d, 12));
        }
        return null;
    }

    //true - if figure concave, else - false;
    public static void checkConcave(Point[] arr) throws PolygonException {
        boolean result = false;
        Solution.sortPointsByCW(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 2; j < arr.length - 1; j++) {
                result |= Solution.difAngleSigns(arr[i], arr[i + 1], arr[j], arr[j + 1]);
            }
            if (i != 0) {
                result |= Solution.difAngleSigns(arr[i], arr[i + 1], arr[arr.length - 1], arr[0]);
            }
        }
        if (result) {
            throw new PolygonException(("Fail - Concave figure"));
        }
    }


    public static Set<Point> crossingPoints(Polygon poly1, Polygon poly2) {
        Point[] p1 = poly1.getPoints();
        sortPointsByCW(p1);
        p1 = Arrays.copyOf(p1, p1.length + 1);
        p1[p1.length - 1] = p1[0];
        Point[] p2 = poly2.getPoints();
        sortPointsByCW(p2);
        p2 = Arrays.copyOf(p2, p2.length + 1);
        p2[p2.length - 1] = p2[0];
        Set<Point> set = new LinkedHashSet<>();
        for (int i = 1; i < p1.length; i++) {
            for (int j = 1; j < p2.length; j++) {
                if (crossVect(p1[i - 1], p1[i], p2[j - 1], p2[j])) {
                    set.add(crossingPoint(p1[i - 1], p1[i], p2[j - 1], p2[j]));
                }
            }
        }
        return set;
    }

    // if vectors crossing - true, else - false (
    public static boolean crossVect(Point u1, Point u2, Point v1, Point v2) {
        return difAngleSigns(u1, u2, v1, v2)
                && difAngleSigns(v1, v2, u1, u2);

    }

    //true - if angles have different signs, else - false (true - angle == 0)
    private static boolean difAngleSigns(Point u1, Point u2, Point v1, Point v2) {
        return Math.abs(angleSide(u1, u2, v1) + angleSide(u1, u2, v2)) < 2;
    }


    public static void find2Points(Point[] p, double angle) throws PolygonException {
        if (p[0].getX() == p[2].getX() || p[0].getX() == p[2].getX()) {
            throw new PolygonException("Incorrect, try again");                                         // страх
        }                                                                                       // мб потом исправлю
        int reverseAngle = Boolean.compare(p[0].getY() > p[2].getY(), p[0].getY() <= p[2].getY());
        double beta = reverseAngle * Math.atan(Math.abs((p[0].getY() - p[2].getY())
                / (p[2].getX() - p[0].getX()))) + Math.toRadians(angle);
        double diameter = Solution.length(p[0], p[2]);
        double edgeLength = diameter * Math.sin(Math.PI / 2 - beta);
        int reverseLen = Boolean.compare(p[0].getX() <= p[2].getX(), p[0].getX() > p[2].getX());
        p[1] = new Point(round(p[0].getX()
                + edgeLength * reverseLen * Math.cos(Math.toRadians(angle)), 14),
                round(p[0].getY() + reverseLen * edgeLength * Math.sin(Math.toRadians(angle)), 14));
        p[3] = new Point(Solution.round(p[2].getX()
                - reverseLen * edgeLength * Math.cos(Math.toRadians(angle)), 14),
                Solution.round(p[2].getY() -
                        reverseLen * edgeLength * Math.sin(Math.toRadians(angle)), 14));
    }

    public static Polygon figureByCrossing(Polygon poly1, Polygon poly2) {
        Set<Point> set = crossingPoints(poly1, poly2);
        set.addAll(includingPoints(poly1, poly2));
        return new Polygon(set.toArray(new Point[set.size()]));
    }

    private static Set<Point> includingPoints(Polygon poly1, Polygon poly2) {
        Point[] p1 = poly1.getPoints();
        sortPointsByCW(p1);
        p1 = Arrays.copyOf(p1, p1.length + 1);
        p1[p1.length - 1] = p1[0];
        Point[] p2 = poly2.getPoints();
        sortPointsByCW(p2);
        p2 = Arrays.copyOf(p2, p2.length + 1);
        p2[p2.length - 1] = p2[0];
        Set<Point> set = new LinkedHashSet<>();
        for (Point p : p1) {
            boolean result = true;
            for (int j = 1; j < p2.length; j++) {
                result &= (angleSide(p2[j - 1], p2[j], p) < 0);
            }
            if (result) {
                set.add(p);
            }
        }
        for (Point p : p2) {
            boolean result = true;
            for (int j = 1; j < p1.length; j++) {
                result &= (angleSide(p1[j - 1], p1[j], p) < 0);
            }
            if (result) {
                set.add(p);
            }
        }
        return set;
    }

    private static double length(Point u1, Point u2) {
        return Math.sqrt(Math.pow(u1.getX() - u2.getX(), 2)
                + Math.pow(u1.getY() - u2.getY(), 2));
    }

    public static double round(double d, int digitsAfterDot) {
        return new BigDecimal(d).setScale(digitsAfterDot, RoundingMode.HALF_DOWN).doubleValue();
    }

    private static void sortPointsByCW(Point[] p) {          //example
        double minX = p[0].getX();                          //         3
        double subMinY = p[0].getY();                       // 2
        int id = 0;                                         //             4
        for (int i = 1; i < p.length; i++) {                // 1
            if (p[i].getX() < minX) {                       //        5
                minX = p[i].getX();
                subMinY = p[i].getY();
            }
        }
        for (int i = 0; i < p.length; i++) {
            if (p[i].getX() == minX) {
                if (p[i].getY() <= subMinY) {
                    subMinY = p[i].getY();
                    id = i;
                }
            }
        }
        if (id != 0) {
            swapPoints(p, 0, id);
        }
        double[] angle = new double[p.length];
        for (int i = 1; i < angle.length; i++) {
            angle[i] = Math.atan2(p[i].getY() - subMinY, p[i].getX() - minX);
        }
        Solution.bubbleSortPointsByAngle(angle, p);
    }


    public static double polygonSquare(Polygon poly) {
        double result = 0;
        Point[] p = poly.getPoints();
        sortPointsByCW(p);
        for (int i = 1; i < p.length; i++) {
            result += squareOfTriangle(length(p[0], p[i - 1]), length(p[i - 1], p[i]), length(p[i], p[0]));
        }
        return result;
    }

    private static double squareOfTriangle(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    private static void swapPoints(Point[] p, int id1, int id2) {
        Point temp = p[id1];
        p[id1] = p[id2];
        p[id2] = temp;
    }
}
