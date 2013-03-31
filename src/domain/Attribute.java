package domain;

public class Attribute {
	
	private String columnName;
	private String dataType;
	private Entity entity;
	private ConditionalValue conditionalValue;

	public Attribute(String columnName, String dataType) {
		this.dataType = dataType;
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public ConditionalValue getConditionalValue() {
		return conditionalValue;
	}

	public void setConditionalValue(ConditionalValue conditionalValue) {
		this.conditionalValue = conditionalValue;
	}
	

}