package lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SyncData{
	//共享数据
	private int data;
	
	private ReadWriteLock rw1 = new ReentrantReadWriteLock();
	
	public void set(int data){
		rw1.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "准备写入数据");
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.data = data;
			System.out.println(Thread.currentThread().getName() + "写入数据:" + this.data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rw1.writeLock().unlock();
		}
	}
	
	public void get(){
		rw1.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "准备读取数据");
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "读取数据:" + this.data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rw1.readLock().unlock();
		}
	}
	
	
	
//	public synchronized void set(int data){
//		System.out.println(Thread.currentThread().getName() + "准备写入数据");
//		try {
//			Thread.sleep(20);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		this.data = data;
//		System.out.println(Thread.currentThread().getName() + "写入数据:" + this.data);
//	}
//	public synchronized void get(){
//		System.out.println(Thread.currentThread().getName() + "准备读取数据");
//		try {
//			Thread.sleep(20);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		this.data = data;
//		System.out.println(Thread.currentThread().getName() + "读取数据:" + this.data);
//	}
}
