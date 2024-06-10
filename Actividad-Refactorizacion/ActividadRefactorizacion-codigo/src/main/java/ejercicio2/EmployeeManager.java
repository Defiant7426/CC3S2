package ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private List<Employee> employees = new ArrayList<>();
    private DepartmentManager departmentManager;

    public EmployeeManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public void addEmployee(String name, String department) {
        if(departmentManager.getDepartment(department) == null) {
            throw new IllegalArgumentException("Department does not exist.");
        }
        employees.add(new Employee(name, departmentManager.getDepartment(department)));
    }

    public void removeEmployee(String name) {

        employees.removeIf(employee -> employee.getName().equals(name));
    }

    public void changeDepartment(String employeeName, String newDepartment) {
        employees.stream()
                .filter(employee -> employee.getName().equals(employeeName))
                .forEach(employee -> employee.setDepartment(new Department(newDepartment)));
    }

    public void printAllEmployees() {
        System.out.println("\nAll Employees:");
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " - " + employee.getDepartment().getName());
        }
        System.out.println();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
