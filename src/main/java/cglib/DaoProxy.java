package cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class DaoProxy implements MethodInterceptor{

	@Override
	public Object intercept(Object object, Method arg1, Object[] objects,
			MethodProxy proxy) throws Throwable {
		System.out.println("Before Method Invoke...");
		proxy.invokeSuper(object, objects);
		System.out.println("After Method Invoke...");
		return object;
	}

}
