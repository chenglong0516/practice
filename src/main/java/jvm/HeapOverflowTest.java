package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试内容：堆溢出
 *
 * 虚拟机参数：-Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOverflowTest
{
    public static void main(String[] args)
    {
        List<HeapOverflowTest> list = new ArrayList<HeapOverflowTest>();
        while (true)
        {
            list.add(new HeapOverflowTest());
        }
    }
}