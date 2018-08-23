package lock;

public class DeadLock1 extends Thread{

	private Object res1;
	private Object res2;
	
	public DeadLock1(Object res1, Object res2){
		this.res1 = res1;
		this.res2 = res2;
	}

	@Override
	public void run() {
		synchronized (res1) {
			System.out.println("线程1取得了res1锁");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			synchronized (res2) {
				System.out.println("线程1取得了res2锁");
			}
		}
	}
	
	
	
}
