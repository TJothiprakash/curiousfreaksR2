package lowleveldesign.june_20.videostreamingservice.VideoStreamingLLD;

public class Video {
    private final int id;
    private final String title;
    private final String description;
    private final int duration;
    private final String filePath;

    public Video(int id, String title, String description, int duration, String filePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.filePath = filePath;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getDuration() { return duration; }
    public String getFilePath() { return filePath; }
}