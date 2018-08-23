package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BoundedBufferMain {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		BoundedBuffer boundedBuffer = BoundedBuffer.newInstance();
		System.out.println("开启读线程。。。");
		pool.execute(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						System.out.println("读线程..." + boundedBuffer.take());
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		System.out.println("开始写线程。。。");
		pool.execute(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						boundedBuffer.put("" + i);
						System.out.println("写线程..." + i);
//						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
