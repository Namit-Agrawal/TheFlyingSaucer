import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Enemy{
   
	private double x;
	private double y;
	private Texture tex;
	private int val;
	private int bullets;
	private int c=4;
	private LinkedList<EnemyFireBall> ball;
	private double velY;
	
	public Enemy(double x, double y, Texture tex, LinkedList<EnemyFireBall> ball)
	{
		this.x=x;
		this.y=y;
		this.tex=tex;
		this.ball=ball;
		val=3;
	}
	
	public void tick()
	{
		if(Window1.state==Window1.STATE.GAME){
			if(y<=0){
				y=0;
				val=3;
			}
			else if(y>=(Window1.HEIGHT*2)-128)
			{
				y=(Window1.HEIGHT*2-128);
				val=-3;
			}
			y+=val;
			if(ball.size()==0||ball.getLast().getX()<=Window1.WIDTH)
			{
				ball.add(new EnemyFireBall(x+64, y+64, tex,c));
				bullets++;
			}
		}
		else{
			y+=velY;
		}
		
	}
	public void setVelY(int vel)
	{
		velY=vel;
	}
	public void setSpeed(int x)
	{
		c=x;
	}
	public void render(Graphics g)
	{
		g.drawImage(tex.getEnemy(), (int)x, (int)y, null);
	}
	public double getX()
	{
		return x;
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)(x), (int)(y+32), 24, 64);
	}
	public double getY()
	{
		return y;
	}

	public int getNumBullets() {
		return bullets;
	}
}
