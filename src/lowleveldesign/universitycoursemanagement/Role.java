package lowleveldesign.universitycoursemanagement;

public enum Role {
    STUDENT("Student"),
    PROFESSOR("Professor"),
    ADMIN("Admin");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public static Role fromString(String roleStr) {
        for (Role r : Role.values()) {
            if (r.getRole().equalsIgnoreCase(roleStr.trim())) {
                return r;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + roleStr);
    }

    public String getRole() {
        return role;
    }
}
