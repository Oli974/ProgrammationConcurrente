package thread;

public class TimerThr extends Thread{
	
	int s;
	boolean pause=false;
	
	public TimerThr() {
		s=0;
	}
	
	public void run() {
		try {
			while(!isInterrupted()) {
				s++;
				sleep(1000);
				if(pause) {
					synchronized(this) {
						wait();
					}
				}
			}	
		} catch (InterruptedException e) {
			this.interrupt();
		}
	}
	
	public int getS() {
		return this.s;
	}
	
	public void setPause(Boolean pause) {
		this.pause=pause;
	}

	
}
