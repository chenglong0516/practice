package lambda;

/**
 * 不能在 lambda 内部修改定义在域外的局部变量
 * @author chenglong
 */
public class Java8Tester1 {

	final static String salutation = "Hello! ";
	
	public static void main(String[] args) {
	
		GreetingService greetingService1 = message -> 
		System.out.println(salutation + message);
		
		greetingService1.sayMessage("Runoob");
		
		final int num = 1;//num用于lambda表达式中，不可被后面修改
		Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
		
		s.convert(2);
		
	}
	
	interface GreetingService{
		void sayMessage(String message);
	}
	
	public interface Converter<T1, T2>{
		void convert(int i);
	}
	
}
