package slidingwindow.sept_05;

public class Substring3258 {
    static class Solution {
        public int countKConstraintSubstrings(String s, int l) {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
            int zeroCount = 0, oneCount = 0;
                for (int j = i; j < s.length(); j++) {

                        if (s.charAt(j) == '0') {
                            zeroCount++;
                        } else {
                            oneCount++;
                        }



                    if (zeroCount <= l || oneCount <= l) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
