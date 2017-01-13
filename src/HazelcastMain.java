import java.util.concurrent.BlockingQueue;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
 
public class HazelcastMain {
    public static void main(String[] args) {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
/*
        Map<Integer, String> mapCustomers = instance.getMap("customers");
        mapCustomers.put(1, "Joe");
        mapCustomers.put(2, "Ali");
        mapCustomers.put(3, "Avi");
 
        System.out.println("Customer with key 1: "+ mapCustomers.get(1));
        System.out.println("Map Size:" + mapCustomers.size());
 
        Queue<String> queueCustomers = instance.getQueue("customers");
        queueCustomers.offer("Tom");
        queueCustomers.offer("Mary");
        queueCustomers.offer("Jane");
        System.out.println("First customer: " + queueCustomers.poll());
        System.out.println("Second customer: "+ queueCustomers.poll());
        System.out.println("Queue size: " + queueCustomers.size());
*/
        BlockingQueue<String> taskQueue = instance.getQueue( "tasks" );
        
        try {
        	for (int i = 0; i < 100; i++) {
        		taskQueue.put( "Task-0"+i );        		
        	}
	        
	        while (!taskQueue.isEmpty()) {
	        	
		        String task = taskQueue.take();
		    	
		        if ( task != null ) {
		        	System.out.println("Task: " + task + " processed");
		        }
		        Thread.sleep(5000);
	        }
	        
        } catch (Exception e) {
        	System.out.println("Exception: " + e.getClass() + " - " + e.getMessage());
        }
    }
}
