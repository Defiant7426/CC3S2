package ejercicio2;

public class Main {
    public static void main(String[] args) {
        DepartmentManager departmentManager = new DepartmentManager();
        departmentManager.addDepartment("IT");
        departmentManager.addDepartment("HR");
        departmentManager.addDepartment("Finance");
        departmentManager.addDepartment("Marketing");
        departmentManager.printAllDepartments();

        EmployeeManager employeeManager = new EmployeeManager(departmentManager);
        employeeManager.addEmployee("John", "IT");
        employeeManager.addEmployee("Jane", "HR");
        employeeManager.addEmployee("Doe", "Finance");
        employeeManager.addEmployee("Smith", "Marketing");
        employeeManager.addEmployee("Jannice", "IT");
        employeeManager.addEmployee("Fernanda", "HR");
        employeeManager.addEmployee("David", "Finance");
        employeeManager.addEmployee("Luis", "Marketing");

        employeeManager.printAllEmployees();

        employeeManager.removeEmployee("Jannice");
        employeeManager.changeDepartment("David", "IT");

        employeeManager.printAllEmployees();

        Report report = new Report(employeeManager);
        report.printDepartmentReport("IT");
    }
}
