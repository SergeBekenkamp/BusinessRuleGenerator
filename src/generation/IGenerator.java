package generation;
import java.util.ArrayList;

import domain.BusinessRule;

public interface IGenerator {
	
	public void generate(ArrayList<BusinessRule> businessRules) throws GenerationException;

}
