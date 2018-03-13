package bean;

import java.io.Serializable;

public class Photo implements Serializable{

	public int idPhoto;
	public byte[] image;

	public int getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	} 

	public byte[] getImg() {
		return image;
	}

	public void setImg(byte[] image) {
		this.image = image;
	}

}
