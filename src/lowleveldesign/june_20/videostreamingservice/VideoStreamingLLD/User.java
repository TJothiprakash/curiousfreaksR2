package lowleveldesign.june_20.videostreamingservice.VideoStreamingLLD;

import java.util.*;

public class User {
    private final int id;
    private final String name;
    private final List<Integer> watchHistory = new ArrayList<>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Integer> getWatchHistory() { return watchHistory; }

    public void addToHistory(int videoId) {
        watchHistory.add(videoId);
    }
}