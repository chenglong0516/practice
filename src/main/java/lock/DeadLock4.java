package lock;

import java.util.concurrent.locks.Lock;

public class DeadLock4 extends Thread{

	private Lock res1;
	private Lock res2;
	
	public DeadLock4(Lock res1, Lock res2){
		this.res1 = res1;
		this.res2 = res2;
	}

	@Override
	public void run() {
		res2.lock();
			System.out.println("线程4取得了res2锁");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			res1.lock();
				System.out.println("线程4取得了res1锁");
			res1.unlock();
		res2.unlock();
	}
	
	
	
}
