package cart;

import java.math.BigDecimal;
import java.util.Objects;
import product.SalableProduct;

public class CartItem {
    private final SalableProduct product;
    private int quantity;

    public CartItem(SalableProduct product, int quantity) {
        this.product = Objects.requireNonNull(product, "product");
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");
        this.quantity = quantity;
    }

    public SalableProduct getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public void add(int qty) {
        if (qty <= 0) throw new IllegalArgumentException("qty must be > 0");
        quantity += qty;
    }

    public int remove(int qty) {
        if (qty <= 0) return 0;
        int removed = Math.min(qty, quantity);
        quantity -= removed;
        return removed;
    }

    public BigDecimal lineTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override public String toString() {
        return String.format("%s x %d = $%s", product.getName(), quantity, lineTotal());
    }
}
