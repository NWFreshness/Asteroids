import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GameBoard extends JFrame{
	
	public static int boardWidth = 1000;
	public static int boardHeight = 1000;
	
	public static void main(String[] args){
		new GameBoard();
	}
	
	public GameBoard(){
		this.setSize(boardWidth, boardHeight);
		this.setTitle("Asteroids");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				//w: 87
				//a: 65
				//s: 83
				//d: 68
				
				if(e.getKeyCode() == 87){
					System.out.println("Forward");
				}else if(e.getKeyCode() == 83){
					System.out.println("Backward");
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		
		});
		
		GameDrawingPanel gamePanel = new GameDrawingPanel();
		
		this.add(gamePanel, BorderLayout.CENTER);
		
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
		
		executor.scheduleAtFixedRate(new RepaintTheBoard(this),  0L,  20L, TimeUnit.MILLISECONDS);
		
		this.setVisible(true);
		
		
	}
	

}

class RepaintTheBoard implements Runnable{
	GameBoard theBoard;
	
	public RepaintTheBoard(GameBoard theBoard){
		this.theBoard = theBoard;
	}
	
	public void run(){
		theBoard.repaint();
	}
}

class GameDrawingPanel extends JComponent{
	public ArrayList<Rock> rocks = new ArrayList<Rock>();
	
	int[] polyXArray = Rock.sPolyXArray;
	int[] polyYArray = Rock.sPolyYArray;
	
	Spaceship theShip = new Spaceship();
	
	public GameDrawingPanel(){
		for(int i = 0; i < 50; i++){
			int randomStartXPos = (int)(Math.random() * (GameBoard.boardWidth - 40) + 1);
			int randomStartYPos = (int)(Math.random() * (GameBoard.boardHeight - 40) + 1);
			
			rocks.add(new Rock(Rock.getpolyXArray(randomStartXPos), Rock.getpolyYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));
		}
	}
	public void paint(Graphics g){
		
		Graphics2D graphicSettings = (Graphics2D)g;
		
		graphicSettings.setColor(Color.BLACK);
		graphicSettings.fillRect(0, 0,  getWidth(),  getHeight());
		
		graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphicSettings.setPaint(Color.WHITE);
		
		for(Rock rock : rocks){
			rock.move();
			
			graphicSettings.draw(rock);
		}
		
		
		theShip.move();
		
		graphicSettings.draw(theShip);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
