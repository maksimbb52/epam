import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static double inputAngle() {
        for (int i = 0; i < 1; i++) {
            try {
                System.out.println("Enter the angle (in degrees):");
                return (double) Float.parseFloat(reader.readLine());
            } catch (RuntimeException | IOException e) {

                System.out.println("Incorrect, try again");
                i--;
            }
        }
        return 0;
    }

    private static Point inputPoint(int id) {
        for (int i = 0; i < 1; i++) {
            try {
                System.out.println("Enter point " + id + ":");
                String[] s = reader.readLine().split(" ");
                return new Point((double) Float.parseFloat(s[0]), (double) Float.parseFloat(s[1]));
            } catch (RuntimeException | IOException e) {
                System.out.println("Incorrect, try again");
                i--;
            }
        }
        return null;
    }

    private static int inputPointsNum() {
        int num = 4;
        for (int i = 0; i < 1; i++) {
            try {
                System.out.println("Enter the number of points");
                num = Integer.parseInt(reader.readLine());

                if (num < 3) {
                    throw new PolygonException("Please, enter the positive number. Try again");
                }
                return num;
            } catch (PolygonException e) {
                e.getMessage();
                i--;
            } catch (RuntimeException | IOException e) {
                System.out.println("Incorrect, try again");
                i--;
            }
        }
        return num;
    }

    private static Polygon inputPolygon() {
        Polygon poly = null;
        int choice = 0;
        String[] str;
        Point[] p;
        String s = "Select type of determine polygon(rectangle)" +
                "\n1. by 2 diagonal points (rectangle)" +
                "\n2. by 2 diagonal points and angle (rectangle)" +
                "\n3. by n points (convex polygon)" +
                "\n10. Print submenu\n0. Back";
        System.out.println(s);
        do {
            try {
                System.out.print("Submenu (10 - print menu)\nYour choice: ");
                choice = Integer.parseInt(reader.readLine());
                switch (choice) {
                    case 1:
                        p = new Point[4];
                        p[0] = inputPoint(1);
                        p[2] = inputPoint(2);
                        Solution.find2Points(p, 0);
                        return new Polygon(p);
                    case 2:
                        p = new Point[4];
                        p[0] = inputPoint(1);
                        p[2] = inputPoint(2);
                        Solution.find2Points(p, inputAngle());
                        return new Polygon(p);
                    case 3:
                        Set<Point> set = new LinkedHashSet<>();
                        int n = inputPointsNum();
                        while (set.size() < n) {
                            set.add(inputPoint(set.size() + 1));
                        }
                        p = set.toArray(new Point[set.size()]);
                        Solution.checkConcave(p);
                        return new Polygon(p);
                    case 10:
                        System.out.println(s);
                        break;
                    default:
                        break;
                }
            } catch (PolygonException ex) {
                System.out.println(ex.getMessage());
            } catch (RuntimeException | IOException ex) {
                System.out.println("Incorrect value.\nTry again\n");
            }
        } while (choice != 0);
        return null;
    }

    public static void main(String[] args) {
        //System.out.println(Solution.crossVect(new Point(2, 5), new Point(2, -1), new Point(-2, 3), new Point(3, 3)));
        //(Solution.crossingPoint(new Point(3, 3), new Point(3, -2), new Point(2, -1), new Point(-2, -4))).printPoint();
        //System.out.println(Solution.polygonSquare(new Polygon(new Point[] {new Point( -1, 1), new Point(5, 1), new Point(7, -2),
        //new Point(5, -3), new Point(3, -3), new Point(1, -2)})));
        Polygon poly1 = null;
        Polygon poly2 = null;
        Polygon poly3 = null;
        int choice = 0;
        String s = "\nMain menu\n1. Enter the 1st polygon\n2. Enter the 2nd polygon" +
                "\n3. Find square of polygon 1\n4. Find square of polygon 2" +
                "\n5. Find crossing square\n6. Print polygon 1\n7. Print polygon 2" +
                "\n8. Print crossing figure\n10. Print menu\n0. Exit";
        System.out.println(s);
        do {
            try {
                System.out.print("Main menu (10 - print menu)\nYour choice: ");
                choice = Integer.parseInt(reader.readLine());
                switch (choice) {
                    case 1:
                        poly1 = inputPolygon();
                        System.out.println("The first polygon was created successfully.");
                        break;
                    case 2:
                        poly2 = inputPolygon();
                        System.out.println("The second polygon was created successfully.");
                        break;
                    case 3:

                        if (poly1 != null) {
                            System.out.printf("Polygon 1 square: %.2f\n", Solution.polygonSquare(poly1));
                        } else {
                            System.out.println("To start, enter the polygon. Try later...");
                        }
                        break;
                    case 4:

                        if (poly2 != null) {
                            System.out.printf("Polygon 2 square: %.2f\n", Solution.polygonSquare(poly2));
                        } else {
                            System.out.println("To start, enter the polygon. Try later...");
                        }
                        break;
                    case 5:

                        if (poly1 != null && poly2 != null) {
                            poly3 = Solution.figureByCrossing(poly1, poly2);
                            System.out.printf("Crossing square is %.2f\n",
                                    Solution.polygonSquare(poly3));
                            System.out.println("Crossing polygon was created successfully.");
                        } else {
                            System.out.println("To start, enter two polygons. Try later...");
                        }
                        break;
                    case 6:
                        System.out.println("Polygon 1:");
                        if (poly1 != null) {
                            poly1.printPolygon();
                        } else {
                            System.out.println("To start, enter polygon. Try later...");
                        }
                        break;
                    case 7:
                        System.out.println("Polygon 2:");
                        if (poly2 != null) {
                            poly2.printPolygon();
                        } else {
                            System.out.println("To start, enter polygon. Try later...");
                        }
                        break;
                    case 8:
                        System.out.println("Crossing figure:");
                        if (poly3 != null) {
                            poly3.printPolygon();
                        } else {
                            System.out.println("To start, enter polygon. Try later...");
                        }
                        break;
                    case 10:
                        System.out.println(s);
                        break;
                    default:
                        break;
                }
            } catch (RuntimeException | IOException ex) {
                System.out.println("Incorrect value. Try again.");
            }
        } while (choice != 0);
    }
}