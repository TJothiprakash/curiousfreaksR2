package lowleveldesign.june_20.syncfiles.FileSyncLLD;

import java.time.LocalDateTime;

public class SyncedFile {
    private final String fileName;
    private String content;
    private int version;
    private LocalDateTime lastModified;
    private FileStatus status;

    public SyncedFile(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.version = 1;
        this.lastModified = LocalDateTime.now();
        this.status = FileStatus.SYNCED;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
        this.version++;
        this.lastModified = LocalDateTime.now();
        this.status = FileStatus.MODIFIED;
    }

    public void markDeleted() {
        this.status = FileStatus.DELETED;
        this.lastModified = LocalDateTime.now();
    }

    public String getFileName() { return fileName; }
    public String getContent() { return content; }
    public int getVersion() { return version; }
    public LocalDateTime getLastModified() { return lastModified; }
    public FileStatus getStatus() { return status; }

    public void markSynced() {
        this.status = FileStatus.SYNCED;
    }
}