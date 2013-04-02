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
		dbConn = new DatabaseConnection("oracle-sourcedb");
	}

	public BusinessRule getBusinessRule(int businessRuleId) {
		System.out.println("BusinessRuleID: " + businessRuleId);
		dbConn.connect();

		ResultSet rs = dbConn.doQuery("SELECT * FROM BusinessRule WHERE businessrule_id = " + businessRuleId);
		BusinessRule br = null;
		try {
			while (rs.next()) {
				Attribute attr = getAttribute(rs.getInt("attribute_id"));
				attr.setEntity(getEntity(rs.getInt("attribute_id")));
				BusinessRuleType brType = getBusinessRuleType(rs.getString("BUSINESSRULETYPE_CODE"));
				int failureid = rs.getInt("FAILURE_ID");
				Failure failure = null;
				if (failureid != 0) {
					failure = getFailure(failureid);
				}

				br = new BusinessRule(rs.getString("name"), getOperator(rs.getInt("OPERATOR_ID")), getEvent(businessRuleId), getEntity(rs.getInt("attribute_id")), attr,
						brType, failure);
				for (ConditionalValue cv : getConditionalValues(businessRuleId)) {
					System.out.println("Conditional value: " + cv.getName());
					br.addConditionalValue(cv);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbConn.closeConnection();
		return br;
	}

	public List<ConditionalValue> getConditionalValues(int businessruleId) {
		ResultSet rs = dbConn.doQuery("SELECT * FROM conditionalvalue WHERE businessrule_id = " + businessruleId);
		List<ConditionalValue> businessrules = new ArrayList<ConditionalValue>();
		try {
			while (rs.next()) {
				Attribute attr = getAttribute(rs.getInt("attribute_id"));
				attr.setEntity(getEntity(rs.getInt("attribute_id")));

				businessrules.add(new ConditionalValue(rs.getString("name"), attr, rs.getString("value")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return businessrules;
	}

	public Failure getFailure(int failureID) {
		Failure f = null;
		ResultSet rs = dbConn.doQuery("SELECT * FROM failure WHERE failure_id = " + failureID);
		try {
			while (rs.next()) {
				f = new Failure(rs.getString("severity"), rs.getString("message"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	public BusinessRuleType getBusinessRuleType(String code) {
		BusinessRuleType type = null;

		ResultSet rs = dbConn.doQuery("SELECT * FROM BusinessRuleType WHERE code = '" + code + "'");
		try {
			while (rs.next()) {
				type = new BusinessRuleType(rs.getString("code"), rs.getString("name"), rs.getString("description"), rs.getString("example"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return type;
	}

	public Attribute getAttribute(int attributeId) {
		Attribute attr = null;
		ResultSet rs = dbConn.doQuery("SELECT * FROM attribute WHERE attribute_id = " + attributeId);
		try {
			while (rs.next()) {
				attr = new Attribute(rs.getString("name"), rs.getString("datatype"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attr;
	}

	public Event getEvent(int businessRuleId) {
		ResultSet rs = dbConn.doQuery("select * from triggerevent where businessrule_id = " + businessRuleId);
		Event event = null;
		try {
			while (rs.next()) {
				event = new Event(toBoolean(rs.getString("update")), toBoolean(rs.getString("delete")), toBoolean(rs.getString("insert")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	public boolean toBoolean(String booleanfy) {
		if (booleanfy.equals("Y")) {
			return true;
		} else {
			return false;
		}
	}

	public Operator getOperator(int operatorID) {
		ResultSet rs = dbConn.doQuery("SELECT * FROM operator WHERE OPERATOR_ID = " + operatorID);
		Operator opr = null;
		try {
			while (rs.next()) {
				opr = new Operator(rs.getString("name"), rs.getInt("amountofvalues"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return opr;
	}

	public Entity getEntity(int attributeid) {
		ResultSet rs = dbConn.doQuery("select * from entity where entity_id = (select entity_id from attribute where attribute_id =" + attributeid + ")");
		Entity entity = null;
		try {
			while (rs.next()) {
				entity = new Entity(rs.getInt("ENTITY_ID"), rs.getString("NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	public List<BusinessRule> getSelectedBusinessRules(int categoryId, String ruleTypeId, int entity) {
		dbConn.connect();
		List<BusinessRule> list = new ArrayList<BusinessRule>();
		ResultSet rs = dbConn.doQuery("SELECT businessrule_id, name FROM businessrule");
		try {
			while (rs.next()) {
				BusinessRule br = new BusinessRule(rs.getInt("businessrule_id"), rs.getString("name"));
				list.add(br);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}

		dbConn.closeConnection();

		return list;
		
	}
	
	public List<BusinessRule> getAllBusinessRules(int categoryId, String ruleTypeCode, int entityId) {
		String query = "SELECT businessrule_id, name FROM businessrule";
		String category = "";
		if (categoryId != 0) {
			query = "SELECT bru.businessrule_id, bru.name FROM businessrule bru, businessruletype brt";
			category = " WHERE bru.businessruletype_code = brt.code AND category_id = " + categoryId;
		}
		String entity = "";
		if (entityId != 0) {
			if (!category.equals("")) {
				query = "SELECT bru.businessrule_id, bru.name FROM businessrule bru, businessruletype brt, attribute att";
				entity = " WHERE bru.businessruletype_code = brt.code AND category_id = " + categoryId + " AND bru.attribute_id = att.attribute_id AND att.entity_id = " + entityId;
			} else {
				query = "SELECT bru.businessrule_id, bru.name FROM businessrule bru, attribute att";
				entity = " WHERE bru.attribute_id = att.attribute_id AND att.entity_id = " + entityId;
			}
		}
		String ruleType = "";
		if (!ruleTypeCode.equals("")){
			if (!category.equals("") || !entity.equals("")) {
				ruleType = " AND bru.businessruletype_code = '" + ruleTypeCode + "'";
			} else {
				ruleType = " WHERE businessruletype_code = '" + ruleTypeCode + "'";
			}
		}
		query = query + category + entity + ruleType;
		
		
		dbConn.connect();
		List<BusinessRule> list = new ArrayList<BusinessRule>();
		
		ResultSet rs = dbConn.doQuery(query);
		try {
			while (rs.next()) {
				BusinessRule br = new BusinessRule(rs.getInt("businessrule_id"), rs.getString("name"));
				list.add(br);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}

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
		} catch (SQLException e) {
			System.err.println(e);
		}

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
		} catch (SQLException e) {
			System.err.println(e);
		}

		dbConn.closeConnection();

		return list;
	}
	
	public List<Entity> getEntities() {
		dbConn.connect();
		List<Entity> list = new ArrayList<Entity>();
		ResultSet rs = dbConn.doQuery("SELECT * FROM entity");
		try {
			while (rs.next()) {
				Entity ent = new Entity(rs.getInt("ENTITY_ID"), rs.getString("NAME"));
				list.add(ent);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}

		dbConn.closeConnection();

		return list;
	}

	public List<Operator> getOperators() {
		return null;
	}

}
