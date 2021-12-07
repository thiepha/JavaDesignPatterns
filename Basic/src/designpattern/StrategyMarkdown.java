package designpattern;

// Strategy: specify common interface, but can switch between implementations

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

// dynamic strategy: change implementation at runtime
// common interface
enum ListType {
    MARKDOWN,
    HTML
}

interface ListStrategy {
    default void start(StringBuilder sb) {}
    void addListItem(StringBuilder sb, String item);
    default void end(StringBuilder sb) {}
}

class MarkdownListStrategy implements ListStrategy {
    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("* ")
                .append(item)
                .append(System.lineSeparator());
    }
}

class HtmlListStrategy implements ListStrategy {
    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>")
                .append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("  <li>")
                .append(item)
                .append("</li>")
                .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>")
                .append(System.lineSeparator());
    }
}

class TextProcessor {
    private StringBuilder sb = new StringBuilder();
    private ListStrategy listStrategy;

    public TextProcessor(ListType type) {
        setListStrategy(type);
    }

    public void setListStrategy(ListType type) {
        if (type == ListType.MARKDOWN) {
            listStrategy = new MarkdownListStrategy();
        } else {
            listStrategy = new HtmlListStrategy();
        }
    }

    public void addItems(List<String> items) {
        listStrategy.start(sb);
        for (String item : items) {
            listStrategy.addListItem(sb, item);
        }
        listStrategy.end(sb);
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

class TextProcessorStatic<LS extends ListStrategy> {
    private final StringBuilder sb = new StringBuilder();
    private final ListStrategy listStrategy;

    public TextProcessorStatic(Supplier<? extends  LS> supplier) {
        listStrategy = supplier.get();
    }


    public void addItems(List<String> items) {
        listStrategy.start(sb);
        for (String item : items) {
            listStrategy.addListItem(sb, item);
        }
        listStrategy.end(sb);
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

public class StrategyMarkdown {
    public static void main(String[] args) {
        // Dynamic strategy: can change type at run time
        TextProcessor tp = new TextProcessor(ListType.MARKDOWN);
        tp.addItems(Arrays.asList("first", "second", "third"));
        System.out.println(tp);
        tp.clear();

        tp.setListStrategy(ListType.HTML);
        tp.addItems(Arrays.asList("One", "Two", "Three"));
        System.out.println(tp);
    }
}

class StrategyMarkdownStaticDemo {
    public static void main(String[] args) {
        // static strategy: specify type at compile time
        TextProcessorStatic<MarkdownListStrategy> tp =
                new TextProcessorStatic<>(MarkdownListStrategy::new);
        tp.addItems(Arrays.asList("first", "second", "third"));
        System.out.println(tp);

        TextProcessorStatic<HtmlListStrategy> tp2 =
                new TextProcessorStatic<>(HtmlListStrategy::new);
        tp2.addItems(Arrays.asList("first", "second", "third"));
        System.out.println(tp2);
    }
}