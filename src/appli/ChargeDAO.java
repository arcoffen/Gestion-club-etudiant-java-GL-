package appli;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Adherent;
import model.Compta;
/** Classe responsable de la gestion des méthodes et la connection avec la base de donnée
 * 
 * @author ZAKARIA EL AMIM
 *
 */



public class ChargeDAO {
	/**
	 * permet la connection avec notre base de donnée
	 * @throws Exception
	 */
private Connection myConn ;
	
	public ChargeDAO() throws Exception {
		//Get the DATABASE proprieties 
		Properties props = new Properties();
		props.load(new FileInputStream("sql/club.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	/**
	 * methode qui permet d'ajouter une charge  en executant une requete sql 
	 * @param theCompta 
	 * @see Compta
	 * @throws Exception
	 */
	
	public void addCharge(Compta theCompta) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into charges"
					+ " (source, date , montant )"
					+ " values (? , ? , ? )");
			
			// set params
			myStmt.setString(1, theCompta.getSource());
			myStmt.setString(2, theCompta.getDate());
			myStmt.setDouble(3, theCompta.getMontant());
			
		
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	/**
	 * methode qui permet de modifier les parametre d'une cherge en executant une requete sql 
	 * @param theCompta instantiation de la classe compta
	 * @see Compta
	 * @throws SQLException
	 */
	
	public void updateCharge(Compta theCompta) throws SQLException {
		PreparedStatement myStmt = null;
		

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update charges"
					+ " set source=?, date=? , montant=?" 
					+ " where id=?");
			
			// set params
			myStmt.setString(1, theCompta.getSource());
			myStmt.setString(2, theCompta.getDate());
			myStmt.setDouble(3, theCompta.getMontant());
			
			myStmt.setInt(4, theCompta.getId());
			
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	/**
	 * methode qui permet de supprimer une cherge  en executant une requete sql 
	 * @param chargeId l'identificateur de la charge
	 * @throws SQLException
	 */
	public void deleteCharge(int chargeId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from charges where id=?");
			
			// set param
			myStmt.setInt(1, chargeId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}
	
	
	/**
	 * une mthode qui permet de  recevoir la totalité des cherges
	 * @return toutes les charges figurant dans la base de donnée 
	 * @throws Exception
	 */
	
	public List<Compta> getAllCompta() throws Exception {
		List<Compta> list = new ArrayList<Compta>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
	
		
		
		
		try {
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("select * from charges order by id ");
			
			while (myRs.next() ) {
			Compta tempCompta = convertRowToCompta(myRs);
			
				list.add(tempCompta);
				
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		
		}
	}
	/**
	 * methode permettant de recevoir les informations  d'une cherge a partir de sa source
	 * @param source d'ou la charge est prevenue 
	 * @return les information de la charge ou les charges ayant la meme source  
	 * @throws Exception
	 */
	
	public List<Compta> searchCompta( String source) throws Exception {
		List<Compta> list = new ArrayList<Compta>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		

		try {
			
			source += "%";
			
			
			
			myStmt = myConn.prepareStatement("select * from charges where source like ?   order by date");
			
			
			myStmt.setString(1, source);
		
			myRs = myStmt.executeQuery();
			
			while (myRs.next() ) {
				Compta tempCompta = convertRowToCompta(myRs);
				
				list.add(tempCompta);
				
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
	
		}
	}
	
	/**
	 * @see AdherentDAO#convertRowToAdherent()
	 */
	private Compta convertRowToCompta(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String source = myRs.getString("source");
		String date = myRs.getString("date");
		int montant = myRs.getInt("montant");
		
		
		
		Compta tempCompta = new Compta(id, source , date , montant );
		
		return tempCompta;
	}
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);		
	}
	

public static void main(String[] args) throws Exception {
		
		ChargeDAO dao = new ChargeDAO();
		System.out.println(dao.searchCompta("sponsor"));
		
		System.out.println(dao.getAllCompta());
		
	}
}
