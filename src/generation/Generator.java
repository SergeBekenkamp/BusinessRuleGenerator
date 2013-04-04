package generation;

import java.io.File;
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
	private String outputType;
	private String outputLocation;
	private String templateDir;

	public Generator(Language language, List<BusinessRule> businessRules, String outputType, String templateDir, String outputLocation) {
		this.selectedLanguage = language;
		this.businessRules = businessRules;
		this.outputType = outputType;
		this.templateDir = templateDir;
		this.outputLocation = outputLocation;
	}

	public void generate() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		IOutput output = OutputFactory.createOutput(outputType);
		HashMap<String, String> codes = new HashMap<>();
		
		for (BusinessRule br : businessRules) {
			replacers = setReplacers(br);
			
			String line = "";			
			TemplateLoader fileIterator = new TemplateLoader(templateDir + File.separator + selectedLanguage.getName() 
						+ File.separator + br.getBusinessRuleType().getCode() + ".txt");
			
			StringBuilder sb = new StringBuilder();
			
			while (line != null) {
				line = fileIterator.nextLine();
				if (line != null) { 
					for (Map.Entry<String, String> entry : replacers.entrySet()) {
						line = line.replaceAll(entry.getKey(), entry.getValue());
					}
					sb.append(line + "\r\n");				
				}
			}
			codes.put(br.getName(), sb.toString());
			fileIterator.close();
		}
		output.setCode(codes);
		output.doOutput(outputLocation);
	}

	private Map<String, String> setReplacers(BusinessRule br) {
		System.out.println("Businessrule name: " + br.getName());
		System.out.println("Attribute: " + br.getEntity().getTableName());
		System.out.println("Number of conditional values: " + br.getConditionalValues().size());

		Map<String, String> map = new HashMap<String, String>();
		if(br.getFailure() != null){
		map.put("<<error>>", "l_error_stack := l_error_stack || 'Error severity: <<error_severity>> Error message: <<error_message>>';");
		map.put("<<error_message>>", br.getFailure().getMessage());
		map.put("<<error_severity>>", br.getFailure().getSeverity());
		} else {
			map.put("<<error>>", "");
		}
		map.put("<<trigger_name>>", br.getBusinessRuleType().getCode() + "_" + br.getName() + "_TRIGGER");
		map.put("<<trigger_event>>", br.getEvent().toString());
		map.put("<<column_name>>", br.getAttribute().getColumnName());
		map.put("<<entity_name>>", br.getAttribute().getEntity().getTableName());
		
		selectedLanguage.loadLanguage();
		map.put("<<operator>>", selectedLanguage.getElement(br.getOperator().getName()));

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
