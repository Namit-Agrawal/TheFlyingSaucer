import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFrame;

public class Window1 extends Canvas implements Runnable{
      
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 2;
	public final String GAME = "GAME";
	
	private boolean isRunning;
	private Thread thread;
	private Player player;
	private Enemy enemy;
	private BufferedImage spriteSheet=null;
	private BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage back;
	private boolean isShooting, isShooting2, isTwoPlayer;
	private Texture tex;
	private BackGround b1, b2;
	private int myBullets, opbullets;
	private Controller controller;
	public LinkedList<FireBall> balls;
	public LinkedList<EnemyFireBall> enemBalls;
	private Menu menu;
	public static enum STATE{
		MENU,
		GAME,
		GAMEOVER,
		WON,
		TWOPLAYER,
		INSTRUCTIONS,
	};
	public static STATE state=STATE.MENU;
	
	public void init()
	{
		requestFocus();
		
		try {
			spriteSheet = loader.loadImage("/spriteSheet.png");
			back=loader.loadImage("/back.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		tex = new Texture(this);
		
		controller=new Controller(tex);
		player = controller.getPlayer();
		enemy = controller.getEnemy();
		balls = controller.getBalls();
		enemBalls = controller.getEnemyBalls();
		menu = new Menu();
		b1 = new BackGround(0,0,back);
		b2 = new BackGround(640,0,back);
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput());
	}
	
	private synchronized void start()
	{
		if(isRunning)
			return;
		
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop()
	{
	   if(!isRunning)
		   return;
	   
	   isRunning = false;
	   try{
		   thread.join();
	   }catch(Exception e){};
	   System.exit(1);
	}
	
	public void run()
	{
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(isRunning)
		{
			long now = System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			if(delta>=1)
			{
				tick();
				updates++;
				delta--;
			}
			try {
				render();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frames++;
			
			if(System.currentTimeMillis()-timer>1000)
			{
				timer+=1000;
				System.out.println(updates+" Ticks, fps "+frames);
				frames=0;
				updates=0;
			}
			
		}
		
		stop();
	}
	
	public void render() throws IOException
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		b1.render(g);
		b2.render(g);
		if(state==STATE.GAME||state==STATE.TWOPLAYER){
			if(b1.getX()<=-1*(WIDTH*SCALE))
				b1.setX(0);
			else if(b2.getX()<=0)
				b2.setX(640);
			controller.render(g);
		}
		else if(state==STATE.MENU)	
		{
			menu.render(g);
		}
		else if(state==STATE.GAMEOVER)
		{
			Font fnt = new Font("Times New Roman", Font.BOLD, 70);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 100, 150);
			int val = controller.numBulletsToDie()-1;
			if(isTwoPlayer)
				val = opbullets;
			String bullet =" bullets";
			if(val==1)
				bullet=" bullet";
			bullet = val+bullet;
			Font fnt2 = new Font("Times New Roman", Font.BOLD, 20);
			g.setFont(fnt2);
			g.drawString("It took "+bullet+" for your saucer to die", 170, 230);
		}
		else if(state == STATE.WON)
		{
			Font fnt = new Font("Times New Roman", Font.BOLD, 70);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("YOU WON!", 150, 150);
			String bullet =" bullets";
			if(myBullets==1)
				bullet=" bullet";
			bullet = (myBullets-1)+bullet;
			Font fnt2 = new Font("Times New Roman", Font.BOLD, 20);
			g.setFont(fnt2);
			g.drawString("It took "+bullet+" for the opponent saucer to die", 150, 200);
		}
		else
		{
			g.setColor(Color.WHITE);
			g.drawRect(5,5,50,20);
			
			Font fnt = new Font("Times New Roman", Font.BOLD, 25);
		    g.setFont(fnt);
		    g.drawString("Single Player Mode", WIDTH-100, 20);
		    Font fnt2 = new Font("Times New Roman", Font.BOLD, 15);
		    g.setFont(fnt2);
		    g.drawString("Back", 12, 20);
		    g.drawString("The purpose of this mode is for you to destroy the opposing saucer", 40, 50);
		    g.drawString("Press UP to move up", 80, 80);
		    g.drawString("Press DOWN to move down", 80, 110);
		    g.drawString("Press SPACE to fire", 80, 140);
		    g.setFont(fnt);
		    g.drawString("Two Player Mode", WIDTH-100, 180);
		    g.setFont(fnt2);
		    g.drawString("The purpose of this mode if for two players to destroy the opposing player's saucer", 40, 210);
		    g.drawString("Player 1 Controls", 40, 240);
		    g.drawString("Press W to move up", 80, 270);
		    g.drawString("Press S to move down", 80, 300);
		    g.drawString("Press Z to fire", 80, 330);
		    g.drawString("Player 2 Controls", 40, 360);
		    g.drawString("Press UP to move up", 80, 390);
		    g.drawString("Press DOWN to move down", 80, 420);
		    g.drawString("Press SPACE to fire", 80, 450);
		}
		g.dispose();
		bs.show();
	}
	
	public void tick()
	{
		if(state==STATE.GAME||state==STATE.TWOPLAYER){
			b1.tick();
			b2.tick();	
			controller.tick();
		}
	}
	
	public void keyPressed(KeyEvent e)
    {
		int key = e.getKeyCode();
		if(state== STATE.GAME){
			if(key==KeyEvent.VK_DOWN)
				player.setVelY(5);
			else if(key==KeyEvent.VK_UP)
				player.setVelY(-5);
			else if(key==KeyEvent.VK_SPACE&&!isShooting){
				isShooting=true;
				if(balls.size()==0||(balls.getLast()!=null && balls.getLast().getX()>=WIDTH)){
					controller.addBall(new FireBall(player.getX()+64,player.getY()+64, tex));
					myBullets++;
				}
			}
		}
		else
		{
			if(key==KeyEvent.VK_DOWN)
				enemy.setVelY(5);
			else if(key==KeyEvent.VK_UP)
				enemy.setVelY(-5);
			else if(key==KeyEvent.VK_SPACE&&!isShooting2){
				isShooting2=true;
				if(enemBalls.size()==0||(enemBalls.getLast()!=null && enemBalls.getLast().getX()<=WIDTH)){
					controller.addEnemyBalls(new EnemyFireBall(enemy.getX()+64,enemy.getY()+64, tex,5));
					opbullets++;
					isTwoPlayer=true;
				}
			}
			if(key==KeyEvent.VK_S)
				player.setVelY(5);
			else if(key==KeyEvent.VK_W)
				player.setVelY(-5);
			else if(key==KeyEvent.VK_Z&&!isShooting){
				isShooting=true;
				if(balls.size()==0||(balls.getLast()!=null && balls.getLast().getX()>=WIDTH)){
					controller.addBall(new FireBall(player.getX()+64,player.getY()+64, tex));
					myBullets++;
				}
			}
		}
    }
	
	public void keyReleased(KeyEvent e)
    {
		int key = e.getKeyCode();
		if(state==STATE.GAME)
		{	
			if(key==KeyEvent.VK_DOWN)
				player.setVelY(0);
			else if(key==KeyEvent.VK_UP)
				player.setVelY(0);
			else if(key==KeyEvent.VK_SPACE)
				isShooting=false;
		}
		else
		{
			if(key==KeyEvent.VK_DOWN)
				enemy.setVelY(0);
			else if(key==KeyEvent.VK_UP)
				enemy.setVelY(0);
			else if(key==KeyEvent.VK_SPACE){
				isShooting2=false;
			}
			if(key==KeyEvent.VK_S)
				player.setVelY(0);
			else if(key==KeyEvent.VK_W)
				player.setVelY(0);
			else if(key==KeyEvent.VK_Z){
				isShooting=false;
			}
		}
    }
	public BufferedImage getSpriteSheet()
	{
		return spriteSheet;
	}
	
	public static void main(String[] args)
	{
		Window1 wind = new Window1();
		
		wind.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		wind.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		wind.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		JFrame frame = new JFrame(wind.GAME);
		frame.add(wind);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		wind.start();
	}
}
