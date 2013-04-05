package domain;

public class Operator {
	private int id;
	private String name;
	private int aantalVariabelen;
	
	public Operator(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Operator(String name, int aantalVariabelen){
		this.name = name;
		this.aantalVariabelen = aantalVariabelen;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAantalVariabelen() {
		return aantalVariabelen;
	}
	public void setAantalVariabelen(int aantalVariabelen) {
		this.aantalVariabelen = aantalVariabelen;
	}
	
	
	

}
