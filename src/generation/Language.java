package generation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import databaseControl.ImportBusinessRules;
import domain.Operator;

public class Language {
	String name;
	String location;
	List<LanguageElement> languageElements = new ArrayList<LanguageElement>();

	public Language(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void loadLanguage() {
		try {
			 
			File fXmlFile = new File(location + File.separator + name +".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			ImportBusinessRules ibr = new ImportBusinessRules();
			ArrayList<Operator> operators = (ArrayList<Operator>) ibr.getOperators();
		 
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName(name);
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					LanguageElement langElement = null;
					
					for (Operator operator : operators) {
						langElement = new LanguageElement(
								operator.getName(),
								eElement.getElementsByTagName(operator.getName()).item(0).getTextContent());
						this.addElement(langElement);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getElement(String elementName) {
		for (LanguageElement e : languageElements) {
			if (e.getElementName().equals(elementName)) {
				return e.getElementValue();
			}
		}
		return "";
	}

	public void addElement(LanguageElement e) {
		languageElements.add(e);
	}

	public void removeElement(LanguageElement e) {
		languageElements.remove(e);
	}

}
