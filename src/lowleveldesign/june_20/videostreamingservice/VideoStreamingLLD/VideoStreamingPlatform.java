package lowleveldesign.june_20.videostreamingservice.VideoStreamingLLD;

import java.util.*;

public class VideoStreamingPlatform {
    private final VideoService videoService = new VideoService();
    private final UserService userService = new UserService();
    private final VideoPlayer videoPlayer = new VideoPlayer();

    public void upload(String title, String desc, int duration, String filePath) {
        videoService.uploadVideo(title, desc, duration, filePath);
    }

    public void registerUser(String name) {
        userService.registerUser(name);
    }

    public void browseVideos() {
        List<Video> videos = videoService.listAllVideos();
        for (Video v : videos) {
            System.out.println(v.getId() + ". " + v.getTitle() + " (" + v.getDuration() + "s)");
        }
    }

    public void play(int userId, int videoId) {
        Video video = videoService.getVideoById(videoId);
        if (video != null) {
            videoPlayer.playVideo(video);
            userService.addToWatchHistory(userId, videoId);
        } else {
            System.out.println("❌ Video not found.");
        }
    }

    public void viewWatchHistory(int userId) {
        List<Video> watched = userService.getWatchHistory(userId, videoService);
        System.out.println("🧾 Watch History:");
        for (Video v : watched) {
            System.out.println("- " + v.getTitle());
        }
    }
}