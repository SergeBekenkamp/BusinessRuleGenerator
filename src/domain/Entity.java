package domain;

public class Entity {
	private int id;
	private String tableName;
	
	public Entity(int id, String tableName){
		this.id = id;
		this.tableName = tableName;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	

}
