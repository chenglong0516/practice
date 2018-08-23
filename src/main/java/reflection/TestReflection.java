package reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class TestReflection {
	public static void main(String[] args) {
		ArrayList<Person> objects = new ArrayList<>();
		objects.add(new Person("chenglong"));
		for (int i = 0; i < objects.size(); i++) {
			Person temp = objects.get(0);
			try {
				Field field = temp.getClass().getDeclaredField("username");
				field.setAccessible(true);
				field.set(temp, "123qwe");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(objects.get(i).getUsername());
		}
	}
}

class Person{
	private static final String username = "";

	public Person(String username){
		username = username;
	}
	
	public String getUsername() {
		return username;
	}
}