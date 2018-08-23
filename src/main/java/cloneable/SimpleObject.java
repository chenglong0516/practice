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
		1、克隆一个对象并不会调用对象的构造方法，因为"Enter SimpleObject.constructor()"语句只出现了一次
		
		2、符合JDK API的clone()方法三条规则
		
		3、so1对于SimpleObject对象str字段的修改再也不会影响到so0了
     */
    
    public static void main(String[] args) throws CloneNotSupportedException {
        SimpleObject so0 = new SimpleObject();
        so0.setStr("111");
        SimpleObject so1 = (SimpleObject)so0.clone();
        
        System.out.println("so0 == so1?" + (so0 == so1));
        System.out.println("so0.getClass() == so1.getClass()?" + (so0.getClass() == so1.getClass()));
        System.out.println("so0.equals(so1)?" + (so0.equals(so1)));
            
        so1.setStr("222");
        System.out.println("so0.getStr()：" + so0.getStr());
        System.out.println("so1.getStr()：" + so1.getStr());
	}
}
