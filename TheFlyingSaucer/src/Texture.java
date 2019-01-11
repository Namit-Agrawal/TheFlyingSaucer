import java.awt.image.BufferedImage;

public class Texture {
      
	private BufferedImage player, fireball, brick, enemy, enemyFireBall;
	private SpriteSheet ss;
	
	public Texture(Window1 game)
	{
		ss = new SpriteSheet(game.getSpriteSheet());
		makeText();
	}
	
	public void makeText()
	{
		player = ss.grabImage(5, 1, 128, 128);
		fireball = ss.grabImage(2, 1, 64, 64);
		brick = ss.grabImage(3, 1, 64, 64);
		enemy = ss.grabImage(7, 1, 128, 128);
		enemyFireBall = ss.grabImage(2, 2, 64, 64);
	}
	
	public BufferedImage getPlayer()
	{
		return player;
	}
	public BufferedImage getfireBall()
	{
		return fireball;
	}
	public BufferedImage getBrick()
	{
		return brick;
	}
	public BufferedImage getEnemy()
	{
		return enemy;
	}
	public BufferedImage getEnemyFireBall()
	{
		return enemyFireBall;
	}

}
