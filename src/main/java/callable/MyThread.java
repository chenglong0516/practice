package callable;

import java.util.concurrent.Callable;

public class MyThread implements Callable<String>{

	private String mainId;
	
	public MyThread(String mainId) {
		super();
		this.mainId = mainId;
	}

	@Override
	public String call() throws Exception {
		Thread.currentThread().setName(Thread.currentThread().getName() + mainId);
		System.out.println("MyThread run, Id is " + Thread.currentThread().getName());
		Thread.sleep(1000);
		System.out.println("MyThread finish");
		return "hello callable from MyThread!";
	}
}
