package thread;

public class Pause extends Thread{
	
	boolean pause= false;
	
	public void run() {
		try {
			while(!isInterrupted()) {
				sleep(1000);
			}	
		} catch (InterruptedException e) {
			this.interrupt();
		}
	}
}
