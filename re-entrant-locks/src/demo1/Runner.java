package demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count =0;
	
	//without ReentrantLock the res of count is never the same, wheather we use sync or ReentrantLock would do the job
	private Lock lock = new ReentrantLock();
	
	public void increment() {
		for(int i =0;i<10000;i++) {
			count++;
		}
	}
	
	public void firstThread() {
		//lock.lock(); //block it from another thread , unless the actual thread finishes his job
		//increment();
		///lock.unlock(); //release it now,bcoz it finished, and let other thread enter, and so on
		//after this we have the same result
		//it must be used in exeception block, in case our code throws an exeception
		try {
			lock.lock();
			increment();
		}finally {
			lock.unlock();
		}
	}
	
	public void secondThread() {
		try {
			lock.lock();
			increment();
		}finally {
			lock.unlock();
		}
	}
	
	public void finished() {
		System.out.println("Count is : " + count);
	}


}
