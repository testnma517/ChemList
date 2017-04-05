// Nathan Ma
// Element.java
//
// The Element class represents one
// entry in the ChemList. It holds 
// the element's name, atomic number, 
// atomic mass, as well as symbol.
// 
// 1/6/16
//

public class Element{
	
	private String name;
	private int number;
	private String symbol;
	private double mass;
	
	public Element(int num, String s, String n, double m){
		name = n;
		number = num;
		symbol = s;
		mass = m;
	}

	public String toString(){
		String output = String.format("%-4d %-14s %-4s %6.2f", number, name, symbol, mass);
		return output;
	}
	
	public double compareToMass(Element other){
		return mass-other.getMass();
	}
	
	public int compareToName(Element other){
		return name.compareTo(other.getName());
	}
	
	public int compareToSymbol(Element other){
		return symbol.compareTo(other.getSymbol());
	}
	
	public int compareToNum(Element other){
		return number-other.getNumber();
	}
	
	public int getNumber(){ 
		return number; 
	}
	public double getMass(){
		return mass;
	}
	public String getName(){
		return name;
	}
	public String getSymbol(){
		return symbol;
	}
	
	
	
}