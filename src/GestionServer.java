
import java.rmi.*;

public class GestionServer {
	public static void main(String[] argv) {
		try {
			Gestion gestion = new Gestion();
			Naming.rebind("rmi://localhost/server", gestion);

			System.out.println("Server is ready.");
		} catch (Exception e) {
			System.out.println("Server failed: " + e);
		}
	}
}
