package threadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 原理
 * 每个线程有一个map
 * ThreadLocal类可以通过当前线程拿到这个map
 * 存储对象以ThreadLocal对象为key，值为value
 * 保证了一个线程（map）的一个threadlocal（key）对应一个值
 * @author chenglong
 */
public class SimpleImpl2  {

	public static class CommonThread extends Thread{
		Map<Integer, Integer> cacheMap = new HashMap<Integer, Integer>();
	}
	
	public static class CustomThreadLocal{
		private int defaultValue;
		public CustomThreadLocal(int value){
			defaultValue = value;
		}
		public Integer get(){
			Integer id = this.hashCode();
			Map<Integer, Integer> cacheMap = getMap();
			if(cacheMap.containsKey(id)){
				return cacheMap.get(id);
			}
			return defaultValue;
		}
		public void set(int value){
			Integer id = this.hashCode();
			Map<Integer, Integer> cacheMap = getMap();
			cacheMap.put(id, value);
		}
		public Map<Integer, Integer> getMap(){
			CommonThread thread = (CommonThread) Thread.currentThread();
			return thread.cacheMap;
		}
	}
	
	public static class Number{
		private CustomThreadLocal value = new CustomThreadLocal(0);
		
		public void increase() throws InterruptedException{
			value.set(10);
			Thread.sleep(10);
			System.out.println("increase value : " + value.get());
		}
		
		public void decrease() throws InterruptedException{
			value.set(-10);
			Thread.sleep(10);
			System.out.println("decrease value : " + value.get());
		}
	}
	
	public static void main(String[] args) {
		final Number number = new Number();
		Thread increaseThread  = new CommonThread() {
			@Override
			public void run() {
				try {
					number.increase();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread decreaseThread  = new CommonThread() {
			@Override
			public void run() {
				try {
					number.decrease();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		increaseThread.start();
		decreaseThread.start();
	}
}
