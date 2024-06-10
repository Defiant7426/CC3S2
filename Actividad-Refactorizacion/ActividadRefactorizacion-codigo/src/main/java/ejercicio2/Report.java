package ejercicio2;

public class Report {

    private EmployeeManager employeeManager;

    public Report(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public void printDepartmentReport(String departmentName) {
        System.out.println("Employees in department " + departmentName);
        for (Employee employee : employeeManager.getEmployees()) {
            if (employee.getDepartment().getName().equals(departmentName)) {
                System.out.println(employee.getName());
            }
        }
    }
}
