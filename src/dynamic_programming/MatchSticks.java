package dynamic_programming;

import java.util.Arrays;
import java.util.Collections;

public class MatchSticks {

}

class Solution {
    public boolean makesquare(int[] matchsticks) {
        int total = Arrays.stream(matchsticks).sum();
        Arrays.sort(matchsticks);
        reverse(matchsticks);
        if (total % 4 != 0) return false;
        int sides[] = new int[4];
        return backtrack(0, matchsticks, total, sides);
    }

    private void reverse(int[] matchsticks) {
        int left = 0, right = matchsticks.length - 1;
        while (left < right) {
            int temp = matchsticks[left];
            matchsticks[left] = matchsticks[right];
            matchsticks[right] = temp;
            left++;
            right--;
        }

    }

    private boolean backtrack(int i, int[] matchsticks, int total, int sides[]) {
        if (i == matchsticks.length) {
            return sides[0] == total && sides[1] == total && sides[2] == total && sides[3] == total;
        }

        int num = matchsticks[i];

        for (int j = 0; j < 4; j++) {
            if (sides[j] + num <= total) {
                sides[j] += num;
                backtrack(i + 1, matchsticks, total, sides);
                sides[j] -= num;
            }
            if (sides[j] == 0) break;
        }
        return false;


    }
}
