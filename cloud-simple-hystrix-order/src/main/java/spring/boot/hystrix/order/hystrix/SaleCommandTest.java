package spring.boot.hystrix.order.hystrix;

public class SaleCommandTest {
	public static void main(String[] args) {
		SaleCommand saleCommand = new SaleCommand();
		System.out.println(saleCommand.execute());
	}
}
