package lowleveldesign.june_20.syncfiles.FileSyncLLD;

import java.util.*;

public class FileSyncService {
    private final Map<String, SyncedFile> cloudFiles = new HashMap<>();

    public void uploadFile(String fileName, String content) {
        if (cloudFiles.containsKey(fileName)) {
            System.out.println("⚠️ File already exists. Use updateFile().");
            return;
        }
        cloudFiles.put(fileName, new SyncedFile(fileName, content));
        System.out.println("✅ Uploaded: " + fileName);
    }

    public void updateFile(String fileName, String newContent) {
        SyncedFile file = cloudFiles.get(fileName);
        if (file == null || file.getStatus() == FileStatus.DELETED) {
            System.out.println("❌ File not found or deleted.");
            return;
        }
        file.updateContent(newContent);
        System.out.println("✏️ Updated: " + fileName + " (v" + file.getVersion() + ")");
    }

    public void deleteFile(String fileName) {
        SyncedFile file = cloudFiles.get(fileName);
        if (file == null || file.getStatus() == FileStatus.DELETED) {
            System.out.println("❌ File not found or already deleted.");
            return;
        }
        file.markDeleted();
        System.out.println("🗑️ Deleted: " + fileName);
    }

    public void syncFiles() {
        System.out.println("🔄 Syncing files...");
        for (SyncedFile file : cloudFiles.values()) {
            if (file.getStatus() == FileStatus.MODIFIED || file.getStatus() == FileStatus.DELETED) {
                System.out.println("- Synced " + file.getFileName() + " | Version: " + file.getVersion());
                file.markSynced();
            }
        }
    }

    public void listFiles() {
        System.out.println("📁 Cloud Files:");
        for (SyncedFile file : cloudFiles.values()) {
            System.out.println("- " + file.getFileName() + " | v" + file.getVersion() + " | " + file.getStatus());
        }
    }
}