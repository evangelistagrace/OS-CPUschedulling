import java.util.*;

public class BurstTimeComparator implements Comparator<Process>
{
    @Override
    public int compare(Process p1, Process p2)
    {
        if(p1.getBurstTime() == p2.getBurstTime()){
            return p1.getPriority() - p2.getPriority();
        }
        return p1.getBurstTime() - p2.getBurstTime();
    } 
}