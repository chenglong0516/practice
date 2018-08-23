package enumTest;

public class Test {

	public static void main(String[] args) {
		EnumDay[] eds = EnumDay.values();
		int i = 0;
		for (EnumDay ed : eds) {
			System.out.println("EnumDay["+i+"]:" + ed.toString());
			i++;
		}
		
	    System.out.println("Enum<EnumDay>.compareTo()：" + eds[0].compareTo(eds[1])); // 比较此枚举与指定枚举的顺序，被比较对象小于、等于、大于比较对象时，分别返回负整数、0、正整数，只能和相同枚举类型相比较
	    System.out.println("Enum<EnumDay>.equals()：" + eds[0].equals(eds[1])); // 比较指定两个枚举类型是否相同
	    System.out.println("Enum<EnumDay>.getDeclaringClass()：" + eds[0].getDeclaringClass()); // 获取指定枚举值的枚举类型对应的Class对象
	    System.out.println("Enum<EnumDay>.hashCode()：" + eds[0].hashCode()); // 获取指定枚举值对应的hashCode
	    System.out.println("Enum<EnumDay>.name()：" + eds[0].name()); // 获取指定枚举值的名称
	    System.out.println("Enum<EnumDay>.ordinal()：" + eds[0].ordinal()); // 获取指定枚举值对应的序数
	    
	    test(EnumDay.MONDAY);
	}
	
	public static void test(EnumDay day){
		System.out.println(day);
	}
}
