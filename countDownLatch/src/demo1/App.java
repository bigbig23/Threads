package demo1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {

	private CountDownLatch latch;

	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Started .....");
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
		//Each time the countDown method is called, the count of the latch is reduced by one.
		//When this count value reaches 0, the latch is tripped and all waiting threads are released.
	}

}

public class App {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);// allows thread to waite for ex: 3  here,
		ExecutorService executor = Executors.newFixedThreadPool(3);

		for (int i = 0; i < 3; i++) {
			executor.submit(new Processor(latch));
		}

		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Completed ....");

	}

}
