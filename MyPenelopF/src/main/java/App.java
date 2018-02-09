import java.io.IOException;

import DataInterface.FileSystemManager;
import controllers.AppController;
import utils.DocumentLooker;

/*
 * MyPenelopeF entry point
 */
public class App
{
	static final AppController ctrl = new AppController(FileSystemManager.get());
	
    public static void main( String[] args ) throws IOException{
    		App.ctrl.init();
 
    }
}
