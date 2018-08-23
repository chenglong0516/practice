package jvm;

/**
 * 测试内容：栈溢出测试（递归调用导致栈深度不断增加）
 * 
 * 虚拟机参数：-Xss128k
 */
public class StackOverflowTest
{
    private int stackLength = 1;
    
    public void stackLeak()
    {
        stackLength++;
        stackLeak();
    }
    
    public static void main(String[] args) throws Throwable
    {
        StackOverflowTest stackOverflow = new StackOverflowTest();
        try
        {
            stackOverflow.stackLeak();
        }
        catch (Throwable e)
        {
            System.out.println("stack length:" + stackOverflow.stackLength);
            throw e;
        }        
    }
}