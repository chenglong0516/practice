package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
   https://www.cnblogs.com/aoeiuv/p/5911692.html
 * @author chenglong
 */
public class Java8Tester9 {

	private static void test0() {
		//创建线程
		Thread td = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("hello,,,");
			}
		});
		td.start();
		
		//lambda
		Thread td1 = new Thread(()->System.out.println("hello, lambda!"));
		td1.start();
		
		//排序
		List<String> list = Arrays.asList(new String[]{"b","c","a"});
//		Collections.sort(list, new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				return o1.compareTo(o2);
//			}
//		});
		//lambda
		Collections.sort(list, (s1, s2)->s1.compareTo(s2));
		System.out.println(list);
		
		//lambda配合stream
		List<String> list1 = Arrays.asList(new String[]{"Ni","Hao","lambda"});
		List<String> lowercaseNames = new ArrayList<String>();
		for (String string : list1) {
			lowercaseNames.add(string.toLowerCase());
		}
		List<String> lowercaseNames1 = list1.stream().map(name-> name.toLowerCase()).collect(Collectors.toList());
		System.out.println(lowercaseNames1);
		
		List<String> collected = Stream.of("a", "b", "hello").map(a -> a.toUpperCase()).collect(Collectors.toList());
		System.out.println(collected);
		
		String result = Stream.of("a", "b", "hello").min((o1,o2) -> o1.compareTo(o2)).get();
//		String result = Stream.of("a", "b", "hello").min(new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				return o2.compareTo(o1);
//			}
//		}).get();
		System.out.println(result);	
		
		//将集合中的某一个属性拼接为一个字符串
		List<Student> list2 = Arrays.asList(new Student(1, 18, "阿龙", GenderColumn.BOY.getCode()),
				new Student(2, 17, "小花", GenderColumn.GIRL.getCode()),
				new Student(3, 17, "阿浪", GenderColumn.LADYBOY.getCode()));
		String result2 =
				list2.stream()
				.map(Student::getName)
				.collect(Collectors.joining(", ", "[", "]"));
		System.out.println(result2);
		
		//将集合分组收集（按照年龄分组）
//		Map<Integer, List<Student>> collect = list2.stream().collect(Collectors.groupingBy(Student::getAge,
//				Collectors.toList()));
		Map<Integer, List<String>> collect = list2.stream().collect(Collectors.groupingBy(Student::getAge,
				Collectors.mapping(Student::getName, Collectors.toList())));
		System.out.println(collect);
	}
	
	/*
	重构代码
	假定选定一组专辑，找出其中所有长度大于 1 分钟的曲目名称。例 3-19 是遗留代码，首先
	初始化一个 Set 对象，用来保存找到的曲目名称。然后使用 for 循环遍历所有专辑，每次
	循环中再使用一个 for 循环遍历每张专辑上的每首曲目，检查其长度是否大于 60 秒，如
	果是，则将该曲目名称加入 Set 对象。
		//原风格
		public Set<String> findLongTracks(List<Album> albums) {
			Set<String> trackNames = new HashSet<>();
			for(Album album : albums) {
				for (Track track : album.getTrackList()) {
					if (track.getLength() > 60) {
						String name = track.getName();
						trackNames.add(name);
					}
				}
			}
			return trackNames;
		}
		//lambda + Stream流风格
		public Set<String> findLongTracks(List<Album> albums) {
			return albums.stream()
			.flatMap(album -> album.getTracks())
			.filter(track -> track.getLength() > 60)
			.map(track -> track.getName())
			.collect(toSet());
		}
	 */
	public static void test1(){
		Stream<Double> randoms = Stream.generate(Math::random).limit(100);
		randoms.map(String::valueOf).forEach(System.out::println);
//		randoms.forEach(s-> System.out.println(s));
	}
	
	public static Stream<Character> characterStream(String input){
		List<Character> result = new ArrayList<>();
		for (char c : input.toCharArray()) {
			result.add(c);
		}
		return result.stream();
		
	}
	public static void test2(){
		Stream<Character> concat = Stream.concat(characterStream("hello"), characterStream("world"));
		concat.map(String::valueOf).forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		test6();
	}
	
	public static void test3(){
		Stream<String> of = Stream.of("a","a","c","e","d","b");
		of.distinct().sorted((a,b)-> b.compareTo(a)).forEach(System.out::println);
	}
	
	public static void test4(){
		Stream<String> of = Stream.of("a","a","c","e","d","b");
		long count = of.count();
		System.out.println(count);
	}
	
	public static void test5(){
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		Integer reduce = list.stream().reduce(1, (a,b) -> a*b);
		System.out.println("sum is " + reduce);
	}
	
	public static void test6(){
		//构造流
		Stream<String> stream = Stream.of("hello","world");
//		TreeSet<String> collect = stream.collect(Collectors.toCollection(TreeSet::new));
//		collect.forEach(System.out::println);
		
		String collect = stream.collect(Collectors.joining());
		System.out.println(collect);
		
		// generate()方法
		Stream<String> stream1 = Stream.generate(UUID.randomUUID()::toString);
		stream1.findFirst().ifPresent(System.out::println);
		// iterate方法
		Stream.iterate(1, item -> item + 2).limit(6).forEach(System.out::println);

	}
	
	public static void test7(){
		
	}
	
	
}
