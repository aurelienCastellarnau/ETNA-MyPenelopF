import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import controllers.ContactController;
import controllers.GroupController;
import controllers.PenelopeController;
import ihm.BaseFrame;

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
	static HashMap<String, PenelopeController> ctrls;

    public static void main( String[] args ) throws IOException
    {
    		App.init();
    		for (String key: App.ctrls.keySet())
    			ctrls.get(key).init();
    		new BaseFrame(ctrls);
    }

    // Ca permet de simplifier le 'enable/disable' d'un controller
    // en gérant son ajout et son init juste en ajoutant une ligne ici
    // encore faut-il que les classes Controllers implémentent
    // la méthode init().
    private static final void init() {
    	App.ctrls  = new HashMap<String, PenelopeController>();
    	//App.ctrls.add("group", new GroupController());
    	App.ctrls.put("contact", new ContactController());
    	// App.ctrls.add("xxx", new xxxController());
    }
}
