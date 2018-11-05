package lambda;

public class Student {
	private int id;
	private int age;
	private String name;
	private String sexCode;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	public Student(int num, int age, String name, String sexCode) {
		super();
		this.id = num;
		this.age = age;
		this.name = name;
		this.sexCode = sexCode;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", age=" + age + ", name=" + name
				+ ", sexCode=" + sexCode + "]";
	}

	
}
