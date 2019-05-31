package appli;

import java.io.FileInputStream;
import java.sql.*;
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


public class EventDAO {
	/**
	 * permet la connection avec notre base de donnée
	 * @throws Exception
	 */
private Connection myConn ;
	
	public EventDAO() throws Exception {
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
	 * methode qui permet d'ajouter un event  en executant une requete sql 
	 * @param theEvent
	 * @see Event
	 * @throws Exception
	 */
	
	public void addEvent(Event theEvent) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into events"
					+ " (name, date , descriptif )"
					+ " values (? , ? , ? )");
			
			// set params
			myStmt.setString(1, theEvent.getName());
			myStmt.setString(2, theEvent.getDate());
			myStmt.setString(3, theEvent.getDescriptif());
			
		
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	/**
	 * methode qui permet de modifier un event  en executant une requete sql 
	 * @param theEvent
	 * @see Event
	 * @throws SQLException
	 */
	public void updateEvent(Event theEvent) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update events"
					+ " set name=?, date=? , descriptif=?" 
					+ " where id=?");
			
			// set params
			myStmt.setString(1, theEvent.getName());
			myStmt.setString(2, theEvent.getDate());
			myStmt.setString(3, theEvent.getDescriptif());
			
			myStmt.setInt(4, theEvent.getId());
			
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	/**
	 * methode qui permet de supprimer un event  en executant une requete sql 
	 * @param eventId l'identificateur de l'event
	 * @throws SQLException
	 */
	public void deleteEvent(int eventId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from events where id=?");
			
			// set param
			myStmt.setInt(1, eventId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}
	
	
	/**
	 * une mthode qui permet de  recevoir la totalité des events
	 * @return toutes les events figurant dans la base de donnée 
	 * @throws Exception
	 */
	
	public List<Event> getAllEvent() throws Exception {
		List<Event> list = new ArrayList<Event>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from events order by id ");
			
			while (myRs.next()) {
				Event tempEvent = convertRowToEvent(myRs);
				list.add(tempEvent);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	/**
	 * methode permettant de recevoir les informations  d'un event a partir de son nom 
	 * @param name le nom de l'evenement 
	 * @return les information de l'event ou les evenement ayant le meme nom  
	 * @throws Exception
	 */
	
	public List<Event> searchEvent( String name) throws Exception {
		List<Event> list = new ArrayList<Event>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			name += "%";
			
			
			
			myStmt = myConn.prepareStatement("select * from events where name like ?   order by date");
			
			
			myStmt.setString(1, name);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Event tempEvent = convertRowToEvent(myRs);
				list.add(tempEvent);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	private Event convertRowToEvent(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String name = myRs.getString("name");
		String date = myRs.getString("date");
		String descriptif = myRs.getString("descriptif");
		
		
		
		Event tempEvent = new Event(id, name , date , descriptif );
		
		return tempEvent;
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
		
		EventDAO dao = new EventDAO();
		System.out.println(dao.searchEvent("Hackathon"));
		
		System.out.println(dao.getAllEvent());
		
	}

}
