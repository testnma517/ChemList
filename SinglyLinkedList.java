import java.util.NoSuchElementException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SinglyLinkedList
{
	private ListNode first;
	private ListNode last;

	public SinglyLinkedList()
	{
		first = null;
		last = null;
	}

	public Object getFirst()
	{
		if (first == null)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return first.getValue();
		}
	}
	
	public Object getLast ()
	{
		if(last == null)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return last.getValue();
		}
	}

	public void addFirst(Object value) 
	{
		if(first == null){
			first = new ListNode(value, null);
			last = first;
		}
		else{
			first = new ListNode(value, first);
		}
	}
	
	public void addLast(Object value){
		if(last == null){
			first = new ListNode(value, null);
			last = first;
		}
		else{
			ListNode prevLast = last;
			last.setNext(new ListNode(value, null));
			last = prevLast.getNext();
		}
	}

	public int size(){
		int count = 0;
		ListNode temp = first;
		while(temp != null){
			count++;
			temp = temp.getNext();
		}
		return count;
	}

	public void printList()
	{
		ListNode temp = first; 
		while (temp != null) 
		{
			System.out.println(temp.getValue() + " ");
			temp = temp.getNext(); 
		}
	}
	
	public void printBackwards(){
		ListNode temp = first; 
		String rishi = "";
		while (temp != null){
			rishi = temp.getValue() + "\n" + rishi;
			temp = temp.getNext(); 
		}
		System.out.println("\n" + rishi + "\n");
	}
	
	public void loadData(){
		Scanner rishi = OpenFile.openToRead("states.txt");
		while(rishi.hasNextLine()){
			addLast(new State(rishi.next(), rishi.next(), rishi.nextInt(), rishi.nextInt(), rishi.nextInt(), rishi.next(), rishi.nextInt(), rishi.nextInt(), rishi.nextInt()));
			rishi.nextLine();
		} 
	}
	
	public void clear(){
		first = null;
		last = null;
	}
	
	public void bubbleSortName(){ 
		
		String name1, name2;
		
		for(int j=0; j<size()-1; j++){
			for(int i=j+1; i<size(); i++){
				
				State s1 = (State)(get(j).getValue());
				State s2 = (State)(get(i).getValue());
				
				name1 = s1.getName().toLowerCase();
				name2 = s2.getName().toLowerCase();
				
				if(name1.compareTo(name2) > 0){
					swap(get(j), get(i));
				}
			}
		}
	}
	
	public void selectionSortYear(){
		
		int max = 0;
		
		for(int i=size(); i>1; i--){
			max = 0;
			for(int j=1; j<n; j++){
				State temp = (State)(get(max).getValue());
				Object obj = get(j).getValue();
				if(temp.compareTo(obj) < 1)
					max = j;
				}
				
				swap(get(max), get(n-j));
			}
	}
	
	private void swap(){
		
	}
	
	public void testFind(){
		String input = Prompt.getString("Enter a State name to be searched (Q to quit): ").toLowerCase().trim();
		while(!input.equals("q")){
			ListNode current = first;
			State temp = (State)current.getValue();
			while(current.getNext()!=null && !temp.getName().toLowerCase().equals(input)){
				current = current.getNext();
				temp = (State)current.getValue();
			}
			if(temp.getName().toLowerCase().equals(input)){
				System.out.println("\n" + current.getValue());
				input = Prompt.getString("\nEnter a State name to be searched (Q to quit): ").toLowerCase().trim();
			}
			else{
				System.out.println("\n" + input + " could not be found.");
				input = Prompt.getString("\nEnter a State name to be searched (Q to quit): ").toLowerCase().trim();
			}
		}
	}
	
	public void testDelete(){
		String input = Prompt.getString("Enter a State name to be deleted (Q to quit): ").trim();
		
	}
} 