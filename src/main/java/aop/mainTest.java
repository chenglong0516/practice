package aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Proxy;

public class mainTest {
	public static void main(String[] args){
		test1();
//		test();
	}

	/*
	 * cglib
	 */
	private static void test1() {
		DaoProxy daoProxy = new DaoProxy();
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(DaoImpl.class);
		enhancer.setCallback(daoProxy);
		
		Dao dao = (DaoImpl)enhancer.create();
		dao.insert();
	    System.out.println("----------分割线----------11");
	    dao.delete();
	    System.out.println("----------分割线----------11");
	    dao.update();
		
	}

	/*
	 * java proxy
	 */
	private static void test() {
		Dao dao = new DaoImpl();
		
		Dao proxyDao = (Dao)Proxy.newProxyInstance(LogInvocationHandler.class.getClassLoader(), new Class<?>[]{Dao.class}, new LogInvocationHandler(dao));
		
		proxyDao.insert();
		System.out.println("----------分割线----------");
		proxyDao.delete();
		System.out.println("----------分割线----------");
		proxyDao.update();
	}
}
