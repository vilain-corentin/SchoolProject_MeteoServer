package server;

import java.net.MalformedURLException;
import java.rmi.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import bean.DonneeMeteo;
import bean.Meteo;
import bean.Photo;
import bean.Vent;

public class GestionClient {

	public GestionInterface gestion;

	public GestionClient() {

		try {
			this.gestion = (GestionInterface) Naming.lookup("//localhost/server");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * try
	 * 
	 * {
	 * 
	 */

	/*
	 * if (gestion.initCo()) { ArrayList<Meteo> resultTab = gestion.meteoByDay(2018,
	 * 12, 24); System.out.println(resultTab.toString());
	 * 
	 * Meteo resultat = resultTab.get(0);
	 * 
	 * resultat.getDonnees().setTemperature(9999); resultat.setLieu("LIEU-TEST");
	 * 
	 * boolean result = gestion.modMeteo(resultat);
	 * 
	 * if (result) { System.out.println("Meteo modifiée avec succes"); } else {
	 * System.out.println("Erreur pour l'entree dans la base"); }
	 * 
	 * ArrayList<Meteo> result = gestion.meteoByMonth(2018, 3);
	 * System.out.println(result.toString());
	 * 
	 * boolean resul = gestion.authentification("jean", "paul");
	 * System.out.println(resul); if (resul) {
	 * System.out.println("Connexion autorisée"); } else {
	 * System.out.println("Connexion refusée"); }
	 * 
	 * ArrayList<Meteo> tabMeteo = new ArrayList<>();
	 * 
	 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 * formatter = formatter.withLocale(Locale.FRANCE); LocalDate d =
	 * LocalDate.parse("24/12/2018", formatter);
	 * 
	 * Vent vent = new Vent(); vent.setDirection("OUEST-NORD");
	 * vent.setVitesse(788);
	 * 
	 * // create random object Random randomno = new Random();
	 * 
	 * // create byte array byte[] nbyte = new byte[30]; byte[] nbyte2 = new
	 * byte[30];
	 * 
	 * // put the next byte in the array randomno.nextBytes(nbyte);
	 * randomno.nextBytes(nbyte2);
	 * 
	 * Photo photo1 = new Photo(); photo1.setImg(nbyte);
	 * 
	 * Photo photo2 = new Photo(); photo2.setImg(nbyte2);
	 * 
	 * DonneeMeteo donnee = new DonneeMeteo(); donnee.setEnsoleilement(100);
	 * donnee.setPrecipitation(0); donnee.setTemperature(19);
	 * donnee.setTypePrecipitation("NONE"); donnee.setVent(vent);
	 * donnee.addPhoto(photo1); donnee.addPhoto(photo2);
	 * 
	 * Meteo meteo = new Meteo(); meteo.setDate(java.sql.Date.valueOf(d));
	 * meteo.setLieu("RENNES"); meteo.setDonnees(donnee);
	 * 
	 * tabMeteo.add(meteo);
	 * 
	 * d = LocalDate.parse("01/01/2018", formatter);
	 * 
	 * vent = new Vent(); vent.setDirection("SUD-SUD"); vent.setVitesse(300);
	 * 
	 * donnee = new DonneeMeteo(); donnee.setEnsoleilement(50);
	 * donnee.setPrecipitation(1000); donnee.setTemperature(45);
	 * donnee.setTypePrecipitation("NONE"); donnee.setVent(vent);
	 * 
	 * meteo = new Meteo(); meteo.setDate(java.sql.Date.valueOf(d));
	 * meteo.setLieu("PARIS"); meteo.setDonnees(donnee);
	 * 
	 * tabMeteo.add(meteo);
	 * 
	 * boolean result = gestion.addMeteo(meteo);
	 * 
	 * if (result) { System.out.println("Meteo ajouté avec succes"); } else {
	 * System.out.println("Erreur pour l'entree dans la base"); }
	 * 
	 * boolean result1 = gestion.addGroupMeteo(tabMeteo);
	 * 
	 * if (result1) { System.out.println("Meteos ajoutés avec succes"); } else {
	 * System.out.println("Erreur pour l'entree dans la base"); }
	 * 
	 * } else { System.out.println("La connexion à la base de donnée est un echec");
	 * }
	 */

	/*
	 * }catch( Exception e) { System.out.println("Client exception: " + e); }
	 */
}