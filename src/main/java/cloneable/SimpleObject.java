package cloneable;

public class SimpleObject implements Cloneable {
private String str;
    
    public SimpleObject()
    {
        System.out.println("Enter SimpleObject.constructor()");
    }

    public String getStr()
    {
        return str;
    }

    public void setStr(String str)
    {
        this.str = str;
    }
    
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
    /**
		1����¡һ�����󲢲�����ö���Ĺ��췽������Ϊ"Enter SimpleObject.constructor()"���ֻ������һ��
		
		2������JDK API��clone()������������
		
		3��so1����SimpleObject����str�ֶε��޸���Ҳ����Ӱ�쵽so0��
     */
    
    public static void main(String[] args) throws CloneNotSupportedException {
        SimpleObject so0 = new SimpleObject();
        so0.setStr("111");
        SimpleObject so1 = (SimpleObject)so0.clone();
        
        System.out.println("so0 == so1?" + (so0 == so1));
        System.out.println("so0.getClass() == so1.getClass()?" + (so0.getClass() == so1.getClass()));
        System.out.println("so0.equals(so1)?" + (so0.equals(so1)));
            
        so1.setStr("222");
        System.out.println("so0.getStr()��" + so0.getStr());
        System.out.println("so1.getStr()��" + so1.getStr());
	}
}
