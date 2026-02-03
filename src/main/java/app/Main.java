package app;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Kør firma kode
        //Company company = new Company();
        //company.runStreamExercise();

        // Kør NY firma kode
        CompanyRefactored companyNew = new CompanyRefactored();
        //companyNew.welcomeGreeting();
       List<Employee> result = companyNew.getAllEmployees();
        result.forEach(employee -> System.out.println(employee));
    }
}
