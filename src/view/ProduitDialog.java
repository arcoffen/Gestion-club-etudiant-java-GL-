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
import appli.ProduitDAO;
import model.Compta;
import model.Event;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class ProduitDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField source_f;
	private JTextField montant_f;
	private JTextField date_j;
	private ProduitDAO produitDAO;

	private ProduitPan produitpan;
	private ChargeDAO chargeDAO;

	private ChargePan Chargepan;

	private Compta previouscompta = null;
	private boolean updateMode = false;
	
	public ProduitDialog(ProduitPan theProduitPan,
			ProduitDAO theproduitDAO, Compta thePreviousCompta, boolean theUpdateMode) {
		this();
		produitDAO = theproduitDAO;
		produitpan = theProduitPan;

		previouscompta = thePreviousCompta;
		
		updateMode = theUpdateMode;
		

		if (updateMode) {
			setTitle("Update Produit");
			
			populateGui(previouscompta);
		}
	}
	public ProduitDialog(ChargePan theChargePan,
			ChargeDAO thechargeDAO, Compta thePreviousCompta, boolean theUpdateMode) {
		this();
		chargeDAO = thechargeDAO;
		Chargepan = theChargePan;

		previouscompta = thePreviousCompta;
		
		updateMode = theUpdateMode;
		

		if (updateMode) {
			setTitle("Update Produit");
			
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
	public ProduitDialog(ProduitPan theProduitPan,
			ProduitDAO theproduitDAO) {
		this(theProduitPan, theproduitDAO, null, false);
	}
	public ProduitDialog(ChargePan theChargePan,
			ChargeDAO thechargeDAO) {
		this(theChargePan, thechargeDAO, null, false);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProduitDialog dialog = new ProduitDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void saveProduit() {

		
		
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
				produitDAO.updateProduit(tempcompta);
			} else {
				produitDAO.addProduit(tempcompta);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			produitpan.refreshProduitView();

			// show success message
			JOptionPane.showMessageDialog(produitpan,
					"produit saved succesfully.", "produit Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(produitpan,
					"Error saving produit: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Create the dialog.
	 */
	public ProduitDialog() {
		setBounds(100, 100, 584, 300);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(280, 0, 343, 285);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblSource = new JLabel("Source");
			lblSource.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSource.setBounds(24, 39, 48, 14);
			contentPanel.add(lblSource);
		}
		{
			JLabel lblDate = new JLabel("Date");
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDate.setBounds(24, 103, 48, 14);
			contentPanel.add(lblDate);
		}
		{
			JLabel lblMontant = new JLabel("Montant");
			lblMontant.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblMontant.setBounds(24, 170, 48, 14);
			contentPanel.add(lblMontant);
		}
		{
			source_f = new JTextField();
			source_f.setBounds(107, 37, 161, 20);
			contentPanel.add(source_f);
			source_f.setColumns(10);
		}
		{
			montant_f = new JTextField();
			montant_f.setBounds(107, 168, 161, 20);
			contentPanel.add(montant_f);
			montant_f.setColumns(10);
		}
		{
			date_j = new JTextField();
			date_j.setBounds(107, 137, 96, 20);
			contentPanel.add(date_j);
			date_j.setColumns(10);
			date_j.setVisible(false);
		}
		
		JDateChooser date_f = new JDateChooser();
		date_f.setBounds(107, 97, 162, 20);
		contentPanel.add(date_f);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				date_j.setText(df.format(date_f.getDate()));
				saveProduit();
			}
		});
		btnNewButton.setBounds(24, 218, 118, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel\r\n");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(152, 218, 116, 23);
		contentPanel.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 281, 299);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ProduitDialog.class.getResource("/img/images (1).jpg")));
		label.setBounds(-58, 11, 370, 230);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Hello World!");
		label_1.setFont(new Font("AR DARLING", Font.ITALIC, 13));
		label_1.setBounds(91, 214, 87, 14);
		panel.add(label_1);
	}
}
