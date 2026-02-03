package app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyRefactoredTest {

    CompanyRefactored companyNew;

    List<Employee> testData = List.of(
            new Employee("Benny Olsen",     LocalDate.of(2000, 1, 1), "Salg",34000),
            new Employee("Anna Larsen",     LocalDate.of(2010, 1, 1), "Salg", 52000),
            new Employee("Thomas Nielsen",  LocalDate.of(2020, 1, 1), "IT",   52000));

    @BeforeEach
    public void setup() {
        companyNew = new CompanyRefactored(testData);
    }


    @Test
    void getAllEmployees() {
        //Arrange - see setup
        // Act
        List<Employee> result = companyNew.getAllEmployees();
        //Assert
        assertEquals(3, result.size());
    }

    @Test
    void calculateAverageAge() {
        //Arrange - see setup
        // Act
        double result = companyNew.calculateAverageAge();
        // Assert
        assertEquals(16, result);

    }

    @Test
    void findHighestPaidEmployee() {
        //Act
        List<Employee> highestPaidEmployees = companyNew.findHighestPaidEmployee();
        Employee emp1 = highestPaidEmployees.getFirst();
        Employee emp2 = highestPaidEmployees.get(1);
        //Assert
        assertEquals(52000, emp1.getSalary());
        assertEquals(52000, emp2.getSalary());
    }

 /*   @Test
    void findMultipleHighestPaidEmployees() {
        //Act
        Employee highestPaidEmployee = companyNew.findHighestPaidEmployee();
        //Assert
        assertEquals(52000, highestPaidEmployee.getSalary());
    }
*/
    @Disabled
    @Test
    void findAverageSalaryPerDepartment() {
    }

    @Test
    void findOldestEmployees() {
    }

    @Test
    void findHighRollers() {
    }
}