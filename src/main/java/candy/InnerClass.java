package candy;

public class InnerClass {
	public static void main(String[] args)
	{
	    InnerClass innerClass = new InnerClass();
	    innerClass.test();
	}
	
	public void test(){
	    int i = 4;
	    class B
	    {
	        public void print()
	            {
	            System.out.println("AAA, i = " + i);
	        }
	    }
	    
	    B a = new B();
	    a.print();
	}
}
