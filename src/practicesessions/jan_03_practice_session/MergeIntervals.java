package practicesessions.jan_03_practice_session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public static List<List<Integer>> mergeIntervals(List<List<Integer>> intervals) {

        if (intervals.size() == 0) return null;
        List<Integer> temp = new ArrayList<>();
        Collections.sort(intervals, Comparator.comparingInt(a -> a.get(0)));
        List<List<Integer>> result = new ArrayList<>();
        result.add(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).get(0) <= result.get(result.size() - 1).get(1)) {
                result.get(result.size() - 1).
                        set(1, Math.max(result.get(result.size() - 1).get(1), intervals.get(i).get(1)));
            } else {
                result.add(intervals.get(i));
            }
        }

        return result;

    }
}
