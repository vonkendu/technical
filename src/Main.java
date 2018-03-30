import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory(); //memory before execution
		long startTime = System.currentTimeMillis(); // time before start
	
		Items a = new Items(args[0].toLowerCase());
		ItemDetails inf = new ItemDetails(a.allValues);
		inf.getAllItems();
		int number_of_requests = 36 + inf.items.size(); // we loop over entire 36 pages of items and than we send request to every url that matches our keyword
		int amount_of_products = inf.items.size(); 
		XmlLoad xml = new XmlLoad(inf.items);
		
		
		
		long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory(); //memory after execution
		  long stopTime = System.currentTimeMillis(); // time after stop
	      long elapsedTime = stopTime - startTime; //tipe passed
	
	      System.out.println("Number of http requests: " + number_of_requests);
		System.out.println("Amount of products: " + amount_of_products);
		System.out.println("Amount of memory used: " + usedMemoryAfter/1000000 + "MB");
		System.out.println("Amount of time used: " + elapsedTime + "ms");
		
	}

}
