package lambda;

/**
 * lambdas 实现 Runnable 接口
 * @author chenglong
 */
public class Java8Tester3 {
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("hello world!");
			}
		}).start();
		
		//使用lambda
		new Thread(()-> System.out.println("hello world1!")).start();
		
		Runnable race1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("hello world2!");
			}
		};
		
		race1.run();
		//使用lambda
		Runnable race2 = ()-> System.out.println("hello world3!");
		race2.run();
		
	}

}
