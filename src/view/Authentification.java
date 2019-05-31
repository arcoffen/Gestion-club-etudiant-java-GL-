package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import appli.UsersDAO;

import java.util.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.MouseMotionAdapter;

public class Authentification extends JFrame {

	private JPanel contentPane;
	private JTextField username_f;
	private UsersDAO userDAO;

	private JPasswordField password_f;
	int xx,xy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification frame = new Authentification();
					frame.setVisible(true);
					
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Authentification() {
		setTitle("Authetification");
		
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
		
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(332, 49, 84, 21);
		contentPane.add(lblUsername);
		
		username_f = new JTextField();
		username_f.setBounds(332, 81, 206, 20);
		contentPane.add(username_f);
		username_f.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(332, 138, 105, 14);
		contentPane.add(lblPassword);
		
		password_f = new JPasswordField();
		password_f.setBounds(332, 163, 206, 20);
		contentPane.add(password_f);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 257, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
	
	
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/img/images (1).jpg")));
		lblNewLabel.setBounds(-64, 11, 370, 230);
		panel.add(lblNewLabel);
		
		JLabel lblHelloWorld = new JLabel("Hello World!");
		lblHelloWorld.setFont(new Font("AR DARLING", Font.ITALIC, 13));
		lblHelloWorld.setBounds(88, 227, 87, 14);
		panel.add(lblHelloWorld);
		
		Button button = new Button("Se Connecter ");
		button.setFont(new Font("Dialog", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username2 = username_f.getText().toString();
				String password2 = password_f.getText().toString();
				try { 
					if (username2.equals("") || password2.equals("")) {
						JOptionPane.showMessageDialog(null,"Veuillez Saisir votre username et votre mot de passe");
					}
					else {
				if(userDAO.Verifier(username2, password2)==true) {
					AdminPan admin = new AdminPan();
					admin.setVisible(true);
					
					admin.setLocationRelativeTo(null);
					admin.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					username_f.setText("");
					password_f.setText("");
					
				}
				else {
					JOptionPane.showMessageDialog(null,"fals");
				}
				
				}}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"something went wrong");
				}
			}
		});
		button.setBackground(Color.GRAY);
		button.setForeground(Color.WHITE);
		button.setBounds(419, 228, 119, 23);
		contentPane.add(button);
	}
}
