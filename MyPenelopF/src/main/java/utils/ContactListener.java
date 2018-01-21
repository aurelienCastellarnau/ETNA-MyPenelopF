package utils;

/**
 * 
 * @author aurelien
 * A class listening on triggerContactChange method
 * called by a modification on Contact model
 * must implement this interface
 */
public interface ContactListener {
	void ContactChangeTriggered();
}
