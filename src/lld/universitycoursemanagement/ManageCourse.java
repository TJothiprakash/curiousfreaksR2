package lld.universitycoursemanagement;

import java.util.ArrayList;
import java.util.List;

public class ManageCourse {

    private CourseRepository courseRepository;

    public ManageCourse(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;


    }

    public void createCourse(Course course) {
        courseRepository.getCourseList().add(course);
    }


    public CourseRepository getCourseRepository() {

        return courseRepository;
    }

    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void assignProfessor(Professor professor, Course course) {

    }
}

class CourseRepository {
    private List<Course> courseList;

    public CourseRepository() {
        this.courseList = new ArrayList<Course>();
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}