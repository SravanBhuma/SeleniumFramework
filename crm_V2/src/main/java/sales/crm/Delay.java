package sales.crm;

public class Delay {
	public static void delay(int millisecond)
	{
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
