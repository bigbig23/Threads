package demo;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Future<Integer> future=	executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				 Random rnd = new Random();
				 int duration = rnd.nextInt(4000);
				 
				 //throw excetpion with call
				 if(duration > 2000) {
					 throw new IOException("sleeeping too long");
				 }
				 System.out.println("Starting .....");
				 
				 Thread.sleep(duration);
				 
				 System.out.println("Finished .....");
				return duration;
			}
			
		});

		executor.shutdown();	
		//we can use Future get the return call() , whish is duration
		try {
			System.out.println("futer is : " + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}

}
