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

import model.Compta;
import model.Event;
/** Classe responsable de la gestion des méthodes et la connection avec la base de donnée
 * 
 * @author ZAKARIA EL AMIM
 *
 */

public class ProduitDAO {
	/**
	 * permet la connection avec notre base de donnée
	 * @throws Exception
	 */
	
private Connection myConn ;
	
	public ProduitDAO() throws Exception {
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
	 * methode qui permet d'ajouter un produit  en executant une requete sql 
	 * @param theCompta 
	 * @see Compta
	 * @throws Exception
	 */
	
	public void addProduit(Compta theCompta) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into produits"
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
	 * methode qui permet de modifier les parametre dun produit  en executant une requete sql 
	 * @param theCompta instantiation de la classe compta
	 * @see Compta
	 * @throws SQLException
	 */
	
	public void updateProduit(Compta theCompta) throws SQLException {
		PreparedStatement myStmt = null;
		

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update produits"
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
	 * methode qui permet de supprimer un produit   en executant une requete sql 
	 * @param produitId l'identificateur du produit
	 * @throws SQLException
	 */
	
	public void deleteProduit(int produitId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from produits where id=?");
			
			// set param
			myStmt.setInt(1, produitId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}
	
	
	/**
	 * une methode qui permet de  recevoir la totalité des produits
	 * @return tous les produits figurant dans la base de donnée 
	 * @throws Exception
	 */
	
	public List<Compta> getAllCompta() throws Exception {
		List<Compta> list = new ArrayList<Compta>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
	
		
		
		
		try {
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("select * from produits order by id ");
			
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
	 * methode permettant de recevoir les informations  d'un produit  a partir de sa source
	 * @param source d'ou le produit est prevenue 
	 * @return les informations du produit ou des produits ayant la meme source  
	 * @throws Exception
	 */
	public List<Compta> searchCompta( String source) throws Exception {
		List<Compta> list = new ArrayList<Compta>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		

		try {
			
			source += "%";
			
			
			
			myStmt = myConn.prepareStatement("select * from produits where source like ?   order by date");
			
			
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
	
	
	private Compta convertRowToCompta(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String source = myRs.getString("source");
		String date = myRs.getString("date");
		int montant = myRs.getInt("montant");
		
		
		
		Compta tempCompta = new Compta(id, source , date , montant );
		
		return tempCompta;
	}
	
	/**
	 * methode qui calcule le total de montant des produits figurant dans la base de donnée
	 * @return le montant total des produits 
	 * @throws SQLException
	 */
	
	public List<String> TotalProduit() throws SQLException {
		List<String> list = new ArrayList<String>();
		Statement myStmt = null;
		ResultSet myRs=null;
		

		try {
			// prepare statement
			myStmt = myConn.createStatement();
			String sql = "Select sum(montant) as somme from produits";
			
			myRs=myStmt.executeQuery(sql);
			
			while(myRs.next()) {
			list.add(myRs.getString("somme"));
			
			}
			return list;
			
		
		}
		finally {
			close(myStmt,myRs);
		}
		
		
	}
	
	/**
	 * methode qui calcule le total de montant des charges figurant dans la base de donnée
	 * @return le montant total des charges 
	 * @throws SQLException
	 */
	public List<String> TotalCharge() throws SQLException {
		List<String> list = new ArrayList<String>();
		Statement myStmt = null;
		ResultSet myRs=null;
		

		try {
			// prepare statement
			myStmt = myConn.createStatement();
			String sql = "Select sum(montant) as somme from charges";
			
			myRs=myStmt.executeQuery(sql);
			
			while(myRs.next()) {
			list.add(myRs.getString("somme"));
			
			}
			return list;
			
		
		}
		finally {
			close(myStmt,myRs);
		}}
	
	/**
	 * methode qui calcule le resultat net
	 * @return la difference entre les produits et les charges
	 * @throws SQLException
	 */
	public List<String> Resultat() throws SQLException {
		List<String> list = new ArrayList<String>();
		Statement myStmt = null;
		ResultSet myRs=null;
		

		try {
			// prepare statement
			myStmt = myConn.createStatement();
			String sql = "SELECT (SELECT SUM(`montant`) FROM `produits`) - (SELECT SUM(`montant`) FROM `charges`) as somme";
			
			myRs=myStmt.executeQuery(sql);
			
			while(myRs.next()) {
			list.add(myRs.getString("somme"));
			
			}
			return list;
			
		
		}
		finally {
			close(myStmt,myRs);
		}}
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
		
		ProduitDAO dao = new ProduitDAO();
		System.out.println(dao.searchCompta("sponsor"));
		
		System.out.println(dao.getAllCompta());
		dao.TotalProduit();
		
	}


}
