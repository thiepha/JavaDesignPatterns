package designpattern;

// Composite: treat group and single elements in the same way
// GraphicObject: group, Circle, Square: single object
// both have same toString() function

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GraphicObject {
    protected String name;
    public String color;
    public List<GraphicObject> children = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GraphicObject() {
        name = "Group";
    }

    private void print(StringBuilder sb, int depth) {
        sb.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth > 0 ? " " : "")
                .append((color == null || color.isEmpty()) ? "" : color + " ")
                .append(getName())
                .append(System.lineSeparator());

        for (GraphicObject child : children) {
            child.print(sb, depth + 1);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }
}

class CircleCp extends GraphicObject {
    public CircleCp(String color) {
        name = "Circle";
        this.color = color;
    }
}

class SquareCp extends GraphicObject {
    public SquareCp(String color) {
        name = "Square";
        this.color = color;
    }
}

public class CompositeShape {
    public static void main(String[] args) {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.children.add(new CircleCp("Blue"));
        drawing.children.add(new SquareCp("Blue"));
        drawing.children.add(new CircleCp("Yellow"));
        drawing.children.add(new SquareCp("Yellow"));

        // drawing can contain group object also
        GraphicObject group = new GraphicObject();
        group.children.add(new CircleCp("Green"));
        group.children.add(new SquareCp("Green"));
        drawing.children.add(group);

        System.out.println(drawing);
    }
}
