package lowleveldesign.june_20.videostreamingservice.VideoStreamingLLD;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class VideoService {
    private final Map<Integer, Video> videos = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    public void uploadVideo(String title, String description, int duration, String filePath) {
        int id = idGenerator.getAndIncrement();
        Video video = new Video(id, title, description, duration, filePath);
        videos.put(id, video);
        System.out.println("✅ Uploaded: " + title + " (ID: " + id + ")");
    }

    public List<Video> listAllVideos() {
        return new ArrayList<>(videos.values());
    }

    public Video getVideoById(int id) {
        return videos.get(id);
    }
}