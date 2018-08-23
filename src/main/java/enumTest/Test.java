package enumTest;

public class Test {

	public static void main(String[] args) {
		EnumDay[] eds = EnumDay.values();
		int i = 0;
		for (EnumDay ed : eds) {
			System.out.println("EnumDay["+i+"]:" + ed.toString());
			i++;
		}
		
	    System.out.println("Enum<EnumDay>.compareTo()��" + eds[0].compareTo(eds[1])); // �Ƚϴ�ö����ָ��ö�ٵ�˳�򣬱��Ƚ϶���С�ڡ����ڡ����ڱȽ϶���ʱ���ֱ𷵻ظ�������0����������ֻ�ܺ���ͬö��������Ƚ�
	    System.out.println("Enum<EnumDay>.equals()��" + eds[0].equals(eds[1])); // �Ƚ�ָ������ö�������Ƿ���ͬ
	    System.out.println("Enum<EnumDay>.getDeclaringClass()��" + eds[0].getDeclaringClass()); // ��ȡָ��ö��ֵ��ö�����Ͷ�Ӧ��Class����
	    System.out.println("Enum<EnumDay>.hashCode()��" + eds[0].hashCode()); // ��ȡָ��ö��ֵ��Ӧ��hashCode
	    System.out.println("Enum<EnumDay>.name()��" + eds[0].name()); // ��ȡָ��ö��ֵ������
	    System.out.println("Enum<EnumDay>.ordinal()��" + eds[0].ordinal()); // ��ȡָ��ö��ֵ��Ӧ������
	    
	    test(EnumDay.MONDAY);
	}
	
	public static void test(EnumDay day){
		System.out.println(day);
	}
}
