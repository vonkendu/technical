import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.*;

public class Items { // class that provides all possible urls with a search word in it
	public ArrayList<String> allValues = new ArrayList<String>();
	URL website;
	BufferedReader in;

	public Items(String searchWord) throws IOException {

		for (int i = 0; i < 36; i++) { // we have 36 pages in our catalogue (this needs to be changed)

			try {
				website = new URL("https://www.aboutyou.de/frauen/bekleidung/?page=" + (i + 1)); // this search works
																									// only for women's
																									// clothing atm
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(website.openStream(), StandardCharsets.UTF_8));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {

				while (inputLine.contains("<a class=\"anchor_wgmchy\"")) {

					int begin = inputLine.indexOf("<a class=\"anchor_wgmchy\"") + 31;

					String first_tempString = inputLine.substring(begin);
					int end = first_tempString.indexOf(">") - 21;

					String result = inputLine.substring(begin, begin + end);

					if (result.contains(searchWord)) { //we are checking every item if it ha our search word in its url

						if (!allValues.contains(result)) {
							allValues.add(result);
							
						}
					}

					inputLine = inputLine.substring(begin + result.length() + 50);

				}

			}

		}

	}

	
}