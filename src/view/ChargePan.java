package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import appli.ChargeDAO;
import appli.EventDAO;
import model.Compta;
import model.Event;

public class ChargePan extends JFrame {

	private JPanel contentPane;
	
	private ChargeDAO chargeDAO;
	private JTextField source_f;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChargePan frame = new ChargePan();
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
	public ChargePan() {
		//create the DAO 
		try {
			 chargeDAO = new ChargeDAO();
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

JLabel lblName = new JLabel("Source");
lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
lblName.setBounds(127, 13, 48, 14);
contentPane.add(lblName);



JButton btnChercher = new JButton("Chercher");
btnChercher.setBackground(Color.LIGHT_GRAY);
btnChercher.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		// Get source from the text field

		// Call DAO and get users for the source

		// If source is empty, then get all products

		// Print out products			
		
		try {
			String source = source_f.getText();

			List<Compta> charge = null;

			if (source != null && source.trim().length() > 0) {
				charge = chargeDAO.searchCompta(source);
			} else {
				charge = chargeDAO.getAllCompta();
			}
			
			// create the model and update the "table"
			ChargeTableModel model = new ChargeTableModel(charge);
			
			table.setModel( model);
			
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(ChargePan.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
	}
});
btnChercher.setBounds(469, 11, 89, 23);
contentPane.add(btnChercher);

JButton btnNewButton = new JButton("");
btnNewButton.setBackground(Color.LIGHT_GRAY);
btnNewButton.setIcon(new ImageIcon(getClass().getResource("/img/Document-add_icon-icons.com_52132.png")));
btnNewButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		// create dialog
		ChargeDialog dialog = new ChargeDialog(ChargePan.this, chargeDAO);

		// show dialog
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
});
btnNewButton.setBounds(510, 73, 48, 37);
contentPane.add(btnNewButton);

JButton btnNewButton_1 = new JButton("");
btnNewButton_1.setBackground(Color.LIGHT_GRAY);
btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/img/Document-edit_icon-icons.com_52127.png")));
btnNewButton_1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();
		
		// make sure a row is selected
		if (row < 0) {
			JOptionPane.showMessageDialog(ChargePan.this, "You must select a product ", "Error",
					JOptionPane.ERROR_MESSAGE);				
			return;
		}
		
		// get the current event
		Compta tempcharge = (Compta) table.getValueAt(row, EventTableModel.OBJECT_COL);
		
		// create dialog
		ChargeDialog dialog = new ChargeDialog(ChargePan.this, chargeDAO, 
													tempcharge, true);

		// show dialog
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
});
btnNewButton_1.setBounds(510, 121, 48, 37);
contentPane.add(btnNewButton_1);

JButton btnNewButton_2 = new JButton("");
btnNewButton_2.setBackground(Color.LIGHT_GRAY);
btnNewButton_2.setIcon(new ImageIcon(getClass().getResource("/img/document_delete_256_icon-icons.com_75995.png")));
btnNewButton_2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		try {
			// get the selected row
			int row = table.getSelectedRow();

			// make sure a row is selected
			if (row < 0) {
				JOptionPane.showMessageDialog(ChargePan.this, 
						"You must select  a product ", "Error", JOptionPane.ERROR_MESSAGE);				
				return;
			}

			// prompt the product
			int response = JOptionPane.showConfirmDialog(
					ChargePan.this, "Delete this charge?", "Confirm", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (response != JOptionPane.YES_OPTION) {
				return;
			}

			// get the current event
			Compta tempcharge = (Compta) table.getValueAt(row, ChargeTableModel.OBJECT_COL);
		
			// delete the event
			chargeDAO.deleteCharge(tempcharge.getId());

			// refresh GUI
			refreshChargeView();

			// show success message
			JOptionPane.showMessageDialog(ChargePan.this,
					"charge deleted succesfully.", "charge Deleted",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception exc) {
			JOptionPane.showMessageDialog(ChargePan.this,
					"Error deleting product: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
});
			btnNewButton_2.setBounds(510, 178, 48, 42);
			contentPane.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_3.setBackground(Color.LIGHT_GRAY);
			btnNewButton_3.setIcon(new ImageIcon(getClass().getResource("/img/Button-circle-previous_icon-icons.com_52229.png")));
			btnNewButton_3.setBounds(10, 11, 48, 37);
			contentPane.add(btnNewButton_3);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 59, 490, 191);
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
				},
				new String[] {
					"name", "date", "descriptif"
				}
			));
			scrollPane.setViewportView(table);
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
				},
				new String[] {
					"source", "date", "montant"
				}
			));
			scrollPane.setViewportView(table);
			source_f = new JTextField();
			source_f.setBounds(214, 12, 96, 20);
			contentPane.add(source_f);
			source_f.setColumns(10);
			}
			
			public void refreshChargeView() {
			
			try {
				List<Compta> charge = chargeDAO.getAllCompta();
			
				// create the model and update the "table"
				ChargeTableModel model = new ChargeTableModel(charge);
			
				table.setModel(model);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			
			}
			}
