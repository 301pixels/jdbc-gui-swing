package com.cortez.willie.main;

import com.cortez.willie.dao.EmployeeDAO;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		EmployeeDAO dao = new EmployeeDAO();

		// System.out.println(dao.getAllEmployees());

		System.out.println(dao.searchEmployees("J"));
	}

}
