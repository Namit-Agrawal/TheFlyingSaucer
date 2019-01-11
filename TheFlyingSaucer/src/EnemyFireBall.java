import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyFireBall{
     
	private double x;
	private double y;
	private int speed;
	private Texture tex;
	
	public EnemyFireBall(double x, double y, Texture tex, int speed)
	{
		this.x=x;
		this.y=y;
		this.tex=tex;
		this.speed=speed;
		
	}
	
	public void tick()
	{
			x=x-speed;			
	}
	public void render(Graphics g)
	{
		g.drawImage(tex.getEnemyFireBall(), (int)x, (int)y, null);
	}
    
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 6, 6);
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

