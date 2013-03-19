import java.util.ArrayList;

import domain.Attribute;
import domain.BusinessRule;
import domain.BusinessRuleType;
import domain.ConditionalValue;
import domain.ConditionalValueType;
import domain.Entity;
import domain.Operator;
import domain.TriggerEvent;
import output.SQLOutput;
import generation.*;

public class main {

	public static void main(String[] args0) {
		System.out.println(System.getProperty("user.dir"));
		
		// Attribute Range Rule
		Operator oRange = new Operator("Between", 2);
		TriggerEvent teRange = new TriggerEvent("insert OR update");
		Entity entRange = new Entity("SPRUIJT");
		Attribute attRange = new Attribute("been", "varchar2");
		attRange.setEntity(entRange);
		ConditionalValueType cvtRange = new ConditionalValueType("int");
		ConditionalValue cv1Range = new ConditionalValue("value1", "10", cvtRange);
		ConditionalValue cv2Range = new ConditionalValue("value2", "20", cvtRange);
		BusinessRuleType brtRange = new BusinessRuleType("ARNG", "BLALALALA", "DESCRIPTION", "DAT EXAMPLE");

		BusinessRule rangeRule = new BusinessRule("Attributerangerule", oRange, teRange, entRange, attRange, brtRange);
		rangeRule.addConditionalValue(cv1Range);
		rangeRule.addConditionalValue(cv2Range);
		
		// Attribute Compare Rule
		Operator oCompare = new Operator("Equals", 1);
		TriggerEvent teCompare = new TriggerEvent("insert OR update");
		Entity entCompare = new Entity("Stok");
		Attribute attCompare = new Attribute("lengte", "int");
		attCompare.setEntity(entCompare);
		ConditionalValueType cvtCompare = new ConditionalValueType("int");
		ConditionalValue cvCompare = new ConditionalValue("value1", "10", cvtCompare);
		BusinessRuleType brtCompare = new BusinessRuleType("ACMP", "compare", "description", "example");

		BusinessRule compareRule = new BusinessRule("Attributecomparerule", oCompare, teCompare, entCompare, attCompare, brtCompare);
		compareRule.addConditionalValue(cvCompare);
		
		// Attribute List Rule
		Operator oList = new Operator("NotIn", 1);
		TriggerEvent teList = new TriggerEvent("insert OR update");
		Entity entList = new Entity("Persoon");
		Attribute attList = new Attribute("naam", "String");
		attList.setEntity(entList);
		ConditionalValueType cvtList = new ConditionalValueType("String");
		ConditionalValue cv1List = new ConditionalValue("value1", "Henk", cvtList);
		ConditionalValue cv2List = new ConditionalValue("value1", "Frits", cvtList);
		ConditionalValue cv3List = new ConditionalValue("value1", "Sjaak", cvtList);
		BusinessRuleType brtList = new BusinessRuleType("ALIS", "list", "description", "example");

		BusinessRule listRule = new BusinessRule("Attributelistrule", oList, teList, entList, attList, brtList);
		listRule.addConditionalValue(cv1List);
		listRule.addConditionalValue(cv2List);
		listRule.addConditionalValue(cv3List);
		
		SQLGenerator gen = new SQLGenerator();
		ArrayList<BusinessRule> brl = new ArrayList<BusinessRule>();
		brl.add(rangeRule);
		brl.add(compareRule);
		brl.add(listRule);
		
		try {
			gen.generate(brl);
		} catch (GenerationException e) {
			System.out.println(e);
		}
	}

}
