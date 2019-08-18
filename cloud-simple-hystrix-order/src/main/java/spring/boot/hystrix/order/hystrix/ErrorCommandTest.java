package spring.boot.hystrix.order.hystrix;

public class ErrorCommandTest {
	public static void main(String[] args) {
		ErrorCommand errorCommand = new ErrorCommand();
		System.out.println(errorCommand.execute());
	}
}
