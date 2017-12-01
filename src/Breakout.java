import java.util.Random;

import processing.core.*;

public class Breakout extends PApplet {
	
	 
	private int nCols = 900;
	private int nRows = 500;
    Random r = new Random(213);
    private int xPos, yPos;
    private int m_xPos, m_yPos;
    private int b_xPos, b_yPos;
    private int bdx;
    private double dx,dy;
    private boolean fired = false;
    int rows = 4;
    int columns = 10;
    private Block blocks[][] = new Block[columns][rows];
    private int[][] colors = new int[rows][3];
    
    private boolean win = false, lose = false;
	public void setup(){
		// code in setup is only done once
		size(nCols,nRows);
		xPos = 1; yPos=0;
		b_xPos = nCols/2;b_yPos = nRows  ;
		m_xPos = b_xPos + 20; m_yPos = b_yPos-10;
		dx = 1;		dy=2;
		//generates colors for blocks
		Random c = new Random();
		for (int i = 0; i < colors.length; i++){
			for(int j = 0; j < colors[i].length; j++){
				colors[i][j] = c.nextInt(256);
			}
		}
		//generates blocks array
		for (int i = 0; i<blocks.length;i++) {
			for (int j = 0; j< blocks[i].length;j++) {
				blocks[i][j] = new Block(this,i*80+xPos,j*20+yPos,75,15);
			}
		}
	}// end setup

	public void draw(){
		int nextXpos;
		background(255);
		nextXpos = (int) (blocks[0][0].xPos + blocks.length * 80 + dx) ;
		
		dy = 0;
		if (nextXpos > nCols ) {
			dx = -dx;
			dy = 1;
		} else if (blocks[0][0].xPos + dx <0) {
			dx = -dx;
			dy = 1;
			
		}
		
		//update block location
		for(int i = 0; i < blocks.length; i++){
			for(int j = 0; j < blocks[i].length; j++){
				blocks[i][j].update(dx, dy);
			}
		}

		drawBlocks();
		fill(255,0,0);
		checkCollisions();
		lose = Block.checkLoss(blocks, b_yPos, rows);
		win = Block.checkWin(blocks);
		if (lose){
			background(255);
			fill(255,0,0);
			text("YOU LOSE", 450, 100);
			noLoop();
		}
		
		if (m_yPos<10) { 
			fired = false;
			m_xPos = b_xPos + 20; m_yPos = b_yPos-10;
		}
		if (fired) {
			m_yPos -=4;
			ellipse(m_xPos,m_yPos,10,20);
		} else {
			ellipse(m_xPos,m_yPos,10,20);
		}
		
		fill(0,200,0);
		rect(b_xPos,b_yPos,40,10 );
		
		
		
		 
	}
	public void mouseClicked(){
		// nothing to be done 60 times/second
		  	 
	}
	public void keyPressed(){
		// nothing to be done 60 times/second
		System.out.println(keyCode);
		 
		    if (keyCode == RIGHT) {
		     if (!fired) { m_xPos +=5;}
		      b_xPos +=5;
		    } else if (keyCode == LEFT) {
		    	if (!fired) {m_xPos-=5;}
		    	b_xPos -=5;
		    } 
		    if (keyCode == UP) {
		    	fired = true;
		    }
	 
	}
	public void drawBlocks() {
			
			for (int i = 0; i<blocks.length;i++) {
				for (int j = 0; j< blocks[i].length;j++) {
					if (!blocks[i][j].collision) {
						int rColor = colors[j][0];
						int gColor = colors[j][1];
						int bColor = colors[j][2];
						blocks[i][j].draw(rColor, bColor, gColor);
						}
				}
			}
	}
	
	public void checkCollisions(){
		
		for(Block[] i : blocks) {
			for(Block j :  i) {
				if (j.checkCollision(m_xPos, m_yPos) == true){
					fired = false;
					m_xPos = b_xPos + 20; m_yPos = b_yPos-10;
					if (dx < 0){
						dx-= 0.5;
					}else if (dx >= 0){
						dx+= 0.5;
					}
					dy+= 1;return;
				}
			}
		}
	}
}// end class Ball1
