package callback;

public class CallbackTest {

	public static void main(String[] args) {
		Student stu = new Ricky();
		Teacher teacher = new Teacher(stu);
		
		teacher.askQuestion();
	}
}
