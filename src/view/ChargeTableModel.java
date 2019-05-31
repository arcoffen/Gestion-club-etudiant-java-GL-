package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Compta;

public class ChargeTableModel extends AbstractTableModel{
	public static final int OBJECT_COL = -1;
	private static final int SOURCE_COL = 0;
	private static final int DATE_COL = 1;
	private static final int MONTANT_COL = 2;
	//private static final int PASSWORD_COL = 3;

	private String[] columnNames = { "source", "date", "montant"
			 };
	private List<Compta> compta;

	public ChargeTableModel(List<Compta> thecompta) {
		compta = thecompta;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	
	public int getRowCount() {
		return compta.size();
	}

	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	
	public Object getValueAt(int row, int col) {

		Compta tempcompta = compta.get(row);

		switch (col) {
		case SOURCE_COL:
			return tempcompta.getSource();
		case DATE_COL:
			return tempcompta.getDate();
		case MONTANT_COL:
			return tempcompta.getMontant();
		//case PASSWORD_COL:
			//return tempuser.getPassword();
		case OBJECT_COL:
			return tempcompta;
		default:
			return tempcompta.getSource();
		}
	}


	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


}
