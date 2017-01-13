import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

public class HazelcastQueueProducer {
	
	private static int numberOfTaks = 0;
	private static int sleepTime = 0;
	
	public static void main( String[] args ) throws Exception {
	  
		if (args != null && args.length == 2) { 
			numberOfTaks = Integer.parseInt(args[0]);
			sleepTime = Integer.parseInt(args[1]);
		} else { 
			System.out.println ("Not all parameters informed"); 
			System.out.println (""); 
			System.out.println ("Usage: java HazelcastQueueProducer <number of tasks> <sleep (ms)>"); 
			System.out.println ("  Example: java HazelcastQueueProducer 1000 5");
			System.out.println (""); 
		} 
		  
		HazelcastInstance hz = Hazelcast.newHazelcastInstance();
		IQueue<String> queue = hz.getQueue( "taskQueue" );
		for ( int k = 1; k < numberOfTaks; k++ ) {
		  queue.put( "Task-"+k );
		  System.out.println( "Producing: " + k );
		  Thread.sleep(sleepTime);
		}
		queue.put( "-1" );
		System.out.println( "Producer Finished!" );
	}
}