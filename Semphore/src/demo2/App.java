package demo2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) throws InterruptedException {
 
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i =0;i<200;i++) {//we r giving 200 threads
			executor.submit(new Runnable() {

				@Override
				public void run() {
 					try {
						Connection.getInstance().connect();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		
	}

}
