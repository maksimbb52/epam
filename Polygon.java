public class Polygon {
    
    final private Point[] p;

    public Polygon(Point[] p) {
        this.p = p;
    }

    public Point[] getPoints() {
        return p.clone();
    }

    public void printPolygon() {
        for (int i = 0; i < p.length; i++) {
            System.out.printf("Point %2d: %.15f %.3f\n", i, p[i].getX(), p[i].getY());
        }
    }
}

