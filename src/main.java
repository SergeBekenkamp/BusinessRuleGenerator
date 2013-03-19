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
		
		SQLGenerator gen = new SQLGenerator();
		ArrayList<BusinessRule> brl = new ArrayList<BusinessRule>();
		brl.add(rangeRule);
		brl.add(compareRule);
		try {
			gen.generate(brl);
		} catch (GenerationException e) {
			System.out.println(e);
		}
	}

}
