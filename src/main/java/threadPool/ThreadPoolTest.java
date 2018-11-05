package threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) throws Exception {
//		new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);	
		//创建线程池
//		ExecutorService pool = Executors.newFixedThreadPool(10);
		ExecutorService pool = new ThreadPoolExecutor(3, 4, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		//接收结果的集合
		List<Future<String>> futureList = new ArrayList<Future<String>>();
		//开启线程
		for (int i=0; i<20 ; i++) {
			Future<String> future = pool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					Thread.sleep(1000);
					return Thread.currentThread().getName();
				}
			});
			//结果对象存入集合
			futureList.add(future);
		}
		//结果有返回就输出(阻塞)
		for (int i = 0; i < futureList.size(); i++) {
			String string = futureList.get(i).get();
			System.out.println(string);
		}
		//线程池关闭
		pool.shutdown();
	}
}
