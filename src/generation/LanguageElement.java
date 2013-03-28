package generation;

public class LanguageElement {
	String elementValue;
	String elementName;
	
	public LanguageElement(){
		
	}
	
	public LanguageElement(String elementValue, String elementName) {
		this.elementValue = elementValue;
		this.elementName = elementName;
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
