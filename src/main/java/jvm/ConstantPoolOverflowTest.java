package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试内容：常量池溢出（这个例子也可以说明运行时常量池为方法区的一部分）
 * 
 * 虚拟机参数-XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class ConstantPoolOverflowTest
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true)
        {
            list.add(String.valueOf(i++).intern());
        }
    }
}