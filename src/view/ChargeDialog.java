package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import appli.ChargeDAO;
import appli.EventDAO;
import model.Compta;
import model.Event;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class ChargeDialog extends JDialog {
	private ChargeDAO chargeDAO;

	private ChargePan chargepan;




	private Compta previouscompta = null;
	private boolean updateMode = false;
	private JTextField source_f;
	private JTextField montant_f;
	private JTextField date_j;
	
	public ChargeDialog(ChargePan theChargePan,
			ChargeDAO thechargeDAO, Compta thePreviousCompta, boolean theUpdateMode) {
		this();
		chargeDAO = thechargeDAO;
		chargepan = theChargePan;

		previouscompta = thePreviousCompta;
		
		updateMode = theUpdateMode;
		

		if (updateMode) {
			setTitle("Update Charge");
			
			populateGui(previouscompta);
		}
	}
	
	
	private void populateGui(Compta thecompta) {

		//id_f.setText(String.valueOf(theevent.getId()));
		source_f.setText(thecompta.getSource());
		
		date_j.setText(thecompta.getDate().toString());
		
		montant_f.setText(String.valueOf(thecompta.getMontant()));
		//password_f.setText(theevent.getPassword());		
	}
	public ChargeDialog(ChargePan theChargePan,
			ChargeDAO thechargeDAO) {
		this(theChargePan, thechargeDAO, null, false);
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChargeDialog dialog = new ChargeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void saveCharge() {

		
		
		// get the product info from gui
		//int id = Integer.parseInt(id_f.getText());
		
		String source = source_f.getText();
		String date =  date_j.getText();
		int montant = Integer.parseInt(montant_f.getText());

		

		Compta tempcompta = null;

		if (updateMode) {
			tempcompta = previouscompta;
			
			//tempuser.setId(id);
			tempcompta.setSource(source);
			tempcompta.setDate(date);
			tempcompta.setMontant(montant);
			
		} else {
			tempcompta = new Compta( source, date , montant);
		}

		try {
			// save to the database
			if (updateMode) {
				chargeDAO.updateCharge(tempcompta);
			} else {
				chargeDAO.addCharge(tempcompta);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			chargepan.refreshChargeView();

			// show success message
			JOptionPane.showMessageDialog(chargepan,
					"charge saved succesfully.", "charge Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(chargepan,
					"Error saving charge: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Create the dialog.
	 */
	public ChargeDialog() {
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
				label.setIcon(new ImageIcon(getClass().getResource("/img/images (1).jpg")));
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
			JLabel lblSource = new JLabel("Source");
			lblSource.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSource.setBounds(285, 48, 64, 14);
			getContentPane().add(lblSource);
		}
		{
			source_f = new JTextField();
			source_f.setBounds(380, 46, 154, 20);
			getContentPane().add(source_f);
			source_f.setColumns(10);
		}
		{
			JLabel lblDate = new JLabel("Date");
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDate.setBounds(285, 121, 64, 14);
			getContentPane().add(lblDate);
		}
		{
			JLabel lblMontant = new JLabel("Montant");
			lblMontant.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblMontant.setBounds(285, 188, 64, 14);
			getContentPane().add(lblMontant);
		}
		{
			montant_f = new JTextField();
			montant_f.setBounds(380, 185, 154, 20);
			getContentPane().add(montant_f);
			montant_f.setColumns(10);
		}
		
		JDateChooser date_f = new JDateChooser();
		date_f.setBounds(380, 115, 154, 20);
		getContentPane().add(date_f);
		
		date_j = new JTextField();
		date_j.setBounds(380, 154, 96, 20);
		getContentPane().add(date_j);
		date_j.setColumns(10);
		date_j.setVisible(false);
		
		JButton btnSave = new JButton("Save");
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(Color.GRAY);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				date_j.setText(df.format(date_f.getDate()));
				saveCharge();
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
	}
}
