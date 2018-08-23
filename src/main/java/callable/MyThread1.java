package callable;

import java.util.concurrent.Callable;

public class MyThread1 implements Callable<String>{

	private String mainId;
	
	public MyThread1(String mainId) {
		super();
		this.mainId = mainId;
	}
	
	@Override
	public String call() throws Exception {
//		try {
//		} catch (Exception e) {
//			return e.getMessage();
//		}
		Thread.currentThread().setName(Thread.currentThread().getName() + mainId);
		System.out.println("MyThread1 run, mainId is " + Thread.currentThread().getName());
//		int i = 1/0;
		Thread.sleep(2000);
		System.out.println("MyThread1 finish");
		return "hello callable from MyThread1!";
	}
}
