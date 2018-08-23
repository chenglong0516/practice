package contextSwitch;

import java.util.concurrent.Callable;



/*
 * 穿行并发时间测试
循环次数	串行执行耗时/ms	并发执行耗时/ms	串行和并发对比
1亿		78				50				并发快约0.5倍
1000万	10				6				并发快约0.5~1倍
100万	3				2				差不多
10万		2				2				差不多
1万		0				1				差不多，十几次执行下来，总体而言串行略快

如何减少上下文切换

既然上下文切换会导致额外的开销，因此减少上下文切换次数便可以提高多线程程序的运行效率。减少上下文切换的方法有无锁并发编程、CAS算法、使用最少线程和使用协程。

无锁并发编程。多线程竞争时，会引起上下文切换，所以多线程处理数据时，可以用一些办法来避免使用锁，如将数据的ID按照Hash取模分段，不同的线程处理不同段的数据
CAS算法。Java的Atomic包使用CAS算法来更新数据，而不需要加锁
使用最少线程。避免创建不需要的线程，比如任务很少，但是创建了很多线程来处理，这样会造成大量线程都处于等待状态
协程。在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换

 */
public class ContextSwitchTest {

	private static final long count = 10000;
	
	public static void main(String[] args) throws Exception {
		concurrency();
		serial();
	}

	private static void serial() {
		long start = System.currentTimeMillis();
		int a = 0;
		for (long i = 0; i < count; i++)
		{
		    a += 5;
		}
		int b = 0;
		for (int i = 0; i < count; i++)
		{
		    b --;
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("Serial：" + time + "ms, b = " + b + ", a = " + a);
		
	}

	private static void concurrency() throws Exception{
		long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int a = 0;
				for (int i = 0; i < count; i++) {
					a += 5;
				}
			}
		});
		thread.start();
		Thread thread1 = new Thread();
		int b = 0;
		for (int i = 0; i < count; i++) {
			b--;
		}
		thread.join();
		long time = System.currentTimeMillis() - start;
		System.out.println("Concurrency：" + time + "ms, b = " + b);
	}
}
