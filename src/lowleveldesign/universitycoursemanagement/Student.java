package lowleveldesign.universitycoursemanagement;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private final int MAX_COURSES_ENROLLMENT = 5;
    private String name;
    private String id;
    private String department;
    private final List<Course> enrolledCourses;

    public Student(String name, Role role, String id, String department) {
        super(name, role, id);
        this.name = name;
        this.department = department;
        this.enrolledCourses = new ArrayList<>(MAX_COURSES_ENROLLMENT);
    }


    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
