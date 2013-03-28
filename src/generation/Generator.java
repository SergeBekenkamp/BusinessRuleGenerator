package generation;

import java.util.List;

import domain.BusinessRule;

public class Generator {
	List<Language> selectedLanguages;
	List<BusinessRule> businessRules;

	public Generator(List<Language> selectedLanguages, List<BusinessRule> businessRules) {
		this.selectedLanguages = selectedLanguages;
		this.businessRules = businessRules;

	}

	public void generate() {

	}

}
