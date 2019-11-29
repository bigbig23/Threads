package demo2;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	//creating a method do deal with deadlock
	private void aquireeLocks(Lock firstLock,Lock secondLock) throws InterruptedException {
		while(true) {
			//Aquires locks
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			
			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
				
			}finally {
				if(gotFirstLock && gotSecondLock ) {
					return;//if found return the function 
				} //else
					if(gotFirstLock) {
						firstLock.unlock();
						//After acquiring a lock with trylock, if we don't unlock it, other threads 
						//cannot get the same lock and they may be blocked forever
					}
					if(gotFirstLock) {
						secondLock.unlock();
					}
			}
			//locks not aquired
			Thread.sleep(1);
		}
	}

	public void firstThread() throws InterruptedException {
		Random rnd = new Random();
		for (int i = 0; i < 10000; i++) {
			aquireeLocks(lock1,lock2);
	
			try {
				Account.transfer(acc1, acc2, rnd.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void secondThread() throws InterruptedException {
		Random rnd = new Random();
		for (int i = 0; i < 10000; i++) {
			aquireeLocks(lock2,lock1);

			 
			try {
				Account.transfer(acc2, acc1, rnd.nextInt(100));
			} finally {
				lock2.unlock();
				lock1.unlock();
			}
		}
	}

	public void finished() {
		System.out.println("Account 1 balance : " + acc1.getBalance());
		System.out.println("Account 2 balance : " + acc2.getBalance());
		System.out.println("Total balance : " + (acc1.getBalance() + acc2.getBalance()));

	}

}
