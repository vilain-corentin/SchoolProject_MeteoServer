package bean;

import java.io.Serializable;
import java.sql.Date;

public class Meteo implements Serializable{

	public int idMeteo;
	public Date date;
	public String lieu;
	public DonneeMeteo donnees;

	public int getIdMeteo() {
		return idMeteo;
	}

	public void setIdMeteo(int idMeteo) {
		this.idMeteo = idMeteo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date d) {
		this.date = d;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public DonneeMeteo getDonnees() {
		return donnees;
	}

	public void setDonnees(DonneeMeteo donnees) {
		this.donnees = donnees;
	}
	
	public String toString() {
		String resultat = "Donnée Météo pour le " + this.getDate().toString() + " | Lieu : " + this.getLieu() + "\n";
		return resultat;
	}

}
