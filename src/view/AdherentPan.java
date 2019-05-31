package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import appli.AdherentDAO;
import appli.UsersDAO;
import model.Adherent;
import model.Users;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class AdherentPan extends JFrame {

	private JPanel contentPane;
	private JTextField firstname_f;
	private JTextField lastname_f;
	private JTable table;
	private AdherentDAO adherentDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdherentPan frame = new AdherentPan();
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
	public AdherentPan() {
		//create the DAO 
		try {
			 adherentDAO = new AdherentDAO();
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
		
		JLabel lblFirstname = new JLabel("firstname");
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFirstname.setBounds(90, 13, 89, 14);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("lastname");
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastname.setBounds(274, 13, 111, 14);
		contentPane.add(lblLastname);
		
		firstname_f = new JTextField();
		firstname_f.setBounds(153, 12, 111, 20);
		contentPane.add(firstname_f);
		firstname_f.setColumns(10);
		
		lastname_f = new JTextField();
		lastname_f.setBounds(342, 12, 117, 20);
		contentPane.add(lastname_f);
		lastname_f.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 474, 177);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"firstname", "lastname", "email", "filiere", "absence"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/img/business_application_addmale_useradd_insert_add_user_client_2312.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				AdherentDialog dialog = new AdherentDialog(AdherentPan.this, adherentDAO);

				// show dialog
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(510, 84, 48, 48);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/img/businessapplication_edit_male_user_thepencil_theclient_negocio_2321.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(AdherentPan.this, "You must select a member ", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current member
				Adherent tempAdherent = (Adherent) table.getValueAt(row, AdherentTableModel.OBJECT_COL);
				
				// create dialog
				AdherentDialog dialog = new AdherentDialog(AdherentPan.this, adherentDAO, 
															tempAdherent, true);

				// show dialog
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_1.setBounds(510, 143, 48, 48);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setIcon(new ImageIcon(getClass().getResource("/img/delete_delete_deleteusers_delete_male_user_maleclient_2348.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(AdherentPan.this, 
								"You must select a member", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the memeber
					int response = JOptionPane.showConfirmDialog(
							AdherentPan.this, "Delete this memeber?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current employee
					Adherent tempadherent = (Adherent) table.getValueAt(row, AdherentTableModel.OBJECT_COL);
				
					// delete the user
					adherentDAO.deleteAdherent(tempadherent.getId());

					// refresh GUI
					refreshAdherentView();

					// show success message
					JOptionPane.showMessageDialog(AdherentPan.this,
							"Adherent deleted succesfully.", "Adherent Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(AdherentPan.this,
							"Error deleting employee: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(510, 202, 48, 48);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setIcon(new ImageIcon(getClass().getResource("/img/Button-circle-previous_icon-icons.com_52229.png")));
		btnNewButton_3.setBounds(10, 11, 48, 41);
		contentPane.add(btnNewButton_3);
		
		JButton btnChercher = new JButton("Chercher");
		btnChercher.setBackground(Color.LIGHT_GRAY);
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get name from the text field

				// Call DAO and get users for the name

				// If name is empty, then get all users

				// Print out users				
				
				try {
					String firstname = firstname_f.getText();
					String lastname = lastname_f.getText();

					List<Adherent> adherent = null;
					
					if (firstname != null  && firstname.trim().length() > 0  ) {
						adherent = adherentDAO.searchAdherent1(firstname);}
					else if ( lastname != null &&  lastname.trim().length() > 0 ) {
						adherent = adherentDAO.searchAdherent2(lastname);
					}

					else if (firstname != null && lastname != null && firstname.trim().length() > 0 && lastname.trim().length() > 0 ) {
						adherent = adherentDAO.searchAdherent(firstname,lastname);
					} else {
						adherent = adherentDAO.getAllAdherent();
					}
					
					// create the model and update the "table"
					AdherentTableModel model = new AdherentTableModel(adherent);
					
					table.setModel(model);
					
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(AdherentPan.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		btnChercher.setBounds(469, 11, 89, 23);
		contentPane.add(btnChercher);
	}
	public void refreshAdherentView() {

		try {
			List<Adherent> adherent = adherentDAO.getAllAdherent();

			// create the model and update the "table"
			AdherentTableModel model = new AdherentTableModel(adherent);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
