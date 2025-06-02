package strings;

import java.util.*;

public class GroupAnagrams {

    List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();


        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String key = String.valueOf(ch);
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());

    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = ga.groupAnagrams(strs);
        for(List<String> list : result){
            for(String s : list){
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
