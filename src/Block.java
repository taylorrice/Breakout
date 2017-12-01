import processing.core.PApplet;

public class Block{
	float xPos;
	float yPos;
	float width;
	float height;
	boolean collision = false;
	static PApplet p;
	public Block(PApplet inp){
		p = inp;
	}
	public Block(PApplet inp, float inxpos, float inypos, float inwidth, float inheight){
		xPos = inxpos;
		yPos = inypos;
		width = inwidth;
		height = inheight;
		p = inp;
		
	}
	
	public void draw(int inr, int ing, int inb) {
		if (collision == true){return;}
		p.fill(inr, ing, inb);
		p.rect(xPos, yPos, width, height);
	}
	
	public void update(double indx, double indy){
		xPos += indx;
		yPos += indy;
	}
	public boolean checkCollision(int inx, int iny){
		if (collision == true) {return false;}
		if ((inx > xPos && inx < xPos + width)&& (iny > yPos && iny < yPos + height)){
			collision = true;
			return true;
		}else{
			return false;
		}
		
	}
	public static boolean checkWin(Block[][] arr){
		//check for a win
		int counter = 0;
		for(Block[] i : arr) {
			for(Block j : i){
				if (j.collision){
					counter++;
				}
			}
		}
		if (counter == arr.length * arr[0].length){
			p.background(255);
			p.fill(0,255,0);
			p.text("YOU WIN", 450, 100);
			p.noLoop();
			return true;
		}else{
			return false;
		}
	}
	public static boolean checkLoss(Block[][] arr, int inbyPos, int inrows){
		//check for a loss
		if (arr[0][inrows-1].yPos == inbyPos){
			for(Block[] i : arr) {
				for(Block j : i){
					j.collision = true;
				}
			}
			return true;
		}else{
			return false;
		}
	}
}
