import java.util.*;

public class BurstTimeComparator implements Comparator<Process>
{
    @Override
    public int compare(Process p1, Process p2)
    {
        
        return p2.burstTime - p1.getBurstTime();
    }
    
}