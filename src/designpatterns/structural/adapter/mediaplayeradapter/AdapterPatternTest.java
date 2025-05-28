package designpatterns.structural.adapter.mediaplayeradapter;


public class AdapterPatternTest {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();

        player.play("mp3", "song.mp3");
        player.play("mp4", "movie.mp4");
        player.play("vlc", "clip.vlc");
        player.play("avi", "old_movie.avi");
    }
}

