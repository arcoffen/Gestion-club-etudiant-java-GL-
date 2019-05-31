package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Button;

public class AdminPan extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPan frame = new AdminPan();
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
	public AdminPan() {
		setTitle("AdminPan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUser = new JButton("");
		btnUser.setBackground(Color.GRAY);
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAdd obj = new UserAdd();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		
		btnUser.setIcon(new ImageIcon(getClass().getResource("/img/add-friend (1).png")));
		btnUser.setBounds(10, 96, 97, 68);
		contentPane.add(btnUser);
		
		JButton button_1 = new JButton("");
		button_1.setBackground(Color.GRAY);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventPan obj = new EventPan();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		button_1.setIcon(new ImageIcon(getClass().getResource("/img/add.png")));
		button_1.setBounds(162, 96, 97, 68);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setBackground(Color.GRAY);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdherentPan obj = new AdherentPan();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		button_2.setIcon(new ImageIcon(getClass().getResource("/img/profile.png")));
		button_2.setBounds(306, 96, 97, 68);
		contentPane.add(button_2);
		
		JLabel lblGestionDesUsers = new JLabel("Gestion Users");
		lblGestionDesUsers.setBounds(20, 175, 97, 14);
		contentPane.add(lblGestionDesUsers);
		
		JLabel lblGestionEvents = new JLabel("Gestion Events");
		lblGestionEvents.setBounds(162, 175, 97, 14);
		contentPane.add(lblGestionEvents);
		
		JLabel lblGestionMembers = new JLabel("Gestion Members");
		lblGestionMembers.setBounds(306, 175, 110, 14);
		contentPane.add(lblGestionMembers);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComptaPan obj = new ComptaPan();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		button.setBackground(Color.GRAY);
		button.setIcon(new ImageIcon(getClass().getResource("/img/11accounting_102113.png")));
		button.setBounds(450, 96, 97, 68);
		contentPane.add(button);
		
		JLabel lblComptabilit = new JLabel("Comptabilit\u00E9");
		lblComptabilit.setBounds(460, 175, 97, 14);
		contentPane.add(lblComptabilit);
	}
}
