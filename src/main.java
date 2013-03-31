import java.io.IOException;
import java.util.List;

import output.OutputFactory;
import databaseControl.ImportBusinessRules;
import domain.BusinessRule;

public class main {

	public static void main(String[] args0) {
		OutputFactory f = new OutputFactory();
		try {
			System.out.println(f.getOutputTypes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImportBusinessRules select = new ImportBusinessRules();
		List<BusinessRule> b = select.getAllBusinessRules();
		for(BusinessRule br : b){
			System.out.println(select.getBusinessRule(br.getId()));
		}

		/*
		 * 
		 * System.out.println(System.getProperty("user.dir"));
		 * 
		 * // Attribute Range Rule Operator oRange = new Operator("Between", 2);
		 * Event teRange = new Event(true, false, true); Entity entRange = new
		 * Entity("SPRUIJT"); Attribute attRange = new Attribute("been",
		 * "varchar2"); attRange.setEntity(entRange); ConditionalValueType
		 * cvtRange = new ConditionalValueType("int"); ConditionalValue cv1Range
		 * = new ConditionalValue("value1", "10", cvtRange); ConditionalValue
		 * cv2Range = new ConditionalValue("value2", "20", cvtRange);
		 * BusinessRuleType brtRange = new BusinessRuleType("ARNG", "BLALALALA",
		 * "DESCRIPTION", "DAT EXAMPLE");
		 * 
		 * BusinessRule rangeRule = new BusinessRule("AttributeRangeRule",
		 * oRange, teRange, entRange, attRange, brtRange, new Failure("", ""));
		 * rangeRule.addConditionalValue(cv1Range);
		 * rangeRule.addConditionalValue(cv2Range);
		 * 
		 * // Attribute Compare Rule Operator oCompare = new Operator("Equals",
		 * 1); Event teCompare = new Event(true, false, true); Entity entCompare
		 * = new Entity("Stok"); Attribute attCompare = new Attribute("lengte",
		 * "int"); attCompare.setEntity(entCompare); ConditionalValueType
		 * cvtCompare = new ConditionalValueType("int"); ConditionalValue
		 * cvCompare = new ConditionalValue("value1", "10", cvtCompare);
		 * BusinessRuleType brtCompare = new BusinessRuleType("ACMP", "compare",
		 * "description", "example");
		 * 
		 * BusinessRule compareRule = new BusinessRule("Attributecomparerule",
		 * oCompare, teCompare, entCompare, attCompare, brtCompare, new
		 * Failure("", "")); compareRule.addConditionalValue(cvCompare);
		 * 
		 * // Attribute List Rule Operator oList = new Operator("NotIn", 1);
		 * Event teList = new Event(true, false, true); Entity entList = new
		 * Entity("Persoon"); Attribute attList = new Attribute("naam",
		 * "String"); attList.setEntity(entList); ConditionalValueType cvtList =
		 * new ConditionalValueType("String"); ConditionalValue cv1List = new
		 * ConditionalValue("value1", "Henk", cvtList); ConditionalValue cv2List
		 * = new ConditionalValue("value1", "Frits", cvtList); ConditionalValue
		 * cv3List = new ConditionalValue("value1", "Sjaak", cvtList);
		 * BusinessRuleType brtList = new BusinessRuleType("ALIS", "list",
		 * "description", "example");
		 * 
		 * BusinessRule listRule = new BusinessRule("AttributeListRule", oList,
		 * teList, entList, attList, brtList, new Failure("", ""));
		 * listRule.addConditionalValue(cv1List);
		 * listRule.addConditionalValue(cv2List);
		 * listRule.addConditionalValue(cv3List);
		 * 
		 * // Tuple Compare Rule Operator oTupleComp = new Operator("Equals",
		 * 1); Event teTupleComp = new Event(true, false, true); Entity
		 * entTupleComp = new Entity("Persoon"); Attribute attTupleComp = new
		 * Attribute("naam", "String"); attTupleComp.setEntity(entTupleComp);
		 * Attribute attTupleComp2 = new Attribute("achternaam", "String");
		 * attTupleComp2.setEntity(entTupleComp); ConditionalValueType
		 * cvtTupleComp = new ConditionalValueType("String"); ConditionalValue
		 * cvTupleComp = new ConditionalValue("Persoon.achternaam",
		 * attTupleComp2, cvtTupleComp); BusinessRuleType brtTupleComp = new
		 * BusinessRuleType("TCMP", "list", "description", "example");
		 * 
		 * BusinessRule tupleCompRule = new BusinessRule("TupleCompareRule",
		 * oTupleComp, teTupleComp, entTupleComp, attTupleComp, brtTupleComp,
		 * new Failure("", "")); tupleCompRule.addConditionalValue(cvTupleComp);
		 * 
		 * ArrayList<BusinessRule> brl = new ArrayList<BusinessRule>();
		 * brl.add(rangeRule); brl.add(compareRule); brl.add(listRule);
		 * brl.add(tupleCompRule); gen.generate(brl);
		 */
	}
}
