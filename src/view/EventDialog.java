package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.Text;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import appli.EventDAO;
import model.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JList;

public class EventDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	//private JTextField name_f;
	private EventDAO eventDAO;

	private EventPan eventpan;

	private Event previousEvent = null;
	private boolean updateMode = false;
	private JTextField poste_f;
	
	private JTextField date_j;
	private JTextField name_f;
	private JTextField descriptif_f;


	
	public EventDialog(EventPan theEventPan,
			EventDAO theeventDAO, Event thePreviousEvent, boolean theUpdateMode) {
		this();
		eventDAO = theeventDAO;
		eventpan = theEventPan;

		previousEvent = thePreviousEvent;
		
		updateMode = theUpdateMode;
		

		if (updateMode) {
			setTitle("Update Event");
			
			populateGui(previousEvent);
		}
	}
	private void populateGui(Event theevent) {

		//id_f.setText(String.valueOf(theevent.getId()));
		name_f.setText(theevent.getName());
		
		date_j.setText(theevent.getDate().toString());
		descriptif_f.setText(theevent.getDescriptif());
		//password_f.setText(theevent.getPassword());		
	}
	public EventDialog(EventPan theEventPan,
			EventDAO theeventDAO) {
		this(theEventPan, theeventDAO, null, false);
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EventDialog dialog = new EventDialog();
			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void saveEvent() {

		
			
		// get the Event info from gui
		//int id = Integer.parseInt(id_f.getText());
		
		String name = name_f.getText();
		String date =  date_j.getText();
		String descriptif = descriptif_f.getText();

		

		Event tempevent = null;

		if (updateMode) {
			tempevent = previousEvent;
			
			//tempuser.setId(id);
			tempevent.setName(name);
			tempevent.setDate(date);
			tempevent.setDescriptif(descriptif);
			
		} else {
			tempevent = new Event( name, date , descriptif);
		}

		try {
			// save to the database
			if (updateMode) {
				eventDAO.updateEvent(tempevent);
			} else {
				eventDAO.addEvent(tempevent);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			eventpan.refreshEventView();

			// show success message
			JOptionPane.showMessageDialog(eventpan,
					"event saved succesfully.", "event Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(eventpan,
					"Error saving event: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Create the dialog.
	 */
	public EventDialog() {
		setTitle("Event Dialog");
		
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
			JLabel lblSource = new JLabel("Name");
			lblSource.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSource.setBounds(285, 11, 64, 14);
			getContentPane().add(lblSource);
		}
		
		{
			JLabel lblDate = new JLabel("Date");
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDate.setBounds(285, 64, 64, 14);
			getContentPane().add(lblDate);
		}
		{
			JLabel lblMontant = new JLabel("Descriptif");
			lblMontant.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblMontant.setBounds(285, 158, 64, 14);
			getContentPane().add(lblMontant);
		}
	
		
		JDateChooser date_f = new JDateChooser();
		date_f.setBounds(380, 64, 154, 20);
		getContentPane().add(date_f);
		
		date_j = new JTextField();
		date_j.setBounds(380, 105, 96, 20);
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
				saveEvent();
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
		
		name_f = new JTextField();
		name_f.setBounds(380, 9, 154, 20);
		getContentPane().add(name_f);
		name_f.setColumns(10);
		
		descriptif_f = new JTextField();
		descriptif_f.setBounds(380, 136, 154, 60);
		getContentPane().add(descriptif_f);
		descriptif_f.setColumns(10);
	}
}
