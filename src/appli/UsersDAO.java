package appli;

import java.util.*;

import model.Compta;
import model.Users;

import java.sql.*;
import java.io.*;

/** Classe responsable de la gestion des méthodes et la connection avec la base de donnée
 * 
 * @author ZAKARIA EL AMIM
 *
 */
public class UsersDAO {
	
	
	private Connection myConn ;
	/**
	 * permet la connection avec notre base de donnée
	 * @throws Exception
	 */
	public UsersDAO() throws Exception {
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
	 * methode qui permet d'ajouter un user  en executant une requete sql 
	 * @param theUser
	 * @see Users
	 * @throws Exception
	 */
	
	public void addUser(Users theUser) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into users"
					+ " (username,poste , password)"
					+ " values (? , ? , ?)");
			
			// set params
			myStmt.setString(1, theUser.getUsername());
			myStmt.setString(2, theUser.getPoste());
			myStmt.setString(3, theUser.getPassword());
		
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	/**
	 * methode qui permet de modifier les parametre d un user  en executant une requete sql 
	 * @param theUser instantiation de la classe Users
	 * @see Users
	 * @throws SQLException
	 */
	public void updateUser(Users theUser) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update users"
					+ " set username=?, poste=? , password=?"
					+ " where id=?");
			
			// set params
			myStmt.setString(1, theUser.getUsername());
			myStmt.setString(2, theUser.getPoste());
			myStmt.setString(3, theUser.getPassword());
			myStmt.setInt(4, theUser.getId());
			
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	/**
	 * methode qui permet de supprimer un user   en executant une requete sql 
	 * @param userId l'identificateur de l'user
	 * @throws SQLException
	 */
	public void deleteUser(int userId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from users where id=?");
			
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
	 * une methode qui permet de verifier l'authentification si le username et le password figurent dans la base de donnée 
	 * il retourne true sinon il retourne false 
	 * @param user le username saisie par l'utilisateur 
	 * @param pass le password saisie par l'utilisateur
	 * @return valeur boolean 
	 * @throws SQLException
	 */
	
	public boolean Verifier(String user,String pass) throws SQLException {
		Statement myStmt = null;
		ResultSet myRs = null;
		String sql = "SELECT username , password from users where username = '"+user+ "'and password ='"+pass+"'";
		boolean isValid=false;
		try {
			myStmt = myConn.createStatement();
			myRs=myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				isValid=true;
			}
		}
		catch(Exception e ) {
			
		}
		finally {
			close(myStmt,myRs);
		}
		return isValid;
	}
	
	public boolean VerifierInfo(String user,String poste) throws SQLException {
		Statement myStmt = null;
		ResultSet myRs = null;
		String sql = "SELECT username , password from users where username = '"+user+ "'and password ='"+poste+"'";
		boolean isValid=false;
		try {
			myStmt = myConn.createStatement();
			myRs=myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				isValid=true;
			}
		}
		catch(Exception e ) {
			
		}
		finally {
			close(myStmt,myRs);
		}
		return isValid;
	}
	/**
	 * une methode qui permet de  recevoir la totalité des users
	 * @return tous les users figurant dans la base de donnée 
	 * @throws Exception
	 */
	
	public List<Users> getAllUsers() throws Exception {
		List<Users> list = new ArrayList<Users>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from users order by id ");
			
			while (myRs.next()) {
				Users tempUser = convertRowToEmployee(myRs);
				list.add(tempUser);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	/**
	 * methode permettant de recevoir les informations  d'un user  a partir de son username
	 * @param username  
	 * @return les informations du users qui a le meme  username  
	 * @throws Exception
	 */
	public List<Users> searchUsers(String username) throws Exception {
		List<Users> list = new ArrayList<Users>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			username += "%";
			myStmt = myConn.prepareStatement("select * from users where username like ?  order by username");
			
			myStmt.setString(1, username);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Users tempUser = convertRowToEmployee(myRs);
				list.add(tempUser);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Users convertRowToEmployee(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String username = myRs.getString("username");
		String poste = myRs.getString("poste");
		String password = myRs.getString("password");
		
		
		Users tempUser = new Users(id, username,poste, password );
		
		return tempUser;
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
		
		UsersDAO dao = new UsersDAO();
		System.out.println(dao.searchUsers("Arcoffen"));
		

		System.out.println(dao.getAllUsers());
		
	}

}
