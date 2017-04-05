// Nathan Ma
// ChemList.java
//
// The ChemList program prints a 
// formatted table of all 118 
// identified elements of the 
// periodic table. The user then
// has the option to sort the list
// by name using bubble sort, 
// number using selection sort, 
// or mass using merge sort. The
// user can also sort the list
// with insertion sort, then 
// search for a specific element
// by inputting a symbol.
// 
// 1/6/16
// 

import java.util.Scanner;
import java.util.ArrayList;

public class ChemList{

	private ArrayList<Element> list;
	
	public static void main(String[] args){
		ChemList chemList = new ChemList();
		chemList.run();	
	}
	
	public ChemList(){
		list = new ArrayList<Element>();
		
	}
	
	public void run(){
		readElements();
		int choice = 0;
		String symChoice = "";
		
		while(choice != 5){
			choice = menu();
			if(choice == 1) {
				bubbleName();
				printList();
			}
			else if(choice == 2) {
				selectionNumber();
				printList();
			}
			else if(choice == 3) {
				mergeMass(0, list.size()-1);
				printList();
			}
			else if(choice == 4) {
				insertionSymbol();
				while(!symChoice.equals("-1")){
					symChoice = getSearchChoice();
					if(!symChoice.equals("-1"))
					{
						binarySearch(symChoice);
					}
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		return;
	}
	
	public int menu(){
		System.out.println("\n1: Display Elements sorted by name");
		System.out.println("2: Display Elements sorted by number");
		System.out.println("3: Display Elements sorted by atomic mass");
		System.out.println("4: Search for an Element");
		System.out.println("5: Exit\n");
		
		return Prompt.getInt("Please Enter 1 through 5, indicating your choice from the menu above: ", 1, 5);
	}
	
	public String getSearchChoice(){
		System.out.println("\n----------------------------------------------------------------\n");
		return Prompt.getString("Please enter an Atomic Symbol to search for (-1 to exit): ");
	}
	
	public void readElements(){
		Scanner infile = OpenFile.openToRead("Elements.txt");
		while(infile.hasNext()){
			int num = infile.nextInt();
			String sym = infile.next();
			String name = infile.next();
			double mass = infile.nextDouble();
			
			list.add(new Element(num, sym, name, mass));
			infile.nextLine();
		}
		
	}
	
	public void printList(){
		System.out.println("\n+------------------+");
		System.out.println("| List of Elements |");
		System.out.println("+------------------+-------------------------+");
		System.out.println("|      #    Element      Symbol  Atomic Mass |");

		for(int i = 0; i < list.size(); i++){
			if(i%5 == 0)
			{
				System.out.println("|                                            |");
			}
			System.out.println("|      " + list.get(i) + "       |");
		}	
		System.out.println("+--------------------------------------------+");
	}
	
	public void swap(int i, int j){
		Element temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	public void bubbleName(){
	
		for (int outer = 0; outer < list.size()-1; outer++)
		{
			for (int inner = 0; inner < list.size()-outer-1; inner++)
			{
				if (list.get(inner).compareToName(list.get(inner+1)) > 0)
				{
					swap(inner, inner+1);
				}
			}
		}
	}
	
	public void selectionNumber(){	
		int min;
		for (int outer = 0; outer < list.size() - 1; outer++)
		{
			min = outer;
			for (int inner = outer + 1; inner < list.size(); inner++)
			{
				if (list.get(inner).compareToNum(list.get(min)) < 0)
				{
					min = inner; // a new smallest item is found
				}
			}
			swap(outer, min);
		}
	}
	
	public void mergeMass(int from, int to){
		if (to - from < 2)
		{
			if (to > from && list.get(to).compareToMass(list.get(from)) < 0)
			{
				swap(to,from);
			}
		}
		else
		{
			int middle = (from + to) / 2;
			mergeMass(from, middle);
			mergeMass(middle + 1, to);
			merge(from, middle, to);
		}
	}
	
	public void merge(int from, int middle, int to){
		int i = from, j = middle + 1, k = from;
		
		ArrayList<Element> temp = new ArrayList<Element>();
		
		for(Element ind: list)
		{
				temp.add(ind);
		}
		
		while (i <= middle && j <= to)
		{
			if (list.get(i).compareToMass(list.get(j)) < 0)
			{
				temp.set(k, list.get(i));
				i++;
			}
			else
			{
				temp.set(k, list.get(j));
				j++;
			}
			k++;
		}
		while (i <= middle)
		{
			temp.set(k, list.get(i));
			i++;
			k++;
		}
		while (j <= to)
		{
			temp.set(k, list.get(j));
			j++;
			k++;
		}
		for (k = from; k <= to; k++)
		{
			list.set(k, list.get(k));
		}
	}
	
	public void insertionSymbol(){
		for (int outer = 1; outer < list.size(); outer++)
		{
			int position = outer;
			Element key = list.get(position);
			
			// Shift larger values to the right
			while (position > 0 && list.get(position - 1).compareToSymbol(key) > 0)
			{
				list.set(position, list.get(position - 1));
				position--;
			}
			list.set(position, key);
		}
	}
	
	public void binarySearch(String x)
    {
        int low = 0;
        int high = list.size() - 1;
        int mid;
        int steps = 0;

        while( low <= high )
        {
            mid = ( low + high ) / 2;
			steps++;
			
            if( list.get(mid).getSymbol().toUpperCase().compareTo( x.toUpperCase() ) < 0 )
                low = mid + 1;
            else if( list.get(mid).getSymbol().toUpperCase().compareTo( x.toUpperCase() ) > 0 )
                high = mid - 1;
            else
            {
               System.out.println("\nThe binary search took " + steps + " steps to find this Element.");
			   System.out.println("\nThe Element is: ");
			   System.out.println(list.get(mid));
			   return;
			}
        }

		System.out.println("\nThe binary search took " + steps + " steps to determine that this Element does not exist.\n");
    }
	

}