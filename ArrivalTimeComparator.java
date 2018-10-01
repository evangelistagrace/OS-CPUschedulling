import java.util.*;

public class ArrivalTimeComparator implements Comparator<Process>
{
    @Override
    public int compare(Process p1, Process p2)
    {
        
        if(p2.getArrivalTime() == p1.getArrivalTime()){
            return p2.getPriority() - p1.getPriority();
        }
        return p2.getArrivalTime() - p1.getArrivalTime();
    }
    
}