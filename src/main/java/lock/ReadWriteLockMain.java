package lock;

import java.util.Random;

public class ReadWriteLockMain {
	public static void main(String[] args) {
		final SyncData data = new SyncData();
		
		for (int i = 0; i < 3; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 1; j++) {
						data.set(new Random().nextInt(30));
					}
				}
			});
			t.setName("Thread-W" + i);
			t.start();
		}
		
		for (int i = 0; i < 3; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 1; j++) {
						data.get();
					}
				}
			});
			t.setName("Thread-R" + i);
			t.start();
			
		}
	}
}
