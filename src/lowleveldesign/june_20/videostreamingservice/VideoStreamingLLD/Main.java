package lowleveldesign.june_20.videostreamingservice.VideoStreamingLLD;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VideoStreamingPlatform platform = new VideoStreamingPlatform();

        while (true) {
            System.out.println("\n[1] Register User\n[2] Upload Video\n[3] Browse Videos\n[4] Play Video\n[5] Watch History\n[6] Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter user name: ");
                    platform.registerUser(sc.nextLine());
                    break;
                case "2":
                    System.out.print("Title: ");
                    String t = sc.nextLine();
                    System.out.print("Description: ");
                    String d = sc.nextLine();
                    System.out.print("Duration (sec): ");
                    int dur = Integer.parseInt(sc.nextLine());
                    System.out.print("File path: ");
                    String path = sc.nextLine();
                    platform.upload(t, d, dur, path);
                    break;
                case "3":
                    platform.browseVideos();
                    break;
                case "4":
                    System.out.print("User ID: ");
                    int uid = Integer.parseInt(sc.nextLine());
                    System.out.print("Video ID: ");
                    int vid = Integer.parseInt(sc.nextLine());
                    platform.play(uid, vid);
                    break;
                case "5":
                    System.out.print("User ID: ");
                    int uid2 = Integer.parseInt(sc.nextLine());
                    platform.viewWatchHistory(uid2);
                    break;
                case "6":
                    System.out.println("👋 Exiting...");
                    return;
                default:
                    System.out.println("❗ Invalid input.");
            }
        }
    }
}