import java.util.HashMap;

public class inventorymanager {
    private HashMap<String, Integer> stockMap = new HashMap<>();
    private HashMap<String, Integer> waitingList = new HashMap<>();

    public inventorymanager() {
        // Initialize stock for products
        stockMap.put("IPHONE15_256GB", 100);
    }

    public synchronized String checkStock(String productId) {
        int stock = stockMap.getOrDefault(productId, 0);
        return stock + " units available";
    }

    public synchronized String purchaseItem(String productId, int userId) {
        int stock = stockMap.getOrDefault(productId, 0);
        if (stock > 0) {
            stockMap.put(productId, stock - 1);
            return "Success, " + (stock - 1) + " units remaining";
        } else {
            waitingList.put(String.valueOf(userId), 1); // Add to waiting list
            return "Added to waiting list, position #" + waitingList.size();
        }
    }

    public static void main(String[] args) {
        inventorymanager manager = new inventorymanager();
        System.out.println(manager.checkStock("IPHONE15_256GB"));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 12345));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 67890));
        // simulate more purchases...
    }
}

