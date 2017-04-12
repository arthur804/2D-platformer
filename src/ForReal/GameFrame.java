package ForReal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JPanel{

	private static final long serialVersionUID = 1042398314087468935L;
	
	private Timer time;
	private char[] keysC = new char[] { 'a', 'w', 's', 'd', 'j', 'k', 'l' , 'r'};
	private boolean[] keysB = new boolean[keysC.length];
	private LevelManager theMan;
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Intresting");
		GameFrame p = new GameFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setContentPane(p);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(0);
//				else if (e.getKeyChar() == (' ')){
//					p.derp();
//				}
				else
					p.move(e.getKeyChar());
			}

			public void keyReleased(KeyEvent e) {
				p.stopMove(e.getKeyChar());
			}

		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public GameFrame() {
		setLayout(null);
		setBackground(Color.black);
		theMan = new LevelManager(800, 800);
		time = new Timer(10, ActionListener -> 
		{
			repaint();
			update();
		});
		time.start();
	}
	private void derp(){
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
		theMan.controls(keysB);
	}
	public void stopMove(int key) {
		for (int i = 0; i < keysC.length; i++)
			if (key == keysC[i]){
				keysB[i] = false;
				break;
			}
		theMan.controls(keysB);
	}
}
