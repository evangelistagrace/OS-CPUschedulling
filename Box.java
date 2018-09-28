public class Box
{
	public Proc p;
	public int startTime = 0;
	public int endTime;

	public Box(Proc p, int startTime, int endTime)
	{
		this.p = p;
		this.startTime = startTime;
		this.endTime = endTime;
	}

}