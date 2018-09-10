import java.util.*;

public class Main
{
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int numOfProcesses;
        int[] arrivalTime = new int[10];
        int[] burstTime = new int[10];
        int[] priority = new int[10];
        int[] timeQuantum = new int[10];
        
        System.out.println("------CPU Scheduling Algorithms Simulation-----");
        System.out.println("Enter number of processes: ");
        numOfProcesses = input.nextInt();
        
        for(int i=0; i<numOfProcesses; i++)
        {
            System.out.println("Process [" + i + "]");
            System.out.println("Enter arrival time: ");
            arrivalTime[i] = input.nextInt();
            System.out.println("Enter burst time: ");
            burstTime[i] = input.nextInt();
            System.out.println("Enter priority: ");
            priority[i] = input.nextInt();
            
            for(int j=0;j<priority.length;j++)
            {
                if((priority[j]==1)||(priority[j]==2))
                {
                    System.out.println("Enter time quantum: ");
                    timeQuantum[j] = input.nextInt();
                }
            }
            
        }
        
        
        
    }
}