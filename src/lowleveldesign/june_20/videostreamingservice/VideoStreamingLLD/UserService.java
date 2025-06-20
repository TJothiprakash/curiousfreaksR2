package lowleveldesign.june_20.videostreamingservice.VideoStreamingLLD;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService {
    private final Map<Integer, User> users = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    public void registerUser(String name) {
        int id = idGenerator.getAndIncrement();
        User user = new User(id, name);
        users.put(id, user);
        System.out.println("✅ Registered user: " + name + " (ID: " + id + ")");
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public void addToWatchHistory(int userId, int videoId) {
        User user = users.get(userId);
        if (user != null) {
            user.addToHistory(videoId);
        }
    }

    public List<Video> getWatchHistory(int userId, VideoService videoService) {
        User user = users.get(userId);
        List<Video> watched = new ArrayList<>();
        if (user != null) {
            for (int vid : user.getWatchHistory()) {
                watched.add(videoService.getVideoById(vid));
            }
        }
        return watched;
    }
}