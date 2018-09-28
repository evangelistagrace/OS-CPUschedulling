public class Box
{
	public Process p;
	public int startTime = 0;
	public int endTime;

	public Box(Process p, int startTime, int endTime)
	{
		this.p = p;
		this.startTime = startTime;
		this.endTime = endTime;
	}

}