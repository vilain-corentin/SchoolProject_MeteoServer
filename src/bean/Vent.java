package bean;

import java.io.Serializable;

public class Vent implements Serializable{

	public int idVent;
	public String direction;
	public float vitesse;

	public int getIdVent() {
		return idVent;
	}

	public void setIdVent(int idVent) {
		this.idVent = idVent;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public float getVitesse() {
		return vitesse;
	}

	public void setVitesse(float vitesse) {
		this.vitesse = vitesse;
	}

}
