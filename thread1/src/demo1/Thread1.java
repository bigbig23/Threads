package demo1;

//first way to extend Thread 
class Runner1 extends Thread{

	@Override
	public void run() { ///overriding run() method of thread
		 for(int i =0;i<10;i++) {
			 System.out.println("Hello " +i);
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	
}

public class Thread1 {

	public static void main(String[] args) {
		Runner1 runner1 = new Runner1();
		runner1.start();
		//these 2 running in asynchronize way bcoz of thread 
		Runner1 runner2 = new Runner1();
		runner2.start();


	}

}
