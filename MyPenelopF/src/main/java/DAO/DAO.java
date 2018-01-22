package DAO;

import java.util.ArrayList;

import DataInterface.DataInterface;

public abstract class DAO<T> {
	  protected DataInterface di = null;
	  
	  /**
	   * @param FileSystemManager fsm
	   * Parameter should be an interface
	   */
	  public DAO(DataInterface di){
	    this.di = di;
	  }
	  

	  /**
	  * Méthode de création
	  * @param obj
	  * @return boolean 
	  */
	  public abstract boolean add(T obj);


	  /**
	  * Méthode pour effacer
	  * @param obj
	  * @return boolean
	  */
	  public abstract boolean remove(T obj);


	  /**
	  * Méthode de mise à jour
	  * @param obj
	  * @return boolean
	  */
	  public abstract boolean update(T obj);

	  /**
	  * Méthode de recherche des informations
	  * @param id
	  * @return ArrayList<T>
	  */
	  public abstract ArrayList<T> get();
}
