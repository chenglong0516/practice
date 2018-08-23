package lock;


public class TicketMain {

	public static void main(String[] args) {
		Ticket ticket = new Ticket(50);
		Thread t1 = new Thread(ticket, "窗口01");
		Thread t2 = new Thread(ticket, "窗口02");
		Thread t3 = new Thread(ticket, "窗口03");
		t1.start();
		t2.start();
		t3.start();
	}
}
