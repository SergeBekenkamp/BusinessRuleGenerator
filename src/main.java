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
		Operator o = new Operator("Between", 2);
		TriggerEvent te = new TriggerEvent("Insert OR Update");
		Entity ent = new Entity("SPRUIJT");
		Attribute att = new Attribute("been", "varchar2");
		att.setEntity(ent);
		ConditionalValueType cvt = new ConditionalValueType("int");
		ConditionalValue cv1 = new ConditionalValue("value1", "10", cvt);
		ConditionalValue cv2 = new ConditionalValue("value2", "20", cvt);
		BusinessRuleType brt = new BusinessRuleType("ARNG", "BLALALALA", "DESCRIPTION", "DAT EXAMPLE");

		BusinessRule b = new BusinessRule("ARNG", o, te, ent, att, brt);
		b.addConditionalValue(cv1);
		b.addConditionalValue(cv2);
		SQLGenerator gen = new SQLGenerator();
		ArrayList<BusinessRule> brl = new ArrayList<BusinessRule>();
		brl.add(b);
		try {
			gen.generate(brl);
		} catch (GenerationException e) {
			System.out.println(e);
		}
	}

}
