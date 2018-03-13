
import java.rmi.*;
import java.util.ArrayList;

import bean.Meteo;

public class GestionClient {
	public static void main(String[] args) {
		GestionInterface gestion;
		try {
			gestion = (GestionInterface) Naming.lookup("rmi://localhost/server");
			
			if(gestion.initCo()) {
				//Meteo result = gestion.MeteoByDay(2018, 3, 12);
				//System.out.println(result.toString());
				
				ArrayList<Meteo> result = gestion.meteoByMonth(2018, 3);
				System.out.println(result.toString());	
			}else {
				System.out.println("La connexion à la base de donnée est un echec");
			}

		} catch (Exception e) {
			System.out.println("Client exception: " + e);
		}
	}
}