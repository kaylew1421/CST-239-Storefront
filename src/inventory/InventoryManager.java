package inventory;

import java.math.BigDecimal;
import java.util.*;
import product.SalableProduct;


public class InventoryManager {
    private final Map<String, SalableProduct> products = new LinkedHashMap<>();

    public void initializeDefault() {
        addProduct(new SalableProduct("Keyboard", "Mechanical, backlit", new BigDecimal("79.99"), 10));
        addProduct(new SalableProduct("Mouse", "Wireless, ergonomic", new BigDecimal("39.99"), 20));
        addProduct(new SalableProduct("USB-C Cable", "1m, braided", new BigDecimal("9.95"), 50));
        addProduct(new SalableProduct("Monitor", "27in IPS", new BigDecimal("189.00"), 8));
    }

    public void addProduct(SalableProduct p) { products.put(p.getName(), p); }

    public List<SalableProduct> listProducts() { return new ArrayList<>(products.values()); }

    public SalableProduct find(String name) { return products.get(name); }

    public boolean hasStock(String name, int qty) {
        SalableProduct p = products.get(name);
        return p != null && qty > 0 && p.getQuantity() >= qty;
    }

    public boolean reserve(String name, int qty) {
        if (!hasStock(name, qty)) return false;
        products.get(name).adjustQuantity(-qty);
        return true;
    }

    public void release(String name, int qty) {
        SalableProduct p = products.get(name);
        if (p != null && qty > 0) p.adjustQuantity(qty);
    }

    public void printInventory() {
        System.out.println("== Inventory ==");
        for (SalableProduct p : listProducts()) {
            System.out.println(" - " + p);
        }
    }
}
