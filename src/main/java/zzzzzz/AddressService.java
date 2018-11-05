package zzzzzz;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AddressService {

	private static volatile AddressService addressService = null;
	
	static Map<String, String> idCard;
	
	public static AddressService of(){
		if(addressService == null || idCard == null){
			synchronized (AddressService.class) {
				if(addressService == null || idCard == null){
					addressService = new AddressService();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					idCard = new HashMap<>();
					idCard.put("aa", "bb");
				}
			}
		}
		return addressService;
	}
	
	public String getIdCard(){
		return idCard.get("aa");
	}
	
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 100, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
		
		for(int i=0; i<50; i++){
			MyTask myTask = new MyTask(i);
			executor.execute(myTask);
		}
		executor.shutdown();
		System.out.println("finish!!!");
	}
}

class MyTask implements Runnable{

	private int taskNum;
	
	public MyTask(int num){
		this.taskNum = num;
	}
	
	@Override
	public void run() {
		String idCard = AddressService.of().getIdCard();
		System.out.println("正在执行task:"+ taskNum + "\t" + idCard);
	}
	
}
