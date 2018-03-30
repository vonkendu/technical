import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ItemDetails { //class that provides details of every single item
	ArrayList<CatalogueItem> items = new ArrayList<CatalogueItem>();
	ArrayList<String> urls;

	public ItemDetails(ArrayList<String> urls) {
		this.urls = urls;
	}

	public void getAllItems() throws IOException {

		for (int n = 0; n < urls.size(); n++) {
			URL oracle = new URL("https://www.aboutyou.de" + urls.get(n));
			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
			String inputLine;
			CatalogueItem item;
			String name = "";
			String brand = "";
			String final_price = "";
			String article = "";
			String initial_price = "";
			while ((inputLine = in.readLine()) != null) {

				if (name.equals("")) {
					name = getName(inputLine);
				//	System.out.println(name);
				}
				if (brand.equals("")) {
					brand = getBrand(inputLine);
				//	System.out.println(brand);
				}
				if (final_price.equals("")) {
				final_price = getFinalPrice(inputLine);
				//	System.out.println(price);
				}
				if (initial_price.equals("")) { //if we don't have an original and final price, that mwans that the original one is the same as final
					initial_price = getInitialPrice(inputLine);
					if (initial_price.equals("")) {
							initial_price= final_price;}
				//	System.out.println(price);
				}
				if (article.equals("")) {
					article = getArtikle(inputLine);
				//	System.out.println(article);
				}

			

			if (name != "" && brand != "" && final_price != "" && article != "" && initial_price!="") {

				item = new CatalogueItem(name, brand, final_price, initial_price ,article);
				items.add(item);
				break;
			}
		}

		in.close();

	}

	}

	public String getBrand(String inputLine) {
		String brand = "";

		if (inputLine.contains("h1 class=\"productName_192josg\"")) { // reading the brand and the name of a product
			int brand_name_begin_index = inputLine.indexOf("h1 class=\"productName_192josg\"");
			// System.out.println(brand_name_begin_index);
			String first_tempString = inputLine.substring(brand_name_begin_index);
			int brand_name_end_index = first_tempString.indexOf('|');
			// System.out.println(brand_name_end_index);
			String brand_substr = first_tempString.substring(50, brand_name_end_index - 1);
			brand = brand_substr;

		}

		return brand;

	}

	public String getName(String inputLine) {
		String name = "";
		if (inputLine.contains("h1 class=\"productName_192josg\"")) { // reading the brand and the name of a product
			int brand_name_begin_index = inputLine.indexOf("h1 class=\"productName_192josg\"");

			String first_tempString = inputLine.substring(brand_name_begin_index);
			int brand_name_end_index = first_tempString.indexOf('|');

			int end_product_name_index = first_tempString.indexOf("</h1>");
			String name_substr = first_tempString.substring(brand_name_end_index + 2, end_product_name_index);
			if (name_substr.contains("&#x27;")) {
				name_substr = name_substr.replaceAll("&#x27;", "'");
			}
			name = name_substr;

		}

		return name;
	}

	public String getFinalPrice(String inputLine) {
		String price = "";
		if (inputLine.contains("<span class=\"finalPrice_klth9m\"")) {
			int final_price_begin_index = inputLine.indexOf("<span class=\"finalPrice_klth9m\"");

			String temp_price_string = inputLine.substring(final_price_begin_index);
			if (temp_price_string.contains("ab</span>")) { // case when a price begins with an "ab"
				
		
				final_price_begin_index = final_price_begin_index + 177; 
				temp_price_string = inputLine.substring(final_price_begin_index); //this is how we cut off the "ab" part
				int final_price_end_index = temp_price_string.indexOf("</span>");
				String final_price = temp_price_string.substring(0, final_price_end_index-3);
				return "ab " + final_price;
			
			}
			
			int final_price_end_index = temp_price_string.indexOf("</span>");

			String final_price = temp_price_string.substring(51, final_price_end_index - 3);
			price = final_price;

		}
		
		if (inputLine.contains("<span class=\"finalPrice_klth9m-o_O-highlight_1t1mqn4")) { // final price after discount
		
		
			int final_price_begin_index = inputLine.indexOf("<span class=\"finalPrice_klth9m-o_O-highlight_1t1mqn4");

			String temp_price_string = inputLine.substring(final_price_begin_index);
			
			
			int final_price_end_index = temp_price_string.indexOf("</span>");

			String final_price = temp_price_string.substring(73, final_price_end_index - 3);
			price = final_price;
			
				}
		
		
		return price;
	}
	
	public String getInitialPrice(String inputLine) {
		String initial_price = "";
	
		if (inputLine.contains("<span class=\"originalPrice_17gsomb-o_O-strikeOut_32pxry")) {
		int original_price = inputLine.indexOf("<span class=\"originalPrice_17gsomb-o_O-strikeOut_32pxry"); // if the price was with discoutn 
	
		
		String temp_price_string = inputLine.substring(original_price);
		
		
		int initial_price_end_index = temp_price_string.indexOf("</span>");

		 initial_price = temp_price_string.substring(76, initial_price_end_index - 3);
	}
		
		return initial_price;
		
		
	}

	public String getArtikle(String inputLine) {
		String article = "";
		if (inputLine.contains("Artikel-Nr")) {
			int article_begin = inputLine.indexOf("Artikel-Nr");
			String temp_article_string = inputLine.substring(article_begin);
			int article_end = temp_article_string.indexOf("!");
			String article_result = temp_article_string.substring(13, article_end - 1);
			article = article_result;
			

		}
		return article;
	}

}
