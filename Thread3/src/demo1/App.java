package demo1;

public class App {
	public int count = 0;
	
	
	//synchronized since it stors 2 data in same time, one must must wait and it stores the first one
	//with synchronized is possible to get this machanisme
	public synchronized void increment() {
		count++;

	}

	public static void main(String[] args) {
		App app = new App();
		app.doWork();

	
	}
	

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				 for(int i=0;i<2000;i++) {
					 increment();
				 }
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				 for(int i=0;i<2000;i++) {
					 increment();
				 }
			}
		});
		t1.start();
		t2.start();
		
		try {
			
			//The join method forces the CURRENT thread to wait until the REFERENCED thread finishes its execution.
			t1.join();//join pause the thread untils it has data, so that when we print it wiht sysout count, it has already data in it
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);
	
	} 
	

	

}