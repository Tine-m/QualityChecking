package app;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CompanyRefactored {

    private List<Employee> employees = List.of(
            new Employee("Benny Olsen",     LocalDate.of(1985, 2, 10), "Salg",        34000),
            new Employee("Anna Larsen",     LocalDate.of(1998, 7, 21), "Marketing",   52000),
            new Employee("Thomas Nielsen",  LocalDate.of(1975, 11, 3), "IT",          52000),
            new Employee("Sara Madsen",     LocalDate.of(1992, 4, 14), "HR",          38000),
            new Employee("Jonas Pedersen",  LocalDate.of(2001, 9, 30), "Support",     28000),
            new Employee("Lene Kristensen", LocalDate.of(1968, 1, 5),  "Ledelse",     65000),
            new Employee("Mikkel Hansen",   LocalDate.of(1989, 6, 18), "IT",          47000),
            new Employee("Fatima Ali",      LocalDate.of(1995, 2, 8), "Økonomi",     42000),
            new Employee("Peter Sørensen",  LocalDate.of(1983, 3, 25), "Produktion",  36000),
            new Employee("Emma Jensen",     LocalDate.of(2003, 5, 2),  "Support",     22000)
            );

    //default constructor
    public CompanyRefactored() {
    }

    //overloaded constructor
    public CompanyRefactored(List<Employee> employees) {
        this.employees = employees;
    }

    public void welcomeGreeting() {
        System.out.println("Velkommen til Firmaet");
    }

    public void printAllEmployees () {   // Udskriv alle medarbejdere
        employees.forEach(employee -> System.out.println(employee));
    }

    public List<Employee> getAllEmployees () {   // Hent alle medarbejdere
        return employees;

    }

    public double calculateAverageAge () {  // Beregn gennemsnitsalder
        double avgAge = employees.stream()
                .collect(Collectors.averagingInt(e -> e.getAge()));

        System.out.println("Gennemsnitsalder på fabrikken: " + avgAge + " år");
        return avgAge;
    }

    public Employee findHighestPaidEmployee () { // Højeste månedsløn
        Employee bigMoney = employees.stream()
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .get();

        System.out.println("Big money employee of the month: " + bigMoney.getName() + ": " + bigMoney.getSalary());
        return bigMoney;
    }

    public Map<String, Double> findAverageSalaryPerDepartment () {
        // Gennemsnitsløn pr. afdeling ->

        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment(),
                        Collectors.averagingInt(e -> e.getSalary())));

        // avgSalaryByDept.forEach( (dept, salary) -> System.out.println("Afdeling: " + dept + ", gennemsnitsløn: " + salary));
        avgSalaryByDept.forEach((dept, salary) ->
                System.out.println(String.format("Afdeling: %s, gennemsnitsløn: %.2f kr.", dept, salary)));

        return avgSalaryByDept;
    }

   public List<Employee> findOldestEmployees() { // De tre ældste medarbejdere

       List<Employee> oldies = employees.stream()
               .sorted(Comparator.comparingInt(Employee::getAge).reversed())
               .limit(3)
               .toList();

       oldies.forEach(e -> System.out.println(e));
       return oldies;
   }

    public List<Employee> findHighRollers() { // Highest paid employees above limit
        int limit = 40000;

        List<Employee> highRollers = employees.stream()
                .filter(e -> e.getSalary() > limit)
                .toList();

        System.out.println("**** Tjener over " + limit + " kr.");

        highRollers.forEach(e -> System.out.println(e));
        return highRollers;
    }
}
