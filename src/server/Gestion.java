package server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

import base.Base;
import bean.Meteo;

public class Gestion extends UnicastRemoteObject implements GestionInterface {

	public Gestion() throws RemoteException {
	}

	public Base base = new Base();

	public Boolean initCo() {
		return base.ouvrir();
	}

	public ArrayList<Meteo> meteoByDay(int y, int m, int d) throws RemoteException {
		return base.getMeteoForOneDay(y, m, d);
	}

	@Override
	public ArrayList<Meteo> meteoByMonth(int y, int m) throws RemoteException {
		return base.getMeteoByMonth(y, m);
	}

	@Override
	public Boolean authentification(String user, String pass) {
		return base.resquestAuth(user, pass);
	}

	@Override
	public Boolean modMeteo(Meteo meteo) {
		return base.modMeteo(meteo);
	}

	@Override
	public Boolean addMeteo(Meteo meteo) {
		return base.addMeteo(meteo);
	}

	@Override
	public Boolean addGroupMeteo(ArrayList<Meteo> groupMeteo) {
		return base.addMeteoGroup(groupMeteo);
	}

}
