public class BuilderTest {
    public static void main(String[] args) {
        Product product = Product.builder()
                                    .withId("001")
                                    .withName("Computer")
                                    .withPrice(1000)
                                    .build();
        System.out.println(product);

        Product product1 = Product.builder()
                                .withId("002")
                                .withName("Telephone")
                                .build();
        System.out.println(product1);
    }
}

class Product {
    private String id;
    private String name;
    private int price;

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "[Product:: id: " + this.id + ", name: " + this.name + ", price: " + this.price + "]";
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private String id;
        private String name;
        private int price;

        public ProductBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withPrice(int price) {
            this.price = price;
            return this;
        }

        public ProductBuilder() {

        }

        public Product build() {
            return new Product(this.id, this.name, this.price);
        }
    }
}
