package designpattern;

import java.util.function.Supplier;

interface ShapeDS {
    String info();
}

class CircleDS implements ShapeDS {
    float radius;

    public CircleDS(float radius) {
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

class SquareDS implements ShapeDS {
    float side;

    public SquareDS(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "Square of side: " + side;
    }
}

class ColorShapeDS<T extends ShapeDS> implements ShapeDS {
    private final ShapeDS shape;
    private final String color;

    public ColorShapeDS(Supplier<? extends ShapeDS> supplier, String color) {
        this.shape = supplier.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has color: " + color;
    }
}

class TransparentShapeDS<T extends ColorShapeDS> implements ShapeDS {
    private final ShapeDS shape;
    private final float trans;

    public TransparentShapeDS(Supplier<? extends ColorShapeDS> supplier,
                              float trans) {
        this.shape = supplier.get();
        this.trans = trans;
    }

    @Override
    public String info() {
        return shape.info() + " has transparency: " + trans;
    }
}

public class DecoratorShapeStatic {
    public static void main(String[] args) {
        CircleDS circle = new CircleDS(3);
        circle.resize(2);
        System.out.println(circle.info());

        ColorShapeDS<CircleDS> colorShape = new ColorShapeDS<>(
                () -> new CircleDS(3),
                "blue");
        System.out.println(colorShape.info());

        TransparentShapeDS<ColorShapeDS<SquareDS>> transShape =
                new TransparentShapeDS<>(
                        () -> new ColorShapeDS<>(
                                () -> new SquareDS(5),
                                "Green"
                        ),
                        0.5f);
        System.out.println(transShape.info());
    }
}
