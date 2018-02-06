package Bbro;

public class DocumentLooker extends Thread{
	
	public DocumentLooker(String name){
	    super(name);
	  }

	public void run(){
		while(true){
			System.out.println(this.getName());
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}   
}
