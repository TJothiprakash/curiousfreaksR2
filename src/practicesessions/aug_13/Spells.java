package practicesessions.aug_13;

import java.util.Arrays;

public class Spells {

    static void main() {

    }
    public static void main(String[] args) {
        int[] spells = {3, 1, 2};
        int[] portions = {8, 5, 8};
        int success = 16;

        Spells obj = new Spells();
        int[] result = obj.successfulSpells(spells, portions, success);

        System.out.println("Result: " + Arrays.toString(result));
    }

    public int[] successfulSpells(int[] spells, int[] portions, long success) {
        Arrays.sort(portions);
        int[] answer = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            answer[i] = portions.length - firstSuccessIndex(spells[i], portions, success);
        }

        return answer;
    }

    static int firstSuccessIndex(int spell, int[] portions, long success) {
        int left = 0, right = portions.length - 1;
        int idx = portions.length; // default = no success found
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) spell * portions[mid] >= success) {
                idx = mid;       // found a valid index
                right = mid - 1; // try to find earlier valid
            } else {
                left = mid + 1;  // need larger portion
            }
        }
        return idx;
    }


}
