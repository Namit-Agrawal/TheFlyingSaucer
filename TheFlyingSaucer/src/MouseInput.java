import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		int x = m.getX();
		int y = m.getY();
		if(x>=Window1.WIDTH-90 && x<=Window1.WIDTH+90)
		{
			if(y>=150 && y<=190)
			{
				Window1.state = Window1.STATE.GAME;
			}
			else if(y>=225 && y<=265)
			{
				Window1.state=Window1.STATE.TWOPLAYER;
			}
			else if(y>=300 && y<=340)
			{
				Window1.state = Window1.STATE.INSTRUCTIONS;
			}
		}
		else if(x>=5 && x<=55 && y>=5 && y<=25)
		{
			Window1.state = Window1.STATE.MENU;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
