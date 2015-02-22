package com.cortez.willie.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.cortez.willie.core.Employee;
import com.cortez.willie.dao.EmployeeDAO;

public class EmployeeSearchApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JTable table;

	private EmployeeDAO employeeDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					EmployeeSearchApp frame = new EmployeeSearchApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeSearchApp() {
		try {
			employeeDAO = new EmployeeDAO();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "Error : " + e2, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		setTitle("Employee Search Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblEnterEmployeesLast = new JLabel("Enter Employees Last Name");
		panel.add(lblEnterEmployeesLast);

		lastNameTextField = new JTextField();
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get last name from the text field

				// Call DAO and get employees for the last name

				// If last name is empty, then get all employees

				// Print out employees
				try {
					String lastName = lastNameTextField.getText();
					List<Employee> employees = null;
					if (lastName != null && lastName.trim().length() > 0) {
						employees = employeeDAO.searchEmployees(lastName);
					} else {
						employees = employeeDAO.getAllEmployees();
					}
					EmployeeTableModel model = new EmployeeTableModel(employees);

					table.setModel(model);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane
							.showMessageDialog(EmployeeSearchApp.this,
									"Error : " + e1, "Error",
									JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
