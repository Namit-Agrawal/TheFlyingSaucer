import java.awt.Graphics;
import java.awt.Rectangle;

public class FireBall{
     
	private double x;
	private double y;
	private Texture tex;
	
	public FireBall(double x, double y, Texture tex)
	{
		this.x=x;
		this.y=y;
		this.tex=tex;
		
	}
	
	public void tick()
	{
		x=x+4;			
	}
	
	public void render(Graphics g)
	{
		g.drawImage(tex.getfireBall(), (int)x, (int)y, null);
	}
    
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 12);
	}
	
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
}
