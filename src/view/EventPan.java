package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import appli.AdherentDAO;
import appli.EventDAO;
import model.Adherent;
import model.Event;
import model.Users;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class EventPan extends JFrame {

	private JPanel contentPane;
	private JTextField name_f;
	private JTable table;
	private EventDAO eventDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventPan frame = new EventPan();
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
	public EventPan() {
		//create the DAO 
				try {
					 eventDAO = new EventDAO();
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
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(127, 13, 48, 14);
		contentPane.add(lblName);
		
		name_f = new JTextField();
		name_f.setBounds(208, 12, 159, 20);
		contentPane.add(name_f);
		name_f.setColumns(10);
		
		JButton btnChercher = new JButton("Chercher");
		btnChercher.setBackground(Color.LIGHT_GRAY);
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get name from the text field

				// Call DAO and get users for the name

				// If name is empty, then get all users

				// Print out users				
				
				try {
					String name = name_f.getText();

					List<Event> event = null;

					if (name != null && name.trim().length() > 0) {
						event = eventDAO.searchEvent(name);
					} else {
						event = eventDAO.getAllEvent();
					}
					
					// create the model and update the "table"
					EventTableModel model = new EventTableModel(event);
					
					table.setModel( model);
					
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(EventPan.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
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
				EventDialog dialog = new EventDialog(EventPan.this, eventDAO);

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
					JOptionPane.showMessageDialog(EventPan.this, "You must select an event ", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current event
				Event tempEvent = (Event) table.getValueAt(row, EventTableModel.OBJECT_COL);
				
				// create dialog
				EventDialog dialog = new EventDialog(EventPan.this, eventDAO, 
															tempEvent, true);

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
						JOptionPane.showMessageDialog(EventPan.this, 
								"You must select an Event", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the event
					int response = JOptionPane.showConfirmDialog(
							EventPan.this, "Delete this event?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current event
					Event tempevent = (Event) table.getValueAt(row, UserTableModel.OBJECT_COL);
				
					// delete the event
					eventDAO.deleteEvent(tempevent.getId());

					// refresh GUI
					refreshEventView();

					// show success message
					JOptionPane.showMessageDialog(EventPan.this,
							"Event deleted succesfully.", "Event Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(EventPan.this,
							"Error deleting event: " + exc.getMessage(), "Error",
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
	}
	
	public void refreshEventView() {

		try {
			List<Event> event = eventDAO.getAllEvent();

			// create the model and update the "table"
			EventTableModel model = new EventTableModel(event);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
