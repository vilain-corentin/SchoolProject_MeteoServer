
package server;

import java.io.InputStream;
import java.rmi.*;
import java.util.ArrayList;

import bean.Meteo;

public interface GestionInterface extends Remote {

	public Boolean initCo() throws RemoteException;

	public ArrayList<Meteo> meteoByDay(int y, int m, int d, String lieu) throws RemoteException;

	public ArrayList<Meteo> meteoByMonth(int y, int m, String lieu) throws RemoteException;

	public Boolean authentification(String user, String pass) throws RemoteException;

	public Boolean addMeteo(Meteo meteo) throws RemoteException;

	public Boolean addGroupMeteo(ArrayList<Meteo> groupMeteo) throws RemoteException;

	public Boolean modMeteo(Meteo meteo) throws RemoteException;
	
	public InputStream getPhoto(int id) throws RemoteException;

}
