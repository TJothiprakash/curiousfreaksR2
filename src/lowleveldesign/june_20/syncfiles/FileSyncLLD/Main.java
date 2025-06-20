package lowleveldesign.june_20.syncfiles.FileSyncLLD;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileSyncService service = new FileSyncService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n[1] Upload File\n[2] Update File\n[3] Delete File\n[4] Sync\n[5] List Files\n[6] Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter file name: ");
                    String uploadName = sc.nextLine();
                    System.out.print("Enter content: ");
                    String content = sc.nextLine();
                    service.uploadFile(uploadName, content);
                    break;
                case "2":
                    System.out.print("Enter file name to update: ");
                    String updateName = sc.nextLine();
                    System.out.print("New content: ");
                    String newContent = sc.nextLine();
                    service.updateFile(updateName, newContent);
                    break;
                case "3":
                    System.out.print("Enter file name to delete: ");
                    String deleteName = sc.nextLine();
                    service.deleteFile(deleteName);
                    break;
                case "4":
                    service.syncFiles();
                    break;
                case "5":
                    service.listFiles();
                    break;
                case "6":
                    System.out.println("👋 Exiting...");
                    return;
                default:
                    System.out.println("❗ Invalid choice");
            }
        }
    }
}