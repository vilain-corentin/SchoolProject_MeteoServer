package test;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bean.DonneeMeteo;
import bean.Meteo;
import bean.Photo;
import bean.Vent;
import server.GestionClient;

public class TestServer {

	public GestionClient manager;
	DateTimeFormatter formatter;

	@Before
	public void beforeTest() {

		manager = new GestionClient();
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		formatter = formatter.withLocale(Locale.FRANCE);

		try {
			Assert.assertTrue(this.manager.gestion.initCo());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void connexionBase() {
		try {
			Assert.assertTrue(this.manager.gestion.initCo());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void authentification() {
		try {
			Assert.assertTrue(this.manager.gestion.authentification("meteo@meteo.com", "meteo"));
			Assert.assertFalse(this.manager.gestion.authentification("meteo@meteo.com", "mauvaisMdp"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ajouterAndRecupererMeteo() {
		try {
			// Creation d'une donnée
			LocalDate d = LocalDate.parse("01/01/2018", formatter);

			Meteo meteo = new Meteo();
			meteo.setDate(java.sql.Date.valueOf(d));
			meteo.setLieu("brest");

			DonneeMeteo donnee = new DonneeMeteo();
			donnee.setEnsoleilement(999);
			donnee.setPrecipitation(999);
			donnee.setTemperature(999);
			donnee.setTypePrecipitation("pluie");

			Vent vent = new Vent();
			vent.setDirection("NORD");
			vent.setVitesse(999);

			donnee.setVent(vent);
			meteo.setDonnees(donnee);

			// Enregistrement de la donnée en base
			Assert.assertTrue(this.manager.gestion.addMeteo(meteo));

			// Recuperation de la donnée depuis la base
			ArrayList<Meteo> meteoTestTab = this.manager.gestion.meteoByDay(2018, 01, 01, "brest");
			Meteo meteoTest = meteoTestTab.get(0);

			// Test d'égalité entre les 2 données
			Assert.assertTrue(meteo.getLieu().equalsIgnoreCase(meteoTest.getLieu()));
			Assert.assertTrue(meteo.getDonnees().getTemperature() == meteoTest.getDonnees().getTemperature());
			Assert.assertTrue(
					meteo.getDonnees().getVent().getVitesse() == meteoTest.getDonnees().getVent().getVitesse());

			// Suppresion de la donnée en base
			Assert.assertTrue(this.manager.gestion.eraseMeteo(meteoTest));

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ajouterAndRecupererGroupeMeteo() {
		try {

			// Creation d'une donnée
			LocalDate d = LocalDate.parse("01/01/2018", formatter);

			Meteo meteo = new Meteo();
			meteo.setDate(java.sql.Date.valueOf(d));
			meteo.setLieu("brest");

			DonneeMeteo donnee = new DonneeMeteo();
			donnee.setEnsoleilement(999);
			donnee.setPrecipitation(999);
			donnee.setTemperature(999);
			donnee.setTypePrecipitation("pluie");

			Vent vent = new Vent();
			vent.setDirection("NORD");
			vent.setVitesse(999);

			donnee.setVent(vent);
			meteo.setDonnees(donnee);

			// Creation d'une donnée 2
			d = LocalDate.parse("02/01/2018", formatter);

			Meteo meteo1 = new Meteo();
			meteo1.setDate(java.sql.Date.valueOf(d));
			meteo1.setLieu("brest");

			DonneeMeteo donnee1 = new DonneeMeteo();
			donnee1.setEnsoleilement(999);
			donnee1.setPrecipitation(999);
			donnee1.setTemperature(999);
			donnee1.setTypePrecipitation("pluie");

			Vent vent1 = new Vent();
			vent1.setDirection("NORD");
			vent1.setVitesse(999);

			donnee1.setVent(vent1);
			meteo1.setDonnees(donnee1);

			// Creation de l'ensemble des données
			ArrayList<Meteo> tabMeteo = new ArrayList<>();
			tabMeteo.add(meteo);
			tabMeteo.add(meteo1);

			// Enregistrement de la donnée en base
			Assert.assertTrue(this.manager.gestion.addGroupMeteo(tabMeteo));

			// Recuperation de la donnée depuis la base
			ArrayList<Meteo> meteoTestTab = this.manager.gestion.meteoByMonth(2018, 01, "brest");
			Meteo meteoTest = meteoTestTab.get(0);
			Meteo meteoTest1 = meteoTestTab.get(1);

			// Test d'égalité entre les 2 données 1
			Assert.assertTrue(meteo.getLieu().equalsIgnoreCase(meteoTest.getLieu()));
			Assert.assertTrue(meteo.getDonnees().getTemperature() == meteoTest.getDonnees().getTemperature());
			Assert.assertTrue(
					meteo.getDonnees().getVent().getVitesse() == meteoTest.getDonnees().getVent().getVitesse());

			// Test d'égalité entre les 2 données 2
			Assert.assertTrue(meteo1.getLieu().equalsIgnoreCase(meteoTest1.getLieu()));
			Assert.assertTrue(meteo1.getDonnees().getTemperature() == meteoTest1.getDonnees().getTemperature());
			Assert.assertTrue(
					meteo1.getDonnees().getVent().getVitesse() == meteoTest1.getDonnees().getVent().getVitesse());

			// Suppresion de la donnée en base
			Assert.assertTrue(this.manager.gestion.eraseMeteo(meteoTest));
			Assert.assertTrue(this.manager.gestion.eraseMeteo(meteoTest1));

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void modificationMeteo() {
		try {
			// Creation d'une donnée
			LocalDate d = LocalDate.parse("01/01/2018", formatter);

			Meteo meteo = new Meteo();
			meteo.setDate(java.sql.Date.valueOf(d));
			meteo.setLieu("brest");

			DonneeMeteo donnee = new DonneeMeteo();
			donnee.setEnsoleilement(999);
			donnee.setPrecipitation(999);
			donnee.setTemperature(999);
			donnee.setTypePrecipitation("pluie");

			Vent vent = new Vent();
			vent.setDirection("NORD");
			vent.setVitesse(999);

			donnee.setVent(vent);
			meteo.setDonnees(donnee);

			// Enregistrement de la donnée en base
			Assert.assertTrue(this.manager.gestion.addMeteo(meteo));

			// Recupération pour suppresion
			ArrayList<Meteo> meteoTestTab = this.manager.gestion.meteoByDay(2018, 01, 01, "brest");
			Meteo meteoTest = meteoTestTab.get(0);

			// Modification de la donnée
			meteoTest.getDonnees().setEnsoleilement(-555);
			meteoTest.setLieu("paris");
			meteoTest.getDonnees().getVent().setVitesse(-555);

			// Enregistrement de la modification
			Assert.assertTrue(this.manager.gestion.modMeteo(meteoTest));

			meteoTestTab = this.manager.gestion.meteoByDay(2018, 01, 01, "paris");
			meteoTest = meteoTestTab.get(0);

			// Suppresion de la donnée
			Assert.assertTrue(this.manager.gestion.eraseMeteo(meteoTest));

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void securiteDoublon() {
		try {
			// Creation d'une donnée
			LocalDate d = LocalDate.parse("01/01/2018", formatter);

			Meteo meteo = new Meteo();
			meteo.setDate(java.sql.Date.valueOf(d));
			meteo.setLieu("brest");

			DonneeMeteo donnee = new DonneeMeteo();
			donnee.setEnsoleilement(999);
			donnee.setPrecipitation(999);
			donnee.setTemperature(999);
			donnee.setTypePrecipitation("pluie");

			Vent vent = new Vent();
			vent.setDirection("NORD");
			vent.setVitesse(999);

			donnee.setVent(vent);
			meteo.setDonnees(donnee);

			// Enregistrement de la donnée en base
			Assert.assertTrue(this.manager.gestion.addMeteo(meteo));

			// Renregistrement de la donnée en base
			Assert.assertFalse(this.manager.gestion.addMeteo(meteo));

			// Recupération pour suppresion
			ArrayList<Meteo> meteoTestTab = this.manager.gestion.meteoByDay(2018, 01, 01, "brest");
			Meteo meteoTest = meteoTestTab.get(0);

			// Suppresion de la donnée
			Assert.assertTrue(this.manager.gestion.eraseMeteo(meteoTest));

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ajouterEtRecupererPhoto() {
		try {
			// Creation d'une donnée
			LocalDate d = LocalDate.parse("01/01/2018", formatter);

			Meteo meteo = new Meteo();
			meteo.setDate(java.sql.Date.valueOf(d));
			meteo.setLieu("brest");

			DonneeMeteo donnee = new DonneeMeteo();
			donnee.setEnsoleilement(999);
			donnee.setPrecipitation(999);
			donnee.setTemperature(999);
			donnee.setTypePrecipitation("pluie");

			Vent vent = new Vent();
			vent.setDirection("NORD");
			vent.setVitesse(999);

			// create random image
			Random randomno = new Random();

			// create byte array
			byte[] nbyte = new byte[300];

			// put the next byte in the array randomno.nextBytes(nbyte);
			randomno.nextBytes(nbyte);

			// init photo
			Photo photo = new Photo();
			photo.setImg(nbyte);

			donnee.photos.add(photo);

			donnee.setVent(vent);
			meteo.setDonnees(donnee);

			// Enregistrement de la donnée en base
			Assert.assertTrue(this.manager.gestion.addMeteo(meteo));

			ArrayList<Meteo> meteoTestTab = this.manager.gestion.meteoByDay(2018, 01, 01, "brest");
			Meteo meteoTest = meteoTestTab.get(0);

			Assert.assertTrue(meteo.getDonnees().photos.get(0).getImg().equals(nbyte));

			// Suppresion de la donnée
			Assert.assertTrue(this.manager.gestion.eraseMeteo(meteoTest));

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void supprimerMeteo() {
		try {
			// Creation d'une donnée
			LocalDate d = LocalDate.parse("01/01/2018", formatter);

			Meteo meteo = new Meteo();
			meteo.setDate(java.sql.Date.valueOf(d));
			meteo.setLieu("brest");

			DonneeMeteo donnee = new DonneeMeteo();
			donnee.setEnsoleilement(999);
			donnee.setPrecipitation(999);
			donnee.setTemperature(999);
			donnee.setTypePrecipitation("pluie");

			Vent vent = new Vent();
			vent.setDirection("NORD");
			vent.setVitesse(999);

			donnee.setVent(vent);
			meteo.setDonnees(donnee);

			// Enregistrement de la donnée en base
			Assert.assertTrue(this.manager.gestion.addMeteo(meteo));

			ArrayList<Meteo> meteoTestTab = this.manager.gestion.meteoByDay(2018, 01, 01, "brest");
			Meteo meteoTest = meteoTestTab.get(0);

			// Suppresion de la donnée
			Assert.assertTrue(this.manager.gestion.eraseMeteo(meteoTest));

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
