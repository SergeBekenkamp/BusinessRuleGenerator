package domain;

public class Event {
	boolean update,delete,insert;
	private int i = 0;

	
	public Event(boolean update, boolean delete, boolean insert) {
		this.update = update;
		this.delete = delete;
		this.insert = insert;
	}

	protected boolean isUpdate() {
		return update;
	}

	protected void setUpdate(boolean update) {
		this.update = update;
	}

	protected boolean isDelete() {
		return delete;
	}

	protected void setDelete(boolean delete) {
		this.delete = delete;
	}

	protected boolean isInsert() {
		return insert;
	}

	protected void setInsert(boolean insert) {
		this.insert = insert;
	}
	
	public String toString(){
		String s = "";
		if(update) s+="Update";
		if(delete && update) s+= " OR delete";
		else s+= "delete";
		if(!update && !delete && insert) s+= "insert";
		else s+= " OR insert";
		return s;
	}
	

}
