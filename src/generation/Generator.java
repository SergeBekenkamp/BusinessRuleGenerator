package generation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import output.IOutput;
import output.OutputFactory;

import domain.BusinessRule;
import domain.ConditionalValue;

public class Generator {
	private Language selectedLanguage;
	private List<BusinessRule> businessRules;
	private Map<String, String> replacers = new HashMap<String, String>();
	String outputType;
	String outputData;

	public Generator(Language language, List<BusinessRule> businessRules, String outputType, String outputData) {
		this.selectedLanguage = language;
		this.businessRules = businessRules;
		this.outputType = outputType;
		this.outputData = outputData;
	}

	public void generate() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		IOutput output = OutputFactory.createOutput(outputType);
		for (BusinessRule b : businessRules) {
			replacers = setReplacers(b);
			TemplateLoader fileIterator = new TemplateLoader("SQLTemplate\\" + b.getBusinessRuleType().getCode() + ".txt");
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
			output.saveOutput("f:\\test");
			fileIterator.close();
		}

	}

	private Map<String, String> setReplacers(BusinessRule br) {
		System.out.println("Businessrule name: " + br.getName());
		System.out.println("Attribute: " + br.getEntity().getTableName());
		System.out.println("Number of conditional values: " + br.getConditionalValues().size());

		Map<String, String> map = new HashMap<String, String>();
		map.put("<<trigger_name>>", br.getBusinessRuleType().getCode() + "_" + br.getName() + "_TRIGGER");
		map.put("<<trigger_event>>", br.getEvent().toString());
		map.put("<<column_name>>", br.getAttribute().getColumnName());
		map.put("<<entity_name>>", br.getAttribute().getEntity().getTableName());

		// String operator =
		// selectedLanguage.getElement(br.getOperator().getName());
		map.put("<<operator>>", "test");

		String multiValues = "";
		String comma = "";
		int valueNumber = 1;

		for (ConditionalValue cv : br.getConditionalValues()) {
			if (cv.getAttribute() != null) {
				map.put("<<column2_name>>", cv.getAttribute().getColumnName());
			}
			if (valueNumber != 1) {
				comma = ", ";
			} else {
				comma = "";
			}
			multiValues += comma + "'" + cv.getValue() + "'";
			map.put("<<value" + valueNumber + ">>", cv.getValue());
			valueNumber++;

		}

		map.put("<<multiple_values>>", multiValues);
		return map;
	}
}
