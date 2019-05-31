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

/** Classe responsable de la gestion des méthodes et la connection avec la base de donnée
 * 
 * @author ZAKARIA EL AMIM
 *
 */


public class AdherentDAO {
	
private Connection myConn ;
	/**
	 * permet la connection avec notre base de donnée
	 * @throws Exception
	 */
	public AdherentDAO() throws Exception {
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
	 * methode qui permet d'ajouter un adherent  en executant une requete sql 
	 * @param theAdherent un adherent 
	 * @see Adherent
	 * @throws Exception
	 */
	
	public void addAdherent(Adherent theAdherent) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			
			myStmt = myConn.prepareStatement("insert into adherents"
					+ " (firstname,lastname,email,filiere,absence )"
					+ " values (? , ? , ? , ? , ?)");
			
			// set params
			myStmt.setString(1, theAdherent.getFirstname());
			myStmt.setString(2, theAdherent.getLastname());
			myStmt.setString(3, theAdherent.getEmail());
			myStmt.setString(4, theAdherent.getFiliere());
			myStmt.setInt(5, theAdherent.getAbsence());
			
		
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	/**
	 * methode qui permet de modifier les parametre d'un adherent en executant une requete sql 
	 * @param theAdherent un adherent
	 * @see Adherent
	 * @throws SQLException
	 */
	public void updateAdherent(Adherent theAdherent) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update adherents"
					+ " set firstname=?, lastname=? , email=? , filiere=? , absence=?" 
					+ " where id=?");
			
			// set params
			myStmt.setString(1, theAdherent.getFirstname());
			myStmt.setString(2, theAdherent.getLastname());
			myStmt.setString(3, theAdherent.getEmail());
			myStmt.setString(4, theAdherent.getFiliere());
			myStmt.setInt(5, theAdherent.getAbsence());
			myStmt.setInt(6, theAdherent.getId());
			
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	/**
	 * methode qui permet de supprimer un adherent en executant une requete sql 
	 * @param userId l'identificateur de l'adherent
	 * @throws SQLException
	 */
	public void deleteAdherent(int userId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from adherents where id=?");
			
			// set param
			myStmt.setInt(1, userId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}
	
	
	/**
	 * une mthode qui permet de  recevoir la totalité des adherents
	 * @return tous les adherents figurant dans la base de donnée 
	 * @throws Exception
	 */
	
	public List<Adherent> getAllAdherent() throws Exception {
		List<Adherent> list = new ArrayList<Adherent>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from adherents order by id ");
			
			while (myRs.next()) {
				Adherent tempAdherent = convertRowToAdherent(myRs);
				list.add(tempAdherent);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	/**
	 * methode permettant de recevoir les informations  d'un adherent a partir de son nom et prenom 
	 * @param firstname le prenom
	 * @param lastname le nom 
	 * @return les information de l'adherent ou des adherents ayant le meme nom et le meme prenom  
	 * @throws Exception
	 */
	
	public List<Adherent> searchAdherent( String firstname ,String lastname ) throws Exception {
		List<Adherent> list = new ArrayList<Adherent>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			lastname += "%";
			firstname += "%";
			
			
			myStmt = myConn.prepareStatement("select * from adherents where firstname like ?  and lastname like ? order by lastname");
			
			
			myStmt.setString(1, firstname);
			myStmt.setString(2, lastname);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Adherent tempAdhrenet = convertRowToAdherent(myRs);
				list.add(tempAdhrenet);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	/**
	 * methode permettant de recevoir les informations  d'un adherent a partir de son nom 
	
	 * @param lastname le nom 
	 * @return les information de l'adherent ou des adherents ayant le meme nom 
	 * @throws Exception
	 */
	public List<Adherent> searchAdherent2(String lastname ) throws Exception {
		List<Adherent> list = new ArrayList<Adherent>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			lastname += "%";
			
			
			
			myStmt = myConn.prepareStatement("select * from adherents where  lastname like ? order by lastname");
			
			
		
			myStmt.setString(1, lastname);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Adherent tempAdhrenet = convertRowToAdherent(myRs);
				list.add(tempAdhrenet);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	/**
	 * methode permettant de recevoir les informations  d'un adherent a partir de son  prenom 
	 * @param firstname le prenom

	 * @return les information de l'adherent ou des adherents ayant  le meme prenom  
	 * @throws Exception
	 */
	public List<Adherent> searchAdherent1( String firstname  ) throws Exception {
		List<Adherent> list = new ArrayList<Adherent>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			
			firstname += "%";
			
			
			myStmt = myConn.prepareStatement("select * from adherents where firstname like ?   order by firstname");
			
			
			myStmt.setString(1, firstname);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Adherent tempAdhrenet = convertRowToAdherent(myRs);
				list.add(tempAdhrenet);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	/**
	 * methode permettant de prendre le resultat de l'execution des requetes sql et de stocker chaque valeur dans la variable 
	 * correspondante
	 * @param myRs le resultat de l'execution des requetes sql 
	 * @return création d'un nouveau objet adherent 
	 * @see Adherent
	 * @throws SQLException
	 */
	
	private Adherent convertRowToAdherent(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String firstname = myRs.getString("firstname");
		String lastname = myRs.getString("lastname");
		String email = myRs.getString("email");
		String filiere = myRs.getString("filiere");
		int absence = myRs.getInt("absence");
		
		
		Adherent tempAdherent = new Adherent(id, firstname,lastname, email,filiere,absence );
		
		return tempAdherent;
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
		
		AdherentDAO dao = new AdherentDAO();
		System.out.println(dao.searchAdherent("zakaria","elamim"));
		
		System.out.println(dao.getAllAdherent());
		
	}
	

}
