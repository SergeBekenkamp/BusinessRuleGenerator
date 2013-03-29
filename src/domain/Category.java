package domain;

import java.util.ArrayList;

public class Category {
	private int id;
	private String name;
	private ArrayList<BusinessRuleType> BusinessRuleTypes = new ArrayList<BusinessRuleType>();
	
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<BusinessRuleType> getBusinessRuleTypes() {
		return BusinessRuleTypes;
	}
	
	public boolean addBusinessRuleType(BusinessRuleType brt){
		if(BusinessRuleTypes.contains(brt)){
			return false;
		}
		BusinessRuleTypes.add(brt);
		return true;
	}
	
	public boolean removeBusinessRuleType(BusinessRuleType brt){
		return BusinessRuleTypes.remove(brt);
	}
	
	
	
	

}
