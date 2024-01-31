package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("ENTER CLIENT DATA:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Birth date (DD/MM/YYYY): ");
		Date birthDate = sdf1.parse(sc.next());
		Client client = new Client(name, email, birthDate);
		System.out.println();
		
		System.out.println("ENTER ORDER DATA:");
		System.out.print("Status: ");
		String status = sc.next();
		System.out.println();
		Order order = new Order(new Date(), OrderStatus.valueOf(status), client);
		
		System.out.print("How many items to this order? ");
		int n = sc.nextInt();
		sc.nextLine();
		
		for(int i = 0; i < n; i++) {
			System.out.println("ENTER #" + (i+1) + " ITEM DATA:");
			System.out.print("Product name:" );
			String itemName = sc.nextLine();
			System.out.print("Product price: ");
			Double price = sc.nextDouble();
			System.out.print("Quantity: ");
			Integer quantity = sc.nextInt();
			sc.nextLine();
			OrderItem item = new OrderItem(quantity, price, new Product(itemName, price));		
			order.addItem(item);
		}
		
		System.out.println("ORDER SUMMARY:");
		System.out.println("Order moment: " + sdf.format(order.getMoment()));
		System.out.println("Order status: " + order.getStatus());
		System.out.println("Client: " + client.getName() + " - " + client.getEmail());
		System.out.println("ORDER ITEMS:");
		for(OrderItem c: order.getOrderItem()) {
			System.out.println(c.getProduct().getName() +
					", $" + c.getPrice() +
					", quantity: " + c.getQuantity() + 
					", subtotal: $" + c.subTotal()	);			
		}
		System.out.printf("Total price: $" + String.format("%.2f", order.total()));
 

		sc.close();
	}

}
