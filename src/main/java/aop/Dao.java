package aop;

//有一个接口Dao有insert、delete、update三个方法，在insert与update被调用的前后，打印调用前的毫秒数与调用后的毫秒数
public interface Dao {

	public void insert();
	
	public void delete();
	
	public void update();
}
