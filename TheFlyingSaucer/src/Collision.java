
public class Collision {
      
	
	public static boolean EnemyHit(FireBall ball, Enemy e)
	{
		if(ball!=null)
			return ball.getBounds().intersects(e.getBounds());
		return false;
	}
	public static boolean PlayerHit(EnemyFireBall ball, Player p)
	{
		if(ball!=null)
			return ball.getBounds().intersects(p.getBounds());
		return false;
	}
	
}
