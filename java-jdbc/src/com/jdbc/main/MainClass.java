package com.jdbc.main;

import java.util.Scanner;

import com.jdbc.model.Employee;
import com.jdbc.services.DatabaseService;

public class MainClass {

	public static void main(String[] args) {

		DatabaseService databaseService = new DatabaseService();

		try (Scanner scanner = new Scanner(System.in);) {
			boolean isrunning = true;
			while (isrunning) {

				System.out.println("Enter your choice: ");
				System.out.println("1. Insert ");
				System.out.println("2. Select All ");
				System.out.println("3. Select Employee by ID ");
				System.out.println("4. Delete Employee ");
				System.out.println("5. Update Employee ");
				System.out.println("6. Exit ");

				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1: // Insert operation
					System.out.println("Enter Name, Address, salary: ");
					databaseService.insertEmployee(new Employee(scanner.nextLine(), scanner.nextLine(),
							Double.parseDouble(scanner.nextLine())));
					break;
				case 2:
					databaseService.getAllEmployees();
					break;
				case 3:
					System.out.println("Enter the ID");
					databaseService.getEmployeebyID(Integer.parseInt(scanner.nextLine()));
					break;
				case 4:
					System.out.println("Enter the ID you want to delete record of: ");
					databaseService.deleteEmployeeByID(Integer.parseInt(scanner.nextLine()));
					break;
				case 5:
					boolean isFound=false;
					System.out.println("Enter the ID: ");
					int updateid = Integer.parseInt(scanner.nextLine());
					isFound = databaseService.getEmployeebyID(updateid);
					System.out.println(isFound);
					if(isFound) {
						System.out.println("Enter Employee Name, Address, Salary: ");
						Employee employee =new Employee(updateid, scanner.nextLine(), 
								scanner.nextLine(), 
								Double.parseDouble(scanner.nextLine()));
						databaseService.updateEmployee(employee);
					}
					break;

				case 6:
					System.out.println("Thank you");
					isrunning = false;
					break;

				default:
					System.out.println("Incorrect Choice");
					break;
				}
			}
		}

	}

}
