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

	public Meteo MeteoByDay(int y, int m, int d) throws RemoteException {
		return base.getMeteoForOneDay(y, m, d);
	}

	@Override
	public ArrayList<Meteo> MeteoByMonth(int y, int d) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Authentification(String user, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean ModMeteo(Meteo meteo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean AddMeteo(Meteo meteo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean AddGroupMeteo(ArrayList<Meteo> groupMeteo) {
		// TODO Auto-generated method stub
		return null;
	}

}
