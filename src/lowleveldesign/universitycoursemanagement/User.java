package lowleveldesign.universitycoursemanagement;

public abstract class User {
    protected String name;
    protected Role role;
    protected String id;

    public User(String name, Role role, String id) {
        this.name = name;
        this.role = role;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public String getId() {
        return id;
    }
}
