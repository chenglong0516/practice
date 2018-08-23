package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 顺序打印ABC（A打印完只能打印B，B打印完只能打印C，C打印完只能打印A）
 */
public class PrintABC {

	private Lock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();
	private Condition conditionB = lock.newCondition();
	private Condition conditionC = lock.newCondition();
	private String type = "A";
	
	public void printA(){
		lock.lock();
		try {
			while (type != "A") {
				try {
					conditionA.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "print A ...");
			type = "B";
			conditionB.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void printB(){
		lock.lock();
		try {
			while (type != "B") {
				try {
					conditionB.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "print B ...");
			type = "C";
			conditionC.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void printC(){
		lock.lock();
		try {
			while (type != "C") {
				try {
					conditionC.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "print C ...");
			type = "A";
			conditionA.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final PrintABC printABC = new PrintABC();
		Thread ta = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					printABC.printA();
				}
			}
		});
		Thread tb = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 19; i++) {
					printABC.printB();
				}
			}
		});
		Thread tc = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					printABC.printC();
				}
			}
		});
		ta.start();
		tb.start();
		tc.start();
	}
}
