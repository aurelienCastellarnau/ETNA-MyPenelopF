import java.io.IOException;

import Bbro.DocumentLooker;
import DataInterface.FileSystemManager;
import controllers.AppController;

/*
 * MyPenelopeF entry point
 */
public class App
{
	static final AppController ctrl = new AppController(FileSystemManager.get());
	
    public static void main( String[] args ) throws IOException{
    		DocumentLooker test = new DocumentLooker("A");
    		App.ctrl.init();
    		test.start();
    }
}
