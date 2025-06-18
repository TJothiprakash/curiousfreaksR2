package lowleveldesign.june_17.key_value_store;

import java.util.concurrent.ConcurrentHashMap;

public class KeyValueStore {

    private static final int MAX_CAPACITY = 1000;
    private final ConcurrentHashMap<String, ValueWrapperObject> map;

    public KeyValueStore() {
        map = new ConcurrentHashMap<>(MAX_CAPACITY);
    }

    public void put(String key, Object value) {
        map.put(key, new ValueWrapperObject(value, null)); // No expiry
    }

    public void put(String key, Object value, long ttlMillis) {
        long expiryTime = System.currentTimeMillis() + ttlMillis;
        map.put(key, new ValueWrapperObject(value, expiryTime));
    }

    public Object get(String key) {
        ValueWrapperObject wrapper = map.get(key);
        if (wrapper == null) return null;

        if (wrapper.expiryTime != null && System.currentTimeMillis() > wrapper.expiryTime) {
            map.remove(key);
            throw new RuntimeException("Key '" + key + "' has expired");
        }
        return wrapper.value;
    }

    public void delete(String key) {
        map.remove(key);
    }

    public int size() {
        return map.size();
    }


    private void startCleanupThread() {
        Thread cleaner = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // Run every 5 seconds

                    for (String key : map.keySet()) {
                        ValueWrapperObject wrapper = map.get(key);
                        if (wrapper != null && wrapper.expiryTime != null &&
                                System.currentTimeMillis() > wrapper.expiryTime) {
                            map.remove(key);
                            System.out.println("Cleaner: Removed expired key = " + key);
                        }
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break; // Exit on interruption
                }
            }
        });

        cleaner.setDaemon(true); // Won’t block app shutdown
        cleaner.start();
    }
}

class ValueWrapperObject {
    Object value;
    Long expiryTime; // Nullable

    public ValueWrapperObject(Object value, Long expiryTime) {
        this.value = value;
        this.expiryTime = expiryTime;
    }

}
