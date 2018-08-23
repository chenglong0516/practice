package collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("1");
		list.add("2");
		
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			if("1".equals(iterator.next())){
				iterator.remove();
			}
		}
		for (String string : list) {
			System.out.println(string);
		}
	}
	
	
}
