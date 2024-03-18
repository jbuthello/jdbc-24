package com.jdbc.util;

public class QueryUtil {

	public static String insertEmployeeQuery() {
		return "INSERT INTO EMPLOYEE_INFO (EMPLOYEE_NAME, EMPLOYEE_ADDRESS, EMPLOYEE_SALARY)VALUES(?,?,?)";
	}

	public static String selectAllEmployee() {
		return "SELECT * FROM EMPLOYEE_INFO;";
	}

	public static String selectEmployeebyID(int employeeId) {
		return "SELECT * FROM EMPLOYEE_INFO WHERE EMPLOYEE_ID = " + employeeId;
	}

	public static String deleteEmployeeById(int employeeId) {
		return "DELETE FROM EMPLOYEE_INFO WHERE EMPLOYEE_ID = " + employeeId;
	}

	public static String updateEmployeeQuery(int employeeId) {
		return "UPDATE EMPLOYEE_INFO SET EMPLOYEE_NAME = ?, EMPLOYEE_ADDRESS = ?, EMPLOYEE_SALARY = ? WHERE EMPLOYEE_ID = "+employeeId;
	}
}
