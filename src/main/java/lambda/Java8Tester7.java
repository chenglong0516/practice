package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 关于jdk1.8的Stream流的一些使用总结
 * stream流主要对集合进行操作，在真正的项目中有许多操作结合这样的场景，比如说
 * 我们从数据库查询出来的数据，需要做一层过滤，再比如所，我们要在结果集里对数据
 * 进行操作等，这写都需要我们做循环，再筛选，再进行操作，其实这并不难，但这些都
 * 给我们增加了大量的代码量，如果用stream流，用一行代码就可以解决我们的需求！
 * @author chenglong
 */
public class Java8Tester7 {

	public static void main(String[] args) {
		List<String> strList = new ArrayList<>();
        strList.add("shaochen");
        strList.add("shaohen");
        strList.add("cool");
        strList.add("bean");
        strList.add("java");
        strList.add("java");
        
        //首先我们做一个简单的循环遍历
        //获取stream流对象
        Stream<String> stream = strList.stream();
        stream.forEach((s)->System.out.println(s));
        stream.close();
        
        //获取集合的长度
        stream = strList.stream();
        System.out.println(stream.count());
        stream.close();
        
        //处理后的数据收集成一个集合
        stream = strList.stream();
//        List<String> list = stream.distinct().collect(Collectors.toList());
        List<String> list = stream.filter(s -> !s.equals("java")).collect(Collectors.toList());
        System.out.println(list.toString());
        stream.close();
        
        //map的使用
        stream = strList.stream();
        // 将集合中的数据循环变为大写字母，然后过滤，找到包含A的数据，循环打印出来
	    stream.map(s->s.toUpperCase()).forEach(System.out::println);
//	    stream.map(s->s.toUpperCase()).filter(s->s.contains("A")).forEach(System.out::println);
	    stream.close();
	    
	    //分页操作
        stream = strList.stream();
        stream.skip(2).limit(2).forEach(s->System.out.println(s));
	    stream.close();
	    
	    //stream流中匹配条件
//	    stream流中有两个匹配的接口，anyMatch和allMatch； 
//	    anyMatch: 只要有一条数据可以匹配，那就会返回true 
//	    allMatch： 必须要每一条数据都匹配才会返回true 
//	         由于用法都是一样的，这里就只用anyMatch作为示例
        stream = strList.stream();
        if(stream.anyMatch(s->s.contains("a"))){
        	System.out.println("匹配a");
        }
	    stream.close();
	    
	    //匹配多条数据
	    stream = strList.stream();
	    Predicate<String> predicateOne = s->s.contains("a");
	    Predicate<String> predicateTwo = s->s.contains("b");
	    if(stream.anyMatch(predicateOne.and(predicateTwo))){
	    	System.out.println("匹配 a b");
	    }
	}
	
}
