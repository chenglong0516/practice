package cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

public class DaoFilter implements CallbackFilter{

	@Override
	public int accept(Method method) {
		if("select".equals(method.getName())){
	        //0代表回调代理对象数组的第一个
			return 0;
		}
		//1代表回调代理对象数组的第二个
		return 1;
	}

}
