package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
流的操作
接下来，当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
Intermediate 
map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
Terminal 
forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
Short-circuiting 
anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
我们下面看一下 Stream 的比较典型用法。
 * @author chenglong
 */
public class Java8Tester8 {

	public static void main(String[] args) {
		listToMapTest();
	}
	
	//转换大写
	public static void toUpperCaseTest(){
		List<String> strList = new ArrayList<>();
        strList.add("shaochen");
        strList.add("shaohen");
        strList.add("cool");
        strList.add("bean");
        strList.add("java");
        strList.add("java");
		Stream<String> stream = strList.stream();
		List<String> list = stream.map(s -> s.toUpperCase()).collect(Collectors.toList());
		System.out.println(list);
	}
	
	//平方数
	public static void power(){
		List<Integer> nums = Arrays.asList(1,2,3,4);
		List<Integer> list = nums.stream().map(n->n*n).collect(Collectors.toList());
		System.out.println(list);
	}
	
	//一对多
	public static void oneToMany(){
		Stream<List<Integer>> inputStream = Stream.of(
				Arrays.asList(1),
				Arrays.asList(2,3),
				Arrays.asList(4,5,6)
				);
		Stream<Integer> outputStream = inputStream.flatMap(childList->childList.stream());
		List<Integer> list = outputStream.collect(Collectors.toList());
		System.out.println(list);
	}
	
	//留下偶数
	public static void keepEvenNumber(){
		Integer[] sixNums = {1,2,3,4,5,6};
		Integer[] evens = Stream.of(sixNums).filter(n -> n%2==0).toArray(Integer[]::new);
		System.out.println(Arrays.asList(evens));
	}
	
	//打印姓名（包含a）
	public static void printName(){
		List<String> strList = new ArrayList<>();
        strList.add("shaochen");
        strList.add("shaohen");
        strList.add("cool");
        strList.add("bean");
        strList.add("java");
        strList.add("java");
        strList.stream().filter(p->p.contains("a")).forEach(p->System.out.println(p));
	}
	
	//对每个元素执行操作并返回一个新Stream
	public static void test(){
		List<String> list = Stream.of("one", "two", "three", "four")
		.filter(e -> e.length() > 3)
		.peek(e -> System.out.println("Filtered value: " + e))
		.map(String::toUpperCase)
		.peek(e -> System.out.println("Mapped value: " + e))
		.collect(Collectors.toList());
		System.out.println(list);
	}
	
	//reduce用法
	public static void reduceTest(){
		// 字符串连接，concat = "ABCD"
		String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
		// 求最小值，minValue = -3.0
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
		// 求和，sumValue = 10, 有起始值
		int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		// 求和，sumValue = 10, 无起始值
		sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		// 过滤，字符串连接，concat = "ace"
		concat = Stream.of("a", "B", "c", "D", "e", "F").
		 filter(x -> x.compareTo("Z") > 0).
		 reduce("", String::concat);
	}
	
	//list转map
	public static void listToMapTest(){
		List<Student> list = Arrays.asList(new Student(1, 18, "阿龙", GenderColumn.BOY.getCode()),
				new Student(2, 17, "小花", GenderColumn.GIRL.getCode()),
				new Student(3, 17, "阿浪", GenderColumn.LADYBOY.getCode()));
		// value 为对象 student -> student jdk1.8返回当前对象
		Map<Integer, Student> map = list.stream().collect(Collectors.toMap(Student::getId, student -> student));
		// 遍历打印结果
		map.forEach((key, value) -> {
			System.out.println("key: " + key + "    value: " + value);
		});
		// value 为对象中的属性
		Map<Integer, String> map1 = list.stream().collect(Collectors.toMap(Student::getId, Student::getName));
		map1.forEach((key, value) -> {
			System.out.println("key: " + key + "    value: " + value);
		});
	}
}
