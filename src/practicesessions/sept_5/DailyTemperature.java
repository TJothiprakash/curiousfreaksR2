package practicesessions.sept_5;


import java.util.Stack;

/*Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.



Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100*/
public class DailyTemperature {



    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length;
            int[] res = new int[n];
            Stack<Integer> stack = new Stack<>(); // store indices

            for (int i = n - 1; i >= 0; i--) {
                // Remove all days that are not warmer
                while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                    stack.pop();
                }

                // If stack not empty → next warmer day found
                if (!stack.isEmpty()) {
                    res[i] = stack.peek() - i;
                } else {
                    res[i] = 0; // no warmer day
                }

                stack.push(i);
            }


            return res;
        }
    }

}
