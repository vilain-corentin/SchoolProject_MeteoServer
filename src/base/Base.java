package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class Base {

	String config = "config"; // fichier config.properties
	
	String url = null;
	String user = null;
	String password = null;
	
	Connection co = null;
	
	static {
		try {
			Class.forName
				("com.mysql.jdbc.Driver").
					newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	

	}


	public String ouvrir() {
		String res = "";
		try {
			ResourceBundle rs = ResourceBundle.getBundle(config);
			url = rs.getString("url");
			user = rs.getString("user");
			password = rs.getString("password");
			System.out.println("url = "+url);
			System.out.println("user = "+user);

			co = DriverManager.getConnection(url, user, password);
			res = "Connexion Reussie";
		}
		catch (Exception e) {
			res = "Erreur Base.ouvrir "+e.getMessage();
		}

		return res;
	}
	
	public void fermer() {
		if (co != null) try {co.close();}catch (Exception e) {}
	}
	
	/*public int enregistrerLivre(Livre l) {
		int res = 0;
		
		try {
			String sql = "insert into t_livre (titre, auteur, annee)"+
					"values (?, ?, ?) ";
			PreparedStatement ps = co.prepareStatement(sql);
			ps.setString(1,  l.getTitre());
			ps.setString(2,  l.getAuteur());
			ps.setInt(3,  l.getAnnee());
			res = ps.executeUpdate();
			System.out.println("Exec sql : "+sql);
		}
		catch (Exception e) {
			System.out.println("Erreur Base.enregistrerLivre "+e.getMessage());
		}
		return res;
	}*/
	
	/*public ArrayList<Livre> listerLivres() {
		ArrayList<Livre> lst = new ArrayList<>();
		try {
			String sql = "select * from t_livre";
			PreparedStatement ps = co.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Livre l = new Livre();
				l.setTitre(rs.getString("titre"));
				l.setAuteur(rs.getString("auteur"));
				l.setAnnee(rs.getInt("annee"));
				lst.add(l);
			}
			System.out.println("Exec sql : "+sql);

		}
		catch (Exception e) {
			System.out.println("Erreur Base.listerLivres "+e.getMessage());
		}
		return lst;
	}*/
	
	/*public static void main(String [] args) {
		System.out.println("Base");
		Base base = new Base();
		base.ouvrir();
		
		Livre l = new Livre("jdbc","aaa",2018);
		base.enregistrerLivre(l);
		
		ArrayList<Livre> res = base.listerLivres();
		for (Livre livre : res) {
			System.out.println("livre "+livre.getTitre());
		}
		
		base.fermer();
	}*/
	
}
