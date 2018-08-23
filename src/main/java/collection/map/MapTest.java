package collection.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapTest {

	/*
	 * 11.如果两个键的hashcode相同，你如何获取值对象？
当我们调用get()方法，HashMap会使用键对象的hashcode找到bucket位置，然后获取值对象。面试官提醒他如果有两个值对象储存在同一个bucket，他给出答案:将会遍历链表直到找到值对象。面试官会问因为你并没有值对象去比较，你是如何确定确定找到值对象的？除非面试者直到HashMap在链表中存储的是键值对，否则他们不可能回答出这一题。
其中一些记得这个重要知识点的面试者会说，找到bucket位置之后，会调用keys.equals()方法去找到链表中正确的节点，最终找到要找的值对象。完美的答案！
许多情况下，面试者会在这个环节中出错，因为他们混淆了hashCode()和equals()方法。因为在此之前hashCode()屡屡出现，而equals()方法仅仅在获取值对象的时候才出现。一些优秀的开发者会指出使用不可变的、声明作final的对象，并且采用合适的equals()和hashCode()方法的话，将会减少碰撞的发生，提高效率。不可变性使得能够缓存不同键的hashcode，这将提高整个获取对象的速度，使用String，Interger这样的wrapper类作为键是非常好的选择。

作者：YitaiCloud
链接：https://www.jianshu.com/p/3609b6cca0f3
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
	 */
	public static void main(String[] args) {
		test3();
		
	}

	private static void test1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		map.put("key5", "value5");
		
		Set<String> keySet = map.keySet();
		
		System.out.println("迭代器");
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()){
			if("key1".equals(iterator.next())){
				iterator.remove();
			}
		}
		System.out.println("增强for循环");
		for (String string : keySet) {
			System.out.println(string);
		}
	}
	
	private static void test2() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "value1");
		map.put("key2", "value1");
		map.put("key3", "value2");
		map.put("key4", "value2");
		map.put("key5", "value5");
		
		Collection<Object> values = map.values();
		
		System.out.println("增强for循环");
		for (Object string : values) {
			System.out.println(string);
		}
	}
	
	private static void test3() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "value1");
		map.put("key2", "value1");
		map.put("key3", "value2");
		map.put("key4", "value2");
		map.put("key5", "value5");
		
		Set<Entry<String,Object>> entrySet = map.entrySet();
		
		System.out.println("增强for循环");
		for (Entry<String, Object> entry : entrySet) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
}
