
import java.rmi.*;
import java.util.ArrayList;

import bean.Meteo;

public interface GestionInterface extends Remote {

	public Boolean initCo() throws RemoteException;

	public Meteo MeteoByDay(int y, int m, int d) throws RemoteException;
	
	public ArrayList<Meteo> MeteoByMonth(int y, int d) throws RemoteException;
	
	public Boolean Authentification(String user, String pass);
	
	public Boolean ModMeteo(Meteo meteo);
	
	public Boolean AddMeteo(Meteo meteo);
	
	public Boolean AddGroupMeteo(ArrayList<Meteo> groupMeteo);

}
