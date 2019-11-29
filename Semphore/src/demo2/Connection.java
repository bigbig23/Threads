package demo2;

import java.util.concurrent.Semaphore;

public class Connection {
	private static Connection instance = new Connection();
	
	private int  connections = 0;
	
	Semaphore sem = new Semaphore(10);//limit threads access up to 10 in connection() section
	//All the threads here can increment "connections". So that needs to be synchronized. After 
	//we add the semaphore, a maximum of ten threads can "connect" at the same time. So synchronized 
	//is still needed because ten threads can potentially try to increment "connections" at the same time.
	
	public Connection() {
		
	}
	
	
	public static Connection getInstance() {
		return instance;
	}
	
	
	public void connect() throws InterruptedException {
		
		sem.acquire();
		
		synchronized (this) {
			connections++;
			System.out.println("Current connections : " + connections);
		}
		
		Thread.sleep(2000);
		
		synchronized (this) {
			connections--;
		}
		sem.release();
	}
	

}
