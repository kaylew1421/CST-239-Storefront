package cart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import product.SalableProduct;

public class ShoppingCart {
    private final Map<String, CartItem> items = new LinkedHashMap<>();

    public void add(SalableProduct product, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("qty must be > 0");
        items.compute(product.getName(), (k, v) -> {
            if (v == null) return new CartItem(product, qty);
            v.add(qty);
            return v;
        });
    }

    
    public int remove(String name, int qty) {
        CartItem item = items.get(name);
        if (item == null) return 0;
        int removed = item.remove(qty);
        if (item.getQuantity() == 0) items.remove(name);
        return removed;
    }

    public Collection<CartItem> getItems() {
        return Collections.unmodifiableCollection(items.values());
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem ci : items.values()) total = total.add(ci.lineTotal());
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public void clear() { items.clear(); }
}
