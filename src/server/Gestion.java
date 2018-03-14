package server;

import java.io.InputStream;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

import base.Base;
import bean.Meteo;

public class Gestion extends UnicastRemoteObject implements GestionInterface {

	public Gestion() throws RemoteException {
	}

	public Base base = new Base();

	public Boolean initCo()  throws RemoteException{
		return base.ouvrir();
	}

	public ArrayList<Meteo> meteoByDay(int y, int m, int d, String lieu) throws RemoteException {
		return base.getMeteoForOneDay(y, m, d, lieu);
	}

	@Override
	public ArrayList<Meteo> meteoByMonth(int y, int m, String lieu) throws RemoteException {
		return base.getMeteoByMonth(y, m, lieu);
	}

	@Override
	public Boolean authentification(String user, String pass)  throws RemoteException{
		return base.resquestAuth(user, pass);
	}

	@Override
	public Boolean modMeteo(Meteo meteo)  throws RemoteException{
		return base.modMeteo(meteo);
	}

	@Override
	public Boolean addMeteo(Meteo meteo)  throws RemoteException{
		return base.addMeteo(meteo);
	}

	@Override
	public Boolean addGroupMeteo(ArrayList<Meteo> groupMeteo)  throws RemoteException{
		return base.addMeteoGroup(groupMeteo);
	}

	@Override
	public InputStream getPhoto(int id)  throws RemoteException{
		return base.getPhoto(id);
	}

}
