package demo1;

import java.util.Scanner;

class Processor extends Thread{
	public volatile boolean  running=true;
	
	public void run() {
		while(running) {
			System.out.println("Hello ");
		}
	}
	
	public void shutdown() {
		running =false;
	}
}

public class App {

	public static void main(String[] args) {
 
		Processor p1 =  new Processor();
		p1.start();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Press return to stop ...");
		sc.nextLine();
		p1.shutdown();  
	}

}
