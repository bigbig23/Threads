package demo2;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count =0;
	
 	private Lock lock = new ReentrantLock();
 	
 	private Condition cond = lock.newCondition(); //it's like wait and notify for rentrantlock
	
	public void increment() {
		for(int i =0;i<10000;i++) {
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException {
		try {
			lock.lock();
			System.out.println("Waiting a return key...");
			cond.await();
			System.out.println("Got the return Key ...");
			increment();
		}finally {
			lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException {
		try {
			Thread.sleep(1000);
			lock.lock();

			System.out.println("Press the return key....");
			
			new Scanner(System.in).nextLine();
			
			increment();
			
			cond.signal(); //waking up the other thread
		}finally {
			lock.unlock();
		}
	}
	
	public void finished() {
		System.out.println("Count is : " + count);
	}


}
