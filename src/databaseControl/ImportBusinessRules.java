package databaseControl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.*;

//TODO: testen!
public class ImportBusinessRules {
	private DatabaseConnection dbConn;
	
	public ImportBusinessRules() {
		dbConn = new DatabaseConnection();
	}
	
	public BusinessRule getBusinessRule(Integer businessRuleId){
		ResultSet rs = dbConn.doQuery("SELECT * FROM BusinessRule WHERE businessrule_id = " + businessRuleId 
				+ " INNER JOIN BusinessRuleType ON BusinessRule.businessruletype_code = BusinessRuleType.code"
				+ " INNER JOIN Failure ON BusinessRule.failure_id = Failure.failure_id"
				+ " INNER JOIN Attribute ON BusinessRule.attribute_id = Attribute.attribute_id");
		BusinessRule br = null;
		try {
			while(rs.next()){	
				Attribute attr = new Attribute(rs.getString("Attribute.name"), rs.getString("Attribute.datatyoe"));
				BusinessRuleType brType = new BusinessRuleType(rs.getString("BusinessRuleType.code"), rs.getString("BusinessRuleType.name"), 
						rs.getString("BusinessRuleType.description"), rs.getString("BusinessRuleType.example"));
				Failure failure = new Failure(rs.getString("Failure.severity"), rs.getString("Failure.message"));
				br = new BusinessRule(rs.getString("BusinessRule.name"), getOperator(businessRuleId), getEvent(businessRuleId), 
						getEntity(businessRuleId), attr, brType, failure);
				br.setName(rs.getString("name"));
				//TODO: fill more data			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return br;
	}
	
	private Event getEvent(Integer businessRuleId) {
		ResultSet rs = dbConn.doQuery("SELECT * FROM Event WHERE businessrule_id = " + businessRuleId);
		Event event = null;
		try {
			while(rs.next()){				
				event = new Event(rs.getBoolean("update"), rs.getBoolean("delete"), rs.getBoolean("insert"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	public Operator getOperator(Integer businessRuleId){
		ResultSet rs = dbConn.doQuery("SELECT * FROM Attribute WHERE businessrule_id = " + businessRuleId);
		Operator opr = null;
		try {
			while(rs.next()){				
				opr = new Operator(rs.getString("name"), rs.getInt("amountofvalues"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return opr;
	}
	
	public Entity getEntity(Integer businessRuleId){
		ResultSet rs = dbConn.doQuery("SELECT * FROM Attribute WHERE businessrule_id = " + businessRuleId);
		Entity entity = null;
		try {
			while(rs.next()){				
				entity = new Entity (rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	public List<ConditionalValue> getConditionalValues(Integer businessRuleId, Attribute attribute){
		ResultSet rs = dbConn.doQuery("SELECT * FROM BusinessRule WHERE businessrule_id = " + businessRuleId);
		List<ConditionalValue> cvList = new ArrayList<ConditionalValue>();
		try {
			while(rs.next()){			
				ConditionalValue cv = new ConditionalValue(rs.getString("name"), attribute, null);
				cvList.add(cv);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cvList;
	}

	public List<BusinessRule> getAllBusinessRules() {
		return null;
	}

	public List<BusinessRuleType> getBusinessRuleTypes() {
		return null;
	}

	public List<Category> getCategories() {
		return null;
	}

	public List<Operator> getOperators() {
		return null;
	}

}
