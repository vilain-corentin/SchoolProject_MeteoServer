

import java.rmi.*;
import java.rmi.server.*;

import base.Base;
 
public class Addition extends UnicastRemoteObject
         implements AdditionInterface {

	public Addition () throws RemoteException {   }
 
	public Base base = new Base();
	
      public String log() throws RemoteException {
    	  return base.ouvrir();
      }
 }
