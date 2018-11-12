package thread;
import obj.*;

public class ThrBalles extends Thread{
	
	PanBall balle;
	boolean pause=false;

	
	public ThrBalles(PanBall balle) {
		this.balle=balle;
	}
	
	public void run() {
		while(!isInterrupted()) {
			balle.moove();
			boolean coll=balle.collision();
			if(coll) balle.removeBalle(true);
			try {
				sleep(5);
			}catch(InterruptedException e) {}
			if(pause) {
				try {
					synchronized(this) {
						wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setPause(Boolean pause) {
		this.pause=pause;
	}
	
	
}
