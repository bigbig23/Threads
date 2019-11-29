package demo3;

public class App {

	public static void main(String[] args) {
		// third way which is with less code with 
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
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
			
		});

		t1.start();
	}
	/*
	  
	  
	  
	  So, the expression new Runnable() { ... } can be read as: defining a class that 
	  implements the Runnable interface, then creating an object of that new class.
	  
	   
	  
	  
	  In Java 8, you can use an even more concise syntax - lambda expressions. 
	  The expression new Runnable() { ... } can be re-written as () -> { ... }.
	   Note that a lambda expression can only be used in the place of an interface with
	    a single abstract method, like Runnable.
	  
	 
	= a way with lambda exp
	Thread thread1 = new Thread(() -> {
	    for (int i = 0; i < 5; i++) {
	        System.out.println("Hello: " + i);
	        try {
	            Thread.sleep(100);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	 
	});
	thread1.start();
	
	*/

}
