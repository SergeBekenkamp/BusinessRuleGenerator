package domain;

import java.util.ArrayList;

public class Category {
	private String name;
	private ArrayList<BusinessRuleType> BusinessRuleTypes = new ArrayList<BusinessRuleType>();
	
	
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
