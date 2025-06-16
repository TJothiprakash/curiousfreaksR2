package lowleveldesign.universitycoursemanagement;

public class Course {
    private String courseName;
    private String courseId;
    private String Department;
    private String description;

    public Course(String courseName, String department, String description) {

        this.courseName = courseName;
        Department = department;
        this.description = description;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
