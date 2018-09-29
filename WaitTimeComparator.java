import java.util.*;
public class WaitTimeComparator implements Comparator<Process>
{
    @Override
    public int compare(Process p1, Process p2)
    {
        if(p1.getWaitingTime() == p2.getWaitingTime()){
            return p1.getPriority() - p2.getPriority();
        }
        return p1.getWaitingTime() - p2.getWaitingTime();
    } 
}