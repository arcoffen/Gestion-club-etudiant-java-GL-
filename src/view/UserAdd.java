package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import appli.UsersDAO;
import model.Users;

import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class UserAdd extends JFrame {

	private JPanel contentPane;
	private UsersDAO userDAO;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAdd frame = new UserAdd();
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
	public UserAdd() {
		setTitle("GestionUsers");
		//create the DAO 
				try {
					 userDAO = new UsersDAO();
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField username_f = new JTextField();
		username_f.setFont(new Font("Tahoma", Font.PLAIN, 13));
		username_f.setBounds(217, 8, 172, 23);
		contentPane.add(username_f);
		username_f.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(111, 12, 96, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnAjouter = new JButton("");
		btnAjouter.setBackground(Color.LIGHT_GRAY);
		btnAjouter.setIcon(new ImageIcon(UserAdd.class.getResource("/img/business_application_addmale_useradd_insert_add_user_client_2312.png")));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				UserDialog dialog = new UserDialog(UserAdd.this, userDAO);

				// show dialog
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnAjouter.setBounds(514, 74, 44, 43);
		contentPane.add(btnAjouter);
		
		JButton btnModifier = new JButton("");
		btnModifier.setBackground(Color.LIGHT_GRAY);
		btnModifier.setIcon(new ImageIcon(UserAdd.class.getResource("/img/businessapplication_edit_male_user_thepencil_theclient_negocio_2321.png")));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(UserAdd.this, "You must select a user ", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current user
				Users tempUser = (Users) table.getValueAt(row, UserTableModel.OBJECT_COL);
				
				// create dialog
				UserDialog dialog = new UserDialog(UserAdd.this, userDAO, 
															tempUser, true);
				

				// show dialog
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}	
	});
		btnModifier.setBounds(514, 140, 44, 43);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("");
		btnSupprimer.setBackground(Color.LIGHT_GRAY);
		btnSupprimer.setIcon(new ImageIcon(UserAdd.class.getResource("/img/delete_delete_deleteusers_delete_male_user_maleclient_2348.png")));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(UserAdd.this, 
								"You must select a User", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							UserAdd.this, "Delete this user?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current employee
					Users tempuser = (Users) table.getValueAt(row, UserTableModel.OBJECT_COL);
				
					// delete the user
					userDAO.deleteUser(tempuser.getId());

					// refresh GUI
					refreshUserView();

					// show success message
					JOptionPane.showMessageDialog(UserAdd.this,
							"User deleted succesfully.", "User Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(UserAdd.this,
							"Error deleting user: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnSupprimer.setBounds(514, 208, 44, 42);
		contentPane.add(btnSupprimer);
		
		JButton btnQuiter = new JButton("");
		btnQuiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuiter.setBackground(Color.LIGHT_GRAY);
		btnQuiter.setIcon(new ImageIcon(UserAdd.class.getResource("/img/Button-circle-previous_icon-icons.com_52229.png")));
		btnQuiter.setBounds(10, 8, 51, 43);
		contentPane.add(btnQuiter);
		
		JButton btnChercher = new JButton("Chercher");
		btnChercher.setBackground(Color.LIGHT_GRAY);
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get username from the text field

				// Call DAO and get users for the username

				// If username is empty, then get all users

				// Print out users				
				
				try {
					String username = username_f.getText();

					List<Users> user = null;

					if (username != null && username.trim().length() > 0) {
						user = userDAO.searchUsers(username);
					} else {
						user = userDAO.getAllUsers();
					}
					
					// create the model and update the "table"
					UserTableModel model = new UserTableModel(user);
					
					table.setModel(model);
					
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(UserAdd.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		btnChercher.setBounds(469, 7, 89, 23);
		contentPane.add(btnChercher);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 477, 176);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"id", "username", "poste"
			}
		));
		scrollPane.setViewportView(table);
	}
	public void refreshUserView() {

		try {
			List<Users> user = userDAO.getAllUsers();

			// create the model and update the "table"
			UserTableModel model = new UserTableModel(user);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
