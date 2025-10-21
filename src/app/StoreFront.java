package app;

import cart.CartItem;
import cart.ShoppingCart;
import inventory.InventoryManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import product.SalableProduct;


public class StoreFront {
    private final InventoryManager inventory = new InventoryManager();
    private final ShoppingCart cart = new ShoppingCart();

    public void initializeStore() {
        inventory.initializeDefault();
    }

    public boolean purchase(String name, int qty) {
        if (!inventory.hasStock(name, qty)) return false;
        if (!inventory.reserve(name, qty)) return false; // double-check
        SalableProduct p = inventory.find(name);
        cart.add(p, qty);
        return true;
    }

    public int cancelPurchase(String name, int qty) {
        int removed = cart.remove(name, qty);
        if (removed > 0) inventory.release(name, removed);
        return removed;
    }

  
    public boolean checkout(boolean paymentOk) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return false;
        }
        if (!paymentOk) {

            for (CartItem item : new ArrayList<>(cart.getItems())) {
                inventory.release(item.getProduct().getName(), item.getQuantity());
            }
            cart.clear();
            System.out.println("Payment failed. Reservation released and cart cleared.");
            return false;
        }

        BigDecimal total = cart.getTotal();
        cart.clear();
        System.out.println("Payment accepted. Order finalized. Total: $" + total);
        return true;
    }

    public void printInventory() { inventory.printInventory(); }

    public void printCart() {
        System.out.println("== Cart ==");
        if (cart.getItems().isEmpty()) {
            System.out.println(" (empty)");
            return;
        }
        for (CartItem ci : cart.getItems()) {
            System.out.printf(" - %s x %d  line=$%s%n",
                    ci.getProduct().getName(), ci.getQuantity(), ci.lineTotal());
        }
        System.out.println(" Cart total = $" + cart.getTotal());
    }
}
