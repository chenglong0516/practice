package lambda;

/**
 * 不能在 lambda 内部修改定义在域外的局部变量
 * @author chenglong
 */
public class Java8Tester2 {
	public static void main(String[] args) {
		test((x,y)->x*y);
	}
	
	public static void test(MyInterface myInterface){
		int result = myInterface.IMethod(1, 2);
		System.out.println(result);
	}
}

interface MyInterface{
	int IMethod(int a, int b);
}