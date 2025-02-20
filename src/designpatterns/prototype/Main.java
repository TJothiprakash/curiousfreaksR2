package designpatterns.prototype;

public class Main
{
    public static void main(String[] args) {
        Student student = new Student("John", 20, "1234 Main St");
        Student studentClone = student.clone();
        System.out.println(student);
        System.out.println(studentClone);
    }
}
