package lowleveldesign.universitycoursemanagement;

import java.util.ArrayList;
import java.util.List;

public class ManageDepartment {
    private final List<Department> departmentList;


    public ManageDepartment(Department department) {
        this.departmentList = new ArrayList<Department>();
    }

    public void addHeadofDpt(Department department) {
        this.departmentList.add(department);
    }


    public void addHeadOfDepartment(Professor professor, String departmentName) {
        for (Department dept : departmentList) {
            if (dept.getDepartmentName().equalsIgnoreCase(departmentName)) {
                dept.setHeadofDepartment(professor.getName());
                System.out.println("Head of " + departmentName + " set to " + professor.getName());
                return;
            }
        }
        System.out.println("Department not found: " + departmentName);
    }
}


