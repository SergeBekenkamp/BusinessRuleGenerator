package generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import output.SQLOutput;
import domain.BusinessRule;
import domain.ConditionalValue;

public class SQLGenerator implements IGenerator {

	private TemplateLoader fileIterator;
	private Map<String, String> replacers = new HashMap<String, String>();
	private SQLOutput output = new SQLOutput();

	@Override
	public void generate(ArrayList<BusinessRule> businessRules) {
		for (BusinessRule b : businessRules) {
			replacers = setReplacers(b);
			fileIterator = new TemplateLoader("SQLTemplate\\" + b.getBusinessRuleType().getCode() + ".txt");
			String s = "";
			while (s != null) {
				s = fileIterator.nextLine();
				if (s != null) {
					for (Map.Entry<String, String> entry : replacers.entrySet()) {
						s = s.replaceAll(entry.getKey(), entry.getValue());
					}
					output.addString(s);
				}
			}
			output.saveOutput(b.getName());
			fileIterator.close();
		}
	}

	private Map<String, String> setReplacers(BusinessRule br) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("<<trigger_name>>", br.getBusinessRuleType().getCode() + "_" + br.getName() + "_TRIGGER");
		map.put("<<trigger_event>>", br.getEvent().toString());
		map.put("<<column_name>>", br.getAttribute().getColumnName());
		map.put("<<entity_name>>", br.getAttribute().getEntity().getTableName());

		String operator = "";
		switch (br.getOperator().getName()) {
		case "Between":
			operator = "BETWEEN";
			break;
		case "NotBetween":
			operator = "NOT BETWEEN";
			break;
		case "Equals":
			operator = "=";
			break;
		case "NotEquals":
			operator = "!=";
			break;
		case "LessThan":
			operator = "<";
			break;
		case "GreaterThan":
			operator = ">";
			break;
		case "LessOrEqualTo":
			operator = "<=";
			break;
		case "GreaterOrEqualTo":
			operator = ">=";
			break;
		case "In":
			operator = "IN";
			break;
		case "NotIn":
			operator = "NOT IN";
			break;
		}
		map.put("<<operator>>", operator);

		String multiValues = "";
		String comma = "";
		int valueNumber = 1;
		for (ConditionalValue cv : br.getConditionalValues()) {
			if (cv.getAttribute() != null) {
				map.put("<<column2_name>>", cv.getAttribute().getColumnName());
			} else {
				if (valueNumber != 1) {
					comma = ", ";
				} else {
					comma = "";
				}
				multiValues += comma + "'" + cv.getValue() + "'";
				map.put("<<value" + valueNumber + ">>", cv.getValue());
				valueNumber++;
			}
		}
		map.put("<<multiple_values>>", multiValues);

		return map;
	}
}
