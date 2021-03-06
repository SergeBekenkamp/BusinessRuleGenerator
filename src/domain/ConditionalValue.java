package domain;

public class ConditionalValue {
	private String name;
	private String value;
	private BusinessRule businessRule;
	private Attribute attribute;
	private ConditionalValueType type;
	
	public ConditionalValue(String name, Attribute attribute, ConditionalValueType type){
		this.name = name;
		this.attribute = attribute;
		this.type = type;
	}
	
	public ConditionalValue(String name,Attribute attribute, String value){
		this.name = name;
		this.value = value;
		this.attribute = attribute;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public BusinessRule getBusinessRule() {
		return businessRule;
	}
	public void setBusinessRule(BusinessRule businessRule) {
		this.businessRule = businessRule;
	}
	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	public ConditionalValueType getType() {
		return type;
	}
	public void setType(ConditionalValueType type) {
		this.type = type;
	}
	
	
	

}
