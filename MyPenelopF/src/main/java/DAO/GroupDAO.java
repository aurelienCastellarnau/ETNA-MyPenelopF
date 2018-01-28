package DAO;

import java.util.ArrayList;

import DataInterface.DataInterface;
import classes.Group;

public class GroupDAO extends DAO<Group> {

	public GroupDAO(DataInterface di) {
		super(di);
	}
	
	 private static class SingletonHolder
	    {       
	        /** Instance unique non préinitialisée 
	         * la classe interne ne sera chargée en mémoire 
	         * que lorsque l'on y fera référence pour la première fois
	         * Permet de protéger d'un double appel en environnement multithreadé.
	         * C'est overkill pour le moment, mais c'est une bonne pratique.
	         * Dans le soucis de pouvoir faire évoluer l'app, cette implémentation
	         * anticipe les problèmes.
	         * Ici on appelle le singleton de la DataInterface FileSystemManager
	         * Si on passait sur une BDD classique, on enverrait ici un singleton
	         * différent mais respectant la DataInterface, contrat du bon déroulement
	         * du programme.
	        */
	        private static GroupDAO instance = null;
	        private final static GroupDAO get(DataInterface di) { 
	        	instance = (instance == null) ? new GroupDAO(di) : instance;
	        	return instance;
	        }
	    }
	    public static GroupDAO getInstance(DataInterface di) {
	    	return SingletonHolder.get(di);
	    }
		@Override
		public boolean add(Group obj) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean remove(Group obj) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean update(Group obj) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public ArrayList<Group> get() {
			// TODO Auto-generated method stub
			return null;
		}
}
