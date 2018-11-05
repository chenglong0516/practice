package lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 使用 Lambdas 排序集合
 * @author chenglong
 */
public class Java8Tester5 {

	static String[] players = {"Rafael Nadal", "Novak Djokovic",   
		    "Stanislas Wawrinka", "David Ferrer",  
		    "Roger Federer", "Andy Murray",  
		    "Tomas Berdych", "Juan Martin Del Potro",  
		    "Richard Gasquet", "John Isner"};  
	
	public static void main(String[] args) {
	
		method4();
		
		for (String string : players) {
			System.out.println(string);
		}
		
	}

	private static void method1() {
		//使用匿名内部类根据name排序
		Arrays.sort(players, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o1.compareTo(o2));
			}
		});
	}
	
	private static void method2(){
		//使用lambda排序
		Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
		Arrays.sort(players, sortByName);
	}
	
	private static void method3(){
		//根据姓氏排序（第二个词）
		Arrays.sort(players, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" "))));
			}
		});
	}
	
	private static void method4(){
		Comparator<String> sortBySurname = (String s1, String s2) ->
			(s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" "))));
		Arrays.sort(players, sortBySurname);
	}
}
