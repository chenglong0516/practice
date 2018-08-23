package threadLocal;

public class MultiThreadDemo  {

	public static class Number{
		private int value = 0;
		
		public void increase() throws InterruptedException{
			int value = 10;
			Thread.sleep(10);
			System.out.println("increase value : " + value);
		}
		
		public void decrease() throws InterruptedException{
			int value = -10;
			Thread.sleep(10);
			System.out.println("decrease value : " + value);
		}
	}
	
	public static void main(String[] args) {
		final Number number = new Number();
		Thread increaseThread  = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					number.increase();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread decreaseThread  = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					number.decrease();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		increaseThread.start();
		decreaseThread.start();
	}
}
