package application.notification;

import java.io.Serializable;
import java.util.*;

/**
 * Abstract class that represents a subject that notifies its observers whenever
 * something in particular changes
 * 
 * @author Miguel Ã�lvarez Valiente, Alejandro Benimeli Miranda, Ã�lvaro Castillo GarcÃ­a
 */
public abstract class Subject implements Serializable {
	
	/**
	 * Version for serialize objects
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of observers a certain subject has
	 */
	protected List<IObserver> observers = new ArrayList<>();
	
	/**
	 * Adds an observer to the observers list of the subject
	 * @param obs Observer that you add
	 */
	public void registerObserver(IObserver obs) {
		for (IObserver o: observers) {
			if (o.equals(obs)) {
				return;
			}
		}
		observers.add(obs);
	}
	
	/**
	 * Removes an observer from the observers list of the subject
	 * @param obs Observer that you remove
	 */
	public void unregisterObserver(IObserver obs) {
		observers.remove(obs);
	}
	
	/**
	 * Notifies all of the observers
	 * @param n Notification
	 */
	public void notifyObservers(Notification n) {
		for (IObserver obs: observers) {
			obs.update(n);
		}
	}
	
	/**
	 * Gets all of the observers
	 * @return observers
	 */
	public List<IObserver> getObservers() {
		return observers;
	}

}
