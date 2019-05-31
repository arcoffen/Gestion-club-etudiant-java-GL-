package model;
/** Classe contenant les getters et setters et les constructeurs d'adherents
 * 
 * @author ZAKARIA EL AMIM
 *
 */


public class Compta {
	private int id;
	private String source;
	private String date;
	private int montant;
	
	/**
	constructeur qui prend tous les parametres
	 */
	public Compta(int id, String source, String date, int montant) {
		super();
		this.id = id;
		this.source = source;
		this.date = date;
		this.montant = montant;
	}
	/**
	constructeur qui ne prend pas le id comme parametre 
	 */
	public Compta( String source, String date, int montant) {
		super();
		
		this.source = source;
		this.date = date;
		this.montant = montant;
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
	 * accesseur de la source
	 * @return la source 
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * modificateur de la source 
	 * @param source la source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * accesseur de la date 
	 * @return la date 
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * modificateur de la date 
	 * @param date la date 
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * accesseur du montant
	 * @return le montant 
	 */
	public int getMontant() {
		return montant;
	}
	
	/**
	 * modificateur de montant 
	 * @param montant le montant 
	 */
	public void setMontant(int montant) {
		this.montant = montant;
	}
	@Override
	public String toString() {
		return String
				.format("Compta [id=%s, source=%s, date=%s, montant=%s ]",
						id, source, date, montant);
	}

}
