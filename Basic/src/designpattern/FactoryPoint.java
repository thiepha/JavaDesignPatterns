package designpattern;

class PointF {
    // Point Factory
    double x, y;

    private PointF(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Same as Factory Method but we gather methods into a static class
    public static class Factory {
        public static PointF newCartisianPoint(double x, double y) {
            return new PointF(x, y);
        }

        public static PointF newPolarisPoint(double roh, double theta) {
            return new PointF(roh * Math.cos(theta),
                    roh * Math.sin(theta));
        }
    }
}

public class FactoryPoint {
    public static void main(String[] args) {
        PointF point = PointF.Factory.newCartisianPoint(2, 5);
        PointF point2 = PointF.Factory.newPolarisPoint(10, 45);
    }
}