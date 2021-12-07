package designpattern;

enum CartisianType { CARTISIAN, POLARIS };

class PointUgly {
    private double x, y;

    // This is very difficult to use, and a lot of handling in constructor
    /**
     * Need to document what a, b is
     * @param a x if type is Cartisian, roh if type is polaris
     * @param b y if type is cartisian, theta if type is polaris
     * @param type
     */
    public PointUgly(double a, double b, CartisianType type) {
        switch (type) {
            case CARTISIAN:
                x = a;
                y = b;
                break;
            case POLARIS:
                x = a * Math.cos(b);
                y = a * Math.sin(b);
                break;
        }
    }
}

/// Better one: use factory method
class PointFM {
    // Point Factory Method
    double x, y;

    private PointFM(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static PointFM newCartisianPoint(double x, double y) {
        return new PointFM(x, y);
    }

    public static PointFM newPolarisPoint(double roh, double theta) {
        return new PointFM(roh * Math.cos(theta),
                roh * Math.sin(theta));
    }
}

public class FactoryMethodPoint {
    public static void main(String[] args) {
        PointFM point = PointFM.newCartisianPoint(2, 5);
        PointFM point2 = PointFM.newPolarisPoint(10, 45);
    }
}
