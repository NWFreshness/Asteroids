import java.awt.Polygon;
public class Spaceship extends Polygon{
	
	int uLeftXPos = 500, uLeftYPos = 400;
	
	int xDirection = 0;
	int yDirection = 0;
	
	int width = GameBoard.boardWidth;
	int height = GameBoard.boardHeight;
	
	public static int[] polyXArray = {500, 527, 500, 508, 500};
	public static int[] polyYArray = {400, 415, 430, 415, 400};
	
	public Spaceship(){
		super(polyXArray, polyYArray, 5);
		
	}
	public void move(){
		int uLeftXPos = super.xpoints[0];
		int uLeftYpos = super.ypoints[0];
		
		if(uLeftXPos < 0 || (uLeftXPos + 25) > width) xDirection = -xDirection;
		if(uLeftYPos < 0 || (uLeftYPos + 50) > height) yDirection = -yDirection;
		
		for(int i = 0; i < super.xpoints.length; i++){
			super.xpoints[i] += xDirection;
			super.ypoints[i] += yDirection;
		}
	}
	
	

}
