package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Adherent;
import model.Users;

public class AdherentTableModel extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
	private static final int FIRSTNAME_COL = 0;
	private static final int LASTNAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int FILIERE_COL = 3;
	private static final int ABSENCE_COL = 4;
	//private static final int PASSWORD_COL = 3;

	private String[] columnNames = { "firstname", "lastname", "email", "filiere", "absence"
			 };
	private List<Adherent> adherent;

	public AdherentTableModel(List<Adherent> theadherent) {
		adherent = theadherent;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	
	public int getRowCount() {
		return adherent.size();
	}

	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	
	public Object getValueAt(int row, int col) {

		Adherent tempadherent = adherent.get(row);

		switch (col) {
		case FIRSTNAME_COL:
			return tempadherent.getFirstname();
		case LASTNAME_COL:
			return tempadherent.getLastname();
		case EMAIL_COL:
			return tempadherent.getEmail();
		//case PASSWORD_COL:
			//return tempuser.getPassword();
		case FILIERE_COL:
			return tempadherent.getFiliere();
		case ABSENCE_COL:
			return tempadherent.getAbsence();
		case OBJECT_COL:
			return tempadherent;
		default:
			return tempadherent.getLastname();
		}
	}


	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


}
