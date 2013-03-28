package domain;

public class Event {
	boolean update,delete,insert;

	
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
	

}
