import java.util.*;

public class ArrivalTimeComparator implements Comparator<Process>
{
    @Override
    public int compare(Process p1, Process p2)
    {
        
        return(p1.getArrivalTime() - p2.getArrivalTime());
    }
    
}