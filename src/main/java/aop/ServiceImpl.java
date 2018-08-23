package aop;

/*
 * 最原始的写法，我要在调用insert()与update()方法前后分别打印时间，
 * 就只能定义一个新的类包一层，在调用insert()方法与update()方法前后分别处理一下
 * 
 * 这是最原始的写法，这种写法的缺点也是一目了然：
	1 方法调用前后输出时间的逻辑无法复用，如果有别的地方要增加这段逻辑就得再写一遍
	2 如果Dao有其它实现类，那么必须新增一个类去包装该实现类，这将导致类数量不断膨胀
 */
public class ServiceImpl {
	private Dao dao = new DaoImpl();
	
    public void insert() {
        System.out.println("insert()方法开始时间：" + System.currentTimeMillis());
        dao.insert();
        System.out.println("insert()方法结束时间：" + System.currentTimeMillis());
    }
    
    public void delete() {
        dao.delete();
    }
    
    public void update() {
        System.out.println("update()方法开始时间：" + System.currentTimeMillis());
        dao.update();
        System.out.println("update()方法结束时间：" + System.currentTimeMillis());
    }
}
