package model;



/** Classe contenant les getters et setters et les constructeurs d'adherents
 * 
 * @author ZAKARIA EL AMIM
 *
 */

public class Event {
	private int id;
	private String name;
	private String date;
	private String descriptif;
	/**
	constructeur qui prend tous les parametres
	 */
	public Event(int id, String name, String date, String descriptif) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.descriptif = descriptif;
	}/**
	constructeur qui ne prend pas le id comme parametre 
	 */
	public Event( String name, String date, String descriptif) {
		super();
		
		this.name = name;
		this.date = date;
		this.descriptif = descriptif;
	}
	
	/**
	 * accesseur de l'indentificateur
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
	 * accesseur du nom 
	 * @return name le nom 
	 */
	public String getName() {
		return name;
	}
	/**
	 * modificateur du nom
	 * @param name le nom
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * accesseur date 
	 * @return la date 
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * modificateur date 
	 * @param date la date 
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * accesseur  descriptif 
	 * @return le descriptif 
	 */
	public String getDescriptif() {
		return descriptif;
	}
	/**
	 * modificateur descriptif 
	 * @param descriptif le descriptif
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	@Override
	public String toString() {
		return String
				.format("Event [id=%s, name=%s, date=%s, descriptif=%s, ]",
						id, name, date, descriptif);
	}

}
