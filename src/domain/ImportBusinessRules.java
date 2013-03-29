package domain;

import java.sql.ResultSet;
import java.util.List;

import databaseControl.DatabaseConnection;

public class ImportBusinessRules {
	private DatabaseConnection dbConn;
	
	public ImportBusinessRules() {
		dbConn = new DatabaseConnection();
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
