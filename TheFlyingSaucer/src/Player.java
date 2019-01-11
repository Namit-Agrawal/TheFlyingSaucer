import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {

	private double x;
	private double y;
	private double velX;
	private double velY;
	private double dx;
	public double jumpVel;
	public double jumpVel2;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH/12*9;

	private Texture tex;
	public Player(double x, double y, Texture tex)
	{
		this.x =x;
		this.y=y;
		jumpVel=25;
		jumpVel2=25;
		this.tex = tex;
	}

	public void render(Graphics g)
	{
		g.drawImage(tex.getPlayer(), (int)x, (int)(y), null);

	}

	
	public double getDx()
	{
		return dx;
	}
	public void setjumpVel(int val)
	{
		jumpVel=val;
	}
	public void setX(double x)
	{
		this.x=x;
	}
	public void setY(double y)
	{
		this.y=y;
	}
	public double getX(){
		return x;
	}
	public double getY()
	{
		return y;
	}
	public void setVelX(double x)
	{
		this.velX=x;
	}
	public void setVelY(double y)
	{
		this.velY=y;
	}
	public double velX(){
		return velX;
	}
	public double velY()
	{
		return velY;
	}
	
	public int length()
	{
		return 62;
	}
	public void tick() {
		// TODO Auto-generated method stub
		y+=velY;
		
		if(y<=0){
			y=0;
			
		}
		else if(y>=(Window1.HEIGHT*2)-128)
		{
			y=(Window1.HEIGHT*2-128);
	
		}
	}
   
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)(x+104), (int)(y+32), 24, 64);
	}

}
