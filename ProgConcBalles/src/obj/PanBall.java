package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class PanBall extends JPanel{
	
	private static final int SIZE=50;
	private int SEUIL=10;
	List<Balles> list_Balles;
	int collisions=0;
	
	public PanBall() {
		list_Balles = new ArrayList<Balles>();
	}

	public void paintComponent(Graphics g) {	
		g.setColor(Color.white);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		for(int i=0;i<list_Balles.size();i++) {
			Balles cour= list_Balles.get(i);
			g.setColor(cour.getCouleur());
			g.fillOval(cour.getX(),cour.getY(),cour.getSize(),cour.getSize());
		}
	}
	
	public void addBalle() {
		
		System.out.println(SEUIL);
		
		if(list_Balles.size()<SEUIL) {
			int widthZ= getWidth();
			int heightZ= getHeight();
		
			int x= (int)(Math.random()*(widthZ-50)); 
			int y= (int)(Math.random()*(heightZ-50));
			Balles new_balle= new Balles(x,y,SIZE);

			list_Balles.add(new_balle);
			repaint();
		}
			
	}
	
	public void removeBalle(boolean collision) {
		if(list_Balles.size()>0){
			if(!collision) {
				int rm_balle= (int) (Math.random()*(list_Balles.size()));
				list_Balles.remove(rm_balle);
				repaint();
			}
			else {
				for(int i=0;i<list_Balles.size();i++) {
					Balles current=list_Balles.get(i);
					if(current.getCollision()){
						list_Balles.remove(i);
						i--;
					}
				}
			}
		}
	}
	
	public int getCollisions() {
		return this.collisions;
	}
	
	
	public synchronized void moove() {
		ListIterator<Balles> li= list_Balles.listIterator();
			
		while(li.hasNext()) {
				
			Balles curr= li.next();
				
			int x= curr.getX();	
			int y= curr.getY();
			int vx= curr.getVx();
			int vy= curr.getVy();
				
			if(x < 0) 			      		  curr.setVx(-vx);
			if(x+(SIZE) > this.getWidth())  curr.setVx(-vx);
				
			if(y < 0)		          		  curr.setVy(-vy);
			if(y+(SIZE) > this.getHeight()) curr.setVy(-vy);
				
			curr.moove();
			
		}
		
		repaint();

	}

	public synchronized boolean collision() {
				
		for(int i =0;i<list_Balles.size();i++){
			Balles curr= list_Balles.get(i);
			
			for(int j=0;j<list_Balles.size();j++){
				Balles curr2= list_Balles.get(j);
			
				if(!curr.equals(curr2)) {
					int dx= curr.getX()-curr2.getX();
					int dy= curr.getY()-curr2.getY();
					
					double distance= Math.sqrt(dx*dx+dy*dy);
					
					if(distance < curr.getSize()/2 + curr2.getSize()/2) {
						collisions++;
						curr.setCollision();
						curr2.setCollision();
						return true;
					}
				}
				
			}
		}
		return false;
	}
	
	public void setSeuil(int seuil) {
		this.SEUIL=seuil;
	}
}
