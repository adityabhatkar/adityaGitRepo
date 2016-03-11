package cci;

import java.util.Stack;

public class TowerOfHanoi {

	public static void main(String[] args) {
		
		Tower originTower=new Tower("originTower");
		Tower bufferTower=new Tower("bufferTower");
		Tower destinationTower=new Tower("destinationTower");
		
		int n=10;
		for(int i=n; i>0; i--){
			originTower.push(i);
		}
		
		moveDisks(n, originTower, bufferTower, destinationTower);
		
		while(!destinationTower.isEmpty()){
			System.out.println("destination Stack item "+destinationTower.pop());
		}
	}
	
	public static void moveDisks(int n, Tower origin, Tower buffer, Tower destination){
		
		if(n<=0){
			return;
		}
		
		moveDisks(n-1, origin, destination, buffer);
		moveDiskAtTop(origin, destination);
		moveDisks(n-1, buffer, origin, destination);
		
	}
	
	public static void moveDiskAtTop(Tower origin, Tower destination){
		
		int item=origin.pop();
		System.out.println("Moving item "+item+" from "+origin+" to "+destination);
		destination.push(item);
	}
}

class Tower{
	
	private Stack<Integer> discStack;
	private String name;
	
	public Tower(String name){
		discStack=new Stack<>();
		this.name=name;
	}
	
	public String getTowerName(){
		return this.name;
	}
	
	public Stack<Integer> getStack(){
		return this.discStack;
	}
	
	public int pop(){
		return this.discStack.pop();
	}
	
	public void push(int item){
		this.discStack.push(item);
	}
	
	public boolean isEmpty(){
		return this.discStack.isEmpty();
	}
	
	public String toString(){
		return this.name;
	}
	
}
