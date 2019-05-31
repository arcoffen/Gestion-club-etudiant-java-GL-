package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import appli.ProduitDAO;
import appli.UsersDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class ComptaPan extends JFrame {

	private JPanel contentPane;
	private ProduitDAO produitDAO;
	private JTextField total_f;
	private JTextField total1_f;
	private JTextField resultat_f;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComptaPan frame = new ComptaPan();
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
	public ComptaPan() {
		//create the DAO 
				try {
					 produitDAO = new ProduitDAO();
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
		setTitle("Compta pan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/img/money-bag (1).png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProduitPan obj = new ProduitPan();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(37, 25, 112, 73);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/img/loss (1).png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChargePan obj = new ChargePan();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_1.setBounds(420, 25, 112, 73);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Produits");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(61, 109, 73, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Charges");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(453, 109, 79, 15);
		contentPane.add(lblNewLabel_1);
		
		total_f = new JTextField();
		total_f.setBounds(37, 163, 112, 20);
		contentPane.add(total_f);
		total_f.setColumns(10);
		
		JButton btnTotal = new JButton("Total Produit");
		btnTotal.setForeground(Color.WHITE);
		btnTotal.setBackground(Color.GRAY);
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					 total_f.setText(produitDAO.TotalProduit().get(0));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTotal.setBounds(37, 134, 112, 23);
		contentPane.add(btnTotal);
		
		JButton btnTotalCharge = new JButton("Total Charge");
		btnTotalCharge.setForeground(Color.WHITE);
		btnTotalCharge.setBackground(Color.GRAY);
		btnTotalCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					 total1_f.setText(produitDAO.TotalCharge().get(0));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTotalCharge.setBounds(420, 134, 112, 23);
		contentPane.add(btnTotalCharge);
		
		total1_f = new JTextField();
		total1_f.setColumns(10);
		total1_f.setBounds(420, 163, 112, 20);
		contentPane.add(total1_f);
		
		JButton btnResultat = new JButton("Resultat");
		btnResultat.setForeground(Color.WHITE);
		btnResultat.setBackground(Color.GRAY);
		btnResultat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					 resultat_f.setText(produitDAO.Resultat().get(0));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnResultat.setBounds(226, 188, 112, 23);
		contentPane.add(btnResultat);
		
		resultat_f = new JTextField();
		resultat_f.setColumns(10);
		resultat_f.setBounds(226, 222, 112, 20);
		contentPane.add(resultat_f);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultat_f.setText("");
				total_f.setText("");
				total1_f.setText("");
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(getClass().getResource("/img/windowclose_104378.png")));
		btnNewButton_2.setBounds(533, 11, 25, 22);
		contentPane.add(btnNewButton_2);
	}
}
