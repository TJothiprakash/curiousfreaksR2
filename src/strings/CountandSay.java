package strings;
class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String previous = countAndSay(n - 1);
        System.out.println("n = " + n + ", previous = " + previous);
        StringBuilder result = new StringBuilder();

        int count = 1;
        for (int i = 0; i < previous.length(); i++) {
            if (i + 1 < previous.length() && previous.charAt(i) == previous.charAt(i + 1)) {
                count++;
            } else {
                result.append(count).append(previous.charAt(i));
                count = 1;
            }
        }
//        System.out.println("n = " + n + ", previous = " + previous);

        return result.toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.countAndSay(5));
    }
}
/*

class Solution {
    private String countAndSay(String a){
        String b = "";
        int n = a.length();
        char prev = a.charAt(0);
        int count = 1;
        for(int i=1;i<n;i++){
            if(prev != a.charAt(i)){
                b += count;
                b += prev;
                count = 0;
            }
            count++;
            prev = a.charAt(i);
        }
        b += count;
        b += prev;
        return b;
    }
    public String countAndSay(int n) {
        String ans = "1";
        for(int i=2;i<=n;i++){
            ans = countAndSay(ans);
        }
        return ans;
    }
}*/
