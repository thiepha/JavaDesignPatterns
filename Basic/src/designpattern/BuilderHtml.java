package designpattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Html {
    String name, text;
    List<Html> children = new ArrayList<>();
    String sep = System.lineSeparator();
    int indentSize = 2;

    public Html() {

    }

    public Html(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public void addChild(String name, String text) {
        Html child = new Html(name, text);
        children.add(child);
    }

    private String toStringImpl(int indent)
    {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, sep));
        if (text != null && !text.isEmpty())
        {
            sb.append(String.join("", Collections.nCopies(indentSize*(indent+1), " ")))
                    .append(text)
                    .append(sep);
        }

        for (Html e : children)
            sb.append(e.toStringImpl(indent + 1));

        sb.append(String.format("%s</%s>%s", i, name, sep));
        return sb.toString();
    }

    @Override
    public String toString()
    {
        return toStringImpl(0);
    }
}
public class BuilderHtml {
    String rootName;
    Html root;

    public BuilderHtml(String name) {
        root = new Html();
        root.name = name;
        rootName = name;
    }

    public BuilderHtml addChild(String childName, String childText) {
        root.addChild(childName, childText);
        return this;
    }

    public BuilderHtml clear() {
        root = new Html();
        root.name = rootName;
        return this;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

class BuilderDemo {
    public static void main(String[] args) {
        BuilderHtml builder = new BuilderHtml("ul");
        builder.addChild("li", "hello")
               .addChild("li", "world");
        System.out.println(builder);
    }
}