package lld.nokiasnakegame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class Level2Qn {
}
/*

interface MostPopular {
    void increasePopularity(Integer contentId);

    Integer mostPopular();

    void decreasePopularity(Integer contentId);
}

Sample execution:

        [
        popularityTracker.

increasePopularity(7);
  popularityTracker.

increasePopularity(7);
  popularityTracker.

increasePopularity(8);
  popularityTracker.

mostPopular();        // returns 7
  popularityTracker.

increasePopularity(8);
  popularityTracker.

increasePopularity(8);
  popularityTracker.

mostPopular();        // returns 8
  popularityTracker.

decreasePopularity(8);
  popularityTracker.

decreasePopularity(8);
  popularityTracker.

mostPopular();        // returns 7
  popularityTracker.

decreasePopularity(7);
  popularityTracker.

decreasePopularity(7);
  popularityTracker.

decreasePopularity(8);
  popularityTracker.

mostPopular();        // returns -1 since there is no content with popularity greater than 0
]
// Most popular means, contentId with heighest popularity value.

Assumptions:

In case
of a
tie:->
Return any
contentId
Popularity of
contentId can
never go
below zero.
Follow-up

Return the
most recent
mostPopular contentId
changed
My Approach:
*/


interface MostPopular {
    void increasePopularity(Integer contentId);

    Integer mostPopular();

    void decreasePopularity(Integer contentId);
}

class PopularityTracker implements MostPopular {
    private final HashMap<Integer, Integer> popularityMap;
    private final TreeMap<Integer, HashSet<Integer>> popularityToContentMap;
    // This map is only required for follow-up question.
    private final HashMap<Integer, Integer> mostRecentMap;

    PopularityTracker() {
        popularityMap = new HashMap<>();
        mostRecentMap = new HashMap<>();
        popularityToContentMap = new TreeMap<>();
    }

    @Override
    public void increasePopularity(Integer contentId) {
        int oldPopularity = popularityMap.getOrDefault(contentId, 0);
        int newPopularity = oldPopularity + 1;

        if (oldPopularity > 0) {
            HashSet<Integer> oldSetContents = popularityToContentMap.get(oldPopularity);
            oldSetContents.remove(contentId);
            if (oldSetContents.isEmpty()) {
                popularityToContentMap.remove(oldPopularity);
            }
        }

        popularityToContentMap.computeIfAbsent(newPopularity, k -> new HashSet<>()).add(contentId);
        popularityMap.put(contentId, newPopularity);
        mostRecentMap.put(newPopularity, contentId);
    }

    @Override
    public Integer mostPopular() {
        System.out.println(popularityToContentMap);
        System.out.println("most recent map");
        System.out.println(mostRecentMap);
        if (popularityToContentMap.isEmpty())
            return -1;

// Code for first implementation
//        return popularityToContentMap.lastEntry().getValue().iterator().next();
// Code change after follow-up
        return mostRecentMap.get(popularityToContentMap.lastEntry().getKey());
    }

    @Override
    public void decreasePopularity(Integer contentId) {
        int oldPopularity = popularityMap.get(contentId);
        int newPopularity = oldPopularity - 1;

        HashSet<Integer> oldSetContents = popularityToContentMap.get(oldPopularity);
        oldSetContents.remove(contentId);
        if (oldSetContents.isEmpty()) {
            popularityToContentMap.remove(oldPopularity);
        }

        if (newPopularity > 0) {
            popularityToContentMap.computeIfAbsent(newPopularity, k -> new HashSet<>()).add(contentId);
            popularityMap.put(contentId, newPopularity);
            mostRecentMap.put(newPopularity, contentId);
        } else {
            popularityMap.remove(contentId);
        }
    }
}

