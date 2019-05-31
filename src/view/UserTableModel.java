package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Users;

public class UserTableModel extends AbstractTableModel{
	public static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int USERNAME_COL = 1;
	private static final int POSTE_COL = 2;
	//private static final int PASSWORD_COL = 3;

	private String[] columnNames = { "id", "username", "poste"
			 };
	private List<Users> user;

	public UserTableModel(List<Users> theuser) {
		user = theuser;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	
	public int getRowCount() {
		return user.size();
	}

	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	
	public Object getValueAt(int row, int col) {

		Users tempuser = user.get(row);

		switch (col) {
		case ID_COL:
			return tempuser.getId();
		case USERNAME_COL:
			return tempuser.getUsername();
		case POSTE_COL:
			return tempuser.getPoste();
		//case PASSWORD_COL:
			//return tempuser.getPassword();
		case OBJECT_COL:
			return tempuser;
		default:
			return tempuser.getUsername();
		}
	}


	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
