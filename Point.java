public class Point {
    final private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean equals(Object p) {
        if (p == null) {
            return false;
        }
        if (p == this) {
            return true;
        }
        if (p.getClass() == this.getClass()) {
            Point point = (Point) p;
            return point.x == this.x && point.y == this.y;
        }
        return false;
    }

    public int hashCode() {
        return 7 + 13 * (int) x + 23 * (int) y;
    }

    public void printPoint() {
        System.out.println(x + " " + y);
    }

}
