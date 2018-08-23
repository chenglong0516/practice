package callable;

public class MyThread2 implements Runnable{

	@Override
	public void run(){
		System.out.println("MyThread2 run...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("MyThread2 finish");

	}

}
