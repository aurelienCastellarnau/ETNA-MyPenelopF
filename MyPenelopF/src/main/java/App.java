import java.io.IOException;
import java.util.ArrayList;

import controllers.ContactController;
import controllers.GroupController;
import controllers.PenelopeController;

/*
 * MyPenelopeF entry point
 */
public class App
{
	// L'app stocke un array de PenelopeController
	// ce type correspond à une interface.
	// pour implémenter cette interface et être un
	// putin de PeneloppeController, il faut
	// avoir la méthode init appellée ici dans le for
	static ArrayList<PenelopeController> ctrls;
	
    public static void main( String[] args ) throws IOException
    {
    	App.init();
    	for (PenelopeController ctrl: App.ctrls)
    		ctrl.init();
    }
    
    // Ca permet de simplifier le 'enable/disable' d'un controller
    // en gérant son ajout et son init juste en ajoutant une ligne ici
    // encore faut-il que les classes Controllers implémentent
    // la méthode init().
    private static final void init() {
    	App.ctrls  = new ArrayList<PenelopeController>();
    	App.ctrls.add(new ContactController());
    	App.ctrls.add(new GroupController());
    	// App.ctrls.add(new xxxController());
    }
}
