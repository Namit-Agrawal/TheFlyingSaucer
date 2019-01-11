import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
     
	private Rectangle playButton = new Rectangle(Window1.WIDTH-50, 150, 100, 40);
	private Rectangle twoPlayerButton = new Rectangle(Window1.WIDTH-70, 225, 140, 40);
	private Rectangle instructionButton = new Rectangle(Window1.WIDTH-90, 300, 180, 40);
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g; 
		Font font1 = new Font("Times New Roman", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		g.drawString("THE FLYING SAUCER", (int)(60), 100);
		g2d.draw(playButton);
		g2d.draw(twoPlayerButton);
		g2d.draw(instructionButton);
		Font font2 = new Font("Times New Roman", Font.BOLD, 30);
		g.setFont(font2);
		g.setColor(Color.WHITE);
		g.drawString("PLAY",Window1.WIDTH-40, 180);
		g.drawString("2 Player", Window1.WIDTH-50, 255);
		g.drawString("Instructions", Window1.WIDTH-75, 330);
    }
}
