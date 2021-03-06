package domain;

import java.util.ArrayList;

public class BusinessRule {
	private Integer id;
	private String name;
	private BusinessRuleType businessRuleType;
	private Operator operator;
	private Entity entity;
	private Attribute attribute;
	private ArrayList<ConditionalValue> conditionalValues = new ArrayList<ConditionalValue>();
	private Event event;
	private Failure failure;
	
	public BusinessRule(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public BusinessRule(String name, Operator operator, Event trigger, Entity entity, Attribute att, BusinessRuleType brt, Failure failure) {
		this.name = name;
		this.operator = operator;
		this.event = trigger;
		this.entity = entity;
		this.attribute = att;
		this.businessRuleType = brt;
		this.failure = failure;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean addConditionalValue(ConditionalValue value) {
		if (conditionalValues.contains(value)) {
			return false;
		}
		conditionalValues.add(value);
		return true;
	}

	public boolean removeConditionalValue(ConditionalValue value) {
		return conditionalValues.remove(value);
	}
	
	public void setAttribute(Attribute att){
		this.attribute = att;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public ArrayList<ConditionalValue> getConditionalValues() {
		return conditionalValues;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public BusinessRuleType getBusinessRuleType() {
		return businessRuleType;
	}

	public void setBusinessRuleType(BusinessRuleType businessRuleType) {
		this.businessRuleType = businessRuleType;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public void setEvent(Event event){
		this.event = event;
	}
	
	public Event getEvent(){
		return event;
	}

	public Failure getFailure() {
		return failure;
	}

	public void setFailure(Failure failure) {
		this.failure = failure;
	}
	
	public String toString(){
		String s = "";
		s += "BusinessRule: " + name + "\n";
		s += "BusinessRuleType: " + businessRuleType.getName() + "\n";
		s += "Operator: " + operator.getName() + "\n";
		s += "Entity: " + entity.getTableName();
		s += "Attribute: " + attribute.getColumnName() + " Datatype: " + attribute.getDataType() + "\n";
		for(ConditionalValue v : conditionalValues){
			s+= "ConditionalValue: " + v.getName() + " Value: " +  v.getValue() + "\n";
		}
		s += "Event: " + event.toString() + "\n";
		s += "Failure: " + failure.getMessage() + " Severity: " + failure.getSeverity();
		
		return s;
	}
	
	

}