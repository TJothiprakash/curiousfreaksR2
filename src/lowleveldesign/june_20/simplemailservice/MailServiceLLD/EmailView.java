package lowleveldesign.june_20.simplemailservice.MailServiceLLD;

import java.util.EnumSet;

public class EmailView {
    private final int emailId;
    private final EnumSet<Tag> tags;

    public EmailView(int emailId, EnumSet<Tag> tags) {
        this.emailId = emailId;
        this.tags = tags;
    }

    public int getEmailId() { return emailId; }
    public EnumSet<Tag> getTags() { return tags; }

    public boolean hasTag(Tag tag) {
        return tags.contains(tag);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
}