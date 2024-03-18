package com.jdbc.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.model.Employee;
import com.jdbc.util.DatabaseUtil;
import com.jdbc.util.QueryUtil;

public class DatabaseService {

	DatabaseUtil databaseUtil = new DatabaseUtil();

	public void insertEmployee(Employee employee) {

		try (Connection connection = databaseUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareCall(QueryUtil.insertEmployeeQuery());) {

			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getEmployeeAddress());
			preparedStatement.setDouble(3, employee.getEmployeeSalary());

			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				System.out.println("Record inserted Sccessfully!!!");
			} else {
				System.out.println("Failed to insert record.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// End of Insert Employee method

	public void getAllEmployees() {
		try (Connection connection = databaseUtil.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllEmployee());) {

			while (resultSet.next()) {
				printEmployee(new Employee(resultSet.getInt("EMPLOYEE_ID"), resultSet.getString("EMPLOYEE_NAME"),
						resultSet.getString("EMPLOYEE_ADDRESS"), resultSet.getDouble("EMPLOYEE_SALARY")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// End of Get All Employees method

	public boolean getEmployeebyID(int id) {

		boolean isFound = false;
		try (Connection connection = databaseUtil.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(QueryUtil.selectEmployeebyID(id));) {
			if (resultSet.next()) {
				printEmployee(new Employee(resultSet.getInt("EMPLOYEE_ID"), resultSet.getString("EMPLOYEE_NAME"),
						resultSet.getString("EMPLOYEE_ADDRESS"), resultSet.getDouble("EMPLOYEE_SALARY")));
				isFound=true;
			} else {
				System.out.println("Record not found for ID: " + id);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isFound;
	}// end of get employee by id method

	private void printEmployee(Employee employee) {
		System.out.println("Employee id: " + employee.getEmployeeId());
		System.out.println("Employee Name: " + employee.getEmployeeName());
		System.out.println("Employee Address: " + employee.getEmployeeAddress());
		System.out.println("Employee Salary: " + employee.getEmployeeSalary());
		System.out.println("________________________________");
	}

	public void deleteEmployeeByID(int employeeID) {

		try (Connection connection = databaseUtil.getConnection();
				Statement statement = connection.createStatement();) {

			int rows = statement.executeUpdate(QueryUtil.deleteEmployeeById(employeeID));
			if (rows > 0) {
				System.out.println("Record Deleted successfully.");
			} else {
				System.out.println("Something went wrong. ");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// End of delete by id method

	public void updateEmployee(Employee employee) {

		try (Connection connection = databaseUtil.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(QueryUtil.updateEmployeeQuery(employee.getEmployeeId()))) {
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getEmployeeAddress());
			preparedStatement.setDouble(3, employee.getEmployeeSalary());
			
			int rows = preparedStatement.executeUpdate();
			if(rows >0) {
				System.out.println("Record Updated Successfully ");
			}else {
				System.out.println("Failed to update record. ");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// end of update employee method
}
