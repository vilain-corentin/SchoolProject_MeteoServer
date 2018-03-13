
import java.rmi.*;
import java.util.ArrayList;

import bean.Meteo;

public interface GestionInterface extends Remote {

	public Boolean initCo() throws RemoteException; //ok

	public Meteo meteoByDay(int y, int m, int d) throws RemoteException; //ok
	
	public ArrayList<Meteo> meteoByMonth(int y, int m) throws RemoteException; //ok
	
	public Boolean authentification(String user, String pass) throws RemoteException; //ok
	
	public Boolean addMeteo(Meteo meteo) throws RemoteException; //ok
	
	public Boolean addGroupMeteo(ArrayList<Meteo> groupMeteo) throws RemoteException;
	
	public Boolean modMeteo(Meteo meteo) throws RemoteException;

}
