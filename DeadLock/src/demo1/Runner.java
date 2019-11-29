package demo1;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	public void firstThread() {
		Random rnd = new Random();
		for (int i = 0; i < 10000; i++) {
			lock1.lock();
			lock2.lock();
			try {
				Account.transfer(acc1, acc2, rnd.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void secondThread() {
		Random rnd = new Random();
		for (int i = 0; i < 10000; i++) {
			lock1.lock();
			lock2.lock();
			//if i change the order it throws an exception  : DeadLock : we sole this in demo2
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