package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeProgram {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = scanner.nextLine();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            List <Employee> employeeList = new ArrayList<>();

            String line = bufferedReader.readLine();
            while (line != null) {
                String[ ] fields = line.split(",");
                employeeList.add(new Employee(fields[1], fields[2], Double.parseDouble(fields[3])));
                line = bufferedReader.readLine();
            }

            System.out.print("Enter salary: ");
            double salary = scanner.nextDouble();

            List <String> emails = employeeList.stream()
                    .filter(employee -> employee.getSalary() > salary)
                    .map(Employee::getEmail).toList();

            System.out.printf("Email of people whose salary is more than %.2f\n", salary);
            emails.forEach(System.out::println);

            double sum = employeeList.stream()
                    .filter(employee -> employee.getName().charAt(0) == 'M')
                    .map(Employee::getSalary)
                    .reduce(0.0, Double::sum);

            System.out.printf("Sum of salary from people whose name starts with 'M': %.2f", sum);

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

















        scanner.close();
    }
}