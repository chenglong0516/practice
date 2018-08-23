package callback;

public class Teacher implements Callback {

	private Student student;
	
	public Teacher(Student stu){
		this.student = stu;
	}
	
	public void askQuestion(){
		student.resloveQuestion(this);
	}
	@Override
	public void tellAnswer(int answer) {
		System.out.println("知道了， 你的答案是：" + answer);
	}

}
