package demo1;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	//BlockingQueue since it's thread safe , we don't have to worry about synchronization
	public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		});
 
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
	
	
	
	
	public static Random rnd = new Random();
	
	public static void producer() throws InterruptedException {
		while(true) {
			queue.put(rnd.nextInt(100));
		}
	}
	
	public static void consumer() throws InterruptedException {
		while(true) {
			Thread.sleep(100);
			//I just did this to get "if" to execute only one out of every ten times. 
			//So I could have written == 8 or any other number.
			if(rnd.nextInt(10)==0) {
				// It returns a random number from 0 to 9, so on average you'll get 0 (or any other number from 0 to 9)
				//one out of every ten times.
				Integer value = queue.take();
				System.out.println("Taken value : " + value + " Queue size : " + queue.size());
			}
 		}
	}
	
	

}
