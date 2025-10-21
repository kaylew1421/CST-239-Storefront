package app;


public class StoreFrontApp {
    public static void main(String[] args) {
        StoreFront sf = new StoreFront();

        sf.initializeStore();
        sf.printInventory();

        System.out.println("\n-- Add to cart (reserve) --");
        System.out.println("Add Keyboard x1  -> " + sf.purchase("Keyboard", 1));
        System.out.println("Add Mouse x2     -> " + sf.purchase("Mouse", 2));
        sf.printCart();
        sf.printInventory();

        System.out.println("\n-- Remove part of order (release) --");
        int removed = sf.cancelPurchase("Mouse", 1);
        System.out.println("Removed Mouse x" + removed);
        sf.printCart();
        sf.printInventory();

        System.out.println("\n-- Checkout (simulate payment failure) --");
        sf.checkout(false);
        sf.printCart();
        sf.printInventory();

        System.out.println("\n-- Add again and checkout success --");
        sf.purchase("USB-C Cable", 3);
        sf.purchase("Monitor", 1);
        sf.printCart();
        sf.checkout(true);
        sf.printCart();
        sf.printInventory();
    }
}
