package lld.universitycoursemanagement;

import java.util.List;

public class Department {
    private String departmentName;
    private String headofDepartment;
    private List<Course> courseList;

    public String getDepartmentName() {

        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadofDepartment() {
        return headofDepartment;
    }

    public void setHeadofDepartment(String headofDepartment) {
        this.headofDepartment = headofDepartment;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

}
