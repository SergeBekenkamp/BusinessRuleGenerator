package domain;

public class Operator {
	private String name;
	private int aantalVariabelen;
	
	public Operator(String name, int aantalVariabelen){
		this.name = name;
		this.aantalVariabelen = aantalVariabelen;
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
