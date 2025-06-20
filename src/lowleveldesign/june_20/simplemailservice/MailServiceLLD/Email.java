package lowleveldesign.june_20.simplemailservice.MailServiceLLD;

import java.time.LocalDateTime;

public class Email {
    private final int id;
    private final int senderId;
    private final int receiverId;
    private final String subject;
    private final String body;
    private final LocalDateTime timestamp;

    public Email(int id, int senderId, int receiverId, String subject, String body) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.subject = subject;
        this.body = body;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() { return id; }
    public int getSenderId() { return senderId; }
    public int getReceiverId() { return receiverId; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public LocalDateTime getTimestamp() { return timestamp; }
}