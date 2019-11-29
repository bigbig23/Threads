package demo1;

import java.util.concurrent.Semaphore;

public class App {

	public static void main(String[] args) throws InterruptedException {
 
		//Semaphore is useful to control acess to some res
		Semaphore sem = new Semaphore(1); //1 is the number of permits
		sem.release();//increments
		sem.acquire();//decrement
		System.out.println("Permits avaiable : " + sem.availablePermits());
	}

}