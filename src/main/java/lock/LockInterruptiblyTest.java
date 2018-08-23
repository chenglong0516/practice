package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockInterruptibly()响应中断的使用方法：
 * @author chenglong
 * http://www.cnblogs.com/dolphin0520/p/3923167.html
 */

public class LockInterruptiblyTest {
	private Lock lock = new ReentrantLock();
	public static void main(String[] args) {
		LockInterruptiblyTest test = new LockInterruptiblyTest();
		MyThread thread1 = new MyThread(test);
		MyThread thread2 = new MyThread(test);
		//先执行0线程
		thread1.start();
		try {Thread.sleep(10);} catch (Exception  e) {}
		//再执行1线程，进入等待锁状态，但是可以被打断
		thread2.start();
		//2秒后打断1线程的等待
		try {Thread.sleep(2000);} catch (Exception  e) {}
		thread2.interrupt();
	}
	
	public void insert(Thread thread)throws InterruptedException{
		lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {  
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
            	if(System.currentTimeMillis() - startTime >= 5000)
                    break;
                //插入数据
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }  
	}
}

class MyThread extends Thread{
	private LockInterruptiblyTest test = null;
	public MyThread(LockInterruptiblyTest test){
		this.test = test;
	}
	public void run(){
		try {
			test.insert(Thread.currentThread());
		} catch (InterruptedException  e) {
			System.out.println(Thread.currentThread().getName()+"被中断");
		}
	}
}
