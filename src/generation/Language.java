package generation;

import java.util.ArrayList;
import java.util.List;

public class Language {
	String name;
	String file;
	List<LanguageElement> languageElements = new ArrayList<LanguageElement>();

	public Language(String name, String file) {
		this.name = name;
		this.file = file;
	}

	public String getElement(String elementName) {
		for (LanguageElement e : languageElements) {
			if (e.getElementName().equals(elementName)) {
				return e.getElementValue();
			}
		}
		return "";

	}

	public void AddElement(LanguageElement e) {
		languageElements.add(e);
	}

	public void RemoveElement(LanguageElement e) {
		languageElements.remove(e);
	}

}
