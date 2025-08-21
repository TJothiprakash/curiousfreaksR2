package slidingwindow;

/*You are visiting a farm that has a single row of fruit trees arranged from left to right.
 The trees are represented by an integer array fruits where fruits[i] is the type of fruit
 the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules
that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit.
There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit
from every tree (including the start tree) while moving to the right.
 The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.



Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].


Constraints:

1 <= fruits.length <= 105
0 <= fruits[i] < fruits.length
*/
/*ou are given an array fruits where each element represents the type of fruit a tree produces.
 You have two baskets, each of which can only store one type of fruit at a time.
 The goal is to collect the maximum number of fruits while adhering to the following rules:

You must only pick fruits from at most two types of trees.
You start at any tree and move to the right, collecting fruits.
If you encounter a tree with a fruit type that is not one of the two types you are collecting,
 you must stop.
Approach:
Sliding Window (Two Pointers):

The idea is to maintain a window of fruits that contains at most two types of fruits
. We will use two pointers, left and right, to represent the current window.
The right pointer will expand the window by moving right, and the left pointer
will shrink the window when there are more than two types of fruits in the window.
For each valid window (containing at most two types of fruits), calculate the size
 of the window and keep track of the maximum size.
Steps:

Use a hashmap or dictionary to store the frequency of the fruit types in the current window.
Expand the window by moving the right pointer.
 For each fruit encountered, update its frequency in the hashmap.
If there are more than two distinct fruit types in the hashmap,
 move the left pointer to shrink the window until there are at most two distinct fruit types.
At each step, calculate the window's size and update the maximum size.
*/

import java.util.HashMap;

public class FruitIntoBasket {
    //    O(n )O(1)
    public int totalFruit(int[] fruits) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < fruits.length; right++) {
            // Add the current fruit to the window
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            // If the window has more than 2 distinct fruits, shrink the window
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++; // Shrink the window
            }

            // Calculate the current window size
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
