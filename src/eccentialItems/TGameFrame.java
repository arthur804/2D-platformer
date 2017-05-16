package eccentialItems;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TGameFrame extends JPanel{

	private static final long serialVersionUID = 1042398314087468935L;
	
	private Timer time;
	private char[] keysC = new char[] { 'a', 'w', 's', 'd', 'j', 'k', 'l' , 'r', 'x', 'z'};
	private boolean[] keysB = new boolean[keysC.length];
	private TLevelManager theMan;
	
	/*
	 * things that begin with T are important to display the game
	 * things that begin with M are movingObjects
	 * things that begin with L are not moving objects
	 */
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Intresting");
		TGameFrame p = new TGameFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setContentPane(p);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_Q)
					System.exit(0);
				else if (e.getKeyChar() == (' ')){
					p.derp();
				}
				else
					p.move(e.getKeyChar());
			}

			public void keyReleased(KeyEvent e) {
				p.stopMove(e.getKeyChar(), e.isAltDown());
			}

		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public TGameFrame() {
		setLayout(null);
		setBackground(Color.lightGray);
		theMan = new TLevelManager(800, 800);
		time = new Timer(10, ActionListener -> 
		{
			repaint();
			update();
		});
		time.start();
	}

	//Debug next Frame
	private void derp(){
		if (time.isRunning())
			time.stop();
		repaint();
		update();
	}
	
	@Override
	public void paintComponent(Graphics g3) {
		super.paintComponent(g3);
		Graphics2D g = (Graphics2D) g3;
		theMan.draw(g);
	}
	
	public void update(){
		theMan.update();
	}
	
	public void move(char key) {
		for (int i = 0; i < keysC.length; i++)
			if (key == keysC[i]){
				keysB[i] = true;
				break;
			}
		//for testing if you press R(not actualy R but in this version it is its actualy the last key in the list)
		if (keysB[7])
			time.start();
		theMan.controls(keysB);
	}
	public void stopMove(int key, boolean altIsDown) {
		if (!altIsDown){
			for (int i = 0; i < keysC.length; i++)
				if (key == keysC[i]){
					keysB[i] = false;
					break;
				}
			theMan.controls(keysB);
		}
	}
}
