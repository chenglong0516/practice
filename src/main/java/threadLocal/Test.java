package threadLocal;

public class Test {

	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
	ThreadLocal<String> stringLocal = new ThreadLocal<String>();
	
	public void set(){
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}
	
	public long getLong(){
		return longLocal.get();
	}
	
	public String getString(){
		return stringLocal.get();
	}
	
	public static void main(String[] args) throws InterruptedException {
		final Test test = new Test();
		
		test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());
		
		Thread thread1 = new Thread(){
			public void run(){
				test.set();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			};
		};
		thread1.start();
		thread1.join();
		
		System.out.println(test.getLong());
		System.out.println(test.getString());
		
	}
/*
 * 经典用法 处理数据库链接，session等
 * 思想：如果没有相关对象（链接，session）,创建之并放入threadlocal对象中
 * get方法从ThreadLocal中获取
 * 
    private static final ThreadLocal threadSession = new ThreadLocal();
 
	public static Session getSession() throws InfrastructureException {
    Session s = (Session) threadSession.get();
    try {
        if (s == null) {
            s = getSessionFactory().openSession();
            threadSession.set(s);
        }
    } catch (HibernateException ex) {
        throw new InfrastructureException(ex);
    }
    return s;
}
 */

}
