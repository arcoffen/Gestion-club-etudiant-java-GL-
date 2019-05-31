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

import appli.EventDAO;
import appli.ProduitDAO;
import model.Compta;
import model.Event;

public class ProduitPan extends JFrame {

	private JPanel contentPane;
	
	private ProduitDAO produitDAO;
	private JTextField source_f;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProduitPan frame = new ProduitPan();
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
	public ProduitPan() {
		//create the DAO 
		try {
			 produitDAO = new ProduitDAO();
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

			List<Compta> produit = null;

			if (source != null && source.trim().length() > 0) {
				produit = produitDAO.searchCompta(source);
			} else {
				produit = produitDAO.getAllCompta();
			}
			
			// create the model and update the "table"
			ProduitTableModel model = new ProduitTableModel(produit);
			
			table.setModel( model);
			
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(ProduitPan.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
	}
});
btnChercher.setBounds(469, 11, 89, 23);
contentPane.add(btnChercher);

JButton btnNewButton = new JButton("");
btnNewButton.setBackground(Color.LIGHT_GRAY);
btnNewButton.setIcon(new ImageIcon(ProduitPan.class.getResource("/img/Document-add_icon-icons.com_52132.png")));
btnNewButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		// create dialog
		ProduitDialog dialog = new ProduitDialog(ProduitPan.this, produitDAO);

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
btnNewButton_1.setIcon(new ImageIcon(ProduitPan.class.getResource("/img/Document-edit_icon-icons.com_52127.png")));
btnNewButton_1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();
		
		// make sure a row is selected
		if (row < 0) {
			JOptionPane.showMessageDialog(ProduitPan.this, "You must select a product ", "Error",
					JOptionPane.ERROR_MESSAGE);				
			return;
		}
		
		// get the current event
		Compta tempproduit = (Compta) table.getValueAt(row, EventTableModel.OBJECT_COL);
		
		// create dialog
		ProduitDialog dialog = new ProduitDialog(ProduitPan.this, produitDAO, 
													tempproduit, true);

		// show dialog
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
});
btnNewButton_1.setBounds(510, 131, 48, 37);
contentPane.add(btnNewButton_1);

JButton btnNewButton_2 = new JButton("");
btnNewButton_2.setBackground(Color.LIGHT_GRAY);
btnNewButton_2.setIcon(new ImageIcon(ProduitPan.class.getResource("/img/document_delete_256_icon-icons.com_75995.png")));
btnNewButton_2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		try {
			// get the selected row
			int row = table.getSelectedRow();

			// make sure a row is selected
			if (row < 0) {
				JOptionPane.showMessageDialog(ProduitPan.this, 
						"You must select  a product ", "Error", JOptionPane.ERROR_MESSAGE);				
				return;
			}

			// prompt the product
			int response = JOptionPane.showConfirmDialog(
					ProduitPan.this, "Delete this produit?", "Confirm", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (response != JOptionPane.YES_OPTION) {
				return;
			}

			// get the current event
			Compta tempproduit = (Compta) table.getValueAt(row, ProduitTableModel.OBJECT_COL);
		
			// delete the event
			produitDAO.deleteProduit(tempproduit.getId());

			// refresh GUI
			refreshProduitView();

			// show success message
			JOptionPane.showMessageDialog(ProduitPan.this,
					"produit deleted succesfully.", "produit Deleted",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception exc) {
			JOptionPane.showMessageDialog(ProduitPan.this,
					"Error deleting product: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
});
			btnNewButton_2.setBounds(510, 194, 48, 42);
			contentPane.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_3.setBackground(Color.LIGHT_GRAY);
			btnNewButton_3.setIcon(new ImageIcon(ProduitPan.class.getResource("/img/Button-circle-previous_icon-icons.com_52229.png")));
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
			
			public void refreshProduitView() {
			
			try {
				List<Compta> produit = produitDAO.getAllCompta();
			
				// create the model and update the "table"
				ProduitTableModel model = new ProduitTableModel(produit);
			
				table.setModel(model);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			
			}
			}
