package lld.universitycoursemanagement;

public class Professor extends  User{
    private  String name;
    private String id;
    private  String subjectExpertise;


    public Professor(String name, Role role, String id) {
        super(name, role, id);
    }


}
