package obj;

import java.awt.Color;

public class Balles {
	int x;
	int y;
	int vx;
	int vy;
	int size;
	Color couleur;
	boolean collision;
	
	public Balles(int x,int y, int size) {
		this.x=x;
		this.y=y;
		this.collision=false;
		int vx= (int)((Math.random()*2)+1);
		int vy= (int)((Math.random()*2)+1);
		this.vx=vx;
		this.vy=vy;
		
		this.size=size;
		
		Color[] couleur = {Color.orange,Color.red,Color.blue,Color.green,Color.black,Color.PINK};
		int randColor= (int)(Math.random()*(couleur.length-0));
		this.couleur=couleur[randColor];
	}
	
	public void setCollision() {
		collision=true;
	}

	public boolean getCollision() {
		return collision;
	}
	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void moove() {
		x+=vx;
		y+=vy;
	}


}
