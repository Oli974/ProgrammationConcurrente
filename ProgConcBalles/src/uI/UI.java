package uI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import thread.*;
import obj.*;
import thread.*;
import java.util.*;
import java.util.List;

public class UI extends JFrame implements ActionListener,WindowListener{
	
	private static int WIDTH=650;
	private static int HEIGHT=650;
	
	private JButton bt_action;
	private JButton bt_incr;
	private JButton bt_decr;
	
	private JLabel score;
	private JLabel score_Value;
	private int score_int;
	
	private JLabel timer; 
	private JLabel timer_Value;
	private JLabel seuil;
	
	private JTextField text_box;
	
	private PanBall panneau;
	private TimerThr time;
	private ThrBalles ballesThr;
	
	
	public UI() {
		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBounds(650, 650, WIDTH, HEIGHT);		
		this.setLayout(new BorderLayout());
		
		//Instanciation du timer, des boutons et de la liste qui contient les balles 
		time=new TimerThr();
		bt_action= new JButton("Start");
		bt_incr=new JButton("+");
		bt_decr=new JButton("-");
		
		this.text_box= new JTextField(5);
		seuil=new JLabel("Seuil : ");
		
		this.addWindowListener(this);
		bt_action.addActionListener(this);
		bt_incr.addActionListener(this);
		bt_decr.addActionListener(this);
		
		panneau= new PanBall();
		
		score=new JLabel("Score : ");
		timer=new JLabel("Timer : ");
		timer_Value=new JLabel(Integer.toString(time.getS()));
		score_Value=new JLabel(Integer.toString(panneau.getCollisions()));
		
		JPanel north= new JPanel();
		JPanel south= new JPanel();
		
		north.setLayout(new GridLayout(1,4));
		south.setLayout(new FlowLayout());		
		
		add(north,BorderLayout.NORTH);
		add(panneau,BorderLayout.CENTER);
		add(south,BorderLayout.SOUTH);
		
		north.add(score,BorderLayout.NORTH);
		north.add(score_Value,BorderLayout.NORTH);
		north.add(timer,BorderLayout.NORTH);
		north.add(timer_Value, BorderLayout.NORTH);
		
		south.add(bt_action,BorderLayout.SOUTH);
		south.add(bt_incr,BorderLayout.SOUTH);
		south.add(bt_decr,BorderLayout.SOUTH);
		south.add(seuil,BorderLayout.SOUTH);
		south.add(text_box,BorderLayout.SOUTH);
		
		ballesThr= new ThrBalles(panneau);
		this.setVisible(true);
		while(true) {
			timer_Value.setText(Integer.toString(time.getS()));
			score_Value.setText(Integer.toString(panneau.getCollisions()));
			
			try {
				int new_seuil= Integer.parseInt(text_box.getText());
				panneau.setSeuil(new_seuil);	
			}catch(Exception e) {
			}
			
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source= e.getSource();
		
		if(source instanceof JButton) {
			
			JButton bouton = (JButton)source;
			String txt= bouton.getText();
			
			switch(txt) {
				case "Start":
					bouton.setText("Stop");
					if(!time.isAlive()) {
						time.start();
						ballesThr.start();
					}
					else {
						synchronized(time) {
							time.notify();
						}
						synchronized(ballesThr) {
							ballesThr.notify();
						}
						time.setPause(false);
						ballesThr.setPause(false);
					}
					break;
					
				case "Stop":
					bouton.setText("Start");
					time.setPause(true);
					ballesThr.setPause(true);
					break;
					
				case "+":
					panneau.addBalle();
					break;
					
				case "-":
					panneau.removeBalle(false);
					break;
				
			}
			
		}
	}

	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new UI();
	}
	
	
	
}
