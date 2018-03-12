package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import bean.DonneeMeteo;
import bean.Meteo;
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

	public Meteo getMeteoForOneDay(int y, int m, int d) {

		Meteo met = new Meteo();

		try {
			DonneeMeteo donnees = new DonneeMeteo();
			Vent vent = new Vent();

			String sql = "SELECT * from meteo mt inner join donnee don on mt.idDonnee = don.idDonnee inner join vent ve on don.idVent = ve.idVent where mt.dateMeteo = '"
					+ y + "-" + m + "-" + d + "' ";

			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
				met.setDonnees(donnees);

			}
			System.out.println("Exec sql : " + sql);

		} catch (Exception e) {
			System.out.println("Erreur " + e.getMessage());
		}
		return met;
	}


	/*
	 * public int enregistrerLivre(Livre l) { int res = 0;
	 * 
	 * try { String sql = "insert into t_livre (titre, auteur, annee)"+
	 * "values (?, ?, ?) "; PreparedStatement ps = co.prepareStatement(sql);
	 * ps.setString(1, l.getTitre()); ps.setString(2, l.getAuteur()); ps.setInt(3,
	 * l.getAnnee()); res = ps.executeUpdate();
	 * System.out.println("Exec sql : "+sql); } catch (Exception e) {
	 * System.out.println("Erreur Base.enregistrerLivre "+e.getMessage()); } return
	 * res; }
	 */

/*
	public ArrayList<Livre> listerLivres() { 
		ArrayList<Livre> lst = newArrayList<>();
		try { String sql = "select * from t_livre";
		PreparedStatement
	  ps = co.prepareStatement(sql); 
		ResultSet rs = ps.executeQuery(); while
	  (rs.next()) {
			Livre l = new Livre(); 
	  l.setTitre(rs.getString("titre"));
	  l.setAuteur(rs.getString("auteur"));
	  l.setAnnee(rs.getInt("annee"));
	  lst.add(l); 
	  } 
		System.out.println("Exec sql : "+sql);
	  
	 } catch (Exception e) {
	 System.out.println("Erreur Base.listerLivres "+e.getMessage()); } return lst;
	 }
*/
}
