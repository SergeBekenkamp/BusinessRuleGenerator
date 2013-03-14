package domain;

import java.util.ArrayList;

public class BusinessRule {
	private String name;
	private BusinessRuleType businessRuleType;
	private Operator operator;
	private Entity entity;
	private ArrayList<Attribute> attributes = new ArrayList<Attribute>();
	private ArrayList<ConditionalValue> conditionalValues = new ArrayList<ConditionalValue>();
	private TriggerEvent triggerEvent;

	public BusinessRule(String name, Operator operator, TriggerEvent trigger, Entity entity, Attribute[] att) {
		this.name = name;
		this.operator = operator;
		triggerEvent = trigger;
		this.entity = entity;
		for(Attribute attribute : att){
			attributes.add(attribute);
		}
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
	
	public boolean addAttribute(Attribute att){
		if(attributes.contains(att)){
			return false;
		}
		attributes.add(att);
		return true;
	}
	
	public boolean removeAttribute(Attribute att){
		return attributes.remove(att);
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
	
	public void setTriggerEvent(TriggerEvent event){
		triggerEvent = event;
	}
	
	public TriggerEvent getTriggetEvent(){
		return triggerEvent;
	}

}