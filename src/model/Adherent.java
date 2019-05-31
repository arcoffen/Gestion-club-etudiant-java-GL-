package model;
/** Classe contenant les getters et setters et les constructeurs d'adherents
 * 
 * @author ZAKARIA EL AMIM
 *
 */



public class Adherent {
	
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String filiere;
	private int absence ;
	
	
	/**
	constructeur qui prend tous les parametres
	 */
	

	public Adherent(int id, String firstname, String lastname, String email, String filiere, int absence) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.filiere = filiere;
		this.absence=absence;
		
	}
	
	/**
	constructeur qui ne prend pas le id comme parametre 
	 */
	public Adherent( String firstname, String lastname, String email, String filiere, int absence) {
		super();
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.filiere = filiere;
		this.absence=absence;
		
	}
	/**
	 * accesseur d'absence
	 * @return le nombre d'absence
 	 */
	public int getAbsence() {
		return absence;
	}
	/**
	 * modificateur d'absence
	 * @param absence le nombre d'absence
	 */
	public void setAbsence(int absence) {
		this.absence = absence;
	}
	/**
	 * accesseur d'identificateur
	 * @return l'identificateur 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * modificateur de l'identificateur
	 * @param id l'identificateur 
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * accesseur de prenom
	 * @return le prenom 
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * modificateur du prenom
	 * @param le prenom
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * accesseur de nom 
	 * @return lenom 
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * modificateur du nom 
	 * @param lastname le nom 
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * accesseur d'adresse email
	 * @return l'adresse email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * modificateur de l'adresse email
	 * @param email  l'adresse email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * accesseur de la filiere
	 * @return la filiere
	 */
	public String getFiliere() {
		return filiere;
	}
	/**
	 * modificateur de la filiere
	 * @param filiere la filiere 
 	 */
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	
	@Override
	public String toString() {
		return String
				.format("Adherent [id=%s, firstname=%s, lastname=%s, email=%s, filiere=%s, absence=%s]",
						id, firstname, lastname, email, filiere,absence);
	}

}
