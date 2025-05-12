package practicesessions.may_11;

public class Main {
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        map.put("apple", 10);
        map.put("banana", 20);
        map.put("grape", 30);
        map.put("apple", 15); // overwrite

        System.out.println("apple: " + map.get("apple")); // 15
        System.out.println("banana: " + map.get("banana")); // 20

        map.remove("banana");
        System.out.println("banana after remove: " + map.get("banana")); // null

        map.printMap();
    }
}
