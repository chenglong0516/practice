package cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class CglibTest {
	public static void main(String[] args) {
		DaoProxy daoProxy = new DaoProxy();
		DaoAnotherProxy  daoAnotherProxy  = new DaoAnotherProxy();
		
		Enhancer enhancer = new Enhancer();
		
		//设置要代理的类
		enhancer.setSuperclass(Dao.class);
		//回调的实现类
//		enhancer.setCallback(daoProxy);
		//不同回调代理对象
		enhancer.setCallbacks(new Callback[]{daoProxy, daoAnotherProxy, NoOp.INSTANCE});
		//过滤方案
		enhancer.setCallbackFilter(new DaoFilter());
		
		//设置构造函数中的方法不拦截
		enhancer.setInterceptDuringConstruction(false);
		//生成代理对象
		Dao dao = (Dao)enhancer.create();
		
		dao.update();
		dao.select();
	}
}
