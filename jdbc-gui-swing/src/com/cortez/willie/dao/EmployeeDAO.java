package com.cortez.willie.dao;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cortez.willie.core.Employee;

public class EmployeeDAO {

	private Connection myConn;

	public EmployeeDAO() throws Exception {

		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");

		myConn = DriverManager.getConnection(dburl, user, password);

		System.out.println("DB connection successful to : " + dburl);
	}

	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> list = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from employees");

			while (myRs.next()) {
				Employee tempEmployee = convertRowToEmployee(myRs);
				list.add(tempEmployee);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Employee> searchEmployees(String lastName) throws Exception {
		List<Employee> list = new ArrayList<>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName += "%";
			myStmt = myConn
					.prepareStatement("select * from employees where last_name like ?");
			myStmt.setString(1, lastName);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Employee tempEmployee = convertRowToEmployee(myRs);
				list.add(tempEmployee);

			}

			return list;

		} finally {
			close(myStmt, myRs);
		}

	}

	private Employee convertRowToEmployee(ResultSet myRs) throws SQLException {
		int id = myRs.getInt("id");
		String lastName = myRs.getString("last_Name");
		String firstName = myRs.getString("first_Name");
		String email = myRs.getString("email");
		String department = myRs.getString("department");
		BigDecimal salary = myRs.getBigDecimal("salary");

		Employee tempEmployee = new Employee(id, lastName, firstName, email,
				department, salary);
		return tempEmployee;
	}

	private static void close(Connection myConn, Statement myStmt,
			ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}
		if (myRs != null) {
			myRs.close();
		}
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws Exception {
		close(null, myStmt, myRs);
	}

}
