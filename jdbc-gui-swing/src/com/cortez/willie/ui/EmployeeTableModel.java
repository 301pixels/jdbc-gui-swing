package com.cortez.willie.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.cortez.willie.core.Employee;

@SuppressWarnings("serial")
class EmployeeTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private static final int LAST_NAME_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int DEPARTMENT_COL = 3;
	private static final int SALARY_COL = 4;

	private String[] columnNames = { "Last Name", "First Name", "Email",
			"Department", "Salary" };

	private List<Employee> employees;

	public EmployeeTableModel(List<Employee> theEmployees) {
		employees = theEmployees;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return employees.size();
	}

	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Employee tempEmployee = employees.get(row);
		switch (col) {
		case LAST_NAME_COL:
			return tempEmployee.getLastName();
		case FIRST_NAME_COL:
			return tempEmployee.getFirstName();
		case EMAIL_COL:
			return tempEmployee.getEmail();
		case DEPARTMENT_COL:
			return tempEmployee.getDepartment();
		case SALARY_COL:
			return tempEmployee.getSalary();
		default:
			return tempEmployee.getLastName();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
