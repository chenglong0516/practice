package umlTest;

/*
 * 基本类
 */
public class Person {

	private String name = "Jack";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	protected void playBasketball(){
		pass();
	}
	
	private void pass(){
		
	}
}
