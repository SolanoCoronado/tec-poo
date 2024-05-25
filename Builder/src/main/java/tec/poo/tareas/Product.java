package tec.poo.tareas;
public class Product {
    private final String name;
    private final String category;
    private final double price;
    private final String description;

    private Product(Builder builder) {
        this.name = builder.name;
        this.category = builder.category;
        this.price = builder.price;
        this.description = builder.description;
    }

    public static class Builder {
        private final String name;
        private String category;
        private double price;
        private String description;

        public Builder(String name) {

            this.name = name;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Product build() {

            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
