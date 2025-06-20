package lowleveldesign.june_20.simplemailservice.MailServiceLLD;

import java.util.*;

public class User {
    private final int id;
    private final String name;
    private final List<EmailView> emailViews = new ArrayList<>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<EmailView> getEmailViews() { return emailViews; }

    public void addEmailView(EmailView view) {
        emailViews.add(view);
    }
}