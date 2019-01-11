import java.awt.image.BufferedImage;

public class SpriteSheet {
    
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage ss)
	{
		image=ss;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height)
	{
		BufferedImage img = image.getSubimage((col-1)*64, (row-1)*64, width, height);
		return img;
	}
	
}
