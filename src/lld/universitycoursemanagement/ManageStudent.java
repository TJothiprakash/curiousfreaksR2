package lld.universitycoursemanagement;

import java.util.ArrayList;
import java.util.List;

public class ManageStudent {
    private StudentRepository studentRepository;

    public ManageStudent(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {

        studentRepository.studentList.add(student);

    }

}

class StudentRepository {
    public List<Student> studentList;

    public StudentRepository() {
        studentList = new ArrayList<>();
    }

}