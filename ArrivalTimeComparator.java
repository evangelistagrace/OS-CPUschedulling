import java.util.*;

public class ArrivalTimeComparator implements Comparator<Proc>
{
    @Override
    public int compare(Proc p1, Proc p2)
    {
        
        return p2.getArrivalTime() - p1.getArrivalTime();
    }
    
}