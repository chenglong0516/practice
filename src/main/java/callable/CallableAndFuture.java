package callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试callable接口相关
 * @author chenglong
 *
 */
public class CallableAndFuture {

	public static ExecutorService pool = Executors.newFixedThreadPool(10);
	
	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		long id = Thread.currentThread().getId();
		String idStr = String.valueOf(id);
		System.out.println("主线程id " + idStr);
		Future<String> future = pool.submit(new MyThread(idStr));
		Future<String> future1 = pool.submit(new MyThread1(idStr));
		pool.execute(new MyThread2());
		
		try {
			String str = future.get(); //阻塞直到所属的线程返回结果
			System.out.println("future.get() : " + str);
			String str1 = future1.get(); //阻塞直到所属的线程返回结果
			System.out.println("future1.get() : " + str1);
			System.out.println(str + str1);
			System.out.println("time : " + String.valueOf(System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}
}
