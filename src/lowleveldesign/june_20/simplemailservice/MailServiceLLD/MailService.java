package lowleveldesign.june_20.simplemailservice.MailServiceLLD;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MailService {
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<Integer, Email> globalEmails = new HashMap<>();
    private final AtomicInteger emailIdGen = new AtomicInteger(0);
    private final AtomicInteger userIdGen = new AtomicInteger(0);

    public int registerUser(String name) {
        int id = userIdGen.getAndIncrement();
        users.put(id, new User(id, name));
        System.out.println("✅ Registered user: " + name + " (ID: " + id + ")");
        return id;
    }

    public void sendEmail(int senderId, int receiverId, String subject, String body) {
        int emailId = emailIdGen.getAndIncrement();
        Email email = new Email(emailId, senderId, receiverId, subject, body);
        globalEmails.put(emailId, email);

        users.get(senderId).addEmailView(new EmailView(emailId, EnumSet.of(Tag.SENT)));
        users.get(receiverId).addEmailView(new EmailView(emailId, EnumSet.of(Tag.INBOX)));

        System.out.println("✉️ Email sent from User" + senderId + " to User" + receiverId);
    }

    public void viewEmails(int userId, Tag tag) {
        User user = users.get(userId);
        List<EmailView> filtered = user.getEmailViews().stream()
            .filter(view -> view.hasTag(tag))
            .collect(Collectors.toList());

        System.out.println("📂 " + tag + " for User " + userId + ":");
        for (EmailView view : filtered) {
            Email email = globalEmails.get(view.getEmailId());
            System.out.println("- [" + email.getTimestamp() + "] From: " + email.getSenderId() +
                    " | To: " + email.getReceiverId() +
                    " | Subject: " + email.getSubject());
        }
    }

    public void deleteEmail(int userId, int emailId) {
        User user = users.get(userId);
        for (EmailView view : user.getEmailViews()) {
            if (view.getEmailId() == emailId) {
                view.removeTag(Tag.INBOX);
                view.removeTag(Tag.SENT);
                view.addTag(Tag.TRASH);
                System.out.println("🗑️ Email " + emailId + " moved to trash for User " + userId);
                return;
            }
        }
        System.out.println("❌ Email not found.");
    }
}