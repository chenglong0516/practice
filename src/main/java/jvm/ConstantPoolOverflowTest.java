package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * �������ݣ�������������������Ҳ����˵������ʱ������Ϊ��������һ���֣�
 * 
 * ���������-XX:PermSize=10M -XX:MaxPermSize=10M
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