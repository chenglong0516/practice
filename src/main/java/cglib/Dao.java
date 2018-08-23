package cglib;

public class Dao {

	public Dao(){
		update();
	}
	
	public void update(){
		System.out.println("peopleDao.update()");
	}
	
	public void select(){
		System.out.println("peopleDao.select()");
	}
}
