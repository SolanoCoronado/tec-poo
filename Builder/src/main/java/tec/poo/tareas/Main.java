package tec.poo.tareas;

public class Main {
    public static void main(String[] args) {
        Product product = new Product.Builder("Laptop")
                .category("Electrónico")
                .price(999.99)
                .description("Laptop gamer")
                .build();

        System.out.println(product);
    }
}
