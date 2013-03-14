package domain;

import java.util.ArrayList;

public class BusinessRuleType {
	private String code;
	private String name;
	private String description;
	private String example;
	private Category category;
	private ArrayList<Operator> operators = new ArrayList<Operator>();

	public BusinessRuleType(String code, String name, String description,
			String example) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.example = example;
	}

	public boolean addOperator(Operator op) {
		if (operators.contains(op)) {
			return false;
		}
		operators.add(op);
		return true;
	}

	public boolean removeOperator(Operator op) {
		return operators.remove(op);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
