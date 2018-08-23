package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockMain {

	public static void main(String[] args) {
		test_3_4();
		
	}

	private static void test_1_2() {
		final Object res1 = "res1";
		final Object res2 = "res2";
		DeadLock1 lock1 = new DeadLock1(res1, res2);
		DeadLock2 lock2 = new DeadLock2(res1, res2);
		lock1.start();
		lock2.start();
	}
	
	private static void test_3_4() {
		final Lock res1 = new ReentrantLock();
		final Lock res2 = new ReentrantLock();
		DeadLock3 lock3 = new DeadLock3(res1, res2);
		DeadLock4 lock4 = new DeadLock4(res1, res2);
		lock3.start();
		lock4.start();
	}
}
