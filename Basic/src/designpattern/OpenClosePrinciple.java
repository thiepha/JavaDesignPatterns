// OCP: Open Close Principle: Open for extension, Close for modification

package designpattern;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

enum Color {
    GREEN, BLUE, RED, YELLOW, PURPLE
}

enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return name + " - " + color + " - " + size;
    }
}

// NOT GOOD implementation: what if we have multiple filters with multiple criteria
class ProductFilter {
    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterByColorAndSize(List<Product> products, Color color, Size size) {
        return products.stream().filter(p -> p.color == color && p.size == size);
    }
}

// OCP
interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> list, Specification<T> spec);
}

class ColorSpecification implements Specification<Product> {
    private final Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }
    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product> {
    private final Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

class ColorSizeSpecification implements Specification<Product> {
    private final Color color;
    private final Size size;

    public ColorSizeSpecification(Color color, Size size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color && item.size == size;
    }
}

class AndSpecfication<T> implements Specification<T> {
    private final Specification<T> first, second;

    public AndSpecfication(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

class ProductFilterCSR implements Filter<Product> {
    @Override
    public Stream<Product> filter(List<Product> list, Specification<Product> spec) {
        return list.stream().filter(spec::isSatisfied);
    }
}

public class OpenClosePrinciple {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.MEDIUM);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = Arrays.asList(apple, tree, house);

        ProductFilter pf = new ProductFilter();
        System.out.println("Green products (old): ");
        pf.filterByColor(products, Color.GREEN).forEach(System.out::println);

        ProductFilterCSR pfc = new ProductFilterCSR();
        System.out.println("Green products (csr): ");
        pfc.filter(products, new ColorSpecification(Color.GREEN)).forEach(System.out::println);

        System.out.println("Green products (csr and): ");
        pfc.filter(products, new AndSpecfication<>(new ColorSpecification(Color.GREEN), new SizeSpecification(Size.MEDIUM)))
                .forEach(System.out::println);
    }
}
