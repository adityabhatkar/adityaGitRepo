package search;

public class Element implements Comparable <Element>{

	private int key;
	
	public Element() {
		// TODO Auto-generated constructor stub
	}
	
	public Element(int key){
		this.key=key;
	}
	
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		return key;
	}

	public boolean equals(Object o){
		
		if(o==null || this.getClass() != o.getClass()){
			return false;
		}
		return this.key==((Element)o).key;		
	}

	@Override
	public int compareTo(Element element) {
		return this.key-element.key;
	}

}
