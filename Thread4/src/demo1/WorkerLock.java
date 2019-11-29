package demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerLock {
	static Object lock1 = new Object();
	static Object lock2 = new Object();

	static List<Integer> list1 = new ArrayList<Integer>();
	static List<Integer> list2 = new ArrayList<Integer>();
	static Random rnd = new Random();

	//putting synchronized inside the method which reduce more time, 
	public static void stageOne() {
		//we can list1 and list2 instead of lock1 and lock2 but it's not a good practice,since we may need to do more operation with list
		synchronized(lock1) {
			try {
				Thread.sleep(1);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(rnd.nextInt(100));
		}
	
	}

	public static void stagetwo() {
		synchronized(lock2) {
			try {
				Thread.sleep(1);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(rnd.nextInt(100));
		}
		
	}

	public static void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stagetwo();

		}
	}

	public static void main(String[] args) {
		
 		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}	
		});
		
 		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}	
		});

		t1.start();
		t2.start();
		//wihout t1.join(); it throws  error,
		//and we need to synchronized the method,so that we have the same res
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("starting ........");

		Long start = System.currentTimeMillis();
		process();
		Long end = System.currentTimeMillis();

		System.out.println("Time taken .." + (end - start));
		System.out.println("list1: " + list1.size() + " list2: " + list2.size());

	}

}
