package trie;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("app");
        trie.insert("apply");

        System.out.println(trie.search("apple"));   // true
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
