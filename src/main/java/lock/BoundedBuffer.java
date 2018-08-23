package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 使用Condition线程间通信
 * 缓冲队列的实现。
 */
public class BoundedBuffer {

	private static BoundedBuffer boundedBuffer = new BoundedBuffer();
	
	private BoundedBuffer() {
	}

	public static BoundedBuffer newInstance(){
		return boundedBuffer;
	}
	
	final Lock lock = new ReentrantLock();
	
	final Condition notFull = lock.newCondition();
	
	final Condition notEmpty = lock.newCondition();
	
	final Object[] items = new Object[5];
	
	int putptr;		//写索引
	int takeptr;	//读索引
	int count;		//队列中存在的数据个数
	
	public void put(Object x)throws InterruptedException{
		lock.lock();
		try {
			while(count == items.length){
				//当计数器等于队列长度，不能再插入，阻塞写线程
//				System.out.println("notFull.await()...");
				notFull.await();
			}
			items[putptr] = x;
			putptr++;
			if(putptr == items.length){
				putptr = 0;
			}
			count++;
//			System.out.println("notEmpty.signal()...");
			notEmpty.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public Object take() throws InterruptedException{
		lock.lock();
		try {
			while(count==0){
//				System.out.println("notEmpty.await()...");
				notEmpty.await();
			}
			Object x = items[takeptr];
			takeptr++;
			if(takeptr == items.length){
				takeptr = 0;
			}
			count--;
//			System.out.println("notFull.signal()...");
			notFull.signal();
			return x;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return null;
	}
}
