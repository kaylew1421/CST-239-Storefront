package product;

import java.math.BigDecimal;
import java.util.Objects;


public class SalableProduct {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private int quantity;

    public SalableProduct(String name, String description, BigDecimal price, int quantity) {
        this.name = Objects.requireNonNull(name, "name");
        this.description = Objects.requireNonNull(description, "description");
        this.price = Objects.requireNonNull(price, "price");
        if (quantity < 0) throw new IllegalArgumentException("quantity must be >= 0");
        this.quantity = quantity;
    }

    public void adjustQuantity(int delta) {
        int next = this.quantity + delta;
        if (next < 0) throw new IllegalArgumentException("Insufficient quantity for delta " + delta);
        this.quantity = next;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public int getQuantity() { return quantity; }

    @Override public String toString() {
        return String.format("%s - %s | $%s | qty=%d", name, description, price, quantity);
    }
}
