package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Event;
import model.Users;

public class EventTableModel extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
	private static final int NAME_COL = 0;
	private static final int DATE_COL = 1;
	private static final int DESCRIPTIF_COL = 2;
	//private static final int PASSWORD_COL = 3;

	private String[] columnNames = { "name", "date", "descriptif"
			 };
	private List<Event> event;

	public EventTableModel(List<Event> theevent) {
		event = theevent;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	
	public int getRowCount() {
		return event.size();
	}

	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	
	public Object getValueAt(int row, int col) {

		Event tempevent = event.get(row);

		switch (col) {
		case NAME_COL:
			return tempevent.getName();
		case DATE_COL:
			return tempevent.getDate();
		case DESCRIPTIF_COL:
			return tempevent.getDescriptif();
		//case PASSWORD_COL:
			//return tempuser.getPassword();
		case OBJECT_COL:
			return tempevent;
		default:
			return tempevent.getName();
		}
	}


	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


}
