package aop;

/*
 * 接着我们使用上设计模式，先用装饰器模式，看看能解决多少问题。
 * 装饰器模式的核心就是实现Dao接口并持有Dao接口的引用，我将新增的类命名为LogDao
 * 
 * 在使用的时候，可以使用"Dao dao = new LogDao(new DaoImpl())"的方式
 * 
 *  这种方式的优点为：
	透明，对调用方来说，它只知道Dao，而不知道加上了日志功能
	类不会无限膨胀，如果Dao的其它实现类需要输出日志，只需要向LogDao的构造函数中传入不同的Dao实现类即可
	
	这种方式同样有明显的缺点，缺点为：
	输出日志的逻辑还是无法复用
	输出日志的逻辑与代码有耦合，如果我要对delete()方法前后同样输出时间，需要修改LogDao
	但是，这种做法相比最原始的代码写法，已经有了很大的改进。
 */
public class LogDao implements Dao{

	private Dao dao;
	
	public LogDao(Dao dao){
		this.dao = dao;
	}
	
	@Override
	public void insert() {
		System.out.println("insert()方法开始时间：" + System.currentTimeMillis());
		dao.insert();
		System.out.println("insert()方法结束时间：" + System.currentTimeMillis());
	}

    @Override
    public void delete() {
        dao.delete();
    }

    @Override
    public void update() {
        System.out.println("update()方法开始时间：" + System.currentTimeMillis());
        dao.update();
        System.out.println("update()方法结束时间：" + System.currentTimeMillis());
    }

}
