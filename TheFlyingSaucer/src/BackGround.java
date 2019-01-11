import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BackGround {
 
	private BufferedImage back;
	private double x;
	private int y;
	public BackGround(double x, int y, BufferedImage back)
	{
		this.back=back;
		this.x=x;
		this.y=y;
	}
	public void render(Graphics g)
	{
		g.drawImage(back, (int)x, y, null);
	}
	public void tick()
	{
		x-=3;
	}
	public void setX(double x)
	{
		this.x=x;
	}
	public double getX()
	{
		return x;
	}
}
