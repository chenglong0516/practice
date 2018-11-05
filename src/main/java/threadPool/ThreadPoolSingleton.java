package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolSingleton {
	/**核心线程数，
     * 经验值设置，如果是CPU计算密集型应用设置为N+1或jdk1.8以上可设置为2N
     * 如果是IO密集型应用设置为N/(1-阻塞系数),阻塞系数一般为0.8~0.9之间（也可直接取0.8或0.9）其中N表示当前服务器的CPU核数
     */
    private static final int CORETHREADS = 10;

    /**
     * 1.默认创建的线程池corePoolSize和maximumPoolSize值是相等的；
     * 2.默认使用的是LinkedBlockingQueue队列，队列长度未指定，默认大小为Integer.MAX_VALUE
     * 3.默认keepAliveTime=0L，表示当前线程数大于corePoolSize时，空闲时间为0，即立即回收
     * 推荐使用
     */
    private static class NewFixedThreadPoolInstance {
        private static ExecutorService instance = Executors.newFixedThreadPool(CORETHREADS);
    }
    /**
     *newFixedThreadPool获取单例
     *
     */
    public static ExecutorService newFixedThreadPool(){
        return NewFixedThreadPoolInstance.instance;
    }
    
    /**
     * 1.默认创建的线程池corePoolSize和maximumPoolSize值是相等的，并且大小都为1；
     * 2.默认使用的是LinkedBlockingQueue队列，队列长度未指定，默认大小为Integer.MAX_VALUE
     * 3.默认keepAliveTime=0L，表示当前线程数大于corePoolSize时，空闲时间为0，即只要空闲立即销毁回收
     */
    private static class NewSingleThreadExecutorInstance {
        private static ExecutorService instance = Executors.newSingleThreadExecutor();
    }
    /**
     *newSingleThreadExecutor获取单例
     *
     */
    public static ExecutorService newSingleThreadPool(){
        return NewSingleThreadExecutorInstance.instance;
    }
    
    /**
     * 1.默认创建的线程池corePoolSize为0，,maximumPoolSize值为Integer.MAX_VALUE；
     * 2.默认使用的是SynchronousQueue队列，表示来了任务就创建线程运行
     * 3.默认keepAliveTime=60L，单位是秒，表示当线程空闲超过60秒，就销毁线程
     */
    private static class NewCachedThreadPoolInstance {
        private static ExecutorService instance = Executors.newCachedThreadPool();
    }
    
    /**
     *newCachedThreadPool获取单例
     */
    public static ExecutorService newCachedThreadPool(){
        return NewCachedThreadPoolInstance.instance;
    }
	
	private ThreadPoolSingleton(){}

	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)ThreadPoolSingleton.newFixedThreadPool();
		System.out.printf("[线程池定时监控]-核心池大小:%d,最大线程数:%d,当前线程数:%d,当前活动线程数:%d\n",threadPoolExecutor.getCorePoolSize(),
                threadPoolExecutor.getMaximumPoolSize(), threadPoolExecutor.getPoolSize(), threadPoolExecutor.getActiveCount());
		System.out.printf("[线程池定时监控]-总任务数:%d,已经执行完毕的任务数:%d,出现过最大的线程数:%d\n",threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(), threadPoolExecutor.getLargestPoolSize());
	}
}
