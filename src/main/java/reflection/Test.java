package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) throws Exception {
//		TestClass();
//		TestPackage();
//		TestField();
//		TestConstructor();
		TestMethod();
	}

	/*
	 * �ʹ�java.lang.Class��ص�Class��ClassLoader����
	 */
	private static void TestClass() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Class<?> c = Class.forName("reflection.Reflection");
		Reflection[] rs = new Reflection[2];
		
	    System.out.println("Class.getClass()��" + c.getClass()); // ��ȡjava.lang.Class��Class����
	    System.out.println("Class.getClassLoader()��" + c.getClassLoader()); // ��ȡ��ļ�����
	    System.out.println("Class.getSuperclass()��" + c.getSuperclass()); // ��ȡ����Class����
	    System.out.println("Class.getInterfaces()��" + c.getInterfaces()[0] + ", " + c.getInterfaces()[1]); // ��ȡ��Ľӿ��б�ע�ⷵ�ص���һ������
	    System.out.println("Class.getComponentType()��" + rs.getClass().getComponentType()); // ��ȡ�������Class����
	        
	    Reflection r = (Reflection)c.newInstance(); // ����Classʵ������һ����ʵ����,Ĭ�ϵ����޲ι��췽��
		System.out.println("Class.newInstance():" + r);
	}
	
	/*
	 * Package��������й�Java����ʵ�ֺ͹淶�İ汾��Ϣ��
	 */
	private static void TestPackage() throws Exception{
		Class<?> c = Class.forName("reflection.Reflection");
		Package p = c.getPackage();
		
	    System.out.println("Package.toString()��" + p.toString()); //toString()
	    System.out.println("Package.getName()��" + p.getName());     // ��ȡ����    
	    System.out.println("Package.getImplementationTitle()��" + p.getImplementationTitle()); // ��ȡ������
	    System.out.println("Package.getImplementationVendor()��" + p.getImplementationVendor()); // ��ȡ�ṩ��ʵ�ֵ���֯����Ӧ�̻�˾������
	    System.out.println("Package.getImplementationVersion()��" + p.getImplementationVersion()); // ��ȡ��ʵ�ֵİ汾
	    System.out.println("Package.isSealed()��" + p.isSealed()); // ��ȡ���Ƿ��ܷ��
	}
	
	/*
	 * �ṩ�й����ӿڵĵ����ֶε���Ϣ���Լ������Ķ�̬����Ȩ�ޡ�
	 */
	private static void TestField() throws Exception{
		Class<?> c = Class.forName("reflection.Reflection");
	    Reflection r = new Reflection();
	    Field f0 = c.getField("b"); 
	    Field f1 = c.getDeclaredField("d");
	    Field[] fs0 = c.getFields();
	    Field[] fs1 = c.getDeclaredFields();
	        
	    System.out.print("Class.getFields()��"); // ��ȡ��������public�ֶ�,˳��public��Field�����˳��
	    for (Field f : fs0)
	        System.out.print(f + "\t");
	    
	    System.out.println();
	    System.out.print("Class.getDeclaredFields()��"); // ��ȡ�����������Ȩ�޵��ֶ�,˳������Field�����˳��
	    for (Field f : fs1)
	        System.out.print(f + "\t");
	        
	    System.out.println();
	    System.out.println("Class.getField(String name)��" + f0); // ����name��ȡ����һ������Ȩ��Ϊpublic���ֶ�
	    System.out.println("Class.getDeclaredField(String name)��" + f1); // ����name��ȡ����һ���������Ȩ�޵��ֶ�
	    
	    System.out.println();
	    System.out.println("Field.getName()��" + f0.getName()); // ��ȡ�ֶ���
	    System.out.println("Field.getType()��" + f0.getType()); // ��ȡ�������
	    System.out.println("Field.getBoolean()��" + f0.getBoolean(r));    // ��ȡĳ��ʵ�������Field��ֵ��ʲô���͵�Field����getXXX(Object obj)
	    System.out.println("Field.getModifiers()��" + f0.getModifiers()); // ��������ʽ���ش�Field�����Java�������η�����public��static��final��
	    System.out.println("Field.isAccessible()��" + f0.isAccessible()); // ����Field�ķ���Ȩ�ޣ���private��Field��ֵ������Ҫ��accessible����Ϊtrue������
	        
	    System.out.println();
	    f1.setAccessible(true);
	    System.out.println("Before setB()��" + r);
	    f1.setDouble(r, 1.1);
	    System.out.println("After setB()��" + r); // ������ָ��Field�趨ֵ
	}
	
	/*
	 * �ṩ������ĵ������췽������Ϣ�Լ������ķ���Ȩ��
	 */
	private static void TestConstructor() throws Exception{
		Class<?> c = Class.forName("reflection.Reflection");
		Constructor<?> constructor = c.getConstructor(String.class);
		Constructor<?>[] constructors = c.getConstructors(); 
	    System.out.println("Class.getConstructor(Class<?>... parameterTypes)��" + constructor); // ��ȡָ�������б�Ĺ��캯��
	    System.out.print("Class.getConstructors()��"); // ��ȡ���еĹ��캯��
	    for (Constructor<?> con : constructors)
	        System.out.print(con + "\t");
	    System.out.println("\n");
	    
	    System.out.println("Constructor.getName()��" + constructor.getName()); // ��ȡ���캯����,ûʲô����,�϶��Ǻ���ͬ��
	    System.out.println("Constructor.getModifiers()��" + constructor.getModifiers()); // ��ȡ��������ʽ���صĴ�Constructor�����Java�������η�����public��static��final��
	    System.out.println("Constructor.isAccessible()��" + constructor.isAccessible()); // ��ȡ��Constructor�ķ���Ȩ��
	    System.out.println("Constructor.getParameterTypes()��" + constructor.getParameterTypes()[0]); // ��ȡConstructor�Ĳ������ͣ��Ǹ�����
	    System.out.println("Constructor.isVarArgs()��" + constructor.isVarArgs()); // ��ȡ��Constructor���Ƿ���˿ɱ������Ĳ�����������"String... str"���͵Ĳ���
	        
	    System.out.println();
	    Reflection r = (Reflection)constructor.newInstance("123"); // ����ָ���Ĺ��췽��ʵ������һ�����ʵ����,��Ҫ
	    System.out.println("Constructor.newInstance()��" + r);
	}
	
	/*
	 * �ṩ�������ӿ��ϵ���ĳ���������Լ���η��ʸ÷���������Ϣ������ӳ�ķ����������෽����ʵ������
	 */
	private static void TestMethod() throws Exception{
		Reflection r = new Reflection();
	    Class<?> c = Class.forName("reflection.Reflection");
	    Method md0 = c.getMethod("publicMethod", int.class, double.class, List.class);
	    Method md1 = c.getDeclaredMethod("privateMethod", new Class[0]);
	    Method[] ms0 = c.getMethods();
	    Method[] ms1 = c.getDeclaredMethods();
	        
	    System.out.println("Method.getMethod()��" + md0); // ���ݷ������Ͳ����б��ȡָ����public����
	    System.out.println("Method.getDeclaredMethod()��" + md1); // ���ݷ������Ͳ����б��ȡָ�����������Ȩ�޵ķ���,���������̳еķ���
	        
	    System.out.print("Method.getMethods()��"); // ��ȡ��������丸�������е�public����
	    for (Method m : ms0)
	        System.out.print(m + "\t");
	    System.out.println();
	        
	    System.out.print("Method.getDeclaredMethods()��"); // ���ش��������еķ���(�޷���Ȩ������),���������̳еķ���
	    for (Method m : ms1)
	        System.out.print(m + "\t");
	    System.out.println("\n");
	        
	    System.out.println("Method.getName()��" + md0.getName()); // ��ȡ����������
	    //ΪʲôAccessible���Ի����false ?���ҵ���false �˻���ִ�У�������ΪAccessible 
	    //�����Բ����������﷨�㼶���ķ���Ȩ�ޣ�����ָ�Ƿ�����׻�ã��Ƿ���а�ȫ��ˡ�
	    System.out.println("Method.isAccessible()��" + md0.isAccessible()); // ��ȡ�����ķ�������
	    System.out.println("Method.isVarArgs()��" + md0.isVarArgs()); // ��ȡ�����Ƿ���пɱ������Ĳ���
	    System.out.println("Method.getReturnType()��" + md0.getReturnType()); // ��ȡ�����ķ�������
	    System.out.println("Method.getParameterTypes()��" + md0.getParameterTypes()[0] + ", " + md0.getParameterTypes()[1] + ", " + md0.getParameterTypes()[2]); // ��ȡ�����Ĳ������ͣ�������ʽ��ע��һ�º�����ķ���������
	    System.out.println("Method.getGenericParameterTypes()��" + md0.getGenericParameterTypes()[0] + ", " + md0.getGenericParameterTypes()[1] + ", " + md0.getGenericParameterTypes()[2]); // ��ȡ�����Ĳ������������ͣ����ͣ�������ʽ
	        
	    System.out.println();
	    System.out.println(md0.invoke(r, 1, 2.2, new ArrayList<String>())); // ������÷�������Ҫ
	}
}
