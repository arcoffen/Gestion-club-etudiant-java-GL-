package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import appli.UsersDAO;
import model.Users;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Color;
import java.awt.List;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class UserDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField username_f;
	private JLabel pass_f;
	
	private UsersDAO userDAO;

	private UserAdd useradd;

	private Users previousUser = null;
	private boolean updateMode = false;
	private JTextField poste_f;
	private JPasswordField password_f;
	

	
	public UserDialog(UserAdd theUserAdd,
			UsersDAO theuserDAO, Users thePreviousUser, boolean theUpdateMode) {
		this();
		userDAO = theuserDAO;
		useradd = theUserAdd;

		previousUser = thePreviousUser;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update User");
			password_f.setVisible(false);
			pass_f.setText("");
			populateGui(previousUser);
			
		}
	}
	private void populateGui(Users theuser) {

		//id_f.setText(String.valueOf(theuser.getId()));
		username_f.setText(theuser.getUsername());
		poste_f.setText(theuser.getPoste());
		//password_f.setText(theuser.getPassword());		
	}
	public UserDialog(UserAdd theUserAdd,
			UsersDAO theuserDAO) {
		this(theUserAdd, theuserDAO, null, false);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UserDialog dialog = new UserDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void saveUser() {

		// get the User info from gui
		//int id = Integer.parseInt(id_f.getText());
		String username = username_f.getText();
		String poste = poste_f.getText();
		String password = password_f.getText();

		

		Users tempuser = null;

		if (updateMode) {
			tempuser = previousUser;
			
			//tempuser.setId(id);
			tempuser.setUsername(username);
			tempuser.setPoste(poste);
			tempuser.setPassword(password);
			
		} else {
			tempuser = new Users( username, poste, password);
		}

		try {
			// save to the database
			if (updateMode) {
				userDAO.updateUser(tempuser);
			} else {
				userDAO.addUser(tempuser);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			useradd.refreshUserView();

			// show success message
			JOptionPane.showMessageDialog(useradd,
					"user saved succesfully.", "user Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(useradd,
					"Error saving user: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Create the dialog.
	 */
	public UserDialog() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 584, 300);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(Color.GRAY);
			panel.setBounds(0, 0, 257, 261);
			getContentPane().add(panel);
			{
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(UserDialog.class.getResource("/img/images (1).jpg")));
				label.setBounds(-62, 11, 370, 230);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Hello World!");
				label.setFont(new Font("AR DARLING", Font.ITALIC, 13));
				label.setBounds(88, 227, 87, 14);
				panel.add(label);
			}
		}
		{
			JLabel lblSource = new JLabel("Username");
			lblSource.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSource.setBounds(285, 48, 64, 14);
			getContentPane().add(lblSource);
		}
		
		{
			JLabel lblDate = new JLabel("Poste");
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDate.setBounds(285, 121, 64, 14);
			getContentPane().add(lblDate);
		}
		{
			 pass_f = new JLabel("Password");
			pass_f.setFont(new Font("Tahoma", Font.PLAIN, 13));
			pass_f.setBounds(285, 188, 64, 14);
			getContentPane().add(pass_f);
		}
		
		
	
		
		JButton btnSave = new JButton("Save");
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(Color.GRAY);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				saveUser();
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSave.setBounds(285, 227, 113, 23);
		getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(Color.GRAY);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancel.setBounds(421, 227, 113, 23);
		getContentPane().add(btnCancel);
		
		username_f = new JTextField();
		username_f.setBounds(383, 46, 151, 20);
		getContentPane().add(username_f);
		username_f.setColumns(10);
		
		poste_f = new JTextField();
		poste_f.setBounds(383, 119, 151, 20);
		getContentPane().add(poste_f);
		poste_f.setColumns(10);
		
		password_f = new JPasswordField();
		password_f.setBounds(383, 186, 151, 20);
		getContentPane().add(password_f);
	}
}