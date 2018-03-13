package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class DonneeMeteo implements Serializable{

	public int idDonnee;
	public float precipitation;
	public String typePrecipitation;
	public float temperature;
	public float ensoleilement;
	public Vent vent;
	public ArrayList<Photo> photos;
	
	public DonneeMeteo() {
		this.photos = new ArrayList<>();
	}
	
	public void addPhoto(Photo photo) {
		this.photos.add(photo);
	}
	
	public void removePhoto(Photo photo) {
		this.photos.remove(photo);
	}

	public int getIdDonnee() {
		return idDonnee;
	}

	public void setIdDonnee(int idDonnee) {
		this.idDonnee = idDonnee;
	}

	public float getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(float precipitation) {
		this.precipitation = precipitation;
	}

	public String getTypePrecipitation() {
		return typePrecipitation;
	}

	public void setTypePrecipitation(String typePrecipitation) {
		this.typePrecipitation = typePrecipitation;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getEnsoleilement() {
		return ensoleilement;
	}

	public void setEnsoleilement(float ensoleilement) {
		this.ensoleilement = ensoleilement;
	}

	public Vent getVent() {
		return vent;
	}

	public void setVent(Vent vent) {
		this.vent = vent;
	}

}
