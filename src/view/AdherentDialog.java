package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import appli.AdherentDAO;
import model.Adherent;
import model.Users;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class AdherentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstname_f;
	private JTextField lastname_f;
	private JTextField email_f;
	private JTextField filiere_f;
	private JTextField absence_f;
	
	private AdherentDAO adherentDAO;

	private AdherentPan adherentpan;

	private Adherent previousAdherent = null;
	private boolean updateMode = false;
	
	public AdherentDialog(AdherentPan theAdherentPan,
			AdherentDAO theadherentDAO, Adherent thePreviousAdherent, boolean theUpdateMode) {
		this();
		adherentDAO = theadherentDAO;
		adherentpan = theAdherentPan;

		previousAdherent = thePreviousAdherent;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Adherent");
			
			populateGui(previousAdherent);
		}
	}
	private void populateGui(Adherent theadherent) {

		//id_f.setText(String.valueOf(theadherent.getId()));
		firstname_f.setText(theadherent.getFirstname());
		lastname_f.setText(theadherent.getLastname());
		email_f.setText(theadherent.getEmail());
		filiere_f.setText(theadherent.getFiliere());
		absence_f.setText(String.valueOf(theadherent.getAbsence()));
		//password_f.setText(theadherent.getPassword());		
	}
	public AdherentDialog(AdherentPan theAdherentPan,
			AdherentDAO theadherentDAO) {
		this(theAdherentPan, theadherentDAO, null, false);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdherentDialog dialog = new AdherentDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void saveAdherent() {

		// get the Adherent info from gui
		//int id = Integer.parseInt(id_f.getText());
		String firstname = firstname_f.getText();
		String lastname = lastname_f.getText();
		String email = email_f.getText();
		String filiere = filiere_f.getText();
		int absence = Integer.parseInt(absence_f.getText());

		

		Adherent tempadherent = null;

		if (updateMode) {
			tempadherent = previousAdherent;
			
			//tempuser.setId(id);
			tempadherent.setFirstname(firstname);
			tempadherent.setLastname(lastname);
			tempadherent.setEmail(email);
			tempadherent.setFiliere(filiere);
			tempadherent.setAbsence(absence);
			
			
		} else {
			tempadherent = new Adherent( firstname, lastname, email, filiere, absence);
		}

		try {
			// save to the database
			if (updateMode) {
				adherentDAO.updateAdherent(tempadherent);
			} else {
				adherentDAO.addAdherent(tempadherent);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			adherentpan.refreshAdherentView();

			// show success message
			JOptionPane.showMessageDialog(adherentpan,
					"adherent saved succesfully.", "adherent Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(adherentpan,
					"Error saving adherent: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Create the dialog.
	 */
	public AdherentDialog() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Adherent Dialog");
		setBounds(100, 100, 584, 300);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(0, 0, 281, 261);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblFirstname = new JLabel("firstname");
			lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFirstname.setBounds(20, 36, 121, 14);
			contentPanel.add(lblFirstname);
		}
		{
			JLabel lblLastname = new JLabel("lastname");
			lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblLastname.setBounds(20, 71, 95, 14);
			contentPanel.add(lblLastname);
		}
		{
			JLabel lblEmail = new JLabel("email");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblEmail.setBounds(20, 109, 48, 14);
			contentPanel.add(lblEmail);
		}
		{
			JLabel lblFiliere = new JLabel("filiere");
			lblFiliere.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFiliere.setBounds(20, 145, 48, 14);
			contentPanel.add(lblFiliere);
		}
		{
			JLabel lblAbsence = new JLabel("absence");
			lblAbsence.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblAbsence.setBounds(20, 180, 48, 14);
			contentPanel.add(lblAbsence);
		}
		{
			firstname_f = new JTextField();
			firstname_f.setBounds(121, 34, 131, 20);
			contentPanel.add(firstname_f);
			firstname_f.setColumns(10);
		}
		{
			lastname_f = new JTextField();
			lastname_f.setBounds(121, 69, 131, 20);
			contentPanel.add(lastname_f);
			lastname_f.setColumns(10);
		}
		{
			email_f = new JTextField();
			email_f.setBounds(121, 107, 131, 20);
			contentPanel.add(email_f);
			email_f.setColumns(10);
		}
		{
			filiere_f = new JTextField();
			filiere_f.setBounds(121, 143, 131, 20);
			contentPanel.add(filiere_f);
			filiere_f.setColumns(10);
		}
		{
			absence_f = new JTextField();
			absence_f.setBounds(121, 178, 131, 20);
			contentPanel.add(absence_f);
			absence_f.setColumns(10);
		}
		{
			JButton btnNewButton = new JButton("Save");
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setBackground(Color.GRAY);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveAdherent();
				}
			});
			btnNewButton.setBounds(20, 227, 111, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton_1 = new JButton("Cancel");
			btnNewButton_1.setForeground(Color.WHITE);
			btnNewButton_1.setBackground(Color.GRAY);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_1.setBounds(141, 227, 111, 23);
			contentPanel.add(btnNewButton_1);
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(278, 0, 290, 261);
		getContentPane().add(panel);
		panel.setLayout(null);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(getClass().getResource("/img/la-nuit-du-hack-1-1024x569.jpg")));
			label.setBounds(-120, -100, 537, 401);
			panel.add(label);
		}
	}
}
