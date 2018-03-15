package base;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import bean.DonneeMeteo;
import bean.Meteo;
import bean.Photo;
import bean.Vent;

public class Base {

	String config = "config"; // fichier config.properties

	String url = null;
	String user = null;
	String password = null;

	Connection co = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public boolean ouvrir() {
		boolean res = false;
		try {
			ResourceBundle rs = ResourceBundle.getBundle(config);
			url = rs.getString("url");
			user = rs.getString("user");
			password = rs.getString("password");
			System.out.println("url = " + url);
			System.out.println("user = " + user);

			co = DriverManager.getConnection(url, user, password);
			res = true;
		} catch (Exception e) {
			System.out.println("Erreur Base.ouvrir " + e.getMessage());
		}

		return res;
	}

	public void fermer() {
		if (co != null)
			try {
				co.close();
			} catch (Exception e) {
			}
	}
	
	public InputStream getPhoto(int id) {
		String sql = "SELECT * FROM `photo` WHERE idPhoto = " + id;

		try {
			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				return rs.getBinaryStream("chemin");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public ArrayList<Meteo> getMeteoForOneDay(int y, int m, int d, String lieu) {

		ArrayList<Meteo> tabMeteo = new ArrayList<>();

		try {

			String sql = "SELECT * from meteo mt inner join donnee don on mt.idDonnee = don.idDonnee inner join vent ve on don.idVent = ve.idVent where mt.dateMeteo = '"
					+ y + "-" + m + "-" + d + "' and lieuMeteo = '" + lieu + "'";

			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Meteo met = new Meteo();
				DonneeMeteo donnees = new DonneeMeteo();
				Vent vent = new Vent();

				vent.setIdVent(rs.getInt("idVent"));
				vent.setDirection(rs.getString("direction"));
				vent.setVitesse(rs.getFloat("vitesse"));

				donnees.setIdDonnee(rs.getInt("idDonnee"));
				donnees.setPrecipitation(rs.getFloat("precipitation"));
				donnees.setTypePrecipitation(rs.getString("typePrecipitation"));
				donnees.setTemperature(rs.getFloat("temperature"));
				donnees.setEnsoleilement(rs.getFloat("ensoleilement"));
				donnees.setVent(vent);

				met.setIdMeteo(rs.getInt("idMeteo"));
				met.setDate(rs.getDate("dateMeteo"));
				met.setLieu(rs.getString("lieuMeteo"));

				sql = "SELECT * from liasonphoto li INNER JOIN photo ph on li.idPhoto = ph.idPhoto where li.idMeteo = "
						+ met.getIdMeteo();

				ps = co.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();

				while (rs2.next()) {
					Blob blobImg;
					Photo photo = new Photo();
					photo.setIdPhoto(rs2.getInt("idPhoto"));
					blobImg = rs2.getBlob("chemin");
					photo.setImg(blobImg.getBytes(1, (int) blobImg.length()));
					donnees.addPhoto(photo);
				}

				met.setDonnees(donnees);
				tabMeteo.add(met);
			}

		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}

		return tabMeteo;
	}

	public ArrayList<Meteo> getMeteoByMonth(int y, int m, String lieu) {

		ArrayList<Meteo> tabMeteo = new ArrayList<>();

		try {

			String sql = "SELECT * from meteo mt inner join donnee don on mt.idDonnee = don.idDonnee inner join vent ve on don.idVent = ve.idVent where (MONTH(mt.dateMeteo) = "
					+ m + " and YEAR(mt.dateMeteo) = " + y + ") and lieuMeteo = '" + lieu + "'";

			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSet rsPhoto;

			// TODO: Refactoring : Faire des fonctions de remplissage pour le vent, donnee,
			// etc... car copié collé du haut !
			while (rs.next()) {
				Meteo met = new Meteo();
				DonneeMeteo donnees = new DonneeMeteo();
				Vent vent = new Vent();

				vent.setIdVent(rs.getInt("idVent"));
				vent.setDirection(rs.getString("direction"));
				vent.setVitesse(rs.getFloat("vitesse"));

				donnees.setIdDonnee(rs.getInt("idDonnee"));
				donnees.setPrecipitation(rs.getFloat("precipitation"));
				donnees.setTypePrecipitation(rs.getString("typePrecipitation"));
				donnees.setTemperature(rs.getFloat("temperature"));
				donnees.setEnsoleilement(rs.getFloat("ensoleilement"));
				donnees.setVent(vent);

				met.setIdMeteo(rs.getInt("idMeteo"));
				met.setDate(rs.getDate("dateMeteo"));
				met.setLieu(rs.getString("lieuMeteo"));

				sql = "SELECT * from liasonphoto li INNER JOIN photo ph on li.idPhoto = ph.idPhoto where li.idMeteo = "
						+ met.getIdMeteo();

				ps = co.prepareStatement(sql);
				rsPhoto = ps.executeQuery();

				while (rsPhoto.next()) {
					Blob blobImg;
					Photo photo = new Photo();
					photo.setIdPhoto(rsPhoto.getInt("idPhoto"));
					blobImg = rsPhoto.getBlob("chemin");
					photo.setImg(blobImg.getBytes(1, (int) blobImg.length()));
					donnees.addPhoto(photo);
				}

				met.setDonnees(donnees);

				tabMeteo.add(met);
			}

		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}

		return tabMeteo;
	}

	public boolean resquestAuth(String user, String pass) {
		String sql = "SELECT * FROM `users` WHERE login = '" + user + "' and pass = '" + pass + "'";
		System.out.println(sql);
		PreparedStatement ps;
		try {

			ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Erreur : " + e.getMessage());
		}

		return false;

	}

	public boolean addMeteo(Meteo meteo) {

		int nbLine = 0;
		int verifNbLine = 0;
		PreparedStatement ps;
		ResultSet rs;

		String sqlAntiDoublon = "SELECT * from meteo mt inner join donnee don on mt.idDonnee = don.idDonnee inner join vent ve on don.idVent = ve.idVent where mt.dateMeteo = '"
				+ meteo.getDate().toString()
				+ "' and lieuMeteo = '" + meteo.getLieu() + "'";
		
		try {
			ps = co.prepareStatement(sqlAntiDoublon);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				return false;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sqlVent = "insert into vent (direction, vitesse) values (? ,?)";
		String sqlDonnee = "insert into donnee (precipitation, typePrecipitation, temperature, ensoleilement, idVent) values (?, ?, ?, ?, ?)";
		String sqlMeteo = "insert into meteo (dateMeteo, lieuMeteo, idDonnee) values (?, ?, ?)";
		String sqlPhoto = "insert into photo (chemin) values (?)";
		String sqlLiasionPhoto = "insert into liasonphoto (idMeteo, idPhoto) values (?, ?)";

		try {
			// vent
			ps = co.prepareStatement(sqlVent);
			ps.setString(1, meteo.getDonnees().getVent().getDirection());
			ps.setFloat(2, meteo.getDonnees().getVent().getVitesse());
			nbLine += ps.executeUpdate();
			verifNbLine++;

			// donnee
			ps = co.prepareStatement(sqlDonnee);
			ps.setFloat(1, meteo.getDonnees().getPrecipitation());
			ps.setString(2, meteo.getDonnees().getTypePrecipitation());
			ps.setFloat(3, meteo.getDonnees().getTemperature());
			ps.setFloat(4, meteo.getDonnees().getEnsoleilement());
			ps.setInt(5, getLastVent());
			nbLine += ps.executeUpdate();
			verifNbLine++;

			// meteo
			ps = co.prepareStatement(sqlMeteo);
			ps.setDate(1, meteo.getDate());
			ps.setString(2, meteo.getLieu());
			ps.setInt(3, getLastDonnee());
			nbLine += ps.executeUpdate();
			verifNbLine++;

			// photo
			if (!meteo.getDonnees().photos.isEmpty()) {
				for (Photo photo : meteo.getDonnees().photos) {
					ps = co.prepareStatement(sqlPhoto);
					ps.setBlob(1, new javax.sql.rowset.serial.SerialBlob(photo.getImg()));
					nbLine += ps.executeUpdate();
					verifNbLine++;

					ps = co.prepareStatement(sqlLiasionPhoto);
					ps.setInt(1, getLastMeteo());
					ps.setInt(2, getLastPhoto());
					nbLine += ps.executeUpdate();
					verifNbLine++;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (nbLine > 0 && (nbLine == verifNbLine)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addMeteoGroup(ArrayList<Meteo> tabMeteo) {

		boolean verif = true;

		for (Meteo meteo : tabMeteo) {
			if (!this.addMeteo(meteo)) {
				verif = false;
			}
		}

		return verif;

	}

	public boolean modMeteo(Meteo meteo) {
		if (this.eraseMeteo(meteo) && this.addMeteo(meteo)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean eraseMeteo(Meteo meteo) {

		PreparedStatement ps;
		int nbLine = 0;
		int verifNbLine = 0;

		for (Photo photo : meteo.getDonnees().photos) {
			try {
				ps = co.prepareStatement("delete from photo where idPhoto = " + photo.getIdPhoto());
				nbLine += ps.executeUpdate();
				verifNbLine++;
				ps = co.prepareStatement("delete from liasonphoto where idPhoto = " + photo.getIdPhoto());
				nbLine += ps.executeUpdate();
				verifNbLine++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		String sqlVent = "delete from vent where idVent = " + meteo.getDonnees().getVent().getIdVent();
		String sqlDonnee = "delete from donnee where idDonnee = " + meteo.getDonnees().getIdDonnee();
		String sqlMeteo = "delete from meteo where idMeteo = " + meteo.getIdMeteo();

		try {
			ps = co.prepareStatement(sqlVent);
			nbLine += ps.executeUpdate();
			verifNbLine++;
			ps = co.prepareStatement(sqlDonnee);
			nbLine += ps.executeUpdate();
			verifNbLine++;
			ps = co.prepareStatement(sqlMeteo);
			nbLine += ps.executeUpdate();
			verifNbLine++;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (nbLine > 0 && (nbLine == verifNbLine)) {
			return true;
		} else {
			return false;
		}

	}

	private int getLastVent() {
		String sql = "SELECT * FROM `vent` order by idVent DESC limit 1";
		int lastid = 0;

		try {
			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lastid = rs.getInt("idVent");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastid;
	}

	private int getLastDonnee() {
		String sql = "SELECT * FROM `donnee` order by idDonnee DESC limit 1";
		int lastid = 0;

		try {
			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lastid = rs.getInt("idDonnee");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastid;
	}

	private int getLastLiasonPhoto() {
		String sql = "SELECT * FROM `liasonphoto` order by idLiason DESC limit 1";
		int lastid = 0;

		try {
			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lastid = rs.getInt("idLiason");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastid;
	}

	private int getLastMeteo() {
		String sql = "SELECT * FROM `meteo` order by idMeteo DESC limit 1";
		int lastid = 0;

		try {
			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lastid = rs.getInt("idMeteo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastid;
	}

	private int getLastPhoto() {
		String sql = "SELECT * FROM `photo` order by idPhoto DESC limit 1";
		int lastid = 0;

		try {
			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lastid = rs.getInt("idPhoto");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastid;
	}

	private int getLastUser() {
		String sql = "SELECT * FROM `users` order by idUser DESC limit 1";
		int lastid = 0;

		try {
			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lastid = rs.getInt("idUser");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastid;
	}

}
