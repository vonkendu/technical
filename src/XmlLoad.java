import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlLoad {

	public XmlLoad(ArrayList<CatalogueItem> items) {

	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Catalogue");
		doc.appendChild(rootElement);
		for (int i = 0; i<items.size(); i++) {
		// staff elements
		Element staff = doc.createElement("Offer");
		rootElement.appendChild(staff);
	
		Element brand_name = doc.createElement("brand");
		brand_name.appendChild(doc.createTextNode(items.get(i).brand));
		staff.appendChild(brand_name);


		Element product_name = doc.createElement("name");
		product_name.appendChild(doc.createTextNode(items.get(i).name));
		staff.appendChild(product_name);

		Element intitial_price = doc.createElement("intial_price");
		intitial_price.appendChild(doc.createTextNode(items.get(i).initial_price));
		staff.appendChild(intitial_price);
		
		Element final_price = doc.createElement("final_price");
		final_price.appendChild(doc.createTextNode(items.get(i).price));
		staff.appendChild(final_price);
		
		Element article = doc.createElement("article");
		article.appendChild(doc.createTextNode(items.get(i).article));
		staff.appendChild(article);
		}
	
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\file.xml"));

		transformer.transform(source, result);

		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}