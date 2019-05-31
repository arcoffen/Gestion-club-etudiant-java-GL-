package model;
/** Classe contenant les getters et setters et les constructeurs d'adherents
 * 
 * @author ZAKARIA EL AMIM
 *
 */
public class Users {
	private int id;
	private String username;
	private String poste;
	private String password;
	
	/**
	constructeur qui prend tous les parametres
	 */
	public Users(int id, String username,String poste, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.poste=poste;
	}
	/**
	constructeur qui ne prend pas le id comme parametre 
	 */
	public Users( String username,String poste, String password) {
		super();
		
		this.username = username;
		this.password = password;
		this.poste=poste;
	}
	/**
	 * accesseur de l'identificateur 
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
	 * accesseur nom d'utilisateur  
	 * @return le nom d'utilisateur 
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * modificateur nom d'utilisateur  
	 * @param username nom d'utilisateur
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * accesseur mot de passe 
	 * @return mot de passe 
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * modificateur password
	 * @param password mot de passe 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return String
				.format("Uers [id=%s, username=%s, password=%s]",
						id, username, password);
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	

}
