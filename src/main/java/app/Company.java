package app;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Company {

    public void runStreamExercise(){
        System.out.println("Velkommen til Firmaet");

        List<Employee> employees = List.of(
                new Employee("Benny Olsen",     LocalDate.of(1985, 2, 10), "Salg",        34000),
                new Employee("Anna Larsen",     LocalDate.of(1998, 7, 21), "Marketing",   31000),
                new Employee("Thomas Nielsen",  LocalDate.of(1975, 11, 3), "IT",          52000),
                new Employee("Sara Madsen",     LocalDate.of(1992, 4, 14), "HR",          38000),
                new Employee("Jonas Pedersen",  LocalDate.of(2001, 9, 30), "Support",     28000),
                new Employee("Lene Kristensen", LocalDate.of(1968, 1, 5),  "Ledelse",     65000),
                new Employee("Mikkel Hansen",   LocalDate.of(1989, 6, 18), "IT",          47000),
                new Employee("Fatima Ali",      LocalDate.of(1995, 2, 8), "Økonomi",     42000),
                new Employee("Peter Sørensen",  LocalDate.of(1983, 3, 25), "Produktion",  36000),
                new Employee("Emma Jensen",     LocalDate.of(2003, 5, 2),  "Support",     22000)
        );

        // Udskriv alle medarbejdere

        employees.forEach(employee -> System.out.println(employee));

        // Beregn gennemsnitsalder

        double avgAge = employees.stream()
                .collect(Collectors.averagingInt(e -> e.getAge()));

        System.out.println("Gennemsnitsalder på fabrikken: " + avgAge + " år");

        // Højeste månedsløn

        Employee bigMoney = employees.stream()
                .max(Comparator.comparingInt( e -> e.getSalary()))
                .get();

        System.out.println("Big money employee of the month: " + bigMoney.getName() + ": " + bigMoney.getSalary());

        // Gennemsnitsløn pr. afdeling ->

        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment() ,
                        Collectors.averagingInt(e -> e.getSalary())));

        // avgSalaryByDept.forEach( (dept, salary) -> System.out.println("Afdeling: " + dept + ", gennemsnitsløn: " + salary));
        avgSalaryByDept.forEach( (dept, salary) ->
                System.out.println( String.format("Afdeling: %s, gennemsnitsløn: %.2f kr.", dept, salary)));


        // Antal ansatte pr. afdeling

        Map<String, Long> countEmployeesByDept = employees.stream()
                .collect(Collectors.groupingBy( e -> e.getDepartment(), Collectors.counting()));

        countEmployeesByDept.forEach( (dept, count) -> System.out.println("Dept: " + dept + ", " + count + " medarbejdere"));

        // De tre ældste medarbejdere

        List<Employee> oldies = employees.stream()
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .limit(3)
                .toList();

        oldies.forEach(e -> System.out.println(e));

        int limit = 40000;

        List<Employee> highRollers = employees.stream()
                .filter(e -> e.getSalary() > limit)
                .toList();

        System.out.println("**** Tjener over " + limit + " kr.");

        highRollers.forEach(e -> System.out.println(e));

        // Vi alle der er født i februar

        System.out.println("**** Februar ansatte *******");

        List<Employee> februarFolk = employees.stream()
                .filter( e -> e.getBirthDate().getMonth() == Month.FEBRUARY)
                .toList();
        februarFolk.forEach( e -> System.out.println(e));

        // Gruppér efter fødselsmåned -> vi skal bruge et MAP - count til at tælle

        System.out.println("****** Antal medarbejdere per måned ********");

        Map<Month, Long> countPerMonth = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getBirthDate().getMonth(), TreeMap::new,
                        Collectors.counting()));

        countPerMonth.forEach( (month, count) -> System.out.println("Month: " + month + ", antal: " + count));

    }
}