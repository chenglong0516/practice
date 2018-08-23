package callback;

public class Ricky implements Student {

	@Override
	public void resloveQuestion(Callback callback) {
		//结题
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		//回调，告诉老师答案
		callback.tellAnswer(3);
	}

}
