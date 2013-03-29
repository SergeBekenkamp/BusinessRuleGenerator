package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import databaseControl.DatabaseConnection;

public class ImportBusinessRules {
	private DatabaseConnection dbConn;
	
	public ImportBusinessRules() {
		dbConn = new DatabaseConnection();
	}

	public List<BusinessRule> getAllBusinessRules() {
		dbConn.connect();
		List<BusinessRule> list = new ArrayList<BusinessRule>();
		ResultSet rs = dbConn.doQuery("SELECT businessrule_id, name FROM businessrule");
		try {
			while (rs.next()) {
	            BusinessRule br = new BusinessRule();
	            br.setId(rs.getInt("businessrule_id"));
	            br.setName(rs.getString("name"));
	            
	            list.add(br);
	        }
	    } catch (SQLException e) { System.err.println(e); }
		
		dbConn.closeConnection();
		
		return list;
	}

	public List<BusinessRuleType> getBusinessRuleTypes() {
		dbConn.connect();
		List<BusinessRuleType> list = new ArrayList<BusinessRuleType>();
		ResultSet rs = dbConn.doQuery("SELECT * FROM businessruletype");
		try {
			while (rs.next()) {
				BusinessRuleType brt = new BusinessRuleType(rs.getString("code"), rs.getString("name"));
	            list.add(brt);
	        }
	    } catch (SQLException e) { System.err.println(e); }
		
		dbConn.closeConnection();
		
		return list;
	}

	public List<Category> getCategories() {
		dbConn.connect();
		List<Category> list = new ArrayList<Category>();
		ResultSet rs = dbConn.doQuery("SELECT * FROM category");
		try {
			while (rs.next()) {
	            Category cat = new Category(rs.getInt("category_id"), rs.getString("name"));
	            list.add(cat);
	        }
	    } catch (SQLException e) { System.err.println(e); }
		
		dbConn.closeConnection();
		
		return list;
	}

	public List<Operator> getOperators() {
		return null;
	}

}
