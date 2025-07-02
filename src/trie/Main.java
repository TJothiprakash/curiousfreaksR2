package trie;

public class Main {
    public static void main(String[] args) throws Exception {
        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("app");
        boolean isAppfound = trie.search("app");
        System.out.println("is app found ? : " + isAppfound);
        trie.insert("apply");
        trie.insert("application");
        boolean is_Apply_Found = trie.search("apply");
        System.out.println("is apply found ? : " + is_Apply_Found);
        boolean isApplyDeleted = trie.delete("apply");
        System.out.println("is apply deleted ? : " + isApplyDeleted);
        boolean ans = trie.delete("app");
        System.out.println("is app deleted : " + ans);
        try {
//            trie.insert(null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("error ");
//            e.printStackTrace();
        } finally {
            System.out.println("inser operation end ");
        }
        System.out.println(trie.search("apple"));
        // true
        System.out.println();
//        System.out.println(trie.search("app"));     // true
//        System.out.println(trie.search("appl"));    // false
        System.out.println(trie.startsWith("appl")); // true
//        System.out.println(trie.startsWith("bat"));  // false
//        Trie trie = new Trie();
//        trie.insert("apple");
//        trie.insert("app");
//
//        trie.delete("app");      // unmarks 'p' as end
//        System.out.println(trie.search("app"));    // false
//        System.out.println(trie.search("apple"));  // true
//
//        trie.delete("apple");    // deletes remaining nodes
//        System.out.println(trie.search("apple"));  // false
//        trie.insert("app");
//        System.out.println(trie.search("apple"));
//        System.out.println(trie.search("app"));

    }
}
