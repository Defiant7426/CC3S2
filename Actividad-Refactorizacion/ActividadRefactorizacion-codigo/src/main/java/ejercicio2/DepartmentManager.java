package ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class DepartmentManager {
    private List<Department> departments = new ArrayList<>();

    public void addDepartment(String department) {
        departments.add(new Department(department));
    }

    public Department getDepartment(String department) {
        return departments.stream()
                .filter(depart -> depart.getName().equals(department))
                .findFirst()
                .orElse(null);
    }

    public void printAllDepartments() {
        for (Department department : departments) {
            System.out.println(department.getName());
        }
    }
}
