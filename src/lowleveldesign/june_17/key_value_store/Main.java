package lowleveldesign.june_17.key_value_store;

import lowleveldesign.june_17.key_value_store.KeyValueStore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        KeyValueStore store = new KeyValueStore();
        store.put("name", "jothi");
        store.put("otp", "123456", 2000); // 2 seconds TTL

        System.out.println(store.get("name")); // jothi
        System.out.println(store.get("otp"));  // 123456

        Thread.sleep(2100);

        try {
            System.out.println(store.get("otp")); // this line may throw
        } catch (RuntimeException e) {
            System.out.println("Handled exception: " + e.getMessage());
        }
        System.out.println(store.size());
        System.out.println("hahah program is still running");
    }
}
