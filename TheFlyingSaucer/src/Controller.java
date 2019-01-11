import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
      
	private LinkedList<FireBall> balls = new LinkedList<FireBall>();
	private LinkedList<EnemyFireBall> enemBalls = new LinkedList<EnemyFireBall>();
	Random rn=new Random();
	FireBall ball;
	private Player player;
	private Rectangle healthBar;
	private boolean isHit;
	private int counter = 3, count=3;
	private Enemy enem;
	private int widthSize, widthSize2;
	Texture tex;
	
	public Controller(Texture tex)
	{
		this.tex=tex;
		widthSize=210;
		widthSize2=210;
		player = new Player(0,Window1.HEIGHT/1.5,tex);
	    enem=new Enemy(Window1.WIDTH*2-115,Window1.HEIGHT/1.5,tex, enemBalls);
	}
	public void tick()
	{
		player.tick();
		if(enemBalls.size()>0)
		{
			if(Collision.PlayerHit(enemBalls.getFirst(), player))
				if(Window1.state==Window1.STATE.GAME)
					Window1.state=Window1.STATE.GAMEOVER;
				else
				{
					isHit = true;
					if(isHit)
					{
						enemBalls.remove(enemBalls.getFirst());
						widthSize2-=30;
						count++;
						if(count==10)
							Window1.state = Window1.STATE.GAMEOVER;
						isHit=false;
					}
				}
		}
		for(int x = 0; x<balls.size(); x++){
			ball = balls.get(x);
			ball.tick();
			if(ball.getX()>=Window1.WIDTH*2)
				removeBall(ball);
		}
		for(int x = 0; x<enemBalls.size(); x++){
		    enemBalls.get(x).tick();
			if(enemBalls.get(x).getX()<=0)
				enemBalls.remove(x);
		}
		enem.tick();
		if(balls.size()>0&&Collision.EnemyHit(balls.getFirst(), enem))
		{
			isHit=true;
			
		}
		if(isHit&&!Collision.EnemyHit(balls.getFirst(), enem))
		{
			balls.remove(balls.getFirst());
			widthSize-=30;
			counter++;
			if(counter==10)
				Window1.state=Window1.STATE.WON;
			enem.setSpeed(counter);
			isHit=false;
		}
	}
	
	public void render(Graphics g)
	{ 
		player.render(g);
		Graphics2D g2d = (Graphics2D) g;
		for(int x = 0; x<balls.size(); x++){
			ball = balls.get(x);
			ball.render(g);
		}
		for(int x = 0; x<enemBalls.size(); x++){
			enemBalls.get(x).render(g);
		}
		enem.render(g);
		g.setColor(Color.WHITE);
		g.fillRect(Window1.WIDTH-100, 20, 210, 20);
		healthBar = new Rectangle(Window1.WIDTH-100, 20, widthSize, 20);
		g.setColor(Color.GREEN);
		g2d.fill(healthBar);
		if(Window1.state==Window1.STATE.TWOPLAYER)
		{
			g.setColor(Color.WHITE);
			g.fillRect(Window1.WIDTH-100, 440, 210, 20);
			healthBar = new Rectangle(Window1.WIDTH-100, 440, widthSize2, 20);
			g.setColor(Color.RED);
			g2d.fill(healthBar);
		}
		
	}
	public void addBall(FireBall ball)
	{
		balls.add(ball);
	}
	public void addEnemyBalls(EnemyFireBall ball)
	{
		enemBalls.add(ball);
	}
	public void removeBall(FireBall ball)
	{
		balls.remove(ball);
	}

	public LinkedList<FireBall> getBalls()
	{
		return balls;
	}
	public Player getPlayer()
	{
		return player;
	}
	public LinkedList<EnemyFireBall> getEnemyBalls()
	{
		return enemBalls;
	}
	public int numBulletsToDie()
	{
		return enem.getNumBullets();
	}
	public Enemy getEnemy()
	{
		return enem;
	}
}
