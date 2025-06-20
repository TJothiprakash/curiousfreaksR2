package lowleveldesign.june_20.videostreamingservice.VideoStreamingLLD;

public class VideoPlayer {
    public void playVideo(Video video) {
        System.out.println("📡 Connecting to CDN for: " + video.getTitle());
        System.out.println("▶️ Playing: " + video.getTitle() + " [" + video.getDuration() + " secs]");

        for (int i = 0; i < video.getDuration(); i += 5) {
            System.out.println("⏱️  Streaming... [" + i + " - " + Math.min(i + 5, video.getDuration()) + " sec]");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {}
        }

        System.out.println("✅ Finished streaming.\n");
    }
}