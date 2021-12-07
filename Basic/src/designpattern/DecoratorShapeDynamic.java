package designpattern;

interface ShapeDeco {
    String info();
}

class CircleDeco implements ShapeDeco {
    float radius;

    public CircleDeco(float radius) {
        this.radius = radius;
    }

    public void resize(float factor) {
        radius *= factor;
    }

    @Override
    public String info() {
        return "Circle of radius: " + radius;
    }
}

class SquareDeco implements ShapeDeco {
    float side;

    public SquareDeco(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "Square of side: " + side;
    }
}

class ColorCircleDeco implements ShapeDeco {
    private CircleDeco circle;
    private String color;

    public ColorCircleDeco(CircleDeco circle, String color) {
        this.circle = circle;
        this.color = color;
    }

    @Override
    public String info() {
        return circle.info() + " has color: " + color;
    }
}

class ColorSquareDeco implements ShapeDeco {
    private SquareDeco square;
    private String color;

    public ColorSquareDeco(SquareDeco square, String color) {
        this.square = square;
        this.color = color;
    }

    @Override
    public String info() {
        return square.info() + " has color: " + color;
    }
}

public class DecoratorShapeDynamic {
    public static void main(String[] args) {
        CircleDeco circle = new CircleDeco(5);
        circle.resize(2);
        System.out.println(circle.info());

        ColorCircleDeco cc = new ColorCircleDeco(
                new CircleDeco(3),
                "Blue");
        System.out.println(cc.info());

        SquareDeco square = new SquareDeco(5);
        System.out.println(square.info());

        ColorSquareDeco cs = new ColorSquareDeco(
                new SquareDeco(6),
                "Purple");
        System.out.println(cs.info());
    }
}
