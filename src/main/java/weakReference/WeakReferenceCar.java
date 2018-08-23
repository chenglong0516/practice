package weakReference;

import java.lang.ref.WeakReference;

public class WeakReferenceCar extends WeakReference<Car>{

	public WeakReferenceCar(Car referent) {
		super(referent);
	}

	public static void main(String[] args) {
		Car car = new Car(2000.0, "red");
		WeakReferenceCar wrc = new WeakReferenceCar(car);
		int i = 0;
        while (true)
        {
            if (wrc.get() != null)
            {
                i++;
                System.out.println("WeakReferenceCar's Car is alive for " + i + ", loop - " + wrc);
            }
            else
            {
                System.out.println("WeakReferenceCar's Car has bean collected");
                break;
            }
        }
	}
}
