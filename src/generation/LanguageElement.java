package generation;

public class LanguageElement {
	String elementName;
	String elementValue;
	
	public LanguageElement(){
		
	}
	
	public LanguageElement(String elementName, String elementValue) {
		this.elementName = elementName;
		this.elementValue = elementValue;
	}
	protected String getElementValue() {
		return elementValue;
	}
	protected void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}
	protected String getElementName() {
		return elementName;
	}
	protected void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
}
